package com.endtoend.historyOfMine.service;

import com.endtoend.historyOfMine.forms.RelativeForm;
import com.endtoend.historyOfMine.models.Relative;
import com.endtoend.historyOfMine.repositories.RelativesRepository;
import com.endtoend.historyOfMine.repositories.UsersRepository;
import com.endtoend.historyOfMine.utils.RelativeUtils;
import org.springframework.http.HttpStatus;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RelativeService {
    private final RelativesRepository relativesRepository;
    private final RelativeUtils relativeUtils;
    private final UsersRepository userRepository;
    private final UserService userService;

    public RelativeService(RelativesRepository relativesRepository, RelativeUtils relativeUtils, UsersRepository userRepository, UserService userService) {
        this.relativesRepository = relativesRepository;
        this.relativeUtils = relativeUtils;
        this.userRepository = userRepository;
        this.userService = userService;
    }

    public HttpStatus addRelative(@NonNull final RelativeForm relativeForm) {
        if(relativesRepository.alreadyExists(relativeForm.getName(),
                relativeForm.getLastName(), relativeForm.getBirth()).isEmpty()) {

            var relative = relativeUtils.create(relativeForm);
            var loggedUser = userService.getLoggedUser();
            loggedUser.addRelative(relative);
            userRepository.save(loggedUser);
            relativesRepository.saveAndFlush(relative);
            return HttpStatus.OK;
        }
        return HttpStatus.CONFLICT;
    }


    public List<Relative> getAllUserRelatives() {
        return relativesRepository.getRelativesByUserId(userService.getLoggedUser().getId());
    }


}
