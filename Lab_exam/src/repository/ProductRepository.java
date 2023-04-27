package repository;

import exceptions.ProductNotFoundException;
import model.Product;

import java.util.HashMap;
import java.util.Map;

public class ProductRepository {
    private Map<String, Product> products = new HashMap<>();
    public Product create(Product product){
        String name = product.getName().toLowerCase();
        products.put(name, product);
        return product;
    }

    public boolean delete(Product product) throws ProductNotFoundException {
        String name = product.getName().toLowerCase();
        Product p = products.remove(name);
        if (p == null){
            throw new ProductNotFoundException("Product with name \"" + name + "\" not found");
        }
        return true;
    }

    public Map<String, Product> getProducts(){
        return (Map<String, Product>)((HashMap<String, Product>)products).clone();
    }
}
