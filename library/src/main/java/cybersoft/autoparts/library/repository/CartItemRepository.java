package cybersoft.autoparts.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import cybersoft.autoparts.library.model.CartItem;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {
}