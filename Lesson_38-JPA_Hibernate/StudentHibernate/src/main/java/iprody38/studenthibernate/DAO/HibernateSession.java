
package iprody38.studenthibernate.DAO;

import iprody38.studenthibernate.Student;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


public class HibernateSession {
    
    private static final SessionFactory SESSIONFACTORY;
    
    static{
        Configuration configuration = new Configuration();
        configuration.setProperty("hibernate.connection.driver_class", "org.postgresql.Driver");
        configuration.setProperty("hibernate.connection.url", "jdbc:postgresql://localhost:5432/MyDB");
        configuration.setProperty("hibernate.connection.username", "postgres");
        configuration.setProperty("hibernate.connection.password", "admin");
        configuration.setProperty("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
        configuration.setProperty("hibernate.show_sql", "true");
        configuration.setProperty("hibernate.format_sql", "true");
        configuration.setProperty("hibernate.hbm2ddl.auto", "validate");
        
        configuration.addAnnotatedClass(Student.class);
        
        SESSIONFACTORY = configuration.buildSessionFactory();
    }
    
    public static SessionFactory getSESSIONFACTORY() {
        return SESSIONFACTORY;
    }
    
}
