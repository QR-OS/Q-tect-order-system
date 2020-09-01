package com.smallB.QOS.user.domain;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SessionResponseDto {
    private String accessToken;
}
