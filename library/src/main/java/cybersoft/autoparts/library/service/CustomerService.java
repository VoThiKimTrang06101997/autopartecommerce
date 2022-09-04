package cybersoft.autoparts.library.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import cybersoft.autoparts.library.model.Customer;
import cybersoft.autoparts.library.web.dto.CustomerRegistrationDto;

public interface CustomerService extends UserDetailsService{
    Customer save(CustomerRegistrationDto registrationDto);//For insert
    Customer save(Customer customer);//For update
    Customer findByUsername(String username);
}
