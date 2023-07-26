package lideraamerica.pruebabackend.repo;

import java.util.Optional;
import lideraamerica.pruebabackend.security.enums.RoleName;
import lideraamerica.pruebabackend.security.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role,Integer> {
  Optional<Role> findByRoleName(RoleName roleName);
}
