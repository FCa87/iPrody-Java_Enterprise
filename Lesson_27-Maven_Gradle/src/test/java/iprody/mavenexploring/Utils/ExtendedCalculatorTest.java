
package iprody.mavenexploring.Utils;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ExtendedCalculatorTest {
    
    public ExtendedCalculatorTest() {
    }
    
    //!!!WRONG TEST!!!
    /*@Test
    public void testSummationFailed() {
        System.out.println("summation");
        int a = Integer.MAX_VALUE;
        int b = 0;
        long expResult = 0;
        long result = ExtendedCalculator.summation(a, b);
        assertEquals(expResult, result);
    }*/


    //Test of summation method
    @Test
    public void testSummation() {
        System.out.println("summation");
        int a = Integer.MAX_VALUE;
        int b = Integer.MAX_VALUE;
        long expResult = (long)a + b;
        long result = ExtendedCalculator.summation(a, b);
        assertEquals(expResult, result);
    }

    //Test of subtraction method, of class ExtendedCalculator.
    @Test
    public void testSubtraction() {
        System.out.println("subtraction");
        int a = Integer.MIN_VALUE;
        int b = Integer.MIN_VALUE;
        long expResult = (long)a - b;
        long result = ExtendedCalculator.subtraction(a, b);
        assertEquals(expResult, result);
    }

    //Test of multiply method, of class ExtendedCalculator.
    @Test
    public void testMultiply() {
        System.out.println("multiply");
        int a = Integer.MAX_VALUE;
        int b = 100;
        long expResult = (long)a * b;
        long result = ExtendedCalculator.multiply(a, b);
        assertEquals(expResult, result);
    }

    //Test of division method, of class ExtendedCalculator.
    @Test
    public void testDivision() {
        System.out.println("division");
        int a = 9;
        int b = 2;
        double expResult = (double)a / 2;
        double result = ExtendedCalculator.division(a, b);
        assertEquals(expResult, result, 0);
    }
    
    @Test
    public void testDivisionOnZero() {
        System.out.println("divisionOnZero");
        int a = 9;
        int b = 0;
        var result = assertThrowsExactly(RuntimeException.class, () -> ExtendedCalculator.division(a, b));
        assertTrue(result.getMessage().compareTo("You can't devide by zero!") == 0);
    }
    

    //Test of factorial method, of class ExtendedCalculator.
    @Test
    public void testFactorial() {
        System.out.println("factorial");
        int number = 8;
        long expResult = 40320L;
        long result = ExtendedCalculator.factorial(number);
        assertEquals(expResult, result);
    }
    
    @Test
    public void testFactorialBelowZero() {
        System.out.println("factorialBelowZero");
        int number = -1;
        var result = assertThrowsExactly(RuntimeException.class, () -> ExtendedCalculator.factorial(number));
        assertTrue(result.getMessage().compareTo("Number must be positive!") == 0);
    }
    
    @Test
    public void testFactorialTooLarge() {
        System.out.println("factorialTooLarge");
        int number = 21;
        var result = assertThrowsExactly(RuntimeException.class, () -> ExtendedCalculator.factorial(number));
        assertTrue(result.getMessage().compareTo("Facterial is too big!") == 0);
    }

    //Test of naturalLog method, of class ExtendedCalculator.
    @Test
    public void testNaturalLog() {
        System.out.println("naturalLog");
        double number = 5698.0;
        double expResult = Math.log(number);
        double result = ExtendedCalculator.naturalLog(number);
        assertEquals(expResult, result, 0);
    }
    
    @Test
    public void testNaturalLogNegativeBase() {
        System.out.println("naturalLogNegativeBase");
        double number = -1.0;
        var result = assertThrowsExactly(RuntimeException.class, () -> ExtendedCalculator.naturalLog(number));
        assertTrue(result.getMessage().compareTo("Number must be greater then zero!") == 0);
    }
    
}
