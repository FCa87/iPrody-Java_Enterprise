package tests;

import framework.assertions.AssertException;
import framework.assertions.Assertions;
import framework.marker.Test;
import util.Calculator;

public class CalculatorLinesTest {

    @Test
    public void testSumLine() throws AssertException {
        Assertions.contains(Calculator.sumLine(2, 3), "2 + 3");
    }

    @Test
    public void testDivideLine() throws AssertException {
        Assertions.contains(Calculator.divideLine(6, 3), "6 / 3");
    }

    @Test
    public void testMultiplyLine() throws AssertException {
        Assertions.contains(Calculator.multiplyLine(2, 3), "2 * 3");
    }

    @Test
    public void testSubtractLine() throws AssertException {
        Assertions.contains(Calculator.subtractLine(6, 3), "6 - 3");
    }
}
