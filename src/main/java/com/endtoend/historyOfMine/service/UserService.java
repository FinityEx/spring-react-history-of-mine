package com.endtoend.historyOfMine.service;

import com.endtoend.historyOfMine.forms.UserForm;
import com.endtoend.historyOfMine.repositories.RelativesRepository;
import com.endtoend.historyOfMine.repositories.UsersRepository;
import com.endtoend.historyOfMine.models.User;
import com.endtoend.historyOfMine.utils.RelativeUtils;
import com.endtoend.historyOfMine.utils.UserUtils;
import com.endtoend.historyOfMine.utils.securityutils.JWTUtils;
import com.endtoend.historyOfMine.websecurity.AuthenticationResponse;
import jakarta.persistence.EntityExistsException;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserService implements UserDetailsService {
    private final UsersRepository usersRepository;
    private final UserUtils userUtils;
    private final RelativesRepository relativesRepository;
    private final RelativeUtils relativeUtils;

    public UserService(UsersRepository usersRepository, UserUtils userUtils, RelativesRepository relativesRepository, RelativeUtils relativeUtils) {
        this.usersRepository = usersRepository;
        this.userUtils = userUtils;
        this.relativesRepository = relativesRepository;
        this.relativeUtils = relativeUtils;
    }

    public AuthenticationResponse createUser(final UserForm userForm){
        if(!usersRepository.existsByUsername(userForm.getUsername())) {
            var user = userUtils.create(userForm);
            usersRepository.saveAndFlush(user);
            user = getUser(userForm.getUsername());
            return new AuthenticationResponse(JWTUtils.generateToken(user));
        }
        throw new EntityExistsException("Username already exists");
     }

    public User getUser(final String username){
        var optionalUser = usersRepository.findByUsername(username);
        if(optionalUser.isPresent()) {
            return optionalUser.get();
        }
        throw new UsernameNotFoundException("Username has not been found");
    }

    public User getLoggedUser(){
        var auth = SecurityContextHolder.getContext().getAuthentication();
        if(auth.isAuthenticated()) {
            var user = usersRepository.findByUsername(auth.getName());
            if(user.isPresent()) {
                return user.get();
            } else {
                throw new UsernameNotFoundException("Username not found!");
            }
        } else {
            throw new AuthenticationServiceException("User is not logged in!");
        }
    }

    public UUID getLoggedUserUUID(){
        return getLoggedUser().getId();
    }

    public boolean deleteUser(final UserForm userForm){
        if(usersRepository.existsByUsername(userForm.getUsername())) {
            var foundUser = getUser(userForm.getUsername());
            if(foundUser.getPassword().equals(userForm.getPassword())) {
                usersRepository.deleteByUsername(userForm.getUsername());
                return true;
            }
        }
        return false;
    }

    public boolean updatePassword(final UserForm userForm){
        var updateOptionalUser = getUser(userForm.getUsername());
        if(updateOptionalUser != null &&
                userForm.getPassword().equals(updateOptionalUser.getPassword())) {
            updateOptionalUser.setPassword(userForm.getPassword());
            usersRepository.saveAndFlush(updateOptionalUser);
            return true;
        }
        return false;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return getUser(username);
    }
}
