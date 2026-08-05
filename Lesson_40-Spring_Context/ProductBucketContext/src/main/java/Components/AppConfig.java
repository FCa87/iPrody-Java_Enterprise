package Components;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class AppConfig {

    @Bean(name = "productRepository")
    public ProductRepository getProductRepository(){
        return new ProductRepository();
    }

    @Bean(name = "cart")
    @Scope("prototype")
    public Cart getCart(){
        return new Cart();
    }

}
