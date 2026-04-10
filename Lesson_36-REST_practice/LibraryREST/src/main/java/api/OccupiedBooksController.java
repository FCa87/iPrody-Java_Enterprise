
package api;

import dto.ErrorDTO;
import dto.OccupiedBookDTO;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.GenericEntity;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import libraryjdbc.LibraryAPI;
import libraryjdbc.OccupiedBook;

@Path("/reservations")
public class OccupiedBooksController {
    
    @GET
    @Path("/{reader_id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response occupiedBooksbyReader_id(@PathParam("reader_id") int reader_id){
        List<OccupiedBookDTO> result = new ArrayList<>();
        try{
            result.addAll(LibraryAPI.occupiedBooks().stream()
                                    .filter( occupiedBook -> occupiedBook.getReader_id() == reader_id)
                                    .map( occupiedBook -> new OccupiedBookDTO(occupiedBook.getId(),
                                                                              occupiedBook.getBook_id(),
                                                                              occupiedBook.getReader_id(),
                                                                              occupiedBook.getBorrow_date(),
                                                                              occupiedBook.getReturn_date(),
                                                                              occupiedBook.getStatus()) )
                                    .toList());
        }
        catch(Exception ex){
            Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(new ErrorDTO("There are problems in recieving date from DB", new Date()))
                    .build();
        }
        if (result.isEmpty()){
            return Response.ok("There are no reservations behind this reader").build();
        } else {
            GenericEntity<List<OccupiedBookDTO>> entityResult = new GenericEntity<List<OccupiedBookDTO>>(result){};
            return Response.ok(entityResult).build();
        }
    }
            
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addReader(OccupiedBookDTO occupiedBookDTO){
        OccupiedBook addingOccupiedBook = new OccupiedBook(occupiedBookDTO.getId(), occupiedBookDTO.getBook_id(), occupiedBookDTO.getReader_id(),
                                                           occupiedBookDTO.getBorrow_date(), occupiedBookDTO.getReturn_date(), occupiedBookDTO.getStatus());
        OccupiedBook result = LibraryAPI.addOccupiedBook(addingOccupiedBook);
        
        if (result == null){
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                           .entity(new ErrorDTO("No reservation hasn't been created!", new Date()))
                           .build();
        } else {
            return Response.ok(new OccupiedBookDTO(result.getId(), result.getBook_id(), result.getReader_id(),
                                                   result.getBorrow_date(), result.getReturn_date(), result.getStatus()))
                           .build();
        }
    }
    
    
}
