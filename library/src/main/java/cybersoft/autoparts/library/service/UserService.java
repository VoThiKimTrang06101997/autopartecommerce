package cybersoft.autoparts.library.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import cybersoft.autoparts.library.model.User;
import cybersoft.autoparts.library.web.dto.UserRegistrationDto;

public interface UserService extends UserDetailsService{
    User save(UserRegistrationDto registrationDto);
    User findByUsername(String username);
}
