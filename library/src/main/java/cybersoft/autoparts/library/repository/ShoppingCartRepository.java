package cybersoft.autoparts.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import cybersoft.autoparts.library.model.ShoppingCart;

public interface ShoppingCartRepository extends JpaRepository<ShoppingCart, Long> {
}