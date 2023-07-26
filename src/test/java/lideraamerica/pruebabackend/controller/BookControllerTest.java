package lideraamerica.pruebabackend.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import lideraamerica.pruebabackend.exception.AuthorNotFoundException;
import lideraamerica.pruebabackend.exception.BookNotFoundException;
import lideraamerica.pruebabackend.model.Book;
import lideraamerica.pruebabackend.model.BookUpdateCreateRequest;
import lideraamerica.pruebabackend.service.AuthorServiceTest;
import lideraamerica.pruebabackend.service.BookService;
import lideraamerica.pruebabackend.service.BookServiceTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class BookControllerTest {

  @Mock
  BookService bookService;

  @InjectMocks
  BookController bookController;

  @Test
  void getByTitleSuccessfullyCase() throws BookNotFoundException {
    // Arrange
    Book expectedBook = Book.builder().build();
    when(bookService.getByTitle(any())).thenReturn(expectedBook);
    // Act && Assert
    Assertions.assertEquals(bookController.getByTitle(anyString()), expectedBook);
  }

  @Test
  void createBookSuccessfullyCase() throws AuthorNotFoundException {
    // Arrange
    BookUpdateCreateRequest bookCreate = new BookUpdateCreateRequest(BookServiceTest.RANDOM_BOOK_TITLE,
        AuthorServiceTest.RANDOM_AUTHOR_NAME);
    Book expectedBook = new Book();
    when(bookService.addBook(bookCreate)).thenReturn(expectedBook);
    // Act && Assert
    Assertions.assertEquals(expectedBook, bookController.createBook(bookCreate));
  }

  @Test
  void updateBookSuccessfullyCase() throws AuthorNotFoundException, BookNotFoundException {
    // Arrange
    BookUpdateCreateRequest bookCreate = new BookUpdateCreateRequest(BookServiceTest.RANDOM_BOOK_TITLE,
        AuthorServiceTest.RANDOM_AUTHOR_NAME);
    Book expectedBook = new Book();
    when(bookService.updateBook(BookServiceTest.RANDOM_BOOK_ID,bookCreate)).thenReturn(expectedBook);
    // Act && Assert
    Assertions.assertEquals(expectedBook, bookController.updateBook(BookServiceTest.RANDOM_BOOK_ID,bookCreate));
  }

  @Test
  void deleteBookSuccessfullyCase() {
    // Arrange
    // Act && Assert
    bookController.deleteById(BookServiceTest.RANDOM_BOOK_ID);
    verify(bookService).deleteById(BookServiceTest.RANDOM_BOOK_ID);
  }

}
