package com.endtoend.historyOfMine.utils;

import com.endtoend.historyOfMine.forms.RelativeForm;
import com.endtoend.historyOfMine.models.Relative;
import io.micrometer.common.lang.NonNull;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class RelativeUtils implements Utils<Relative, RelativeForm> {

    @Override
    public Relative create(@NonNull RelativeForm relativeForm){
        var relative = new Relative();
        var auth = SecurityContextHolder.getContext().getAuthentication();
        if(!auth.isAuthenticated()) {
            throw new AuthenticationServiceException("User is not logged in!");
        }

        relative.setUser(relativeForm.getUser());
        relative.setRelatedTo(relativeForm.getRelatedTo());
        relative.setAs(relativeForm.getAs());
        relative.setName(relativeForm.getName());
        relative.setLastName(relativeForm.getLastName());
        relative.setSex(relativeForm.getSex());
        relative.setBirth(relativeForm.getBirth());
        relative.setDeath(relativeForm.getDeath());
        relative.setPlaceOfBirth(relativeForm.getPlaceOfBirth());

        return relative;
    }
}
