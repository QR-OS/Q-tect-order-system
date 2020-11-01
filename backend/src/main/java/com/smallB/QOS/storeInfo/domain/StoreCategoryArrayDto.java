package com.smallB.QOS.storeInfo.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import java.util.ArrayList;

@Getter
@Setter
@AllArgsConstructor
public class StoreCategoryArrayDto {
    private String store_id;
    private ArrayList<StoreTypeDto> storeType;
}
