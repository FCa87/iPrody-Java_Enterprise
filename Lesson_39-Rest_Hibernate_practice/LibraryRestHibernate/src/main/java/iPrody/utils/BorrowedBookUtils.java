package iPrody.utils;

import iPrody.dto.BorrowedBookDTO;
import iPrody.model.BorrowedBook;

import java.sql.Date;
import java.util.Objects;

public class BorrowedBookUtils {

    public static boolean isValid(BorrowedBookDTO borrowedBookDTO){
        try{
            int result = 0;
            if (borrowedBookDTO.getId() == null || borrowedBookDTO.getId() > 0) result++;
            if (borrowedBookDTO.getBookId() != null) result++;
            if (borrowedBookDTO.getReaderId() != null) result++;
            if (borrowedBookDTO.getBorrowDate() != null ) result++;
            if (borrowedBookDTO.getStatus().compareTo("borrowed") == 0 || borrowedBookDTO.getStatus().compareTo("returned") == 0) result++;
            return result == 5;
        } catch (Exception ex){
            return false;
        }
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
        int result = 0;
        if (Objects.equals(borrowedBook1.getId(), borrowedBook2.getId())) result++;
        if (borrowedBook1.getBookId().compareTo(borrowedBook2.getBookId()) == 0) result++;
        if (borrowedBook1.getReaderId().compareTo(borrowedBook2.getReaderId()) == 0) result++;
        if (borrowedBook1.getBorrowDate().compareTo(borrowedBook2.getBorrowDate()) == 0) result++;
        if (Objects.equals(borrowedBook1.getReturnDate(), borrowedBook2.getReturnDate())) result++;
        if (borrowedBook1.getStatus().compareTo(borrowedBook2.getStatus()) == 0) result++;
        return result == 6;
    }

}
