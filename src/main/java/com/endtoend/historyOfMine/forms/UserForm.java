package com.endtoend.historyOfMine.forms;

import jakarta.annotation.Nullable;

public class UserForm {
    private String username;
    private String password;
    private String email;

    public UserForm(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public record AuthenticationForm(String username, String password) {

    }
}
