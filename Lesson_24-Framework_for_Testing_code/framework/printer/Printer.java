package framework.printer;

import framework.execution.Execution;

import java.util.List;

public interface Printer {
    void write(List<Execution> executions);
}
