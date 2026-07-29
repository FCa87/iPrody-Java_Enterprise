package iPrody.utils;

import jakarta.ws.rs.core.Response;

public class CommonUtils {

    public static Response badRequestResponse(String message){
        return Response.status(Response.Status.BAD_REQUEST)
                .entity(message)
                .build();
    }

    public static Response notFoundResponse(String message){
        return Response.status(Response.Status.NOT_FOUND)
                .entity(message)
                .build();
    }

}
