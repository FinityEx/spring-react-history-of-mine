package com.endtoend.bfit.service;

import com.endtoend.bfit.forms.UserDTO;
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

    public User createUser(final UserDTO userDTO){
        if(!usersRepository.existsByUsername(userDTO.getUsername())) {
            final User user = new User(UUID.randomUUID(), userDTO.getUsername(), userDTO.getPassword());
            usersRepository.saveAndFlush(user);
            return user;
        }
        return null;
    }

    public User getUser(final UserDTO userDTO){
        var optionalUser = usersRepository.findByUsername(userDTO.getUsername());
        if(optionalUser.isPresent()) {
            return optionalUser.get();
        }
        return null;
    }

    public boolean deleteUser(final UserDTO userDTO){
        if(usersRepository.existsByUsername(userDTO.getUsername())) {
            var foundUser = getUser(userDTO);
            if(foundUser.getPassword().equals(userDTO.getPassword())) {
                usersRepository.deleteByUsername(userDTO.getUsername());
                return true;
            }
        }
        return false;
    }

    public boolean updatePassword(final UserDTO userDTO){
        var updateOptionalUser = getUser(userDTO);
        if(updateOptionalUser != null &&
                userDTO.getPassword().equals(updateOptionalUser.getPassword())) {
            updateOptionalUser.setPassword(userDTO.getNewPassword());
            usersRepository.saveAndFlush(updateOptionalUser);
            return true;
        }
        return false;
    }
}
