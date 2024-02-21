package com.example.accessingdatamysql.transformers;

import com.example.accessingdatamysql.dto.CreateBookDTO;
import com.example.accessingdatamysql.entity.Book;
import org.springframework.stereotype.Component;

@Component
public class CreateBookTransformer implements Transformer<Book, CreateBookDTO> {
    public CreateBookDTO fromEntity(Book book){
        CreateBookDTO createBookDTO = new CreateBookDTO();
        createBookDTO.setTitle(book.getTitle());
        createBookDTO.setPageCount(book.getPageCount());
        createBookDTO.setPublisher(book.getPublisher());
        return createBookDTO;
    }

    public Book fromDTO(CreateBookDTO createBookDTO){
        Book book = new Book();
        book.setTitle(createBookDTO.getTitle());
        book.setPublisher(createBookDTO.getPublisher());
        book.setPageCount(createBookDTO.getPageCount());
        return book;
    }
}
