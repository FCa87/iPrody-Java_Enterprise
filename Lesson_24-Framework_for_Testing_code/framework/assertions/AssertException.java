package framework.assertions;

public class AssertException extends Exception{
    private final AssertResult assertResult;

    public AssertException(AssertResult assertResult) {
        this.assertResult = assertResult;
    }


    public AssertResult getAssertResult() {
        return assertResult;
    }
}
