package bd.ac.bauet.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import bd.ac.bauet.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByEmail(String email);

}
