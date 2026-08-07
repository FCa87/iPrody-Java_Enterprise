package iPrody.model;

public class Book {

    private Integer id;
    private String title;
    private Integer publishedYear;
    private Integer authorId;

    public Book() {
    }

    public Book(Integer id, String title, Integer publishedYear, Integer authorId) {
        this.id = id;
        this.title = title;
        this.publishedYear = publishedYear;
        this.authorId = authorId;
    }

    public Book(String title, Integer publishedYear, Integer authorId) {
        this.title = title;
        this.publishedYear = publishedYear;
        this.authorId = authorId;
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

    public Integer getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Integer authorId) {
        this.authorId = authorId;
    }

    public Integer getPublishedYear() {
        return publishedYear;
    }

    public void setPublishedYear(Integer publishedYear) {
        this.publishedYear = publishedYear;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", publishedYear=" + publishedYear +
                ", authorId=" + authorId +
                '}';
    }
}
