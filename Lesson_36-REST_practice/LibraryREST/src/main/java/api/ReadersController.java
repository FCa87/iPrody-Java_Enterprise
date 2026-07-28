
package api;

import dto.ErrorDTO;
import dto.ReaderDTO;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.Date;
import libraryjdbc.LibraryAPI;
import libraryjdbc.Reader;

@Path("/readers")
public class ReadersController {
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addReader(ReaderDTO readerDTO){
        Reader addingReader = new Reader(readerDTO.getId(), readerDTO.getName(), readerDTO.getEmail(), readerDTO.getPhone());
        Reader result = LibraryAPI.addReader(addingReader);
        if (result == null){
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                           .entity(new ErrorDTO("Reader hasn't been added!", new Date()))
                           .build();
        } else {
            return Response.ok(new ReaderDTO(result.getId(), result.getName(), result.getEmail(), result.getPhone()))
                           .build();
        }
    }
    
    
}
