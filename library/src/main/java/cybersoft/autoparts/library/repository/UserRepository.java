package cybersoft.autoparts.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import cybersoft.autoparts.library.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}