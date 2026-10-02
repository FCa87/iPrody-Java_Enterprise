package iPrody.model;

import java.util.List;

public class Author {

    private Integer id;
    private String name;
    private String country;

    public Author() {
    }

    public Author(Integer id, String name, String country) {
        this.id = id;
        this.name = name;
        this.country = country;
    }

    public Author(String name, String country) {
        this.name = name;
        this.country = country;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public String toString() {
        return "Author{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", country='" + country + '\'' +
                '}';
    }
}
