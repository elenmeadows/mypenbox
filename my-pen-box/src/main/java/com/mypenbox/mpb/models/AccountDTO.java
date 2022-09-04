package com.mypenbox.mpb.models;

import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
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
}
