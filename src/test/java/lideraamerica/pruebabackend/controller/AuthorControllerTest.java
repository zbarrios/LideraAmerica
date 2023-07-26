package lideraamerica.pruebabackend.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import lideraamerica.pruebabackend.controller.responses.RestControllerErrorTemplate;
import lideraamerica.pruebabackend.exception.AuthorNotFoundException;
import lideraamerica.pruebabackend.exception.BookNotFoundException;
import lideraamerica.pruebabackend.model.Author;
import lideraamerica.pruebabackend.model.AuthorUpdateCreateRequest;
import lideraamerica.pruebabackend.model.Book;
import lideraamerica.pruebabackend.service.AuthorService;
import lideraamerica.pruebabackend.service.AuthorServiceTest;
import lideraamerica.pruebabackend.service.BookService;
import lideraamerica.pruebabackend.service.BookServiceTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@ExtendWith(MockitoExtension.class)
public class AuthorControllerTest {

  @Mock
  AuthorService authorService;

  @InjectMocks
  AuthorController authorController;

  @Test
  void getAuthorByNameSuccessfullyCase() throws AuthorNotFoundException {
    // Arrange
    Author expectedAuthor = new Author();
    when(authorService.getByName(any())).thenReturn(expectedAuthor);
    // Act && Assert
    Assertions.assertEquals(expectedAuthor, authorController.getAuthorByName(AuthorServiceTest.RANDOM_AUTHOR_NAME));
  }

  @Test
  void addAuthorSuccessfullyCase() {
    // Arrange
    Author expectedAuthor = new Author();
    AuthorUpdateCreateRequest authorToCreate = new AuthorUpdateCreateRequest(AuthorServiceTest.RANDOM_AUTHOR_NAME,new ArrayList<>());
    when(authorService.addAuthor(authorToCreate)).thenReturn(expectedAuthor);
    // Act && Assert
    Assertions.assertEquals(expectedAuthor, authorController.addAuthor(authorToCreate));
  }

  @Test
  void updateAuthorSuccessfullyCase() throws AuthorNotFoundException {
    // Arrange
    Author expectedAuthor = new Author();
    AuthorUpdateCreateRequest authorToCreate = new AuthorUpdateCreateRequest(AuthorServiceTest.RANDOM_AUTHOR_NAME,new ArrayList<>());
    when(authorService.updateAuthor(AuthorServiceTest.RANDOM_AUTHOR_ID,authorToCreate)).thenReturn(expectedAuthor);
    // Act && Assert
    Assertions.assertEquals(expectedAuthor, authorController.updateAuthor(AuthorServiceTest.RANDOM_AUTHOR_ID,authorToCreate));
  }

  @Test
  void deleteAuthorSuccessfullyCase() {
    // Arrange
    // Act && Assert
    authorController.deleteAuthorById(AuthorServiceTest.RANDOM_AUTHOR_ID);
    verify(authorService).deleteById(AuthorServiceTest.RANDOM_AUTHOR_ID);
  }

}
