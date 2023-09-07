package com.example.accessingdatamysql.service;

import com.example.accessingdatamysql.dto.CartDTO;
import com.example.accessingdatamysql.dto.DisplayUserDTO;
import com.example.accessingdatamysql.dto.ProductDTO;
import com.example.accessingdatamysql.entity.Product;
import com.example.accessingdatamysql.entity.User;
import com.example.accessingdatamysql.errorhandling.NoProductWithIdException;
import com.example.accessingdatamysql.errorhandling.NoUserWithIdException;
import com.example.accessingdatamysql.repository.ProductRepository;
import com.example.accessingdatamysql.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CartServiceImpl implements CartService{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductRepository productRepository;

    @Override
    public CartDTO addProductToUser(Integer userId, Integer productId) throws NoUserWithIdException, NoProductWithIdException{
        Optional<User> foundOptionalUser = userRepository.findById(userId);
        User foundUser = foundOptionalUser.orElseThrow(() -> new NoUserWithIdException("No user found with this id."));
        Optional<Product> foundOptionalProduct = productRepository.findById(productId);
        Product foundProduct = foundOptionalProduct.orElseThrow(() -> new NoProductWithIdException("No product found with this id."));
        foundUser.getProducts().add(foundProduct);
        foundProduct.getBuyers().add(foundUser);
        User user = userRepository.save(foundUser);
        productRepository.save(foundProduct);
        Set<Product> products = user.getProducts();
        Set<ProductDTO> productDTOs = products.stream().map(ProductDTO::fromEntity).collect(Collectors.toSet());
        return CartDTO.toCartDTO(DisplayUserDTO.fromEntity(user), productDTOs);
    }
}
