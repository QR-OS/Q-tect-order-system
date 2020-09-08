package com.smallB.QOS.user.domain;

import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
@Getter
@Setter
@Builder
public class UserDto {
    @NotEmpty @NotBlank @NotNull
    private String user_id;
    private String user_name;
    private String user_pw;
    private String user_ph;
    @Email
    private String user_email;
    private String store_id = null;
    private int status = 1;
    private boolean deleted;
}
