package setlab.calculations;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import setlab.controller.MainWindowController;
import setlab.cores.SetCore.SetObj;

public class ReversePolish_Set {

    public static String get(String command) {
        ArrayList<String> input = getTokens(command);
        return calc(input);
    }

    private static ArrayList<String> getTokens(String line) {
        ArrayList<String> res = new ArrayList<>();
        String pattern = "([\\s]{0,}(\\d{0,7})[\\s]{0,})|\\W";
        Pattern p = Pattern.compile(pattern);

        Matcher m = p.matcher(line);

        try {
            while (m.find()) {
                res.add(m.group(0));
            }
        } catch (Exception ex) {
            System.err.println("Ошибка getTokens() не найдено совпадение");
        }
        return res;
    }

    private static String calc(ArrayList<String> input) {
        HashMap<String, SetObj> mapOfSets = MainWindowController.MapOfSets;
        HashMap<String, Integer> operations;
        operations = new HashMap<>();
        operations.put("∪", 1);
        operations.put("/", 1);
        operations.put("∆", 1);
        operations.put("∩", 2);

        Stack<SetObj> A = new Stack<>();
        Stack<String> B = new Stack<>();
        SetObj Res = new SetObj("Res");
        
        for (String s : input) {
            if (mapOfSets.containsKey(s)) { // -------- is set
                A.add(mapOfSets.get(s));
            } else if (operations.containsKey(s)) { // ------- is operation
                if (B.isEmpty()) {
                    B.add(s);
                } else {
                    
                }
            } else {
                return "Sintax error: was not finded '" + s + "'";
            }

        }

        return Res.toString();
    }
}
