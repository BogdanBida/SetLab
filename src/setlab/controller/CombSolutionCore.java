package setlab.controller;

public class CombSolutionCore {

    public static String get(int idFunction, int[] n, int m) {
        StringBuilder res = new StringBuilder("<html>");
        switch (idFunction) {
            case 1:
                res.append(P(n[0]));
                break;
            case 2:
                res.append(Pk(n,m));
                break;
            case 3:
                res.append(Amn(n[0],m));
                break;
            case 4:
                res.append(A_mn(n[0],m));
                break;
            case 5:
                res.append(Cmn(n[0],m));
                break;
            case 6:
                res.append(C_mn(n[0],m));
                break;
        }
        res.append("</html>");
        return res.toString();
    }
    
    public static String P(int n) {
        return "it is " + n +"!";
    }
    public static String Pk(int[] k, int m) {
        return "Pk";
    }
    public static String Amn(int n, int m) {
        return "Amn";
    }
    public static String A_mn(int n, int m) {
        return "A_mn";
    }
    public static String Cmn(int n, int m) {
        return "Cmn";
    }
    public static String C_mn(int n, int m) {
        return "C_mn";
    }
}
