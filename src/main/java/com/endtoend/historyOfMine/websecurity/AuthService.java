package com.endtoend.historyOfMine.websecurity;

import com.endtoend.historyOfMine.forms.UserForm;
import com.endtoend.historyOfMine.service.UserService;
import com.endtoend.historyOfMine.utils.securityutils.BCryptEncodingUtils;
import com.endtoend.historyOfMine.utils.securityutils.JWTUtils;
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
                            userForm.username(),
                            BCryptEncodingUtils.encode(userForm.password())
                    )
            );
            var user = userService.getUser(userForm.username());
            return new AuthenticationResponse(JWTUtils.generateToken(user));
        }
    }
