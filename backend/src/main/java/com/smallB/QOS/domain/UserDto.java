package com.smallB.QOS.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@Builder
public class UserDto {
    private String user_id;
    private String user_name;
    private String user_pw;
    private String user_ph;
    private String user_email;
    private String store_id = null;
    private int status = 1;
}
