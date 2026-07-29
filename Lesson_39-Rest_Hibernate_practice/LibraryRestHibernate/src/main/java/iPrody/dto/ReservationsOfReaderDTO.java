package iPrody.dto;

import java.sql.Date;

public class ReservationsOfReaderDTO {

    Integer bookId;
    String title;
    String author;
    Integer publishedYear;
    String genre;
    String borrowDate;

    public ReservationsOfReaderDTO(Integer bookId, String title, String author, Integer publishedYear, String genre, Date borrowDate) {
        this.bookId = bookId;
        this.title = title;
        this.author = author;
        this.publishedYear = publishedYear;
        this.genre = genre;
        this.borrowDate = borrowDate.toLocalDate().toString();
    }

    public Integer getBookId() {
        return bookId;
    }

    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Integer getPublishedYear() {
        return publishedYear;
    }

    public void setPublishedYear(Integer publishedYear) {
        this.publishedYear = publishedYear;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getBorrowDate() {
        return borrowDate;
    }

    public void setBorrowDate(Date borrowDate) {
        this.borrowDate = borrowDate.toLocalDate().toString();
    }
}
