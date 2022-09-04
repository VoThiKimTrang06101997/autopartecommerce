package cybersoft.autoparts.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import cybersoft.autoparts.library.model.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {

}