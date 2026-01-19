package framework.printer;

import framework.execution.Execution;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class FilePrinter implements Printer{
    private final String template;
    private final Path path;

    public FilePrinter(String template, Path path) {
        this.template = template;
        this.path = path;
    }

    @Override
    public void write(List<Execution> executions) {
        try (BufferedWriter writer = Files.newBufferedWriter(path, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING)) {
            for (var execution : executions) {
                StringBuilder out = new StringBuilder();
                out.append("Test class: ").append(execution.getTestClass().getName()).append("\n")
                   .append("Start time: ").append(execution.getStartTime().format(DateTimeFormatter.ofPattern(template))).append("\n")
                   .append("End time: ").append(execution.getEndTime().format(DateTimeFormatter.ofPattern(template))).append("\n")
                   .append("-----------------------------------Tests---------------------------------------\n");
                boolean testsAllResult = true;
                for (var executionItem : execution.getExecutionItems()) {
                    testsAllResult &= executionItem.getAssertResult().isSuccess();
                    var result = executionItem.getAssertResult().isSuccess() ? "   OK   | " : " FAILED | ";
                    out.append(result)
                       .append(String.format("%-25s|%-25s\n", executionItem.getMethod().getName(), executionItem.getAssertResult().toString()));
                }
                if (testsAllResult){
                    out.append("All tests passed successfully!");
                } else {
                    out.append("WARNING!!! One or more tests failed!");
                }
                out.append("\n\n\n");
                writer.write(out.toString());
            }
            writer.flush();
        } catch (IOException ex) {
            throw new RuntimeException("Error of printing in file");
        }
    }

}
