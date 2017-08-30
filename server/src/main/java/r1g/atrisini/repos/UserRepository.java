package r1g.atrisini.repos;

import org.springframework.data.repository.CrudRepository;
import r1g.atrisini.models.User;

public interface UserRepository extends CrudRepository<User, Long> {
  User findByEmail(String email);
}
