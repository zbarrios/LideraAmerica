package lideraamerica.pruebabackend.controller;

import java.util.List;
import lideraamerica.pruebabackend.controller.responses.RestControllerErrorTemplate;
import lideraamerica.pruebabackend.exception.AuthorNotFoundException;
import lideraamerica.pruebabackend.model.Author;
import lideraamerica.pruebabackend.model.AuthorUpdateCreateRequest;
import lideraamerica.pruebabackend.service.AuthorService;
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
public class AuthorController {

  private final AuthorService authorService;

  public AuthorController(AuthorService authorService) {
    this.authorService = authorService;
  }

  @ResponseBody
  @GetMapping(value = "/author/all")
  public List<Author> getAuthors() {
    return authorService.getAll();
  }

  @ResponseBody
  @GetMapping(value = "/author/{id}")
  public Author getAuthorById(@PathVariable Integer id) throws AuthorNotFoundException {
    return authorService.getById(id);
  }

  @ResponseBody
  @GetMapping(value = "/author/by-name/{name}")
  public Author getAuthorByName(@PathVariable String name) throws AuthorNotFoundException {
    return authorService.getByName(name);
  }

  @ResponseBody
  @PostMapping(value = "/author")
  public Author addAuthor(@RequestBody AuthorUpdateCreateRequest author){
    return authorService.addAuthor(author);
  }

  @ResponseBody
  @PutMapping(value = "/author/{id}")
  public Author updateAuthor(@PathVariable Integer id, @RequestBody AuthorUpdateCreateRequest authorUpdate)
      throws AuthorNotFoundException {
    return authorService.updateAuthor(id,authorUpdate);
  }

  @ResponseBody
  @DeleteMapping(value = "/author/{id}")
  public Object deleteAuthorById(@PathVariable Integer id){
    authorService.deleteById(id);
    return new RestControllerErrorTemplate("Author successfully deleted", HttpStatus.OK.value());
  }

}
