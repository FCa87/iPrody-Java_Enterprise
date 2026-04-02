package iprody34.libraryjdbc;

public enum Status {
    
    BORROWED("borrowed"),
    RETURNED("returned");
    
    public final String status;
    
    private Status(String status) {
        this.status = status;
    }
    
}
