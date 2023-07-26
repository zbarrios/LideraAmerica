package lideraamerica.pruebabackend.repo;

import java.util.Optional;
import lideraamerica.pruebabackend.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Integer> {

  Optional <Book> findByTitle(String title);
}
