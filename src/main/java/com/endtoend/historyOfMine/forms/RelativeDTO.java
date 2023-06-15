package com.endtoend.historyOfMine.forms;


import com.fasterxml.jackson.annotation.JsonIgnore;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class RelativeDTO {
    private static final SimpleDateFormat dateFormat
            = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    private Long id;
    private UserDTO userDTO;
    private String name;
    private String sex;
    private String lastName;
    @JsonIgnore
    private String birth;
    @JsonIgnore
    private String death;
    private String placeOfBirth;
    private Integer relatedTo;
    private String as;

    public Date getBirth() throws ParseException {
        return dateFormat.parse(this.birth);
    }
    public void setBirth(Date birth){
        if(birth != null) {
            this.birth = dateFormat.format(birth);
        }
    }

    public Date getDeath() throws ParseException {
        return dateFormat.parse(this.death);
    }
    public void setDeath(Date death) {
        if(death != null) {
            this.death = dateFormat.format(death);
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UserDTO getUserDTO() {
        return userDTO;
    }

    public void setUserDTO(UserDTO userDTO) {
        this.userDTO = userDTO;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }


    public String getPlaceOfBirth() {
        return placeOfBirth;
    }

    public void setPlaceOfBirth(String placeOfBirth) {
        this.placeOfBirth = placeOfBirth;
    }

    public Integer getRelatedTo() {
        return relatedTo;
    }

    public void setRelatedTo(Integer relatedTo) {
        this.relatedTo = relatedTo;
    }

    public String getAs() {
        return as;
    }

    public void setAs(String as) {
        this.as = as;
    }
}
