package com.endtoend.bfit.service;

import com.endtoend.bfit.models.User;
import com.endtoend.bfit.repositories.UsersRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class UserService{
    private final UsersRepository usersRepository;
    private final User user;
    public UserService(UsersRepository usersRepository, User user) {
        this.usersRepository = usersRepository;
        this.user = user;
    }
    public void saveUser(final String username, final String password){
        final User user = new User(UUID.randomUUID(), username, password);
        usersRepository.saveAndFlush(user);
    }

    public Optional<User> getUser(String username){
        return usersRepository.findByUsername(username);
    }
}
