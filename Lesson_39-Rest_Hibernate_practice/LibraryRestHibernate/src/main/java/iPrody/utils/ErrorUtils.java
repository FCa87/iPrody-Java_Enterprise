package iPrody.utils;

import iPrody.dto.ErrorDTO;
import jakarta.ws.rs.core.Response;

import java.time.LocalDateTime;

public class ErrorUtils {

    public static Response buildErrorResponce(String message, Response.Status status){
        return Response.status(status)
                       .entity(new ErrorDTO(message, LocalDateTime.now()))
                       .build();
    }

}
