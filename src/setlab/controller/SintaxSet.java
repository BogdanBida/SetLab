package setlab.controller;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import setlab.calculations.ReversePolish_Set;
import setlab.cores.SetCore.SetObj;

public class SintaxSet {

    private static final String CREATE = "([A-Za-z][A-Za-z0-9]{0,7})[\\s]{0,}[=][\\s]{0,}[{]([\\w]{1,}[[,][\\w]{1,}]{0,})[}]";
    private static final String CREATE_FROM_EXP = "([A-Za-z][A-Za-z0-9]{0,7})[\\s]{0,}[=][\\s]{0,}(.{0,})";

    public static String get(String command) {
        StringBuilder res = new StringBuilder(">> ");
        // print
        if (MainWindowController.MapOfSets.containsKey(command)) {
            return res + MainWindowController.MapOfSets.get(command).toString() + "\n";
        }
        //if create
        Matcher matcher1 = Pattern.compile(CREATE).matcher(command);
        Matcher matcher2 = Pattern.compile(CREATE_FROM_EXP).matcher(command);
        if (matcher1.matches()) {
            res.append(getNewSet(matcher1.group(1), matcher1.group(2))).append("\n");
        } else if (matcher2.matches()) {
            res.append(command).append("\n>> ");
            String[] name_line = command.split("=");
            System.out.println(name_line[0] + "=" + name_line[1]);
            name_line[0].replaceAll(" ", "");
            name_line[1].replaceAll(" ", "");
            name_line[0] = "C";
            name_line[1] = "A∪B";
            System.out.println(name_line[0] + "=" + name_line[1]);
            res.append(getNewSet(name_line[0],ReversePolish_Set.get(name_line[1]))).append("\n");
        } else {
            // if expression
            SetObj newSet = ReversePolish_Set.get(command);
            if (!newSet.isError()) {
                MainWindowController.addNewSet(newSet);
            }
            res.append(command).append("\n>> ");
            res.append(newSet).append("\n");
        }
        res.append("\n");
        return res.toString();
    }

    public static String getNewSet(String name, String inner) {
        SetObj newSet = new SetObj(name, inner);
        MainWindowController.addNewSet(newSet);
        return newSet.toString();
    }

    public static String getNewSet(String name, SetObj set) {
        SetObj newSet = new SetObj(name, set);
        MainWindowController.addNewSet(newSet);
        return newSet.toString();
    }
}
