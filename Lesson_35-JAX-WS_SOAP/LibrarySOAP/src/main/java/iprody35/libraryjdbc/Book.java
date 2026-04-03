package iprody35.libraryjdbc;

public class Book {
    
    private int id;
    private String title;
    private String author;
    private int published_year;
    private String genre;

    public Book(int id, String title, String author, int published_year, String genre) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.published_year = published_year;
        this.genre = genre;
    }
    
    public Book(String title, String author, int published_year, String genre) {
        this.title = title;
        this.author = author;
        this.published_year = published_year;
        this.genre = genre;
    }

    public Book() {
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public int getPublished_year() {
        return published_year;
    }

    public String getGenre() {
        return genre;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setPublished_year(int published_year) {
        this.published_year = published_year;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    @Override
    public String toString() {
        return "Book{" + "id=" + id + ", title=" + title + ", author=" + author + ", published_year=" + published_year + ", genre=" + genre + '}';
    }

}
