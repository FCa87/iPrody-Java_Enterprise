package iPrody.config;


import iPrody.controller.BookController;
import iPrody.controller.BorrowedBookController;
import iPrody.controller.ReaderController;
import jakarta.ws.rs.ApplicationPath;
import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.server.ResourceConfig;

@ApplicationPath("/api")
public class AppConfig extends ResourceConfig {

    public AppConfig() {
        register(JacksonFeature.class);
        register(BookController.class);
        register(ReaderController.class);
        register(BorrowedBookController.class);
    }

}
