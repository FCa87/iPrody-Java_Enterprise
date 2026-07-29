package iPrody.controller;


import iPrody.dto.BorrowedBookDTO;
import iPrody.repo.BorrowedBookRepository;
import iPrody.utils.BorrowedBookUtils;
import iPrody.utils.CommonUtils;
import iPrody.utils.ErrorUtils;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Path("/reservations")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class BorrowedBookController {

    private static Logger logger = LogManager.getLogger();

    private final BorrowedBookRepository borrowedBookRepository = new BorrowedBookRepository();

    @GET
    public Response getAllBorrowedBooks(){
        try{
            return Response.ok(borrowedBookRepository.getAll().stream()
                            .map(BorrowedBookUtils::toREST)
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
    public Response getBorrowedBookById(@PathParam("id") Integer id){
        try{
            var result = borrowedBookRepository.getById(id);
            if (result.isPresent()){
                return Response.ok(BorrowedBookUtils.toREST(result.get())).build();
            } else {
                return CommonUtils.notFoundResponse("The reservation with id=" + id + " hasn't been found!");
            }
        } catch (Exception ex){
            logger.catching(ex);
            return ErrorUtils.buildErrorResponce(
                    "Something has gone wrong! Contact our support.", Response.Status.INTERNAL_SERVER_ERROR);
        }
    }

    @GET
    @Path("/rb")
    public Response getReservationsOfReader(@QueryParam("readerId") Integer readerId){
        if (readerId == null || readerId <= 0) {
            return CommonUtils.badRequestResponse("Invalid value. ReaderId must be above 0");
        }
        try {
            return Response.ok(borrowedBookRepository.getReservationsOfReader(readerId))
                    .build();
        } catch (Exception ex) {
            logger.catching(ex);
            return ErrorUtils.buildErrorResponce(
                    "Something has gone wrong! Contact our support.", Response.Status.INTERNAL_SERVER_ERROR);
        }

    }

    @POST
    public Response createBorrowedBook(BorrowedBookDTO borrowedBookDTO){
        if (BorrowedBookUtils.isValid(borrowedBookDTO)){
            try{
                borrowedBookDTO.setId(null);
                return Response.ok(BorrowedBookUtils.toREST(borrowedBookRepository.create(BorrowedBookUtils.toDB(borrowedBookDTO)))).build();
            } catch (Exception ex){
                logger.catching(ex);
                return ErrorUtils.buildErrorResponce(
                        "Something has gone wrong! Contact our support.", Response.Status.INTERNAL_SERVER_ERROR);
            }
        } else {
            return CommonUtils.badRequestResponse("Invalid values of input fields. Please check them.");
        }

    }

    @PUT
    public Response updateBorrowedBook(BorrowedBookDTO borrowedBookDTO){
        if (BorrowedBookUtils.isValid(borrowedBookDTO) && borrowedBookDTO.getId() != null){
            var result = borrowedBookRepository.getById(borrowedBookDTO.getId());
            if (result.isEmpty()){
                return CommonUtils.notFoundResponse("The book with id=" + borrowedBookDTO.getId() + " hasn't been found!");
            }
            try{
                borrowedBookRepository.update(BorrowedBookUtils.toDB(borrowedBookDTO));
                return Response.ok("The reservation has been updated successfully!").build();
            } catch (Exception ex){
                logger.catching(ex);
                return ErrorUtils.buildErrorResponce(
                        "Something has gone wrong! Contact our support.", Response.Status.INTERNAL_SERVER_ERROR);
            }
        } else {
            return CommonUtils.badRequestResponse("Invalid values of input fields. Please check them.");
        }

    }

    @DELETE
    public Response deleteBorrowedBook(BorrowedBookDTO borrowedBookDTO){
        if (BorrowedBookUtils.isValid(borrowedBookDTO) && borrowedBookDTO.getId() != null){
            var result = borrowedBookRepository.getById(borrowedBookDTO.getId());
            if (result.isPresent()) {
                try{
                    if (BorrowedBookUtils.compareFields(result.get(), BorrowedBookUtils.toDB(borrowedBookDTO))){
                        borrowedBookRepository.delete(BorrowedBookUtils.toDB(borrowedBookDTO));
                        return Response.ok()
                                .entity("The reservation has been deleted successfully!")
                                .build();
                    } else {
                        return CommonUtils.badRequestResponse("Values of input fields don't match with database fields. Please check them.");
                    }
                } catch (Exception ex){
                    logger.catching(ex);
                    return ErrorUtils.buildErrorResponce(
                            "Something has gone wrong! Contact our support.", Response.Status.INTERNAL_SERVER_ERROR);
                }
            } else {
                return CommonUtils.notFoundResponse("The reservation with id=" + borrowedBookDTO.getId() + " hasn't been found!");
            }

        } else {
            return CommonUtils.badRequestResponse("Invalid values of input fields. Please check them.");
        }
    }

    @DELETE
    @Path("/{id}")
    public Response deleteBorrowedBookById(@PathParam("id") Integer id){
        try{
            var borrowedBookOpt = borrowedBookRepository.getById(id);
            if (borrowedBookOpt.isPresent()){
                borrowedBookRepository.delete(borrowedBookOpt.get());
                return Response.ok()
                        .entity("The reservation has been deleted successfully!")
                        .build();
            } else {
                return CommonUtils.notFoundResponse("The reservation with id=" + id + " hasn't been found!");
            }
        } catch (Exception ex){
            logger.catching(ex);
            return ErrorUtils.buildErrorResponce(
                    "Something has gone wrong! Contact our support.", Response.Status.INTERNAL_SERVER_ERROR);
        }
    }

}
