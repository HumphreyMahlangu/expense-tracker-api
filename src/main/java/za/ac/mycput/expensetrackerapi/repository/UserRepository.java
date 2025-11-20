package za.ac.mycput.expensetrackerapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import za.ac.mycput.expensetrackerapi.model.User;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    // This is a "Query Method".
    // Spring automatically writes the SQL: SELECT * FROM users WHERE username = ?
    Optional<User> findByUsername(String username);

    // We also need to check if a user exists before registering them
    Boolean existsByUsername(String username);
}