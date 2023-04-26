package com.endtoend.bfit.websecurity;

import org.springframework.security.core.GrantedAuthority;

public enum Authority implements GrantedAuthority {
    ADMIN("Highest level of authority"),
    MODERATOR("Moderate level of authority"),
    USER("Lowest level of authority");

    Authority(String description) {
    }

    @Override
    public String getAuthority() {
        return null;
    }
}
