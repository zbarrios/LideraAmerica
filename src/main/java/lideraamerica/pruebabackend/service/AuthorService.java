package lideraamerica.pruebabackend.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import lideraamerica.pruebabackend.exception.AuthorNotFoundException;
import lideraamerica.pruebabackend.exception.BookNotFoundException;
import lideraamerica.pruebabackend.model.Author;
import lideraamerica.pruebabackend.model.AuthorUpdateCreateRequest;
import lideraamerica.pruebabackend.model.Book;
import lideraamerica.pruebabackend.repo.AuthorRepository;
import lideraamerica.pruebabackend.repo.BookRepository;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

@Service
public class AuthorService {

  private final AuthorRepository authorRepository;
  private final BookRepository bookRepository;

  public AuthorService(AuthorRepository authorRepository, BookRepository bookRepository) {
    this.authorRepository = authorRepository;
    this.bookRepository = bookRepository;
  }

  public Author getByName(String name) throws AuthorNotFoundException {
    return authorRepository.findByAuthorName(name).orElseThrow(AuthorNotFoundException::new);
  }

  public Author getById(Integer id) throws AuthorNotFoundException {
    return authorRepository.findById(id).orElseThrow(AuthorNotFoundException::new);
  }

  public List<Author> getAll(){
    return authorRepository.findAll();
  }

  public Author addAuthor(AuthorUpdateCreateRequest authorToCreate){
    Author author = Author.builder().authorName(authorToCreate.getAuthorName()).build();
    return authorRepository.save(author);
  }

  public Author updateAuthor(Integer id, AuthorUpdateCreateRequest authorUpdate)
      throws AuthorNotFoundException {
    Author existingAuthor = getById(id);
    existingAuthor.setAuthorName(authorUpdate.getAuthorName());
    if (authorUpdate.getBookIds()!= null){
      Set<Book> booksByAuthor = new HashSet<>();
      authorUpdate.getBookIds().forEach(bookId-> booksByAuthor.add(updateBooksAuthor(bookId,existingAuthor)));
      existingAuthor.setBooks(booksByAuthor);
    }
    return authorRepository.save(existingAuthor);
  }

  @SneakyThrows
  public Book updateBooksAuthor(Integer bookId, Author authorUpdate) {
    Book existingBook = bookRepository.findById(bookId).orElseThrow(BookNotFoundException::new);
    existingBook.setAuthor(authorUpdate);
    return bookRepository.save(existingBook);
  }

  public void deleteById(Integer id){
    authorRepository.deleteById(id);
  }
}
