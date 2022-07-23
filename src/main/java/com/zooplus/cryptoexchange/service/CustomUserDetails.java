package com.zooplus.cryptoexchange.service;

import com.zooplus.cryptoexchange.entity.User;
import com.zooplus.cryptoexchange.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetails implements UserDetailsService {


    @Autowired
    private UsersRepository usersRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
       User user= usersRepository.findByEmail(email);

        if (user == null) {
            return new User(1L,
                    " ", " ", true, " ", " ", " ");
        }
        return user;
    }
}
