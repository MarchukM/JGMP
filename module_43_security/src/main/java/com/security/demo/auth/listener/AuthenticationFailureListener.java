package com.security.demo.auth.listener;

import com.security.demo.auth.service.LoginAttemptService;
import com.security.demo.auth.service.UserDetailServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AuthenticationFailureBadCredentialsEvent;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
public class AuthenticationFailureListener implements ApplicationListener<AuthenticationFailureBadCredentialsEvent> {

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private LoginAttemptService loginAttemptService;

    @Autowired
    private UserDetailServiceImpl userDetailService;

    @Override
    public void onApplicationEvent(AuthenticationFailureBadCredentialsEvent e) {
        final String userEmail = request.getParameter("email");
        if (userDetailService.isUserExist(userEmail)) {
            loginAttemptService.loginFailed(userEmail);
        }
    }
}
