package com.example.accessingdatamysql.controller;

import com.example.accessingdatamysql.dto.CartDTO;
import com.example.accessingdatamysql.errorhandling.NoProductWithIdException;
import com.example.accessingdatamysql.errorhandling.NoUserWithIdException;
import com.example.accessingdatamysql.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.io.FileNotFoundException;

@RestController // This means that this class is a Controller
@RequestMapping(path="/demo/cart") // This means URLs start with /demo (after Application path)
@CrossOrigin(origins = "*",  allowedHeaders="*")
public class CartController {

    @Autowired
    private CartService cartService;

    @PostMapping(path="/add_product/{user_id}/{product_id}")
    public @ResponseBody CartDTO addProductToUser(@PathVariable(value = "user_id") Integer userId, @PathVariable(value = "product_id") Integer productId) throws FileNotFoundException {
        try {
            return cartService.addProductToUser(userId, productId);
        } catch(NoProductWithIdException | NoUserWithIdException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
        } catch (ClassCastException e){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), e);
        }
    }
}
