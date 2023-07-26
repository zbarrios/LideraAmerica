package lideraamerica.pruebabackend.repo;

import java.util.Optional;
import lideraamerica.pruebabackend.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author, Integer> {

  Optional<Author> findByAuthorName (String authorName);

}
