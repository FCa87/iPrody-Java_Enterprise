package iPrody.controller;

import iPrody.dto.BookDTO;
import iPrody.repo.BookRepository;
import iPrody.utils.BookUtils;
import iPrody.utils.ErrorUtils;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


@Path("/books")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class BookController {

    private static Logger logger = LogManager.getLogger();

    private final BookRepository bookRepository = new BookRepository();

    @GET
    public Response getAllBooks(){
        try{
            return Response.ok(bookRepository.getAll().stream()
                    .map(BookUtils::toREST)
                    .toList())
                    .build();
        } catch (Exception ex){
            logger.catching(ex);
            return ErrorUtils.buildErrorResponce(
                    "Something has gone wrong! Contact our support.", Response.Status.INTERNAL_SERVER_ERROR);
        }
    }

    @GET
    @Path("/{id}")
    public Response getBookById(@PathParam("id") Integer id){
        try{
            var result = bookRepository.getById(id);
            if (result.isPresent()){
                return Response.ok(BookUtils.toREST(result.get())).build();
            } else {
                return Response.status(Response.Status.NOT_FOUND)
                        .entity("The book with id=" + id + " hasn't been found!")
                        .build();
            }
        } catch (Exception ex){
            logger.catching(ex);
            return ErrorUtils.buildErrorResponce(
                    "Something has gone wrong! Contact our support.", Response.Status.INTERNAL_SERVER_ERROR);
        }
    }

    @POST
    public Response createBook(BookDTO bookDTO){
        if (BookUtils.isValid(bookDTO)){
            try{
                return Response.ok(BookUtils.toREST(bookRepository.create(BookUtils.toDB(bookDTO)))).build();
            } catch (Exception ex){
                logger.catching(ex);
                return ErrorUtils.buildErrorResponce(
                        "Something has gone wrong! Contact our support.", Response.Status.INTERNAL_SERVER_ERROR);
            }
        } else {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Invalid values of input fields. Please check them.")
                    .build();
        }

    }

    @PUT
    public Response updateBook(BookDTO bookDTO){
        if (BookUtils.isValid(bookDTO) && bookDTO.getId() != null){
            try{
                bookRepository.update(BookUtils.toDB(bookDTO));
                return Response.ok("The book has been updated successfully.").build();
            } catch (Exception ex){
                logger.catching(ex);
                return ErrorUtils.buildErrorResponce(
                        "Something has gone wrong! Contact our support.", Response.Status.INTERNAL_SERVER_ERROR);
            }
        } else {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Invalid values of input fields. Please check them.")
                    .build();
        }

    }

    @DELETE
    public Response deleteBook(BookDTO bookDTO){
        if (BookUtils.isValid(bookDTO) && bookDTO.getId() != null){
            var result = bookRepository.getById(bookDTO.getId());
            if (result.isPresent()) {
                try{
                    if (BookUtils.compareFields(result.get(), BookUtils.toDB(bookDTO))){
                        bookRepository.delete(BookUtils.toDB(bookDTO));
                        return Response.ok()
                                .entity("The book has been deleted successfully.")
                                .build();
                    } else {
                        return Response.status(Response.Status.BAD_REQUEST)
                                .entity("Values of input fields don't match with database fields. Please check them.")
                                .build();
                    }
                } catch (Exception ex){
                    logger.catching(ex);
                    return ErrorUtils.buildErrorResponce(
                            "Something has gone wrong! Contact our support.", Response.Status.INTERNAL_SERVER_ERROR);
                }
            } else {
                return Response.status(Response.Status.NOT_FOUND)
                        .entity("The book with id=" + bookDTO.getId() + " hasn't been found!")
                        .build();
            }

        } else {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Invalid values of input fields. Please check them.")
                    .build();
        }
    }

    @DELETE
    @Path("/{id}")
    public Response deleteBookById(@PathParam("id") Integer id){
        try{
            var bookOpt = bookRepository.getById(id);
            if (bookOpt.isPresent()){
                bookRepository.delete(bookOpt.get());
                return Response.ok()
                        .entity("The book has been deleted successfully.")
                        .build();
            } else {
                return Response.status(Response.Status.NOT_FOUND)
                        .entity("The book with id=" + id + " hasn't been found!")
                        .build();
            }
        } catch (Exception ex){
            logger.catching(ex);
            return ErrorUtils.buildErrorResponce(
                    "Something has gone wrong! Contact our support.", Response.Status.INTERNAL_SERVER_ERROR);
        }
    }
}
