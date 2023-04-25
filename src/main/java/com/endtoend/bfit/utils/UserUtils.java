package com.endtoend.bfit.utils;

import com.endtoend.bfit.forms.UserForm;
import com.endtoend.bfit.models.User;
import org.springframework.stereotype.Service;

import java.util.UUID;
@Service
public class UserUtils {
    public static User createNewUser(UserForm userForm){
        return new User(UUID.randomUUID(), userForm.getUsername(), BCryptEncodingUtils.encode(
                userForm.getPassword()), userForm.getEmail());
    }
}
