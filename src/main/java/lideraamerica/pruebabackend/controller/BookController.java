package lideraamerica.pruebabackend.controller;

import java.util.List;
import lideraamerica.pruebabackend.controller.responses.RestControllerErrorTemplate;
import lideraamerica.pruebabackend.exception.AuthorNotFoundException;
import lideraamerica.pruebabackend.exception.BookNotFoundException;
import lideraamerica.pruebabackend.model.Book;
import lideraamerica.pruebabackend.model.BookUpdateCreateRequest;
import lideraamerica.pruebabackend.service.BookService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BookController {

  private final BookService bookService;

  public BookController(BookService bookService) {
    this.bookService = bookService;
  }

  @ResponseBody
  @GetMapping(value = "/book/all")
  public List<Book> getBooks() {
    return bookService.getBooksList();
  }

  @ResponseBody
  @GetMapping(value = "/book/by-name/{name}")
  public Book getByTitle(@PathVariable("name") String title) throws BookNotFoundException {
    return bookService.getByTitle(title);
  }

  @ResponseBody
  @PostMapping(value = "/book")
  public Book createBook(@RequestBody BookUpdateCreateRequest bookCreate)
      throws AuthorNotFoundException {
    return bookService.addBook(bookCreate);
  }

  @ResponseBody
  @PutMapping(value = "/book/{id}")
  public Book updateBook(@PathVariable("id") Integer id, @RequestBody BookUpdateCreateRequest bookUpdate)
      throws BookNotFoundException, AuthorNotFoundException {
    return bookService.updateBook(id,bookUpdate);
  }

  @ResponseBody
  @DeleteMapping(value = "/book/{id}")
  public Object deleteById(@PathVariable("id") Integer id) {
    bookService.deleteById(id);
    return new RestControllerErrorTemplate("Book successfully deleted", HttpStatus.OK.value());
  }

}
