package com.endtoend.historyOfMine.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;

import java.util.Date;
import java.util.List;
@ToString(exclude = "relatives")
@EqualsAndHashCode(exclude = "relatives")
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
    private Integer id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private User user;
    private String name;
    private String sex;
    private String lastName;
    private Date birth;
    private Date death;
    //TODO maps api
    private String placeOfBirth;
    @JsonIgnoreProperties("relatives")
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name="tbl_relatives",
            joinColumns =@JoinColumn(name="r_id",
            referencedColumnName = "id"),
            inverseJoinColumns=@JoinColumn(name="relative_of_id",
            referencedColumnName = "id"))
    private List<Relative> relatives;

    @JsonIgnoreProperties("relatives")
    @JoinTable(name="tbl_relatives",
            joinColumns = @JoinColumn(name="r_id",
            referencedColumnName = "id"))
    private String as;

    public Relative() {
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Relative> getRelatives() {
        return relatives;
    }

    public void setRelatives(List<Relative> relatives) {
        this.relatives = relatives;
    }

    public void addRelative(Relative relative){
        getRelatives().add(relative);
    }

    public Integer getId() {
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

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Date getDeath() {
        return death;
    }

    public void setDeath(Date death) {
        this.death = death;
    }

    public String getAs() {
        return as;
    }

    public void setAs(String as) {
        this.as = as;
    }

    public enum Kinship{
        SELF,
        PARENT,
        CHILD,
        SIBLING,
        SPOUSE
    }



}
