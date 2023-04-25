package com.endtoend.historyOfMine.tables;


import com.endtoend.historyOfMine.websecurity.Authority;
import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;

@Entity
 public class User implements UserDetails{
    @Id
    @GeneratedValue
    private UUID id;
    @Column
    private String username;
    @Column
    private String password;
    @Column
    private String email;
    @Column
    private boolean accountNonLocked;
    @Enumerated(EnumType.STRING)
    private Authority authority;
    private String name;
    @Enumerated(EnumType.STRING)
    private Relative.Sex sex;
    private String lastName;
    private Date birth;
    @Column(name = "place_of_birth")
    private String placeOfBirth;
    @OneToMany(mappedBy = "user")
    private List<Relative> relatives;

    private User(){}

    public User(UUID id, String username, String password, String email) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.accountNonLocked = true;
        this.authority = Authority.USER;
    }


    public UUID getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return accountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(authority);
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setAuthority(Authority authority) {
        this.authority = authority;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setAccountNonLocked(boolean accountNonLocked) {
        this.accountNonLocked = accountNonLocked;
    }

    public List<Relative> getRelatives() {
        return relatives;
    }

    public void addRelative(Relative relative){
        this.relatives.add(relative);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Relative.Sex getSex() {
        return sex;
    }

    public void setSex(Relative.Sex sex) {
        this.sex = sex;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getBirth() {
        return birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }

    public String getPlaceOfBirth() {
        return placeOfBirth;
    }

    public void setPlaceOfBirth(String placeOfBirth) {
        this.placeOfBirth = placeOfBirth;
    }


}
