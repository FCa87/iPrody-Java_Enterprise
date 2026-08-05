import Components.AppConfig;
import Components.Cart;
import Components.ProductRepository;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {

    public static void main(String[] args){

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

        ProductRepository productRepository = context.getBean(ProductRepository.class);
        productRepository.getAllProducts().forEach(System.out::println);
        Cart cart = context.getBean(Cart.class);
        System.out.print("The first bean: ");
        cart.getProductById(4);
        Cart cart2 = context.getBean(Cart.class);
        System.out.print("The second bean: ");
        cart.getProductById(2);
        System.out.println("Hashcode of the first bean: " + cart.hashCode());
        System.out.println("Hashcode of the second bean: " + cart2.hashCode());

        context.close();
    }

}
