package com.endtoend.historyOfMine.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;

import java.util.Date;

@Entity(name = "Relatives")
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
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private User user;
    private String name;
    private String sex;
    private String lastName;
    @Column(name = "date_of_birth")
    private Date birth;
    private Date death;
    //TODO maps api
    @Column(name = "place_of_birth")
    private String placeOfBirth;
    private Integer relatedTo;
    private String as;

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

    public String getSex() {
        return sex;
    }

    public void setSex(Sex sex) {
        this.sex = sex.toString();
    }

    public Date getDeath() {
        return death;
    }

    public void setDeath(Date death) {
        this.death = death;
    }

    public Integer getRelatedTo() {
        return relatedTo;
    }

    public void setRelatedTo(Integer relatedTo) {
        this.relatedTo = relatedTo;
    }

    public String getAs() {
        return as.toString();
    }

    public void setAs(Kinship as) {
        this.as = as.toString();
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
