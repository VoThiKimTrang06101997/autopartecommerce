package cybersoft.autoparts.library.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import cybersoft.autoparts.library.model.Country;
import cybersoft.autoparts.library.model.Customer;
import cybersoft.autoparts.library.model.Role;
import cybersoft.autoparts.library.model.User;
import cybersoft.autoparts.library.repository.CountryRepository;
import cybersoft.autoparts.library.repository.CustomerRepository;
import cybersoft.autoparts.library.repository.RoleRepository;
import cybersoft.autoparts.library.service.CustomerService;
import cybersoft.autoparts.library.web.dto.CustomerRegistrationDto;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private CountryRepository countryRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;



    public CustomerServiceImpl() {
        super();
    }

    @Override
    public Customer save(CustomerRegistrationDto registrationDto) {

        //Creating admin role user
        Customer customer = new Customer();
        customer.setFirstName(registrationDto.getFirstName());
        customer.setLastName(registrationDto.getLastName());
        customer.setUsername(registrationDto.getUsername());
        customer.setPhone(registrationDto.getPhone());
        customer.setPassword(passwordEncoder.encode(registrationDto.getPassword()));
        customer.setRoles(Arrays.asList(roleRepository.findByName("CUSTOMER")));
        customer.setIsDeleted(false);

        //Get country
        Country country = countryRepository.findByName(registrationDto.getCountry());

        //Address
        customer.setCompany(registrationDto.getCompany());
        customer.setAddress1(registrationDto.getAddress1());
        customer.setAddress2(registrationDto.getAddress2());
        customer.setPostalCode(registrationDto.getPostalCode());
        customer.setCity(registrationDto.getCity());
        customer.setCountry(country);
        customer.setState(registrationDto.getState());

        return customerRepository.save(customer);
    }

    @Override
    public Customer findByUsername(String username) {
        return customerRepository.findByUsername(username);
    }

    @Override
    public Customer save(Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Customer customer = customerRepository.findByUsername(username);
        if(customer == null) {
            throw new UsernameNotFoundException("Invalid username or password.");
        }
        return new org.springframework.security.core.userdetails.User(customer.getUsername(), customer.getPassword()
                , mapRolesToAuthorities(customer.getRoles()));
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles){
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
    }

}
