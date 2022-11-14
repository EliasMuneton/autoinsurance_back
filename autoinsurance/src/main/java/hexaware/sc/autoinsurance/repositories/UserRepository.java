package hexaware.sc.autoinsurance.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import hexaware.sc.autoinsurance.domain.User;

public interface UserRepository extends CrudRepository<User, Long>{

    Optional<User> findByEmail(String email);

}
