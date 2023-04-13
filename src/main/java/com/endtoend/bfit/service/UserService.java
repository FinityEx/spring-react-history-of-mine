package com.endtoend.bfit.service;

import com.endtoend.bfit.forms.UserForm;
import com.endtoend.bfit.models.User;
import com.endtoend.bfit.repositories.UsersRepository;
import com.endtoend.bfit.utils.JWTUtils;
import com.endtoend.bfit.utils.UserUtils;
import com.endtoend.bfit.websecurity.AuthenticationResponse;
import jakarta.persistence.EntityExistsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {
    private final UsersRepository usersRepository;

    public UserService(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    public AuthenticationResponse createUser(final UserForm userForm){
        if(!usersRepository.existsByUsername(userForm.getUsername())) {
            var user = UserUtils.createNewUser(userForm);
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
