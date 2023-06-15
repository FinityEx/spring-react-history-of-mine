package com.endtoend.historyOfMine.controllers;

import com.endtoend.historyOfMine.forms.UserForm;
import com.endtoend.historyOfMine.websecurity.AuthService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.naming.AuthenticationException;

@Controller
@RequestMapping("/login")
public class LoginController {
    private final AuthService authService;

    public LoginController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping
    @ResponseBody
    public ResponseEntity<?> login(@RequestBody UserForm.AuthenticationForm authenticationForm,
                                    HttpServletResponse response) throws AuthenticationException {

        var cookie = new Cookie("jwtToken", authService.authenticate(authenticationForm).getAccessToken());
        cookie.setMaxAge(-1);
        cookie.setPath("/");
        cookie.setSecure(false);
        cookie.setHttpOnly(true);
        response.addCookie(cookie);

        return new ResponseEntity<>(HttpStatus.OK);

    }
}