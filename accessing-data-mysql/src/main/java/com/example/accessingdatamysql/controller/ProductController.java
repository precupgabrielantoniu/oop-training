package com.example.accessingdatamysql.controller;

import com.example.accessingdatamysql.dto.DisplayUserDTO;
import com.example.accessingdatamysql.dto.ProductDTO;
import com.example.accessingdatamysql.errorhandling.NoProductWithIdException;
import com.example.accessingdatamysql.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Set;

@RestController // This means that this class is a Controller
@RequestMapping(path="/demo/product") // This means URLs start with /demo (after Application path)
@CrossOrigin(origins = "*",  allowedHeaders="*")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping(path="/add")
    public @ResponseBody ProductDTO addProduct(@RequestBody ProductDTO productDTO){
        return productService.saveProduct(productDTO);
    }

    @GetMapping(path="/buyers/{product_id}")
    public @ResponseBody Set<DisplayUserDTO> getBuyersForProduct(@PathVariable("product_id") Integer productId) {
        try{
            return productService.getBuyers(productId);
        } catch (NoProductWithIdException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
        }
    }

}
