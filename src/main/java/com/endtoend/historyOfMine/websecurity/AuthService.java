package com.endtoend.historyOfMine.websecurity;

import com.endtoend.historyOfMine.forms.UserForm;
import com.endtoend.historyOfMine.repositories.UsersRepository;
import com.endtoend.historyOfMine.service.UserService;
import com.endtoend.historyOfMine.websecurity.securityutils.JWTUtils;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import javax.naming.AuthenticationException;
import java.util.Objects;

@Service
public class AuthService {
    private final AuthenticationManager authenticationManager;
    private final UsersRepository usersRepository;

    public AuthService(AuthenticationManager authenticationManager, UserService userService, UsersRepository usersRepository) {
        this.authenticationManager = authenticationManager;
        this.usersRepository = usersRepository;
    }
    public AuthenticationResponse authenticate(UserForm.AuthenticationForm userForm) throws AuthenticationException {
        Objects.requireNonNull(userForm, "userForm can't be null!");
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            userForm.username(),
                            userForm.password()
                    )
            );
            var user = usersRepository.findByUsername(userForm.username());
            if(user.isPresent()){
                return new AuthenticationResponse(JWTUtils.generateToken(user.get()), user.get().getId());
            }
            else{
                throw new AuthenticationException("Username not found!");
            }
        }
    }
