package com.endtoend.historyOfMine.utils;

import com.endtoend.historyOfMine.forms.UserForm;
import com.endtoend.historyOfMine.models.User;
import com.endtoend.historyOfMine.utils.securityutils.BCryptEncodingUtils;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

@Service
public class UserUtils implements Utils<User, UserForm>{
    @Override
    public User create(@NonNull UserForm userForm){
        return new User(userForm.getUsername(), BCryptEncodingUtils.encode(
                userForm.getPassword()), userForm.getEmail());
    }

}
