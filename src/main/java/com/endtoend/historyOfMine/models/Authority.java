package com.endtoend.historyOfMine.models;

import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
@Entity
public enum Authority implements GrantedAuthority {
    ADMIN("Highest level of authority"),
    MODERATOR("Moderate level of authority"),
    USER("Lowest level of authority");

    Authority(String description) {
    }

    @Id
    private Integer id;

    @Override
    public String getAuthority() {
        return null;
    }
}
