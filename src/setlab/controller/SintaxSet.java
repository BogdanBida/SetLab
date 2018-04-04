package setlab.controller;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import setlab.cores.SetCore.SetObj;

public class SintaxSet {

    private static final String CREATE = "([A-Za-z][A-Za-z0-9]{0,7})[\\s]{0,}[=][\\s]{0,}[{]([\\w]{1,}[[,][\\w]{1,}]{0,})[}]";
    private static final String EXPRESSION = "";

    public static String get(String command) {
        StringBuilder res = new StringBuilder(">");

        //if create
        Matcher matcher = Pattern.compile(CREATE).matcher(command);
        if (matcher.matches()) {
            res.append(getNewSet(matcher.group(1), matcher.group(2)));
        }

        res.append("\n");
        return res.toString();
    }

    public static String getNewSet(String name, String inner) {
        SetObj NewSet = new SetObj(name, inner);
        MainWindowController.MapOfSets.put(name, NewSet);
        return NewSet.toString();
    }

}
