package com.mypenbox.mpb.models;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.Date;

@Entity
@Table(name = "account")
public class Account {

    private Long accountId;

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
    @Pattern(regexp = "^[a-zA-ZЁёА-я][A-Za-z0-9-'ЁёА-я]*$")
    private String lastName;

    @NotBlank
    @Size(min = 3, max = 30)
    @Pattern(regexp = "^[A-Za-z0-9-._@]*$")
    private String nickname;

    private Date birth;

    @NotBlank
    @Size(min = 8, max = 30)
    @Pattern(regexp = "^(?=.{8,})(?=.*\\d)(?=.*[a-zёа-я])(?=.*[A-ZЁА-я])(?!.*\\s).*$")
    private String password;

    private String avatar;
    private Date joinedDate;

    public Account() {
    }


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "account_id")
    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    @Column(name = "email", nullable=false, unique = true)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Column(name = "first_name", nullable=false)
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Column(name = "last_name", nullable=false)
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Column(name = "nickname", nullable=false, unique = true)
    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    @Column(name = "birth")
    public Date getBirth() {
        return birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }

    @Column(name = "password", nullable=false)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Column(name = "avatar")
    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    @CreationTimestamp
    @Temporal(TemporalType.DATE)
    @Column(name = "joined")
    public Date getJoinedDate() {
        return joinedDate;
    }

    public void setJoinedDate(Date joinedDate) {
        this.joinedDate = joinedDate;
    }
}
