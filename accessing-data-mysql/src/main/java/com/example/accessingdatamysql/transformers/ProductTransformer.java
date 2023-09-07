package com.example.accessingdatamysql.transformers;

import com.example.accessingdatamysql.dto.ProductDTO;
import com.example.accessingdatamysql.entity.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductTransformer implements Transformer<Product, ProductDTO> {

    public Product fromDTO(ProductDTO productDTO){
        Product product = new Product();
        product.setCategory(productDTO.getCategory());
        product.setCompany(productDTO.getCompany());
        product.setPrice(productDTO.getPrice());
        return product;
    }

    public ProductDTO fromEntity(Product product){
        ProductDTO productDTO = new ProductDTO();
        productDTO.setCategory(product.getCategory());
        productDTO.setCompany(product.getCompany());
        productDTO.setPrice(product.getPrice());
        return productDTO;
    }
}
