package framework.assertions;

public abstract class AssertResult {
    private final boolean success;

    public AssertResult(boolean success) {
        this.success = success;
    }


    public boolean isSuccess() {
        return success;
    }

    public abstract String toString();
}
