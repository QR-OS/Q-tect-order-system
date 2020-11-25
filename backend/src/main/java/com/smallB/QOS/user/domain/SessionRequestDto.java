package com.smallB.QOS.user.domain;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

import javax.validation.constraints.NotNull;

@Data
@Builder
public class SessionRequestDto {
    @NotNull(message = "user_id must not null")
    private String user_id;
    @NotNull(message = "user_pw must not null")
    private String user_pw;
}
