package com.smallB.QOS.order.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class OrderHistoryDto {
    @PositiveOrZero
    private int order_type;
    private Date order_time;
    private Date book_time;
    private String order_id;
    @PositiveOrZero
    private int total_price;
    private String pay_type;
    private String order_state;
    @NotEmpty @NotBlank @NotNull
    private String store_id;
    @NotEmpty @NotBlank @NotNull
    private String user_id;
    private String store_name;
    private String ceo_product_name;
    private int detail_order_count;
}
