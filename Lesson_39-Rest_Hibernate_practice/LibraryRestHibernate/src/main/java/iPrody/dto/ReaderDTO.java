package iPrody.dto;

import iPrody.model.BorrowedBook;

import java.util.List;

public class ReaderDTO {

    private Integer id;
    private String name;
    private String email;
    private String phone;
    private List<BorrowedBookDTO> borrowedList;

    public ReaderDTO() {
    }

    public ReaderDTO(Integer id, String name, String email, String phone, List<BorrowedBookDTO> borrowedList) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.borrowedList = borrowedList;
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

    public List<BorrowedBookDTO> getBorrowedList() {
        return borrowedList;
    }

    public void setBorrowedList(List<BorrowedBookDTO> borrowedList) {
        this.borrowedList = borrowedList;
    }
}
