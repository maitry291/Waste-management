package com.distritubuteddatabase.authenticationservice.service;

import com.distritubuteddatabase.authenticationservice.model.AuthUser;
import com.distritubuteddatabase.authenticationservice.repository.AuthUserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class AuthUserDetailsService implements UserDetailsService{
    private final AuthUserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<AuthUser> authUser = userRepository.findByUserName(username.toLowerCase());
        if (!authUser.isPresent()) {
            throw new UsernameNotFoundException(username);
        } else {
//            return new User(authUser.get().getRole(),authUser.get().getUserName());
            return User.builder()
                    .username(authUser.get().getUserName())
                    .password(authUser.get().getPassword())
                    .build();
        }
    }
}
