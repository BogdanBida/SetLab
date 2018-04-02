package setlab.controller;
import setlab.controller.MainWindowController;
import setlab.cores.SetCore.SetObj;

public class SintaxSet {
    public static String get(String command) {
        StringBuilder res = new StringBuilder(">");
        
        //if create
        res.append(getNewSet(command));
        
        res.append("\n");
        return res.toString();
    }
    
    private final String CREATE = "^[\\s]{0,}([A-Za-z][A-Za-z0-9]{0,7})[\\s]{0,}[=]"
            + "[(](.{1,})[)]";
    private final String EXPRESSION = "";
    
    public static String getNewSet(String command) {
        String[] line = command.replaceAll(" ", "").split("=");
        SetObj NewSet = new SetObj(line[0], line[1]);
        MainWindowController.MapOfSets.put(line[0], NewSet);
        return NewSet.toString();
    }
    
}
