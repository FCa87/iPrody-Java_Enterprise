
package iprody35.service;


import com.sun.xml.ws.transport.http.servlet.WSServletContextListener;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

@WebListener
public class AppInitializer implements ServletContextListener{
    
    private final WSServletContextListener listener = new WSServletContextListener();
    
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException ex) {
            throw new RuntimeException("Database driver initialisation error!");
        }
        listener.contextInitialized(sce);
    }
    
    @Override 
    public void contextDestroyed(ServletContextEvent sce){
        listener.contextDestroyed(sce);
    }
    
}
