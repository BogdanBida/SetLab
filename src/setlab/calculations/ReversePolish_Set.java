package setlab.calculations;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.EmptyStackException;
import java.util.HashMap;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import setlab.controller.MainWindowController;
import setlab.cores.SetCore;
import setlab.cores.SetCore.SetObj;

public class ReversePolish_Set {

    public static SetObj get(String command) {
        ArrayList<String> input = getTokens(command);
        HashMap<String, SetObj> mapOfSets = MainWindowController.MapOfSets;
        HashMap<String, Integer> operations;
        operations = new HashMap<>();
        operations.put("∪", 1);
        operations.put("\\", 1);
        operations.put("∆", 1);
        operations.put("∩", 2);
        operations.put("(", 0);
        operations.put(")", 0);

        Stack<SetObj> A = new Stack<>();
        Stack<String> B = new Stack<>();
        SetObj Res = new SetObj("Res");
        try {
            for (String s : input) {
                if (mapOfSets.containsKey(s)) { // -------- is set (operand)
                    A.add(mapOfSets.get(s));
                } else if (operations.containsKey(s)) { // ------- is operation
                    if (B.isEmpty() || s.equals("(")) {
                        B.push(s);
                    } else if (s.equals(")")) {
                        while (!B.peek().equals("(")) {
                            SetObj b = A.pop();
                            SetObj a = A.pop();
                            A.push(Action(a, b, B.pop()));
                        }
                        if (B.peek().equals("(")) {
                            B.pop();
                        }
                    } else {
                        if (operations.get(s) <= operations.get(B.peek())) {
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
                    System.err.println("Error: length of operation < 1");
                }
                SetObj b = A.pop();
                SetObj a = A.pop();
                A.push(Action(a, b, B.pop()));
            }
            Res = A.pop();
            return Res;
        } catch (EmptyStackException e) {
            Res.setError("Stack of operands is empty");
            return Res;
        }
    }

    private static ArrayList<String> getTokens(String line) {
        ArrayList<String> res;
       // String pattern = "([\\w]{0,}([\\d]{0,7})[\\s]{0,})|";
       // Pattern p = Pattern.compile(pattern);
       // Matcher m = p.matcher(line);

//        try {
//            while (m.find()) {
//                res.add(m.group(0));
//            }
//        } catch (Exception ex) {
//            System.err.println("Ошибка getTokens() не найдено совпадение");
//        }
        res = new ArrayList<>(Arrays.asList(line.split("")));
        return res;
    }

    private static SetObj Action(SetObj a, SetObj b, String op) {
        switch (op) {
            case "∪":
                return SetCore.Union(a, b);
            case "\\":
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
