package com.endtoend.bfit.forms;

import jakarta.annotation.Nullable;

public class UserForm {
    private String username;
    private String password;
    private String email;

    public UserForm(String username, String password, @Nullable String email) {
        this.username = username;
        this.password = password;
        this.email = email;
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

    public final static class AuthenticationForm{
        private final String username;
        private final String password;

        public AuthenticationForm(String username, String password) {
            this.username = username;
            this.password = password;
        }

        public String getUsername() {
            return username;
        }

        public String getPassword() {
            return password;
        }

    }
}
