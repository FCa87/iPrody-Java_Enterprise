package iPrody.controller;


import iPrody.dto.ReaderDTO;
import iPrody.repo.ReaderRepository;
import iPrody.utils.ErrorUtils;
import iPrody.utils.ReaderUtils;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Path("/readers")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ReaderController {

    private static Logger logger = LogManager.getLogger();

    private final ReaderRepository readerRepository = new ReaderRepository();

    @GET
    public Response getAllReaders(){
        try{
            return Response.ok(readerRepository.getAll().stream()
                           .map(ReaderUtils::toREST)
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
    public Response getReaderById(@PathParam("id") Integer id){
        try{
            var result = readerRepository.getById(id);
            if (result.isPresent()){
                return Response.ok(ReaderUtils.toREST(result.get())).build();
            } else {
                return Response.status(Response.Status.NOT_FOUND)
                        .entity("The reader with id=" + id + " hasn't been found!")
                        .build();
            }
        } catch (Exception ex){
            logger.catching(ex);
            return ErrorUtils.buildErrorResponce(
                    "Something has gone wrong! Contact our support.", Response.Status.INTERNAL_SERVER_ERROR);
        }
    }

    @POST
    public Response createReader(ReaderDTO readerDTO){
        if (ReaderUtils.isValid(readerDTO)) {
            var reader = ReaderUtils.toDB(readerDTO);
            var check = readerRepository.checkForUnique(reader);
            if (check != null){
                return Response.status(Response.Status.BAD_REQUEST)
                        .entity(check)
                        .build();
            }
            try {
                return Response.ok(ReaderUtils.toREST(readerRepository.create(reader))).build();
            }catch (Exception ex){
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
    public Response updateReader(ReaderDTO readerDTO){
        if (ReaderUtils.isValid(readerDTO) && readerDTO.getId() != null) {
            var result = readerRepository.getById(readerDTO.getId());
            if (result.isPresent()){
                try {
                    readerRepository.update(ReaderUtils.toDB(readerDTO));
                    return Response.ok("The reader has been updated successfully!").build();
                }catch (Exception ex){
                    logger.catching(ex);
                    return ErrorUtils.buildErrorResponce(
                            "Something has gone wrong! Contact our support.", Response.Status.INTERNAL_SERVER_ERROR);
                }
            } else {
                return Response.status(Response.Status.NOT_FOUND)
                        .entity("The reader with id=" + readerDTO.getId() + " hasn't been found!")
                        .build();
            }
        } else {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Invalid values of input fields. Please check them.")
                    .build();
        }
    }

    @DELETE
    public Response deleteReader(ReaderDTO readerDTO){
        if (ReaderUtils.isValid(readerDTO) && readerDTO.getId() != null) {
            var result = readerRepository.getById(readerDTO.getId());
            if (result.isPresent()) {
                try {
                    if (ReaderUtils.compareFields(result.get(), ReaderUtils.toDB(readerDTO))){
                        readerRepository.delete(ReaderUtils.toDB(readerDTO));
                        return Response.ok()
                                .entity("The reader has been deleted successfully!")
                                .build();
                    } else {
                        return Response.status(Response.Status.BAD_REQUEST)
                                .entity("Values of input fields don't match with database fields. Please check them.")
                                .build();
                    }
                } catch (Exception ex) {
                    logger.catching(ex);
                    return ErrorUtils.buildErrorResponce(
                            "Something has gone wrong! Contact our support", Response.Status.INTERNAL_SERVER_ERROR);
                }
            } else {
                return Response.status(Response.Status.NOT_FOUND)
                        .entity("The reader with id=" + readerDTO.getId() + " hasn't been found!")
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
    public Response deleteReaderById(@PathParam("id") Integer id){
        try {
            var reader = readerRepository.getById(id);
            if (reader.isPresent()) {
                readerRepository.delete(reader.get());
                return Response.ok()
                        .entity("The reader has been deleted successfully!")
                        .build();
            } else {
                return Response.status(Response.Status.NOT_FOUND)
                        .entity("The reader with id=" + id + " hasn't been found!")
                        .build();
            }
        } catch (Exception ex) {
            logger.catching(ex);
            return ErrorUtils.buildErrorResponce(
                    "Something has gone wrong! Contact our support.", Response.Status.INTERNAL_SERVER_ERROR);
        }

    }

}
