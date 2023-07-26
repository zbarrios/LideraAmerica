package lideraamerica.pruebabackend.repo;

import java.util.Optional;
import lideraamerica.pruebabackend.security.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {
  Optional<User> findByUserName(String userName);
}
