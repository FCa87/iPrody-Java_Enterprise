package iPrody.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Entity
@Table(name = "books", schema = "library")
public class Book {

    @Id
    @GeneratedValue(
        strategy = GenerationType.SEQUENCE,
        generator = "book_seq_gen"
    )
    @SequenceGenerator(
        name = "book_seq_gen",
        sequenceName = "library.books_id_seq",
        initialValue = 1,
        allocationSize = 1
    )
    private Integer id;

    @Column(nullable = false, length = 255)
    @Size(
        min = 0,
        max = 255,
        message = "Title's length should be under 255 symbols"
    )
    private String title;

    @Column(nullable = false, length = 255)
    @Size(
            min = 0,
            max = 255,
            message = "Author's length should be under 255 symbols"
    )
    private String author;

    @Column(
        name = "published_year",
        check = @CheckConstraint(constraint  = "published_year > 0"),
        comment = "Published year should be positive"
    )
//    @Column(name = "published_year")
//    @Positive
    private Integer publishedYear;

    @Column
    @Size(
            min = 0,
            max = 100,
            message = "Genre's length should be under 100 symbols"
    )
    private String genre;

    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL)
    private List<BorrowedBook> borrowedList;

    public Book() {
    }

    public Book(Integer id, String title, String author, Integer publishedYear, String genre) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.publishedYear = publishedYear;
        this.genre = genre;
    }

    public Book(String title, String author, Integer publishedYear, String genre) {
        this.title = title;
        this.author = author;
        this.publishedYear = publishedYear;
        this.genre = genre;
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

    public Integer getPublishedYear() {
        return publishedYear;
    }

    public void setPublishedYear(Integer publishedYear) {
        this.publishedYear = publishedYear;
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

    public List<BorrowedBook> getBorrowedList() {
        return borrowedList;
    }

    public void setBorrowedList(List<BorrowedBook> borrowedList) {
        this.borrowedList = borrowedList;
    }
}
