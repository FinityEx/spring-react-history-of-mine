package com.endtoend.historyOfMine.websecurity;

import com.endtoend.historyOfMine.forms.UserForm;
import com.endtoend.historyOfMine.repositories.UsersRepository;
import com.endtoend.historyOfMine.service.UserService;
import com.endtoend.historyOfMine.utils.securityutils.BCryptEncodingUtils;
import com.endtoend.historyOfMine.utils.securityutils.JWTUtils;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;
import org.springframework.util.function.SupplierUtils;

import javax.naming.AuthenticationException;
import java.util.Objects;
import java.util.function.Supplier;

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
                            BCryptEncodingUtils.encode(userForm.password())
                    )
            );
            var user = usersRepository.findByUsername(userForm.username());
            if(user.isPresent()){
                return new AuthenticationResponse(JWTUtils.generateToken(user.get()));
            }
            else{
                throw new AuthenticationException("Username not found!");
            }
        }
    }
