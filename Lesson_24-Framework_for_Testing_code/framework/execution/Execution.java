package framework.execution;

import java.time.LocalDateTime;
import java.util.List;

public class Execution {
    private final Class<?> testClass;
    private final List<ExecutionItem> executionItems;
    private final LocalDateTime startTime;
    private final LocalDateTime endTime;

    public Execution(Class<?> testClass, List<ExecutionItem> executionItems, LocalDateTime startTime, LocalDateTime endTime) {
        this.testClass = testClass;
        this.executionItems = executionItems;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public Class<?> getTestClass() {
        return testClass;
    }

    public List<ExecutionItem> getExecutionItems() {
        return executionItems;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }
}
