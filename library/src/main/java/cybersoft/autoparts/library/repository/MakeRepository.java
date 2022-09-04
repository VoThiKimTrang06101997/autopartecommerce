package cybersoft.autoparts.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import cybersoft.autoparts.library.model.Make;

public interface MakeRepository extends JpaRepository<Make, Long> {
    Make findByName(String name);
}