package com.example.accessingdatamysql.service;

import com.example.accessingdatamysql.dto.CartDTO;
import com.example.accessingdatamysql.dto.ProductDTO;
import com.example.accessingdatamysql.entity.Product;
import com.example.accessingdatamysql.entity.User;
import com.example.accessingdatamysql.errorhandling.NoProductWithIdException;
import com.example.accessingdatamysql.errorhandling.NoUserWithIdException;
import com.example.accessingdatamysql.repository.ProductRepository;
import com.example.accessingdatamysql.repository.UserRepository;
import com.example.accessingdatamysql.transformers.CartTransformer;
import com.example.accessingdatamysql.transformers.DisplayUserTransformer;
import com.example.accessingdatamysql.transformers.ProductTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private DisplayUserTransformer displayUserTransformer;

    @Autowired
    private CartTransformer cartTransformer;

    @Autowired
    private ProductTransformer productTransformer;

    @Override
    // If we use spring framework, rollback happens only when we have a runtime exception.
    // For checked exceptions, they must be mentioned.
    // For runtime exceptions, there is a rollback, unless we mention that they should be ignored for the rollback.
    //@Transactional(rollbackFor = FileNotFoundException.class)
    //@Transactional(noRollbackFor = RuntimeException.class)
    @Transactional
    public CartDTO addProductToUser(Integer userId, Integer productId) throws NoUserWithIdException, NoProductWithIdException {
        Optional<User> foundOptionalUser = userRepository.findById(userId);
        User foundUser = foundOptionalUser.orElseThrow(() -> new NoUserWithIdException("No user found with this id."));
        Optional<Product> foundOptionalProduct = productRepository.findById(productId);
        Product foundProduct = foundOptionalProduct.orElseThrow(() -> new NoProductWithIdException("No product found with this id."));
        foundUser.getProducts().add(foundProduct);
        User user = userRepository.save(foundUser);
        Set<Product> products = user.getProducts();
        Set<ProductDTO> productDTOs = products.stream().map(productTransformer::fromEntity).collect(Collectors.toSet());
        return cartTransformer.transform(displayUserTransformer.fromEntity(user), productDTOs);
    }
}
