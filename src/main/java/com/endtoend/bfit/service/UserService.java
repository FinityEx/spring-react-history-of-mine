package com.endtoend.bfit.service;

import com.endtoend.bfit.forms.UserForm;
import com.endtoend.bfit.models.User;
import com.endtoend.bfit.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class UserService{
    @Autowired
    private final UsersRepository usersRepository;
    public UserService(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }
    public void saveUser(final UserForm userForm){
        final User user = new User(UUID.randomUUID(), userForm.getUsername(), userForm.getPassword());
        usersRepository.saveAndFlush(user);
    }

    public Optional<User> getUser(String username){
        return usersRepository.findByUsername(username);
    }

    public void deleteUser(String username){
       usersRepository.deleteByUsername(username);
    }

    public void updatePassword(final UserForm userForm){
        var updateOptionalUser = getUser(userForm.getUsername());
        if(updateOptionalUser.isPresent()) {
            var updateUser = updateOptionalUser.get();
            updateUser.setPassword(userForm.getNewPassword());
            usersRepository.saveAndFlush(updateUser);
        }
    }
}
