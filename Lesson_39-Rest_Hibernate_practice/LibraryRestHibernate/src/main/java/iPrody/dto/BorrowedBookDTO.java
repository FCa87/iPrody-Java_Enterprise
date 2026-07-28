package iPrody.dto;

public class BorrowedBookDTO {

    private Integer id;
    private Integer bookId;
    private Integer readerId;
    private String borrowDate;
    private String returnDate;
    private String status;

    public BorrowedBookDTO() {
    }

    public BorrowedBookDTO(Integer id, Integer bookId, Integer readerId, String borrowDate, String returnDate, String status) {
        this.id = id;
        this.bookId = bookId;
        this.readerId = readerId;
        this.borrowDate = borrowDate;
        this.returnDate = returnDate;
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getBookId() {
        return bookId;
    }

    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }

    public Integer getReaderId() {
        return readerId;
    }

    public void setReaderId(Integer readerId) {
        this.readerId = readerId;
    }

    public String getBorrowDate() {
        return borrowDate;
    }

    public void setBorrowDate(String borrowDate) {
        this.borrowDate = borrowDate;
    }

    public String getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(String returnDate) {
        this.returnDate = returnDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
