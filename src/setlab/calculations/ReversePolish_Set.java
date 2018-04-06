package setlab.calculations;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import setlab.controller.MainWindowController;
import setlab.cores.SetCore;
import setlab.cores.SetCore.SetObj;

public class ReversePolish_Set {

    public static SetObj get(String command) {
        return calc(getTokens(command));
    }

    private static ArrayList<String> getTokens(String line) {
        ArrayList<String> res = new ArrayList<>();
        String pattern = "([\\w]{0,}([\\d]{0,7})[\\s]{0,})|([\\u222a]{1})";
        Pattern p = Pattern.compile(pattern);
        Matcher m = p.matcher(line);

        try {
            while (m.find()) {
                res.add(m.group(0));
            }
        } catch (Exception ex) {
            System.err.println("Ошибка getTokens() не найдено совпадение");
        }
        System.out.println(res.toString());
        return res;
    }

    private static SetObj calc(ArrayList<String> input) {
        HashMap<String, SetObj> mapOfSets = MainWindowController.MapOfSets;
        HashMap<String, Integer> operations;
        operations = new HashMap<>();
        operations.put("∪", 1);
        operations.put("/", 1);
        operations.put("∆", 1);
        operations.put("∩", 2);
        System.out.println(input.toString());

        Stack<SetObj> A = new Stack<>();
        Stack<String> B = new Stack<>();
        SetObj Res = new SetObj("Res");

        for (String s : input) {
            if (mapOfSets.containsKey(s)) { // -------- is set (operand)
                System.out.println("add - " + s);
                A.add(mapOfSets.get(s));
            } else if (operations.containsKey(s)) { // ------- is operation
                if (B.isEmpty()) {
                    System.out.println("add op - " + s);
                    B.push(s);
                } else {
                    if (operations.get(s) >= operations.get(B.peek())) {
                        SetObj b = A.pop();
                        SetObj a = A.pop();
                        A.push(Action(a, b, B.pop()));
                    }
                    B.push(s);
                }
            } else {
                Res.setError("was not finded '" + s + "'");
                return Res;
            }
        }

        while (A.size() > 1) {
            if (B.size() < 1) {
                System.err.println("Error: operation length < 1");
            }
            SetObj b = A.pop();
            SetObj a = A.pop();
            A.push(Action(a, b, B.pop()));
        }
        Res = A.pop();
        return Res;
    }

    private static SetObj Action(SetObj a, SetObj b, String op) {
        switch (op) {
            case "∪":
                return SetCore.Union(a, b);
            case "/":
                return SetCore.Diff(a, b);
            case "∆":
                return SetCore.SymmetricDiff(a, b);
            case "∩":
                return SetCore.Intersect(a, b);
            default:
                System.err.print("unknown error in Action()");
                return new SetObj("Error");
        }
    }
}
