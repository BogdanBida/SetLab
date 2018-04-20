package setlab.controller;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import setlab.cores.SetCore.SetObj;
import setlab.calculations.ReversePolish_Set;

public class SintaxSet {

    private static final String CREATE = "([A-Za-z][A-Za-z0-9]{0,7})[\\s]{0,}[=][\\s]{0,}[{]([\\w]{1,}[[,][\\w]{1,}]{0,})[}]";

    public static String get(String command) {
        StringBuilder res = new StringBuilder(">> ");
        // print
        if (MainWindowController.MapOfSets.containsKey(command)) {
            return res + MainWindowController.MapOfSets.get(command).toString() + "\n";
        }
        //if create
        Matcher matcher = Pattern.compile(CREATE).matcher(command);
        if (matcher.matches()) {
            res.append(getNewSet(matcher.group(1), matcher.group(2))).append("\n");
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

}
