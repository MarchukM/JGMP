package com.security.demo.auth.service;

import com.security.demo.auth.model.AuthGroup;
import com.security.demo.auth.repo.AuthRepo;
import com.security.demo.auth.repo.UserRepo;
import com.security.demo.auth.model.User;
import com.security.demo.auth.model.UserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserDetailServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private AuthRepo authRepo;

    @Autowired
    private LoginAttemptService loginAttemptService;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        if (loginAttemptService.isBlocked()) {
            throw new RuntimeException("blocked");
        }

        User user = userRepo.findByEmail(email);
        if (null == user) {
            throw new UsernameNotFoundException("User not found: " + email);
        }
        List<AuthGroup> authGroups = authRepo.findAllByUserName(user.getUserName());
        return new UserPrincipal(user, authGroups);
    }

    public boolean isUserExist(String email) {
        return userRepo.existsUserByEmail(email);
    }
}
