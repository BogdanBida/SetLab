package setlab.cores;

import java.math.BigInteger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class CombCoreTest {
    
    public CombCoreTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void testP() {
        System.out.println("P");
        int n = 0;
        BigInteger expResult = null;
        BigInteger result = CombCore.P(n);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    @Test
    public void testPmk() {
        System.out.println("Pmk");
        int m = 0;
        int[] k = null;
        BigInteger expResult = null;
        BigInteger result = CombCore.Pmk(m, k);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    @Test
    public void testAmn() {
        System.out.println("Amn");
        int n = 0;
        int m = 0;
        BigInteger expResult = null;
        BigInteger result = CombCore.Amn(n, m);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    @Test
    public void testA_mn() {
        System.out.println("A_mn");
        int n = 0;
        int m = 0;
        BigInteger expResult = null;
        BigInteger result = CombCore.A_mn(n, m);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    @Test
    public void testCmn() {
        System.out.println("Cmn");
        int n = 0;
        int m = 0;
        BigInteger expResult = null;
        BigInteger result = CombCore.Cmn(n, m);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    @Test
    public void testC_mn() {
        System.out.println("C_mn");
        int n = 0;
        int m = 0;
        BigInteger expResult = null;
        BigInteger result = CombCore.C_mn(n, m);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    @Test
    public void testGetTime() {
        System.out.println("getTime");
        String expResult = "";
        String result = CombCore.getTime();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
