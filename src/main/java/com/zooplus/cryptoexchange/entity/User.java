package com.zooplus.cryptoexchange.entity;

import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;



@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class User implements Serializable, UserDetails {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "email", nullable = false)
    @NotNull
    private String email;

    @Column(name = "password", nullable = false)
    @NotNull
    private String password;

    @Column(name = "enabled", nullable = false)
    @NotNull
    private boolean enabled;

    @Column
    private String firstname;

    @Column
    private String lastname;

    @Column
    private String birthday;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return AuthorityUtils.createAuthorityList("USER", "SUPERUSER", "ROLE_SUPERUSER","ROLE_ANONYMOUS");
        // return Arrays.asList(new SimpleGrantedAuthority("USER"),
        // new SimpleGrantedAuthority("SUPERUSER"));
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

}
