package com.example.accessingdatamysql.controller;

import com.example.accessingdatamysql.dto.BookDTO;
import com.example.accessingdatamysql.errorhandling.NoBookWithIdException;
import com.example.accessingdatamysql.errorhandling.NoUserWithIdException;
import com.example.accessingdatamysql.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController // This means that this class is a Controller
@RequestMapping(path="/demo/book") // This means URLs start with /demo (after Application path)
@CrossOrigin(origins = "*",  allowedHeaders="*")
public class BookController {
    @Autowired
    BookService bookService;

    @PostMapping(path="/add/request-body/{owner_id}")
    public @ResponseBody BookDTO addNewBookWithRequestBody(@PathVariable(value = "owner_id") Integer ownerId, @RequestBody BookDTO bookDTO) {
        try {
            return bookService.saveBook(bookDTO, ownerId);
        } catch (NoUserWithIdException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
        }
    }

    @DeleteMapping(path="/delete/{book_id}")
    public @ResponseBody BookDTO deleteBook(@PathVariable(value = "book_id") Integer bookId) {
        try {
            return bookService.deleteBook(bookId);
        } catch (NoBookWithIdException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
        }
    }

}
