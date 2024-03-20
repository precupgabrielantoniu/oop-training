package com.example.accessingdatamysql.service;

import com.example.accessingdatamysql.dto.DisplayUserDTO;
import com.example.accessingdatamysql.dto.ProductDTO;
import com.example.accessingdatamysql.entity.Product;
import com.example.accessingdatamysql.entity.User;
import com.example.accessingdatamysql.errorhandling.NoProductWithIdException;
import com.example.accessingdatamysql.repository.ProductRepository;
import com.example.accessingdatamysql.transformers.DisplayUserTransformer;
import com.example.accessingdatamysql.transformers.ProductTransformer;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Optional;
import java.util.Set;

import static java.util.Collections.emptySet;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ProductServiceImplTest {

    final static Integer PRODUCT_ID = 3;

    final static Integer INVALID_PRODUCT_ID = 255;

    final static String FAILED_CONNECTION = "Connection interrupted.";

    final static String NO_PRODUCT_MESSAGE = "No product found with id ";

    final PrintStream standardOut = System.out;

    final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @Mock
    ProductRepository productRepository;

    @Mock
    ProductTransformer productTransformer;

    @Mock
    DisplayUserTransformer displayUserTransformer;

    @InjectMocks
    ProductServiceImpl productServiceUnderTest = new ProductServiceImpl();

    @Test
    @DisplayName("Test saveProduct returns null when failed connection")
    public void testSaveProductFailedConnection(){
        ProductDTO productDTO = new ProductDTO();
        Product product = new Product();
        System.setOut(new PrintStream(outputStreamCaptor));
        when(productTransformer.fromDTO(productDTO)).thenReturn(product);
        when(productRepository.save(product)).thenThrow(RuntimeException.class);

        ProductDTO actualProductDTO = productServiceUnderTest.saveProduct(productDTO);
        assertNull(actualProductDTO);
        assertEquals(FAILED_CONNECTION, outputStreamCaptor.toString().trim());
        System.setOut(standardOut);
    }

    @Test
    @DisplayName("Test saveProduct saves the products successfully")
    public void testSaveProductSuccess(){
        ProductDTO productDTO = new ProductDTO();
        productDTO.setPrice(34.);
        productDTO.setCategory("meat");
        productDTO.setCompany("Napolact");
        ProductDTO expectedProductDTO = new ProductDTO();
        expectedProductDTO.setPrice(34.);
        expectedProductDTO.setCategory("meat");
        expectedProductDTO.setCompany("Napolact");
        Product product = new Product();
        product.setPrice(34.);
        product.setCategory("meat");
        product.setCompany("Napolact");
        Product savedProduct = new Product();
        savedProduct.setPrice(34.);
        savedProduct.setCategory("meat");
        savedProduct.setCompany("Napolact");
        savedProduct.setId(1);
        when(productTransformer.fromDTO(productDTO)).thenReturn(product);
        when(productRepository.save(product)).thenReturn(savedProduct);
        when(productTransformer.fromEntity(savedProduct)).thenReturn(expectedProductDTO);
        ProductDTO actualProductDTO = productServiceUnderTest.saveProduct(productDTO);
        assertEquals(expectedProductDTO, actualProductDTO);
    }

    @Test
    @DisplayName("Test getBuyers for product with associated buyers returns them")
    public void testGetBuyersSuccess() throws NoProductWithIdException {
        DisplayUserDTO displayUserDTO1 = new DisplayUserDTO();
        displayUserDTO1.setId(2);
        displayUserDTO1.setEmail("adresa1");
        displayUserDTO1.setName("Marius");
        User user = User.builder()
                .id(2)
                .name("Marius")
                .email("adresa1").build();
        DisplayUserDTO displayUserDTO2 = new DisplayUserDTO();
        displayUserDTO2.setId(5);
        User user2 = User.builder().id(5).build();
        Set<DisplayUserDTO> expectedBuyers = Set.of(displayUserDTO1, displayUserDTO2);

        Product foundProduct = new Product();
        foundProduct.setId(PRODUCT_ID);
        foundProduct.setBuyers(Set.of(user, user2));
        when(productRepository.findById(PRODUCT_ID)).thenReturn(Optional.of(foundProduct));
        when(displayUserTransformer.fromEntity(user)).thenReturn(displayUserDTO1);
        when(displayUserTransformer.fromEntity(user2)).thenReturn(displayUserDTO2);
        Set<DisplayUserDTO> actualBuyers = productServiceUnderTest.getBuyers(PRODUCT_ID);
        assertEquals(expectedBuyers, actualBuyers);
        verify(displayUserTransformer, times(2)).fromEntity(any());
    }

    @Test
    @DisplayName("Test getBuyers for invalid product id throws an exception")
    public void testGetBuyersThrowError(){
        Optional<Product> emptyProduct = Optional.empty();
        when(productRepository.findById(INVALID_PRODUCT_ID)).thenReturn(emptyProduct);

        Exception exception = assertThrows(NoProductWithIdException.class, () -> {
            productServiceUnderTest.getBuyers(INVALID_PRODUCT_ID);
        });
        assertEquals(NO_PRODUCT_MESSAGE + INVALID_PRODUCT_ID, exception.getMessage());
        //verify(displayUserTransformer, times(0)).fromEntity(any());
        verifyNoInteractions(displayUserTransformer);
    }

    @Test
    @DisplayName("Test getBuyers for product with no associated buyers returns empty set")
    public void testGetBuyersEmptySet() throws NoProductWithIdException {
        Product foundProduct = new Product();
        foundProduct.setId(3);
        Optional<Product> optionalProduct = Optional.of(foundProduct);
        foundProduct.setBuyers(emptySet());

        when(productRepository.findById(3)).thenReturn(optionalProduct);
        Set<DisplayUserDTO> actualBuyers = productServiceUnderTest.getBuyers(3);

        assertEquals(emptySet(), actualBuyers);
    }
}