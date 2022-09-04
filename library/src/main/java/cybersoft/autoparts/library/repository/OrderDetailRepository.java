package cybersoft.autoparts.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import cybersoft.autoparts.library.model.OrderDetail;

public interface OrderDetailRepository extends JpaRepository<OrderDetail, Long> {

}