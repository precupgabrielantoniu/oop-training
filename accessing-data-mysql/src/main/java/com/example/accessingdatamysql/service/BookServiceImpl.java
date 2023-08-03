package com.example.accessingdatamysql.service;

import com.example.accessingdatamysql.dto.BookDTO;
import com.example.accessingdatamysql.entity.Book;
import com.example.accessingdatamysql.entity.User;
import com.example.accessingdatamysql.errorhandling.NoUserWithIdException;
import com.example.accessingdatamysql.repository.BookRepository;
import com.example.accessingdatamysql.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BookServiceImpl implements BookService{

    @Autowired
    BookRepository bookRepository;

    @Autowired
    UserRepository userRepository;

    public BookDTO saveBook(BookDTO bookDTO, Integer userId) throws NoUserWithIdException {
        Optional<User> foundOptionalUser = userRepository.findById(userId);
        User foundUser = foundOptionalUser.orElseThrow(() -> new NoUserWithIdException("No user found with this id."));
        bookDTO.setOwner(foundUser);
        Book book = BookDTO.fromDTO(bookDTO);
        return BookDTO.fromEntity(bookRepository.save(book));
    }
}
