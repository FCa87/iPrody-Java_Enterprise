package iPrody.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;

import java.util.LinkedList;
import java.util.List;


@Entity
@Table(name = "readers", schema = "library")
public class Reader {

    @Id
    @GeneratedValue(
        strategy = GenerationType.SEQUENCE,
        generator = "reader_seq_gen"
    )
    @SequenceGenerator(
        name = "reader_seq_gen",
        sequenceName = "library.readers_id_seq",
        initialValue = 1,
        allocationSize = 1
    )
    private Integer id;

    @Column(nullable = false, length = 100)
    @Size(
        min = 0,
        max = 100,
        message = "Name's length should be under 100 symbols"
    )
    private String name;

    @Column(unique = true, nullable = false, length = 255)
    @Size(
            min = 0,
            max = 255,
            message = "E-mail's length should be under 255 symbols"
    )
    private String email;

    @Column(unique = true, length = 15)
    @Size(
            min = 0,
            max = 15,
            message = "Phone's length should be under 15 symbols"
    )
    private String phone;

    @OneToMany(mappedBy = "reader", cascade = CascadeType.ALL)
    private List<BorrowedBook> borrowedList;

    public Reader() {
    }

    public Reader(Integer id, String name, String email, String phone) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
    }

    public Reader(String phone, String email, String name) {
        this.phone = phone;
        this.email = email;
        this.name = name;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public List<BorrowedBook> getBorrowedList() {
        return borrowedList;
    }

    public void setBorrowedList(List<BorrowedBook> borrowedList) {
        this.borrowedList = borrowedList;
    }
}
