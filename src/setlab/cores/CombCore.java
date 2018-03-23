package setlab.cores;

import java.math.BigInteger;

public class CombCore {

    private static BigInteger Factorial(int n) {
        BigInteger res = BigInteger.ONE;
        for (int i = 1; i <= n; ++i) {
            res = res.multiply(BigInteger.valueOf(i));
        }
        return res;
    }

    public static BigInteger P(int n) {
        return Factorial(n);
    }

    public static BigInteger Pmk(int m, int[] k) {
        return new BigInteger("2");
    }

    public static BigInteger Amn(int m, int[] k) {
        return new BigInteger("2");
    }

    public static BigInteger A_mn(int m, int[] k) {
        return new BigInteger("2");
    }

    public static BigInteger Cmn(int m, int[] k) {
        return new BigInteger("2");
    }

    public static BigInteger C_mn(int m, int[] k) {
        return new BigInteger("2");
    }

}
