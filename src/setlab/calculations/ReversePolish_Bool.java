package setlab.calculations;

import java.util.HashSet;
import java.util.Stack;

public class ReversePolish_Bool {

    private static final HashSet<String> op = new HashSet<>();

    static {
        op.add("∨");
        op.add("∧");
        op.add("→");
        op.add("~");
        op.add("⊕");
        op.add("↑");
        op.add("↓");
        op.add("¬");
    }

    public static String getStr(String line) {
        StringBuilder res = new StringBuilder("");
        Stack<String> A = new Stack<>();
        Stack<String> B = new Stack<>();

        for (int i = 0; i < line.length(); i++) {
            String token = String.valueOf(line.charAt(i));
            if (op.contains(token)) {
                B.add(token);
            } else {
                A.add(token);
            }
        }

        return res.toString();
    }
}
