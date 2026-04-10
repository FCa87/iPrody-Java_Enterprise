
package api;

import dto.BookDTO;
import dto.ErrorDTO;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.Date;
import libraryjdbc.Book;
import libraryjdbc.LibraryAPI;

@Path("/books")
public class BooksController {
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addBook(BookDTO bookDTO){
        Book addingBook = new Book(bookDTO.getId(), bookDTO.getTitle(), bookDTO.getAuthor(), bookDTO.getPublished_year(), bookDTO.getGenre());
        Book result = LibraryAPI.addBook(addingBook);
        if (result == null){
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                           .entity(new ErrorDTO("Book hasn't been added!", new Date()))
                           .build();
        } else {
            return Response.ok(new BookDTO(result.getId(), result.getTitle(), result.getAuthor(), result.getPublished_year(), result.getGenre()))
                           .build();
        }
    }
    
    
}
