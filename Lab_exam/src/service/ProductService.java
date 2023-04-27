package service;

import exceptions.ProductNotFoundException;
import exceptions.ValidationFailedException;
import model.Product;
import repository.ProductRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ProductService implements AppService<Product> {
    private ProductRepository productRepository;
    public ProductService(ProductRepository pr){
        productRepository = pr;
    }
    public Product create(Product product){
        String name = product.getName();
        Double price = product.getPrice();
        if (name == null || price == null) {
            throw new ValidationFailedException("Invalid product");
        }
        return productRepository.create(product);
    }

    public boolean delete(Product product){
        try{
            productRepository.delete(product);
        } catch (ProductNotFoundException pnfe){
            return false;
        }
        return true;
    }

    public List<Product> search(String s){
        List<Product> productList = new ArrayList<>();
        for (Map.Entry<String, Product> e : productRepository.getProducts().entrySet()){
            if (e.getKey().startsWith(s.toLowerCase())){
                productList.add(e.getValue());
            }
        }
        return productList;
    }
}
