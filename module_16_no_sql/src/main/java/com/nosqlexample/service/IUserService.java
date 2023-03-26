package com.nosqlexample.service;

import com.nosqlexample.model.Sport;
import com.nosqlexample.model.User;

public interface IUserService {
    User findById(String id);
    User findByEmail(String email);
    User create(User user);
    User updateWithSport(String id, Sport sport);
    User findBySportName(String sportName);
}
