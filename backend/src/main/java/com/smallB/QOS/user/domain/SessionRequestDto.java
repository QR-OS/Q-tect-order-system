package com.smallB.QOS.user.domain;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SessionRequestDto {
    private String user_id;
    private String user_pw;
}
