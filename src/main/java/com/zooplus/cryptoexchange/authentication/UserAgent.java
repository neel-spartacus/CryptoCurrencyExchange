package com.zooplus.cryptoexchange.authentication;


import com.zooplus.cryptoexchange.entity.User;
import com.zooplus.cryptoexchange.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class UserAgent {

    /*@Autowired
    @Qualifier("authenticationManagerBean")
    private AuthenticationManager authenticationManagerBean;*/

    @Autowired
    private UsersRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public boolean signup(final String firstname, final String lastname, final String email, final String bday, final String password) {

        User user= userRepository.findByEmail(email);
        if(Objects.nonNull(user)){
            return false;
        }
        user = new User();
        user.setEmail(email);
        user.setPassword(new BCryptPasswordEncoder().encode(password));
        user.setEnabled(Boolean.TRUE);
        user.setFirstname(firstname);
        user.setLastname(lastname);
        user.setBirthday(bday);
        return userRepository.save(user) != null?true:false;

    }

    public boolean signIn(String userEmail,String password){
        User user= userRepository.findByEmail(userEmail);
        if(Objects.nonNull(user)){
            return passwordEncoder.matches(password,user.getPassword());
        }else{
            return false;
        }
    }
}
