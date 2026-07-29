package iPrody.utils;

import iPrody.dto.BookDTO;
import iPrody.dto.BorrowedBookDTO;
import iPrody.dto.ReaderDTO;
import iPrody.model.Book;
import iPrody.model.BorrowedBook;
import iPrody.model.Reader;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ReaderUtils {

    public static boolean isValid(ReaderDTO readerDTO){
        if (readerDTO.getId() != null && readerDTO.getId() <= 0) return false;
        if (readerDTO.getName() == null || readerDTO.getName().length() > 100) return false;
        if (readerDTO.getEmail() == null || readerDTO.getEmail().length() > 255) return false;
        if (readerDTO.getPhone() == null || readerDTO.getPhone().length() > 15) return false;
        if (readerDTO.getBorrowedList() == null) readerDTO.setBorrowedList(new ArrayList<>());
        return true;
    }

    public static ReaderDTO toREST (Reader reader){
        List<BorrowedBookDTO> borrowedListDTO = reader.getBorrowedList().stream()
                                                      .map(BorrowedBookUtils::toREST)
                                                      .toList();
        return new ReaderDTO(reader.getId(), reader.getName(), reader.getEmail(), reader.getPhone(), borrowedListDTO);
    }

    public static Reader toDB(ReaderDTO readerDTO){
        Reader result = new Reader(readerDTO.getId(), readerDTO.getName(), readerDTO.getEmail(), readerDTO.getPhone());
        List<BorrowedBook> borrowedList =  readerDTO.getBorrowedList().stream()
                                                    .map(BorrowedBookUtils::toDB)
                                                    .toList();
        result.setBorrowedList(borrowedList);
        return result;
    }

    public static boolean compareFields(Reader reader1, Reader reader2){
        if (!Objects.equals(reader1.getId(), reader2.getId())) return false;
        if (reader1.getName().compareTo(reader2.getName()) != 0) return false;
        if (reader1.getEmail().compareTo(reader2.getEmail()) != 0) return false;
        if (reader1.getPhone().compareTo(reader2.getPhone()) != 0) return false;
        return true;
    }

}
