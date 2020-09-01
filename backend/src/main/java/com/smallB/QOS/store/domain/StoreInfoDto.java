package com.smallB.QOS.store.domain;

import java.time.LocalTime;

public class StoreInfoDto {
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
    private LocalTime open_time;
    private LocalTime close_time;
    private boolean store_state;
}
