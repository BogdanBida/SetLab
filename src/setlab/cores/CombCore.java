package setlab.cores;

import java.math.BigInteger;

public class CombCore {

    static private long time = 0;

    private static BigInteger Fact(int n) {
        
        BigInteger res = BigInteger.ONE;
        long start = System.currentTimeMillis();

        for (int i = 1; i <= n; ++i) {
            res = res.multiply(BigInteger.valueOf(i));
        }

        time += System.currentTimeMillis() - start;
        return res;
    }

    public static BigInteger P(int n) {
        return Fact(n);
    }

    public static BigInteger Pmk(int m, int[] k) {
        BigInteger temp = BigInteger.ONE;
        for (int i = 0; i < k.length; i++) {
            temp = temp.multiply(new BigInteger(String.valueOf(k[i])));
        }

        return Fact(m).divide(temp);
    }

    public static BigInteger Amn(int n, int m) {
        return Fact(n).divide(Fact(n - m));
    }

    public static BigInteger A_mn(int n, int m) {
        return new BigInteger(String.valueOf(n)).pow(m);
    }

    public static BigInteger Cmn(int n, int m) {
        return Fact(n).divide(new BigInteger(String.valueOf(m)).multiply(new BigInteger(String.valueOf(n - m))));
    }

    public static BigInteger C_mn(int n, int m) {
        return Fact(n + m - 1).divide(new BigInteger(String.valueOf(m)).multiply(new BigInteger(String.valueOf(n - 1))));
    }

    public static String getTime() {
        long t = time;
        time = 0;
        if (t > 0) {
            return "<br>&nbsp;&nbsp;&nbsp;&nbsp;Time: " + t + " ms";
        } else {
            return "";
        }
    }

}
