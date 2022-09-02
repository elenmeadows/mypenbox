package com.mypenbox.mpb.models;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;


@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@Entity
@Table(name = "account")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "account_id")
    private Long accountId;

    @Column(name = "email", nullable=false, unique = true)
    private String email;

    @Column(name = "first_name", nullable=false)
    private String firstName;

    @Column(name = "last_name", nullable=false)
    private String lastName;

    @Column(name = "nickname", nullable=false, unique = true)
    private String nickname;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    @Column(name = "birth")
    private Date birth;

    @Column(name = "password", nullable=false)
    private String password;

    @Column(name = "avatar")
    private String avatar;

    @Enumerated(EnumType.STRING)
    private AccountRole accountRole;

    @CreationTimestamp
    @Temporal(TemporalType.DATE)
    @Column(name = "joined")
    private Date joinedDate;

    private Boolean locked;

    private Boolean enabled;

    public Account(String email,
                   String firstName,
                   String lastName,
                   String nickname,
                   Date birth,
                   String password,
                   String avatar,
                   AccountRole accountRole,
                   Date joinedDate,
                   Boolean locked,
                   Boolean enabled) {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.nickname = nickname;
        this.birth = birth;
        this.password = password;
        this.avatar = avatar;
        this.accountRole = accountRole;
        this.joinedDate = joinedDate;
        this.locked = locked;
        this.enabled = enabled;
    }
}
