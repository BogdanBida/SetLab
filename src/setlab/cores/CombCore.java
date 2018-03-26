package setlab.cores;

import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

public class CombCore {

    private static BigInteger Fact(int n) {
        BigInteger res = BigInteger.ONE;
        for (int i = 1; i <= n; ++i) {
            res = res.multiply(BigInteger.valueOf(i));
        }
        return res;
    }

    public static BigInteger P(int n) {
        return Fact(n);
    }

    public static BigInteger Pmk(int m, int[] k) {
        BigInteger temp = BigInteger.ONE;
        for (int i = 0; i < k.length; i++) {
            temp.multiply(new BigInteger(String.valueOf(k[i])));
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

}
