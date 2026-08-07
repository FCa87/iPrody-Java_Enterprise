package Components;

import jakarta.annotation.PostConstruct;

import java.util.ArrayList;
import java.util.List;

public class ProductRepository {

    private List<Product> products;

    public ProductRepository() {
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    @PostConstruct
    private void initProductRepository()
    {
        products = List.of(
        new Product(1, "Onion", 5),
        new Product(2, "Cheese", 15),
        new Product(3, "Meet", 50),
        new Product(4, "Milk", 10),
        new Product(5, "Pineapple", 100));
        System.out.println("PostConstruct method of ProductRepository has finished!");
    }

    public List<Product> getAllProducts(){
        return products.stream().toList();
    }

    public Product getProductById(Integer id){
        if (id == null){
            System.out.println("Id is null! Please enter number");
            return null;
        }
        if (id <= 0 || id > products.size()){
            System.out.println("Id must be above 0 and no more than " + products.size());
            return null;
        }
        for (Product product : products) {
            if (product.getId().compareTo(id) == 0) {
                return product;
            }
        }
        return null;
    }
}
