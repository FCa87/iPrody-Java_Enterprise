package Components;

import org.springframework.beans.factory.annotation.Autowired;

public class Cart {

    ProductRepository productRepository;

    public Cart() {
    }

    public ProductRepository getProductRepository() {
        return productRepository;
    }

    @Autowired
    public void setProductRepository(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public void getProductById(Integer id){
        var result = productRepository.getProductById(id);
        if (result == null){
            System.out.println("Product hasn't been found!");
        } else{
            System.out.println(result);
        }
    }
}
