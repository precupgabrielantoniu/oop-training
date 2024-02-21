package com.example.accessingdatamysql.service;

import com.example.accessingdatamysql.dto.CreateBookDTO;
import com.example.accessingdatamysql.dto.DisplayBookDTO;
import com.example.accessingdatamysql.entity.Book;
import com.example.accessingdatamysql.entity.User;
import com.example.accessingdatamysql.errorhandling.NoBookWithIdException;
import com.example.accessingdatamysql.errorhandling.NoUserWithIdException;
import com.example.accessingdatamysql.repository.BookRepository;
import com.example.accessingdatamysql.repository.UserRepository;
import com.example.accessingdatamysql.transformers.CreateBookTransformer;
import com.example.accessingdatamysql.transformers.DisplayBookTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BookServiceImpl implements BookService{

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CreateBookTransformer createBookTransformer;

    @Autowired
    private DisplayBookTransformer displayBookTransformer;

    @Override
    public DisplayBookDTO saveBook(CreateBookDTO createBookDTO, Integer userId) throws NoUserWithIdException {
        Optional<User> foundOptionalUser = userRepository.findById(userId);
        User foundUser = foundOptionalUser.orElseThrow(() -> new NoUserWithIdException("No user found with this id."));
        Book book = createBookTransformer.fromDTO(createBookDTO);
        book.setOwner(foundUser);
        return displayBookTransformer.fromEntity(bookRepository.save(book));
    }

    @Override
    public DisplayBookDTO deleteBook(Integer bookId) throws NoBookWithIdException {
        Optional<Book> foundOptionalBook = bookRepository.findById(bookId);
        Book foundBook = foundOptionalBook.orElseThrow(() -> new NoBookWithIdException("No book found with this id."));
        bookRepository.delete(foundBook);
        return displayBookTransformer.fromEntity(foundBook);
    }
}
