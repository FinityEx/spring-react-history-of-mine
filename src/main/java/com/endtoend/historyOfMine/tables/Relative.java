package com.endtoend.historyOfMine.tables;

import jakarta.persistence.*;

import java.util.Date;
import java.util.UUID;

@Entity
public class Relative  {
    @Id
    @GeneratedValue
    private UUID uuid;
    @ManyToOne
    private User user;
    protected String name;
    @Enumerated(EnumType.STRING)
    protected Sex sex;
    protected String lastName;
    protected Date birth;
    protected Date death;
    //TODO maps api
    @Column(name = "place_of_birth")
    protected String placeOfBirth;
    private UUID relatedTo;
    @Enumerated(value = EnumType.STRING)
    private Kinship as;

    public Relative() {
    }

    public Relative(UUID uuid){
        this.uuid = uuid;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public UUID getUuid() {
        return uuid;
    }


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

    public Sex getSex() {
        return sex;
    }

    public void setSex(Sex sex) {
        this.sex = sex;
    }

    public Date getDeath() {
        return death;
    }

    public void setDeath(Date death) {
        this.death = death;
    }

    public UUID getRelatedTo() {
        return relatedTo;
    }

    public void setRelatedTo(UUID relatedTo) {
        this.relatedTo = relatedTo;
    }

    public Kinship getAs() {
        return as;
    }

    public void setAs(Kinship as) {
        this.as = as;
    }

    public enum Kinship{
        SELF,
        PARENT,
        CHILD,
        SIBLING,
        SPOUSE
    }
    public enum Sex{
        MALE,
        FEMALE
    }


}
