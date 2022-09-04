package cybersoft.autoparts.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import cybersoft.autoparts.library.model.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    Category findByName(String name);
}