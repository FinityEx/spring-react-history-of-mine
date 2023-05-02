package com.endtoend.historyOfMine.forms;

import com.endtoend.historyOfMine.models.Relative;
import com.endtoend.historyOfMine.models.User;

import java.util.Date;
import java.util.Objects;
import java.util.UUID;

public class RelativeForm {
    private User user;
    private String name;
    private Relative.Sex sex;
    private String lastName;
    private Date birth;
    private Date death;
    private String placeOfBirth;
    private UUID relatedTo;
    private Relative.Kinship as;

    public RelativeForm(UUID relatedTo, Relative.Kinship as){
        this.relatedTo = Objects.requireNonNull(relatedTo, "Person must be related to someone!");
        this.as = Objects.requireNonNull(as, "Type of kinship is required!");
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setSex(Relative.Sex sex) {
        this.sex = sex;
    }

    public Date getBirth() {
        return birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }

    public Date getDeath() {
        return death;
    }

    public void setDeath(Date death) {
        this.death = death;
    }

    public String getPlaceOfBirth() {
        return placeOfBirth;
    }

    public void setPlaceOfBirth(String placeOfBirth) {
        this.placeOfBirth = placeOfBirth;
    }

    public UUID getRelatedTo() {
        return relatedTo;
    }

    public void setRelatedTo(UUID relatedTo) {
        this.relatedTo = relatedTo;
    }

    public Relative.Kinship getAs() {
        return as;
    }

    public void setAs(Relative.Kinship as) {
        this.as = as;
    }

    public RelativeForm(){}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Relative.Sex getSex() {
        return sex;
    }

}
