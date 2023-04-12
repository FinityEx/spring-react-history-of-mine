package com.endtoend.bfit.service;

import com.endtoend.bfit.forms.UserForm;
import com.endtoend.bfit.models.User;
import com.endtoend.bfit.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserService{
    @Autowired
    private final UsersRepository usersRepository;
    public UserService(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    public User createUser(final UserForm userForm){
        if(!usersRepository.existsByUsername(userForm.getUsername())) {
            final User user = new User(UUID.randomUUID(), userForm.getUsername(), userForm.getPassword());
            usersRepository.saveAndFlush(user);
            return user;
        }
        return null;
    }

    public User getUser(final String username){
        var optionalUser = usersRepository.findByUsername(username);
        if(optionalUser.isPresent()) {
            return optionalUser.get();
        }
        return null;
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
}
