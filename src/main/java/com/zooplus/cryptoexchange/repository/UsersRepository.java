package com.zooplus.cryptoexchange.repository;

import com.zooplus.cryptoexchange.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
}