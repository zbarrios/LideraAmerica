package lideraamerica.pruebabackend.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Optional;
import lideraamerica.pruebabackend.exception.AuthorNotFoundException;
import lideraamerica.pruebabackend.exception.BookNotFoundException;
import lideraamerica.pruebabackend.model.Author;
import lideraamerica.pruebabackend.model.AuthorUpdateCreateRequest;
import lideraamerica.pruebabackend.model.Book;
import lideraamerica.pruebabackend.repo.AuthorRepository;
import lideraamerica.pruebabackend.repo.BookRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class AuthorServiceTest {

  @Mock
  AuthorRepository authorRepository;
  @Mock
  BookRepository bookRepository;
  @InjectMocks
  AuthorService authorService;

  public static final String RANDOM_AUTHOR_NAME = "randomAuthorName";
  public static final Integer RANDOM_AUTHOR_ID = 1;


  @Test
  void getAuthorByNameSuccessfullyCase() throws AuthorNotFoundException {
    //Arrange
    Author author = new Author();
    when(authorRepository.findByAuthorName(any())).thenReturn(Optional.of(author));

    // Act & Assert
    Assertions.assertEquals(author, authorService.getByName(any()));
  }

  @Test
  void getPhoneByNameShouldThrowAuthorNotFoundException() {
    //Arrange
    when(authorRepository.findByAuthorName(any())).thenReturn(Optional.empty());

    // Act & Assert
    Assertions.assertThrows(AuthorNotFoundException.class, ()-> authorService.getByName(any()));
  }

  @Test
  void createNewAuthorSuccessfullyCase() {
    //Arrange
    AuthorUpdateCreateRequest authorToCreate = new AuthorUpdateCreateRequest(RANDOM_AUTHOR_NAME,new ArrayList<>());
    Author expectedAuthor = Author.builder().authorName(RANDOM_AUTHOR_NAME).build();
    // Act
    when(authorRepository.save(any())).thenReturn(expectedAuthor);
    Author actualAuthor = authorService.addAuthor(authorToCreate);
    // Assert
    Assertions.assertEquals(expectedAuthor,actualAuthor);
  }

  @Test
  void updateAuthorSuccessfullyCase() throws AuthorNotFoundException {
    //Arrange
    AuthorUpdateCreateRequest authorToCreate = new AuthorUpdateCreateRequest(RANDOM_AUTHOR_NAME,new ArrayList<>());
    Author expectedAuthor = Author.builder().authorName(RANDOM_AUTHOR_NAME).build();
    when(authorRepository.findById(any())).thenReturn(Optional.of(expectedAuthor));
    when(authorRepository.save(any())).thenReturn(expectedAuthor);
    // Act
    Author actualAuthor = authorService.updateAuthor(RANDOM_AUTHOR_ID,authorToCreate);
    // Assert
    Assertions.assertEquals(expectedAuthor,actualAuthor);
  }

  @Test
  void updateBooksAuthorSuccessfullyCase() throws BookNotFoundException {
    //Arrange
    Author authorUpdate = Author.builder().authorName(RANDOM_AUTHOR_NAME).build();
    Book existingBook = new Book();
    when(bookRepository.findById(any())).thenReturn(Optional.of(existingBook));
    when(bookRepository.save(any())).thenReturn(existingBook);
    // Act
    Book actualBook = authorService.updateBooksAuthor(any(),authorUpdate);
    // Assert
    Assertions.assertEquals(existingBook,actualBook);
  }

  @Test
  void updateBooksAuthorShouldThrowBookNotFoundException() throws BookNotFoundException {
    //Arrange
    Author authorUpdate = Author.builder().authorName(RANDOM_AUTHOR_NAME).build();
    when(bookRepository.findById(any())).thenReturn(Optional.empty());

    // Act & Assert
    Assertions.assertThrows(BookNotFoundException.class, ()-> authorService.updateBooksAuthor(any(),authorUpdate));
  }

  @Test
  void deleteAuthorByIdSuccessfullyCase() throws BookNotFoundException {
    //Arrange
    // Act
    authorService.deleteById(RANDOM_AUTHOR_ID);
    // Assert
    verify(authorRepository).deleteById(RANDOM_AUTHOR_ID);
  }

}
