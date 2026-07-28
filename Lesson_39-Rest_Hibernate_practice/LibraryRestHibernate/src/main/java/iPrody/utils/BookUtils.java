package iPrody.utils;

import iPrody.dto.BookDTO;
import iPrody.dto.BorrowedBookDTO;
import iPrody.model.Book;
import iPrody.model.BorrowedBook;
import iPrody.model.Reader;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class BookUtils {

    public static boolean isValid(BookDTO bookDTO){
        try{
            int result = 0;
            if (bookDTO.getId() == null || bookDTO.getId() > 0) result++;
            if (bookDTO.getTitle() != null && bookDTO.getTitle().length() <= 255) result++;
            if (bookDTO.getAuthor() != null && bookDTO.getAuthor().length() <= 255) result++;
            if (bookDTO.getPublishedYear() != null && bookDTO.getPublishedYear() > 0) result++;
            if (bookDTO.getGenre() != null && bookDTO.getGenre().length() <= 100) result++;
            if (bookDTO.getBorrowedList() == null) bookDTO.setBorrowedList(new ArrayList<>());
            return result == 5;
        } catch (Exception ex){
            return false;
        }
    }

    public static BookDTO toREST (Book book){
        List<BorrowedBookDTO> borrowedListDTO = book.getBorrowedList().stream()
                                                    .map(BorrowedBookUtils::toREST)
                                                    .toList();
        return new BookDTO(book.getId(), book.getTitle(), book.getAuthor(), book.getPublishedYear(), book.getGenre(), borrowedListDTO);
    }

    public static Book toDB(BookDTO bookDTO){
        Book result = new Book(bookDTO.getId(), bookDTO.getTitle(), bookDTO.getAuthor(), bookDTO.getPublishedYear(), bookDTO.getGenre());
        List<BorrowedBook> borrowedList =  bookDTO.getBorrowedList().stream()
                                                  .map(BorrowedBookUtils::toDB)
                                                  .toList();
        result.setBorrowedList(borrowedList);
        return result;
    }

    public static boolean compareFields(Book book1, Book book2){
        int result = 0;
        if (Objects.equals(book1.getId(), book2.getId())) result++;
        if (book1.getTitle().compareTo(book2.getTitle()) == 0) result++;
        if (book1.getAuthor().compareTo(book2.getAuthor()) == 0) result++;
        if (book1.getGenre().compareTo(book2.getGenre()) == 0) result++;
        if (Objects.equals(book1.getPublishedYear(), book2.getPublishedYear())) result++;
        return result == 5;
    }

}
