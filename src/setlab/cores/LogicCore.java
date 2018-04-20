package setlab.cores;

public class LogicCore {

    public static int inv(int a) {
        return (a*1 + 1)%2;
    }
    public static int or(int a, int b) {
        boolean x = bin(a);
        boolean y = bin(b);
        return x || y ? 1 : 0;
    }

    public static int and(int a, int b) {
        boolean x = bin(a);
        boolean y = bin(b);
        return x && y ? 1 : 0;
    }
    
    
    public static int imp(int a, int b) {
        if (a == 1 && b == 0)
            return 0;
        return 1;
    }

    public static int eq(int a, int b) {
        boolean x = bin(a);
        boolean y = bin(b);
        return x == y ? 1:0;
    }
    
    public static int oppJ(int a, int b) {
        return inv(eq(a,b));
    }  
    
    public static int sh(int a, int b) {
        return inv(and(a,b));
    }
    
    public static int arrP(int a, int b) {
        return inv(or(a,b));
    }
    
    public static void main(String[] args) {
        StringBuilder res = new StringBuilder("");
        res.append("x|y|¬x |x∨y|x∧y|x→x|x=x|x⊕x|x↑x|x↓x|\n");
        for (int x = 0; x < 2; x++) {
            for (int y = 0; y < 2; y++) {
                res.append(x).append("|").append(y).append("|");
                res.append(" ").append(inv(x)).append(" |");
                res.append(" ").append(or(x, y)).append(" |");
                res.append(" ").append(and(x, y)).append(" |");
                res.append(" ").append(imp(x, y)).append(" |");
                res.append(" ").append(eq(x, y)).append(" |");
                res.append(" ").append(oppJ(x, y)).append(" |");
                res.append(" ").append(sh(x, y)).append(" |");
                res.append(" ").append(arrP(x, y)).append(" |");
                res.append("\n");
            }
        }
        System.out.println(res);
    }

    private static boolean bin(int b) {
        return b != 0;
    }
}