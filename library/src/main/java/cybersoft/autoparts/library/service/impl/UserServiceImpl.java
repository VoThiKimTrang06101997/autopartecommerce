package cybersoft.autoparts.library.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import cybersoft.autoparts.library.model.Role;
import cybersoft.autoparts.library.model.User;
import cybersoft.autoparts.library.repository.RoleRepository;
import cybersoft.autoparts.library.repository.UserRepository;
import cybersoft.autoparts.library.service.UserService;
import cybersoft.autoparts.library.web.dto.UserRegistrationDto;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    public BCryptPasswordEncoder passwordEncoder;


    public UserServiceImpl() {
        super();
    }

    @Override
    public User save(UserRegistrationDto registrationDto) {

        //Creating admin role user
        User user = new User();
        user.setFirstName(registrationDto.getFirstName());
        user.setLastName(registrationDto.getLastName());
        user.setUsername(registrationDto.getUsername());
        user.setPassword(passwordEncoder.encode(registrationDto.getPassword()));
        user.setRoles(Arrays.asList(roleRepository.findByName("ADMIN")));
        user.setIsActive(true);
        user.setIsDeleted(false);

        return userRepository.save(user);
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userRepository.findByUsername(username);
        if(user == null) {
            throw new UsernameNotFoundException("Invalid username or password.");
        }
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword()
                , mapRolesToAuthorities(user.getRoles()));
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles){
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
    }

}
