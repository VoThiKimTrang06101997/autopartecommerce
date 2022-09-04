package cybersoft.autoparts.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import cybersoft.autoparts.library.model.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Customer findByUsername(String username);
}