package com.security.demo.auth.repo;

import com.security.demo.auth.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepo extends CrudRepository<User, String> {
    User findByUserName(String userName);
    User findByEmail(String email);
    boolean existsUserByEmail(String email);
}
