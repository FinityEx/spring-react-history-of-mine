package com.endtoend.bfit.websecurity;

import com.endtoend.bfit.forms.UserForm;
import com.endtoend.bfit.service.UserService;
import com.endtoend.bfit.utils.JWTUtils;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class AuthService {
    private final AuthenticationManager authenticationManager;
    private final UserService userService;

    public AuthService(AuthenticationManager authenticationManager, UserService userService) {
        this.authenticationManager = authenticationManager;
        this.userService = userService;
    }
    public AuthenticationResponse authenticate(UserForm.AuthenticationForm userForm){
        Objects.requireNonNull(userForm, "userForm can't be null!");
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            userForm.getUsername(),
                            userForm.getPassword()
                    )
            );
            var user = userService.getUser(userForm.getUsername());
            return new AuthenticationResponse(JWTUtils.generateToken(user));
        }
    }
