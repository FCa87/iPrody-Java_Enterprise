package iprody35.service;

import iprody35.libraryjdbc.Reader;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebResult;
import jakarta.jws.WebService;


@WebService
public interface ReaderService {
    
    @WebMethod
    @WebResult(name = "reader")
    public Reader addReader(@WebParam(name = "reader") Reader reader);
    
}
