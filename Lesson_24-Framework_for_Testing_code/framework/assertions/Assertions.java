package framework.assertions;

import java.util.Objects;

public class Assertions {

    public static <T> void equals(T expected, T actual) throws AssertException {
        boolean success = Objects.equals(expected, actual);
        throw new AssertException(new EqualsAssertResult<>(expected, actual, success));
    }

    public static void contains(String line, String textToFetch) throws AssertException {
        boolean success = line != null && textToFetch != null && line.contains(textToFetch);
        throw new AssertException(new ContainsAssertResult(line, textToFetch, success));
    }
}
