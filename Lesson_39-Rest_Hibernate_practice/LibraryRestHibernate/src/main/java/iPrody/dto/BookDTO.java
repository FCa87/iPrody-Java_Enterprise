package iPrody.dto;


import java.util.List;

public class BookDTO {

    private Integer id;
    private String title;
    private String author;
    private Integer publishedYear;
    private String genre;
    private List<BorrowedBookDTO> borrowedList;

    public BookDTO(Integer id, String title, String author, Integer publishedYear, String genre, List<BorrowedBookDTO> borrowedList) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.publishedYear = publishedYear;
        this.genre = genre;
        this.borrowedList = borrowedList;
    }

    public BookDTO() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public Integer getPublishedYear() {
        return publishedYear;
    }

    public void setPublishedYear(Integer publishedYear) {
        this.publishedYear = publishedYear;
    }

    public List<BorrowedBookDTO> getBorrowedList() {
        return borrowedList;
    }

    public void setBorrowedList(List<BorrowedBookDTO> borrowedList) {
        this.borrowedList = borrowedList;
    }
}
