package recipes.persistence;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import recipes.businesslayer.User;

@Repository
public interface UserRepository extends CrudRepository<User, String> {
}
