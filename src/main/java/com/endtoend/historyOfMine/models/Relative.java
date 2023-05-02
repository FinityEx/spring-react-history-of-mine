package com.endtoend.historyOfMine.models;

import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;

import java.util.Date;
import java.util.UUID;

@Entity
public class Relative  {
    @Id
    @GeneratedValue(generator = "sequence-generator")
    @GenericGenerator(
            name = "sequence-generator",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                    @org.hibernate.annotations.Parameter(name = "sequence_name", value = "relative_sequence"),
                    @org.hibernate.annotations.Parameter(name = "initial_value", value = "1"),
                    @org.hibernate.annotations.Parameter(name = "increment_size", value = "1")
            }
    )
    private Long id;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Long getId() {
        return id;
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
