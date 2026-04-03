package iprody35.service;

import iprody35.libraryjdbc.Book;
import iprody35.libraryjdbc.OccupiedBook;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebResult;
import jakarta.jws.WebService;
import java.util.List;


@WebService
public interface OccipiedBookService {
    
    @WebMethod
    @WebResult(name = "borrowedbook")
    public OccupiedBook addOccupiedBook(@WebParam(name = "borrowedbook") OccupiedBook occupiedBook);
    
    @WebMethod
    @WebResult(name = "borrowedbook")
    public List<OccupiedBook> occupiedBooksByReader(@WebParam(name = "reader_id") int reader_id);
    
}
