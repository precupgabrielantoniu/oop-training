package com.example.accessingdatamysql.transformers;

import com.example.accessingdatamysql.dto.BookDTO;
import com.example.accessingdatamysql.entity.Book;
import org.springframework.stereotype.Component;

@Component
public class BookTransformer implements Transformer<Book, BookDTO> {
    public BookDTO fromEntity(Book book){
        BookDTO bookDTO = new BookDTO();
        bookDTO.setTitle(book.getTitle());
        bookDTO.setOwner(book.getOwner());
        bookDTO.setPageCount(book.getPageCount());
        bookDTO.setPublisher(book.getPublisher());
        return bookDTO;
    }

    public Book fromDTO(BookDTO bookDTO){
        Book book = new Book();
        book.setTitle(bookDTO.getTitle());
        book.setOwner(bookDTO.getOwner());
        book.setPublisher(bookDTO.getPublisher());
        book.setPageCount(bookDTO.getPageCount());
        return book;
    }
}
