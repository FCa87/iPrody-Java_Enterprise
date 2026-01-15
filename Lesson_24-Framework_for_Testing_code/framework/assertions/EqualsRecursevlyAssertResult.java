package framework.assertions;

public class EqualsRecursevlyAssertResult<T> extends AssertResult{
    T expected;
    T actual;

    public EqualsRecursevlyAssertResult(T expected, T actual, boolean success) {
        super(success);
        this.expected = expected;
        this.actual = actual;
    }

    @Override
    public String toString() {
        
        return String.format(" %-60s | %-60s", "Object1: " + expected, "Object2: " + actual);
    }
}
