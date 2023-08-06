package com.endtoend.historyOfMine.service;

import com.endtoend.historyOfMine.forms.RelativeDTO;
import com.endtoend.historyOfMine.models.Relative;
import com.endtoend.historyOfMine.repositories.RelativesRepository;
import com.endtoend.historyOfMine.repositories.UsersRepository;
import com.endtoend.historyOfMine.utils.RelativeUtils;
import org.springframework.http.HttpStatus;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import java.text.ParseException;
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

    public HttpStatus addRelativeOfUser(@NonNull final RelativeDTO relativeDTO) throws ParseException {
        if (relativesRepository.alreadyExists(relativeDTO.getName(),
                relativeDTO.getLastName(), relativeDTO.getBirth()).isEmpty()) {

            var relative = relativeUtils.create(relativeDTO);
            var loggedUser = userService.getLoggedUser();
            relative.setUser(loggedUser);
            loggedUser.addRelative(relative);
            userRepository.save(loggedUser);
            relativesRepository.saveAndFlush(relative);
            return HttpStatus.OK;
        }
        return HttpStatus.CONFLICT;
    }
    //TODO: implement this method
    public HttpStatus addRelativeOfRelative(@NonNull final RelativeDTO relativeDTO) throws ParseException {
        if (relativesRepository.alreadyExists(relativeDTO.getName(),
                relativeDTO.getLastName(), relativeDTO.getBirth()).isEmpty()) {

            var relativeOpt = relativesRepository.findById(relativeDTO.getRelativeId());
            var newRelative = relativeUtils.create(relativeDTO);

//            if (relativeOpt.isPresent()) {
//                var relative = relativeOpt.get();
//                var relativeList = relativesRepository.getRelatives(relative.getId());
//                var newRelativeList = new ArrayList<Relative>();
//
//                var kinship = relativeDTO.getAs();
//
//                switch (kinship) {
//                    case "SIBLING": {
//                        newRelativeList.addAll(relativesRepository.getParents(relative.getId()));
//                        newRelativeList.addAll(relativesRepository.getSiblings(relative.getId()));
//
//                        if (!relativeList.isEmpty()) {
//                            relativeList.forEach((r) -> r.addRelative(newRelative));
//                        }
//                        relativeList.add(newRelative);
//                        relative.setRelatives(relativeList);
//
//                        newRelativeList.add(relative);
//                        newRelative.setRelatives(newRelativeList);
//
//
//                        relativesRepository.saveAndFlush(relative);
//                        return HttpStatus.OK;
//                    }
//
//                }
            return HttpStatus.CONFLICT;
        }


        return null;
    }

    public List<Relative> getAllUserRelatives() {
        //TODO: replace temp implementation of this method
        var tempRel = new Relative();
        tempRel.setName("relativeName");
        tempRel.setLastName("relativeLastName");
        return List.of(tempRel);
        //        return relativesRepository.getRelativesByUserId(userService.getLoggedUser().getId());
    }
}