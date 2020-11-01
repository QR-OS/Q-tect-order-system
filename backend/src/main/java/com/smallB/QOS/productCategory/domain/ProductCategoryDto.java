package com.smallB.QOS.productCategory.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@NotNull
@Builder

public class ProductCategoryDto {
    @NotEmpty @NotBlank @NotNull
    private String product_category;
    @NotEmpty @NotBlank @NotNull
    private String store_id;
}
