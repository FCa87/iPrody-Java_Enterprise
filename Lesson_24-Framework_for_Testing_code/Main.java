import framework.printer.Printer;
import framework.printer.StdoutPrinter;
import framework.runner.Runner;
import framework.runner.TestAutomationRunner;
import tests.CalculatorLinesTest;
import tests.CalculatorTest;

import java.util.List;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Printer printer = new StdoutPrinter("yyyy-MM-dd HH:mm:ss.SSS");
        Runner runner = new TestAutomationRunner(printer);
        runner.run(List.of(CalculatorTest.class, CalculatorLinesTest.class));
    }
}