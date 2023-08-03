package com.example.accessingdatamysql.dto;

import com.example.accessingdatamysql.entity.Address;
import com.example.accessingdatamysql.entity.Book;
import com.example.accessingdatamysql.entity.User;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class BookDTO implements Serializable {
    @JsonProperty("title")
    private String title;

    @JsonProperty("publisher")
    private String publisher;

    @JsonProperty("page_count")
    private Integer pageCount;

    private User owner;

    public void setOwner(User owner){
        this.owner = owner;
    }

    public static BookDTO fromEntity(Book book){
        BookDTO bookDTO = new BookDTO();
        bookDTO.title = book.getTitle();
        bookDTO.owner = book.getOwner();
        bookDTO.pageCount = book.getPageCount();
        bookDTO.publisher = book.getPublisher();
        return bookDTO;
    }

    public static Book fromDTO(BookDTO bookDTO){
        Book book = new Book();
        book.setTitle(bookDTO.title);
        book.setOwner(bookDTO.owner);
        book.setPublisher(bookDTO.publisher);
        book.setPageCount(bookDTO.pageCount);
        return book;
    }
}
