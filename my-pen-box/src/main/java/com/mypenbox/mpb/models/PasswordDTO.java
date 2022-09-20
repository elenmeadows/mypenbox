package com.mypenbox.mpb.models;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class PasswordDTO {

    private String token;

    private String oldPassword;

    @NotBlank
    @Size(min = 8, max = 30)
    @Pattern(regexp = "^(?=.{8,})(?=.*\\d)(?=.*[a-zёа-я])(?=.*[A-ZЁА-я])(?!.*\\s).*$")
    private String newPassword;
}
