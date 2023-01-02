package com.synchrony.imageapi.repository;

import com.synchrony.imageapi.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SynchronyUserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
