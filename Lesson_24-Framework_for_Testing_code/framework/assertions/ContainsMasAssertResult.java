package framework.assertions;

import java.util.Arrays;

public class ContainsMasAssertResult<T> extends AssertResult{
    private final T[] current;
    private final T[] toContain;


    public ContainsMasAssertResult (T[] current, T[] toContain, boolean success) {
        super(success);
        this.current = current;
        this.toContain = toContain;
    }

    @Override
    public String toString() {
        return String.format(" %-50s | %-50s", "big mass: '" +Arrays.toString(current)+"'", "small mass: '" + Arrays.toString(toContain)+"'");
    }
}

