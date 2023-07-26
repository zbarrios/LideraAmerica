package lideraamerica.pruebabackend.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;
import lideraamerica.pruebabackend.exception.AuthorNotFoundException;
import lideraamerica.pruebabackend.exception.BookNotFoundException;
import lideraamerica.pruebabackend.model.Author;
import lideraamerica.pruebabackend.model.Book;
import lideraamerica.pruebabackend.model.BookUpdateCreateRequest;
import lideraamerica.pruebabackend.repo.AuthorRepository;
import lideraamerica.pruebabackend.repo.BookRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class BookServiceTest {

  @Mock
  AuthorService authorService;
  @Mock
  BookRepository bookRepository;
  @InjectMocks
  BookService bookService;

  public static final String RANDOM_BOOK_TITLE = "randomBookTitle";
  public static final Integer RANDOM_BOOK_ID = 1;

  @Test
  void getBookByTitleSuccessfullyCase() throws BookNotFoundException {
    //Arrange
    Book expectedBook = new Book();
    when(bookRepository.findByTitle(any())).thenReturn(Optional.of(expectedBook));
    // Act & Assert
    Assertions.assertEquals(expectedBook, bookService.getByTitle(any()));
  }

  @Test
  void getBookByTitleShouldThrowBookNotFoundException() throws BookNotFoundException {
    //Arrange
    when(bookRepository.findByTitle(any())).thenReturn(Optional.empty());
    // Act & Assert
    Assertions.assertThrows(BookNotFoundException.class, ()-> bookService.getByTitle(any()));
  }

  @Test
  void addBookBookSuccessfullyCase() throws AuthorNotFoundException {
    //Arrange
    BookUpdateCreateRequest bookCreate = new BookUpdateCreateRequest(RANDOM_BOOK_TITLE,AuthorServiceTest.RANDOM_AUTHOR_NAME);
    Book expectedBook = new Book();
    when(bookRepository.save(any())).thenReturn(expectedBook);
    // Act & Assert
    Assertions.assertEquals(expectedBook, bookService.addBook(bookCreate));
  }

  @Test
  void updateBookBookSuccessfullyCase() throws AuthorNotFoundException, BookNotFoundException {
    //Arrange
    BookUpdateCreateRequest bookUpdate = new BookUpdateCreateRequest(RANDOM_BOOK_TITLE,"");
    Book expectedBook = new Book();
    when(bookRepository.findById(any())).thenReturn(Optional.of(expectedBook));
    when(bookRepository.save(any())).thenReturn(expectedBook);
    // Act & Assert
    Assertions.assertEquals(expectedBook, bookService.updateBook(RANDOM_BOOK_ID,bookUpdate));
  }

  @Test
  void updateBookBookWithAuthorNameSuccessfullyCase() throws AuthorNotFoundException, BookNotFoundException {
    //Arrange
    BookUpdateCreateRequest bookUpdate = new BookUpdateCreateRequest(RANDOM_BOOK_TITLE,AuthorServiceTest.RANDOM_AUTHOR_NAME);
    Book expectedBook = new Book();
    when(bookRepository.findById(any())).thenReturn(Optional.of(expectedBook));
    when(bookRepository.save(any())).thenReturn(expectedBook);
    // Act & Assert
    Assertions.assertEquals(expectedBook, bookService.updateBook(RANDOM_BOOK_ID,bookUpdate));
  }

  @Test
  void deleteBookByIdSuccessfullyCase() throws BookNotFoundException {
    //Arrange
    // Act
    bookService.deleteById(RANDOM_BOOK_ID);
    // Assert
    verify(bookRepository).deleteById(RANDOM_BOOK_ID);
  }




}
