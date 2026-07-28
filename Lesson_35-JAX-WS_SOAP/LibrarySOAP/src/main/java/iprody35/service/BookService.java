package iprody35.service;

import iprody35.libraryjdbc.Book;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebResult;
import jakarta.jws.WebService;


@WebService
public interface BookService {
    
    @WebMethod
    @WebResult(name = "book")
    public Book addBook(@WebParam(name = "book") Book book);
    
}
