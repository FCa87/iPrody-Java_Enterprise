package iprody31.libraryjdbc;

import java.sql.Date;

public class OccupiedBook {
    
    private int id;
    private int book_id;
    private int reader_id;
    private Date borrow_date;
    private Date return_date;
    private Status status;

    public OccupiedBook(int id, int book_id, int reader_id, Date borrow_date, Date return_date, Status status) {
        this.id = id;
        this.book_id = book_id;
        this.reader_id = reader_id;
        this.borrow_date = borrow_date;
        this.return_date = return_date;
        this.status = status;
    }
    
    public OccupiedBook(int book_id, int reader_id, Date borrow_date, Date return_date, Status status) {
        this.book_id = book_id;
        this.reader_id = reader_id;
        this.borrow_date = borrow_date;
        this.return_date = return_date;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public int getBook_id() {
        return book_id;
    }

    public int getReader_id() {
        return reader_id;
    }

    public Date getBorrow_date() {
        return borrow_date;
    }

    public Date getReturn_date() {
        return return_date;
    }

    public Status getStatus() {
        return status;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setBook_id(int book_id) {
        this.book_id = book_id;
    }

    public void setReader_id(int reader_id) {
        this.reader_id = reader_id;
    }

    public void setBorrow_date(Date borrow_date) {
        this.borrow_date = borrow_date;
    }

    public void setReturn_date(Date return_date) {
        this.return_date = return_date;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "OccupiedBook{" + "id=" + id + ", book_id=" + book_id + ", reader_id=" + reader_id + ", borrow_date=" + borrow_date + ", return_date=" + return_date + ", status=" + status + '}';
    }
      
}
