package setlab.calculations;

import java.util.ArrayList;
import java.util.EmptyStackException;
import java.util.HashMap;
import java.util.Stack;
import setlab.cores.LogicCore;
import setlab.cores.SetCore;

public class ReversePolish_Bool {

    private static final HashMap<String, Integer> op = new HashMap<>();

    static {
        op.put("∨", 2);
        op.put("∧", 2);
        op.put("→", 1);
        op.put("~", 1);
        op.put("⊕", 1);
        op.put("↑", 1);
        op.put("↓", 1);
        op.put("¬", 3);
    }

    public static int get(String command) {
        ArrayList<String> input = new ArrayList<String>();

        Stack<Integer> A = new Stack<>();
        Stack<String> B = new Stack<>();
        int Res = 0;
        try {
            
            
        } catch (Exception e) {
            return Res;
        }
        return Res;
    }

    private static ArrayList<String> getTokens(String command) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private static Integer Action(Integer a, Integer b, String pop) {
        switch (pop) {
            case "∨":
                return LogicCore.or(a, b);
            default:
                System.err.print("unknown error in Action()");
                return 0;
        }
    }

    public static String getStr(String line) {
        StringBuilder res = new StringBuilder("");
        Stack<String> A = new Stack<>();
        Stack<String> B = new Stack<>();

        for (int i = 0; i < line.length(); i++) {
            String token = String.valueOf(line.charAt(i));
            if (op.containsKey(token)) {
                B.add(token);
            } else {
                A.add(token);
            }
        }

        return res.toString();
    }

}
