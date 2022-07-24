package com.zooplus.cryptoexchange.service;

import com.zooplus.cryptoexchange.entity.User;
import com.zooplus.cryptoexchange.repository.UsersRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service("userDetailsService")
public class SimpleUserDetailsService implements UserDetailsService {

    private UsersRepository userRepository;

    public SimpleUserDetailsService(UsersRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        if (username == null || username.isEmpty()) {
            throw new UsernameNotFoundException("Username and domain must be provided");
        }
        User user = userRepository.findByEmail(username);
        if (user == null) {
            throw new UsernameNotFoundException(
                    String.format("Username not found username=%s",
                            username));
        }
        return user;
    }
}
