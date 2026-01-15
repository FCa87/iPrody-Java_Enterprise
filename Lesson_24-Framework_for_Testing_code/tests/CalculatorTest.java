package tests;

import framework.assertions.AssertException;
import framework.assertions.Assertions;
import framework.marker.Test;
import util.Calculator;

public class CalculatorTest {

    @Test
    public void testSum() throws AssertException {
        Assertions.equals(5L, Calculator.sum(2, 3));
    }

    @Test
    public void testDivide() throws AssertException {
        Assertions.equals(2.0, Calculator.divide(6, 3));
    }

    @Test
    public void testSubtract() throws AssertException {
        Assertions.equals(3, Calculator.subtract(6, 3));
    }

    @Test
    public void testMultiply() throws AssertException {
        Assertions.equals(18L, Calculator.multiply(6, 3));
    }
}
