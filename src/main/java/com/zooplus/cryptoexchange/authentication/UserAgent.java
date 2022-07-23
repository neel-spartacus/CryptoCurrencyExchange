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
        User newUser = new User();
        newUser.setEmail(email);
        newUser.setPassword(new BCryptPasswordEncoder().encode(password));
        newUser.setEnabled(Boolean.TRUE);
        newUser.setFirstname(firstname);
        newUser.setLastname(lastname);
        newUser.setBirthday(bday);
        return userRepository.save(newUser) != null?true:false;

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
