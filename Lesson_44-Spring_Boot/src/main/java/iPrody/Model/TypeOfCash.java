package iPrody.Model;

public enum TypeOfCash {
    R("RUBLE"),
    $("DOLLAR");

    private final String fullName;

    TypeOfCash(String fullName) {
        this.fullName = fullName;
    }

    public String getFullName() {
        return fullName;
    }
}
