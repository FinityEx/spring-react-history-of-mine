package com.endtoend.historyOfMine.utils;

import com.endtoend.historyOfMine.forms.RelativeDTO;
import com.endtoend.historyOfMine.models.Relative;
import io.micrometer.common.lang.NonNull;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.text.ParseException;

@Service
public class RelativeUtils implements Utils<Relative, RelativeDTO> {

    @Override
    public Relative create(@NonNull RelativeDTO relativeDTO) throws ParseException {
        final var relative = new Relative();
        var auth = SecurityContextHolder.getContext().getAuthentication();
        if(!auth.isAuthenticated()) {
            throw new AuthenticationServiceException("User is not logged in!");
        }

        relative.setName(relativeDTO.getName());
        relative.setLastName(relativeDTO.getLastName());
        relative.setSex(relativeDTO.getSex());
        relative.setAs(relativeDTO.getAs());
        relative.setBirth(relativeDTO.getBirth());
        relative.setDeath(relativeDTO.getDeath());
        relative.setPlaceOfBirth(relativeDTO.getPlaceOfBirth());
        return relative;
    }

}
