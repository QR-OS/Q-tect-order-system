package com.smallB.QOS.product.domain;

import lombok.*;

import javax.validation.constraints.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductDto {
    @NotEmpty @NotBlank @NotNull
    private String product_id;
    @NotEmpty @NotBlank @NotNull
    private String store_id;
    private String product_category;
    private String product_name;
    @PositiveOrZero
    private int product_price;
    @PositiveOrZero
    private int product_stock;
    private String product_image;
    private boolean product_state = true;
}
