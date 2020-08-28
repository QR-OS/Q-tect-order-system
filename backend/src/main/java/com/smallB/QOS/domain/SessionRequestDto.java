package com.smallB.QOS.domain;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SessionRequestDto {
    private String user_id;
    private String user_pw;
}
