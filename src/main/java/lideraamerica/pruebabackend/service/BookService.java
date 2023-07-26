package lideraamerica.pruebabackend.service;

import java.util.List;
import lideraamerica.pruebabackend.exception.AuthorNotFoundException;
import lideraamerica.pruebabackend.exception.BookNotFoundException;
import lideraamerica.pruebabackend.model.Book;
import lideraamerica.pruebabackend.model.BookUpdateCreateRequest;
import lideraamerica.pruebabackend.repo.BookRepository;
import org.springframework.stereotype.Service;


@Service
public class BookService {

  private final BookRepository bookRepository;
  private final AuthorService authorService;

  public BookService(BookRepository bookRepository, AuthorService authorService) {
    this.bookRepository = bookRepository;
    this.authorService = authorService;
  }

  public List<Book> getBooksList(){
    return bookRepository.findAll();
  }

  public Book getByTitle(String title) throws BookNotFoundException {
    return bookRepository.findByTitle(title).orElseThrow(BookNotFoundException::new);
  }

  public Book addBook(BookUpdateCreateRequest bookCreate) throws AuthorNotFoundException {
    Book bookToCreate = Book.builder()
        .title(bookCreate.getTitle())
        .author(authorService.getByName(bookCreate.getAuthorName()))
        .build();
    return bookRepository.save(bookToCreate);
  }

  public Book updateBook(Integer bookId, BookUpdateCreateRequest bookUpdate)
      throws BookNotFoundException, AuthorNotFoundException {
    Book existingBook = bookRepository.findById(bookId).orElseThrow(BookNotFoundException::new);
    existingBook.setTitle(bookUpdate.getTitle());
    if (!bookUpdate.getAuthorName().isBlank())
      existingBook.setAuthor(authorService.getByName(bookUpdate.getAuthorName()));
    return bookRepository.save(existingBook);
  }

  public void deleteById(Integer id) {
    bookRepository.deleteById(id);
  }
}
