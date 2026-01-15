package framework.assertions;

public class ContainsAssertResult extends AssertResult {
    private final String lineToCheck;
    private final String expectedSubstring;


    public ContainsAssertResult(String lineToCheck, String expectedSubstring, boolean success) {
        super(success);
        this.lineToCheck = lineToCheck;
        this.expectedSubstring = expectedSubstring;
    }

    @Override
    public String toString() {
        return String.format("  %-40s |  %-30s", "expected substring: '" + expectedSubstring+"'", "line: '" + lineToCheck+"'");
    }
}
