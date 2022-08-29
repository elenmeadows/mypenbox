package com.mypenbox.mpb.models;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Date;

public class AccountDTO {

    @NotBlank
    @Email
    @Pattern(regexp = "([\\w\\.\\-_]+)?\\w+@[\\w-_]+(\\.\\w+){1,}$")
    private String email;

    @NotBlank
    @Size(min = 1, max = 30)
    @Pattern(regexp = "^[a-zA-ZЁёА-я][A-Za-z-'ЁёА-я]*$")
    private String firstName;

    @NotBlank
    @Size (min = 1, max = 30)
    @Pattern(regexp = "^[a-zA-ZЁёА-я][A-Za-z-'ЁёА-я]*$")
    private String lastName;

    @NotBlank
    @Size(min = 3, max = 30)
    @Pattern(regexp = "^[A-Za-z0-9-._]*$")
    private String nickname;

    private Date birth;

    @NotBlank
    @Size(min = 8, max = 30)
    @Pattern(regexp = "^(?=.{8,})(?=.*\\d)(?=.*[a-zёа-я])(?=.*[A-ZЁА-я])(?!.*\\s).*$")
    private String password;

    private String avatar;
    private Date joinedDate;

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getNickname() {
        return nickname;
    }
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public Date getBirth() {
        return birth;
    }
    public void setBirth(Date birth) {
        this.birth = birth;
    }

    public String getPassword() { return password; }
    public void setPassword(String password) {
        this.password = password;
    }

    public String getAvatar() {
        return avatar;
    }
    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Date getJoinedDate() {
        return joinedDate;
    }
    public void setJoinedDate(Date joinedDate) {
        this.joinedDate = joinedDate;
    }
}
