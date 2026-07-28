package iprody35.service;

import iprody35.libraryjdbc.Book;
import iprody35.libraryjdbc.LibraryAPI;
import jakarta.jws.WebService;

@WebService(endpointInterface = "iprody35.service.BookService")
public class BookServiceImpl implements BookService{

    @Override
    public Book addBook(Book book) {
        return LibraryAPI.addBook(book);
    }
    
}
