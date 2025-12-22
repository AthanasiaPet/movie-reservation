package gr.aueb.cf.moviereservation.repository;

import gr.aueb.cf.moviereservation.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUuid(String uuid);
    Optional<User> findByUsername(String username);
    Optional<User> findByEmail(String email);

}
