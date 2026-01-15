package framework.assertions;

public class EqualsAssertResult<T> extends AssertResult{
    T expected;
    T actual;

    public EqualsAssertResult(T expected, T actual, boolean success) {
        super(success);
        this.expected = expected;
        this.actual = actual;
    }

    @Override
    public String toString() {
        return String.format("  %-20s |  %-30s", "expected: " + expected, "actual: " + actual);
    }
}
