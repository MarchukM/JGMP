package com.security.demo.auth.repo;

import com.security.demo.auth.model.AuthGroup;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AuthRepo extends CrudRepository<AuthGroup, String> {
    List<AuthGroup> findAllByUserName(String userName);
}
