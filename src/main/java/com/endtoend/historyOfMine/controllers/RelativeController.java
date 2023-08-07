package com.endtoend.historyOfMine.controllers;

import com.endtoend.historyOfMine.forms.RelativeDTO;
import com.endtoend.historyOfMine.forms.RelativeForm;
import com.endtoend.historyOfMine.service.RelativeService;
import com.endtoend.historyOfMine.service.UserService;
import com.endtoend.historyOfMine.utils.Mapper;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user/relative")
public class RelativeController {
    private final RelativeService relativeService;
    private final UserService userService;
    private final Mapper mapper;

    public RelativeController(RelativeService relativeService, UserService userService, Mapper mapper) {
        this.relativeService = relativeService;
        this.userService = userService;
        this.mapper = mapper;
    }

    @PostMapping
    public void addRelative(@RequestBody RelativeForm relativeForm){
        var loggedUser = userService.getLoggedUser();
        relativeForm.setRelatedTo(loggedUser.getId());
        relativeForm.setUser(loggedUser);
        relativeService.addRelative(relativeForm);
    }

    @GetMapping
    @ResponseBody
    public List<RelativeDTO> getAllRelatives(){
        var list = relativeService.getAllUserRelatives();
        var response = new ArrayList<RelativeDTO>();

        for(var relative : list) {
            response.add(mapper.convertToDTO(relative));
        }
        return response;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }
}
