package com.smallB.QOS.storeInfo.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalTime;

@Getter
@NoArgsConstructor
public class StoreInfoDto {
    @Setter
    private String store_id;
    private String store_name;
    private String store_num;
    private String post_num;
    private String address1;
    private String address2;
    private String store_tel;
    private boolean orderable;
    private boolean bookable;
    private String holiday;
    private String open_time;
    private String close_time;
    private boolean store_state;
}
