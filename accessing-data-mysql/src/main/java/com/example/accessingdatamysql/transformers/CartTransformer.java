package com.example.accessingdatamysql.transformers;

import com.example.accessingdatamysql.dto.CartDTO;
import com.example.accessingdatamysql.dto.DisplayUserDTO;
import com.example.accessingdatamysql.dto.ProductDTO;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class CartTransformer implements CompositeObjectTransformer<CartDTO> {

    @Override
    public CartDTO transform(Object... args) throws ClassCastException {
        DisplayUserDTO userDTO = (DisplayUserDTO) args[0];
        Set<ProductDTO> productDTOs = (Set<ProductDTO>) args[1];
        CartDTO cartDTO = new CartDTO();
        cartDTO.setBuyerName(userDTO.getName());
        cartDTO.setProducts(productDTOs);
        return cartDTO;
    }
}
