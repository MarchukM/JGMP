package com.nosqlexample.service.impl;

import com.nosqlexample.exception.NotFoundException;
import com.nosqlexample.model.Sport;
import com.nosqlexample.model.User;
import com.nosqlexample.repo.UserRepository;
import com.nosqlexample.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Collections;


@Service
public class UserService implements IUserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User findById(String id) {
        return userRepository.findById(id).orElseThrow(NotFoundException::new);
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email).orElseThrow(NotFoundException::new);
    }

    @Override
    public User create(User user) {
        return userRepository.save(user);
    }

    @Override
    public User updateWithSport(String id, Sport sport) {
        User user = findById(id);

        if (CollectionUtils.isEmpty(user.getSport())) {
            user.setSport(Collections.singletonList(sport));
        } else {
            user.getSport().add(sport);
        }

        return userRepository.save(user);
    }

    @Override
    public User findBySportName(String sportName) {
        return userRepository.findBySport(sportName).orElseThrow(NotFoundException::new);
    }
}
