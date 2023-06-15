package com.endtoend.historyOfMine.utils;

import com.endtoend.historyOfMine.forms.RelativeDTO;
import com.endtoend.historyOfMine.forms.UserDTO;
import com.endtoend.historyOfMine.models.Relative;
import com.endtoend.historyOfMine.models.User;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class Mapper{
    @Bean
    public ModelMapper modelMapper(){
        var modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setSkipNullEnabled(true);
        return modelMapper;
    }

    private UserDTO convertToDTO(User user){
        UserDTO userDTO = modelMapper().map(user, UserDTO.class);
        userDTO.setBirth(user.getBirth());

        return userDTO;
    }

    public RelativeDTO convertToDTO(Relative relative) {
        RelativeDTO relativeDTO = modelMapper().map(relative, RelativeDTO.class);
        relativeDTO.setBirth(relative.getBirth());
        relativeDTO.setDeath(relative.getDeath());

        return relativeDTO;
    }


}
