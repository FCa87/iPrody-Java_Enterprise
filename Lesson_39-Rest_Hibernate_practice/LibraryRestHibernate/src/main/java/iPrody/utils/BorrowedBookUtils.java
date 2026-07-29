package iPrody.utils;

import iPrody.dto.BorrowedBookDTO;
import iPrody.model.BorrowedBook;

import java.sql.Date;
import java.util.Objects;

public class BorrowedBookUtils {

    public static boolean isValid(BorrowedBookDTO borrowedBookDTO){
        if (borrowedBookDTO.getId() != null && borrowedBookDTO.getId() <= 0) return false;
        if (borrowedBookDTO.getBookId() == null) return false;
        if (borrowedBookDTO.getReaderId() == null) return false;
        if (borrowedBookDTO.getBorrowDate() == null ) return false;
        if (borrowedBookDTO.getStatus().compareTo("borrowed") != 0 && borrowedBookDTO.getStatus().compareTo("returned") != 0) return false;
        return true;
    }

    public static BorrowedBookDTO toREST (BorrowedBook borrowedBook){
        return new BorrowedBookDTO(borrowedBook.getId(), borrowedBook.getBookId(), borrowedBook.getReaderId(),
                                   borrowedBook.getBorrowDate().toLocalDate().toString(),
                                   borrowedBook.getReturnDate() == null ? null : borrowedBook.getReturnDate().toLocalDate().toString(),
                                   borrowedBook.getStatus());
    }

    public static BorrowedBook toDB(BorrowedBookDTO borrowedBookDTO){
        return new BorrowedBook(borrowedBookDTO.getId(), borrowedBookDTO.getBookId(), borrowedBookDTO.getReaderId(),
                                Date.valueOf(borrowedBookDTO.getBorrowDate()),
                                borrowedBookDTO.getReturnDate() == null ? null : Date.valueOf(borrowedBookDTO.getReturnDate()),
                                borrowedBookDTO.getStatus());
    }

    public static boolean compareFields(BorrowedBook borrowedBook1, BorrowedBook borrowedBook2){
        if (!Objects.equals(borrowedBook1.getId(), borrowedBook2.getId())) return false;
        if (borrowedBook1.getBookId().compareTo(borrowedBook2.getBookId()) != 0) return false;
        if (borrowedBook1.getReaderId().compareTo(borrowedBook2.getReaderId()) != 0) return false;
        if (borrowedBook1.getBorrowDate().compareTo(borrowedBook2.getBorrowDate()) != 0) return false;
        if (!Objects.equals(borrowedBook1.getReturnDate(), borrowedBook2.getReturnDate())) return false;
        if (borrowedBook1.getStatus().compareTo(borrowedBook2.getStatus()) != 0) return false;
        return true;
    }

}
