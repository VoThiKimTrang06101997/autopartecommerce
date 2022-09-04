package cybersoft.autoparts.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import cybersoft.autoparts.library.model.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(String name);
}