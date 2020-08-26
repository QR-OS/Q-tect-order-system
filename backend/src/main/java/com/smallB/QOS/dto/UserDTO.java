package com.smallB.QOS.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class UserDTO {
    private String user_id;
    private String user_name;
    private String user_pw;
    private String user_ph;
    private String user_email;
    private String store_id;
    private int status;
}
