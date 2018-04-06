package setlab.controller;

import java.util.Arrays;
import setlab.cores.CombCore;

public class CombSolutionCore {

    private static final StringBuilder res = new StringBuilder("<html><body>");

    static {
        res.append("");
    }

    public static String get(int idFunction, int[] n, int m) {
        res.append("&nbsp;&nbsp;>> ");
        switch (idFunction) {
            case 1:
                res.append(P(n[0]));
                res.append(CombCore.getTime());
                break;
            case 2:
                res.append(Pk(n, m));
                res.append(CombCore.getTime());
                break;
            case 3:
                res.append(Amn(n[0], m));
                res.append(CombCore.getTime());
                break;
            case 4:
                res.append(A_mn(n[0], m));
                res.append(CombCore.getTime());
                break;
            case 5:
                res.append(Cmn(n[0], m));
                res.append(CombCore.getTime());
                break;
            case 6:
                res.append(C_mn(n[0], m));
                res.append(CombCore.getTime());
                break;
        }
        res.append("<br><br>");
        return res.toString();
    }
    
    public static String P(int n) {
        return "P<sub>" + n + "</sub> = " + n + "! = " + CombCore.P(n);
    }

    public static String Pk(int[] k, int m) {
        return "P<sub>" + m + "</sub> + (" + Arrays.toString(k) + ") = "
                + m + "!/(" + Arrays.toString(k) + ")!! = " + CombCore.Pmk(m, k);
    }
    
    public static String Amn(int n, int m) {
        return "A(" + n + "," + m + ") = "
                + n + "!/(" + n + "-" + m + ")! = " + CombCore.Amn(n, m);
    }

    public static String A_mn(int n, int m) {
        return "A_(" + n + "," + m + ") = "
                + n + "<sup>" + m + "</sup> = " + CombCore.A_mn(n, m);
    }

    public static String Cmn(int n, int m) {
        return "C(" + n + "," + m + ") = " + n + "!/(" + m + "!*(" + n + "-" + m + ")!) = "
                + CombCore.Cmn(n, m);
    }

    public static String C_mn(int n, int m) {
        return "C_(" + n + "," + m + ") = C(" + n + "+" + m + "-1," + m + ") = "
                + Cmn(n+m-1,m);
    }
}
