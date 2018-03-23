package setlab.cores;

import java.math.BigInteger;


public class CombCore {
    static double Gamma(double z)
    {
        double tmp1 = Math.sqrt(2*Math.PI/z);
        double tmp2 = z + 1.0/(12 * z - 1.0/(10*z));
        tmp2 = Math.pow(z/Math.E, z); // ooops; thanks hj
        tmp2 = Math.pow(tmp2/Math.E, z);
        return tmp1 * tmp2;
    }
    
}
