import framework.printer.FilePrinter;
import framework.printer.Printer;
import framework.printer.StdoutPrinter;
import framework.runner.ExtendedTestAutomationRunner;
import framework.runner.Runner;
import java.nio.file.Path;
import java.util.LinkedList;
import tests.CalculatorLinesTest;
import tests.CalculatorTest;

import java.util.List;
import tests.MassivesContainTest;
import tests.ObjectEqualTest;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        List<Printer> printers = new LinkedList<>(List.of(
            new StdoutPrinter("yyyy-MM-dd HH:mm:ss.SSS"),
            new StdoutPrinter("yyyy-MM-dd HH:mm:ss.SSS"),
            new FilePrinter("yyyy-MM-dd HH:mm:ss.SSS", Path.of("input.txt")),
            new FilePrinter("yyyy-MM-dd HH:mm:ss.SSS", Path.of("input_2.txt")),
            new FilePrinter("yyyy-MM-dd HH:mm:ss.SSS", Path.of("input.txt"))
        ));
        Runner runner = new ExtendedTestAutomationRunner(printers);
        runner.run(List.of(CalculatorTest.class, CalculatorLinesTest.class, MassivesContainTest.class, ObjectEqualTest.class));
    }
}