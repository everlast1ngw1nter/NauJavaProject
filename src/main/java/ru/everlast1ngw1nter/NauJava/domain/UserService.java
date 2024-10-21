package ru.everlast1ngw1nter.NauJava.domain;

import java.util.Collection;
import java.util.Collections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.everlast1ngw1nter.NauJava.database.UserRepository;
import ru.everlast1ngw1nter.NauJava.models.Role;
import ru.everlast1ngw1nter.NauJava.models.User;

@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User getUser(String name) {
        return userRepository.findByName(name);
    }

    public boolean addUser(User user) {
        var dbUser = userRepository.findByName(user.getName());
        if (dbUser != null) {
            return false;
        }
        user.setRole(Role.USER);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return true;
    }

    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        var user = userRepository.findByName(name);
        return new org.springframework.security.core.userdetails.User(user.getName(), user.getPassword(),
                mapRoleToAuthority(user.getRole()));
    }

    public Collection<? extends GrantedAuthority> mapRoleToAuthority(Role role) {
        return Collections.singleton(new SimpleGrantedAuthority("ROLE_" + role.name()));
    }
}
