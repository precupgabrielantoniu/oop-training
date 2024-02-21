package com.example.accessingdatamysql.transformers;


import com.example.accessingdatamysql.dto.DisplayBookDTO;
import com.example.accessingdatamysql.entity.Book;
import org.springframework.stereotype.Component;

@Component
public class DisplayBookTransformer implements Transformer<Book, DisplayBookDTO> {
    public DisplayBookDTO fromEntity(Book book){
        DisplayBookDTO displayBookDTO = new DisplayBookDTO();
        displayBookDTO.setTitle(book.getTitle());
        displayBookDTO.setOwnerName(book.getOwner().getName());
        displayBookDTO.setPageCount(book.getPageCount());
        displayBookDTO.setPublisher(book.getPublisher());
        return displayBookDTO;
    }

    public Book fromDTO(DisplayBookDTO displayBookDTO){
        Book book = new Book();
        book.setTitle(displayBookDTO.getTitle());
        book.setPublisher(displayBookDTO.getPublisher());
        book.setPageCount(displayBookDTO.getPageCount());
        return book;
    }
}
