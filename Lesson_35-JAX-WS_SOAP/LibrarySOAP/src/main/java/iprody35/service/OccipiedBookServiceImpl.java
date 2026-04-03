package iprody35.service;

import iprody35.libraryjdbc.LibraryAPI;
import iprody35.libraryjdbc.OccupiedBook;
import jakarta.jws.WebService;
import java.util.ArrayList;
import java.util.List;


@WebService(endpointInterface = "iprody35.service.OccipiedBookService")
public class OccipiedBookServiceImpl implements OccipiedBookService{

    @Override
    public OccupiedBook addOccupiedBook(OccupiedBook occupiedBook) {
        return LibraryAPI.addOccupiedBook(occupiedBook);
    }
    
    @Override
    public List<OccupiedBook> occupiedBooksByReader(int reader_id){
        if (reader_id < 1){
            throw new RuntimeException("Reader's id must be above zero!");
        }
        List<OccupiedBook> occupiedBooks = LibraryAPI.occupiedBooks();
        List<OccupiedBook> result = new ArrayList<>();
        for (OccupiedBook ob : occupiedBooks){
            if (ob.getReader_id() == reader_id){
                result.add(ob);
            }
        }
        return result;
    }
    
}
