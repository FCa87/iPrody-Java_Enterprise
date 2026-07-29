package iPrody.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;

import java.sql.Date;


@Entity
@Table(name = "borrowed_books", schema = "library")
public class BorrowedBook {

    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "borrowedBook_seq_gen"
    )
    @SequenceGenerator(
            name = "borrowedBook_seq_gen",
            sequenceName = "library.borrowed_books_id_seq",
            initialValue = 1,
            allocationSize = 1
    )
    private Integer id;

    @Column(name = "book_id", nullable = false)
    private Integer bookId;

    @Column(name = "reader_id", nullable = false)
    private Integer readerId;

    @Column(name = "borrow_date", nullable = false)
    private Date borrowDate;

    @Column(name = "return_date")
    private Date returnDate;

    @Column(
        length = 20,
        check = @CheckConstraint(constraint  = "is_active IN ('borrowed', 'returned')"),
        comment = "Status can be only 'borrowed' or 'returned'"
    )
    @Size(
            min = 0,
            max = 20,
            message = "Status' length should be 20 symbols or less"
    )
    private String status;

    @JoinColumn(insertable = false, updatable = false, name = "book_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Book book;

    @JoinColumn(insertable = false, updatable = false, name = "reader_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Reader reader;

    public BorrowedBook() {
    }

    public BorrowedBook(Integer id, Integer bookId, Integer readerId, Date borrowDate, Date returnDate, String status) {
        this.id = id;
        this.bookId = bookId;
        this.readerId = readerId;
        this.borrowDate = borrowDate;
        this.returnDate = returnDate;
        this.status = status;
    }

    public BorrowedBook(Integer bookId, Integer readerId, Date borrowDate, Date returnDate, String status) {
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

    public Date getBorrowDate() {
        return borrowDate;
    }

    public void setBorrowDate(Date borrowDate) {
        this.borrowDate = borrowDate;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Reader getReader() {
        return reader;
    }

    public void setReader(Reader reader) {
        this.reader = reader;
    }
}
