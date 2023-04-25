package com.endtoend.historyOfMine.utils;

import com.endtoend.historyOfMine.forms.UserForm;
import com.endtoend.historyOfMine.tables.User;
import com.endtoend.historyOfMine.utils.securityutils.BCryptEncodingUtils;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserUtils implements Utils<User, UserForm>{
    @Override
    public User create(@NonNull UserForm userForm){
        return new User(UUID.randomUUID(), userForm.getUsername(), BCryptEncodingUtils.encode(
                userForm.getPassword()), userForm.getEmail());
    }

}
