package iprody35.service;

import iprody35.libraryjdbc.LibraryAPI;
import iprody35.libraryjdbc.Reader;
import jakarta.jws.WebService;


@WebService(endpointInterface = "iprody35.service.ReaderService")
public class ReaderServiceImpl implements ReaderService{

    @Override
    public Reader addReader(Reader reader) {
        return LibraryAPI.addReader(reader);
    }
    
}
