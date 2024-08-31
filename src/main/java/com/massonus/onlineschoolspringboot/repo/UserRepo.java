package com.massonus.onlineschoolspringboot.repo;

import com.massonus.onlineschoolspringboot.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, Long> {
    User findUserByName(String name);
}
