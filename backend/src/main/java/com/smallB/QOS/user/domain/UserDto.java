package com.smallB.QOS.user.domain;

import lombok.*;

import javax.validation.Valid;

@AllArgsConstructor
@Getter
@Setter
@Builder
public class UserDto {
    @Valid
    private String user_id;
    @Valid
    private String user_name;
    @Valid
    private String user_pw;
    private String user_ph;
    private String user_email;
    private String store_id = null;
    @Valid
    private int status = 1;
}
