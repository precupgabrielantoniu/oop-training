package com.example.accessingdatamysql.service;

import com.example.accessingdatamysql.dto.DisplayUserDTO;
import com.example.accessingdatamysql.dto.ProductDTO;
import com.example.accessingdatamysql.entity.Product;
import com.example.accessingdatamysql.entity.User;
import com.example.accessingdatamysql.errorhandling.NoProductWithIdException;
import com.example.accessingdatamysql.repository.ProductRepository;
import com.example.accessingdatamysql.transformers.DisplayUserTransformer;
import com.example.accessingdatamysql.transformers.ProductTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private DisplayUserTransformer displayUserTransformer;

    @Autowired
    private ProductTransformer productTransformer;

    @Override
    public ProductDTO saveProduct(ProductDTO productDTO) {
        Product product = productTransformer.fromDTO(productDTO);
        Product savedProduct = productRepository.save(product);
        return productTransformer.fromEntity(savedProduct);
    }

    @Override
    public Set<DisplayUserDTO> getBuyers(Integer productId) throws NoProductWithIdException {
        Optional<Product> foundOptionalProduct = productRepository.findById(productId);
        Product foundProduct = foundOptionalProduct.orElseThrow(() -> new NoProductWithIdException("No product found with this id."));
        Set<User> buyers = foundProduct.getBuyers();
        return buyers.stream().map(displayUserTransformer::fromEntity).collect(Collectors.toSet());
    }

    public List<ProductDTO> getProductPageByPrice(Double price, Pageable pageable) {
        List<Product> productList = productRepository.findAllByPrice(price, pageable);
        return productList.stream().map(product -> productTransformer.fromEntity(product)).collect(Collectors.toList());
    }

    public Page<ProductDTO> getProductPageByCategory(String category, Pageable pageable) {
        List<Product> productList = productRepository.findAllByCategory(category, pageable);
        List<ProductDTO> productDTOList = productList.stream().map(product -> productTransformer.fromEntity(product)).collect(Collectors.toList());
        return new PageImpl<>(productDTOList);
    }
}
