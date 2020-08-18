package com.bit.myfood.model.enumclass;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum CategoryType {

    BEAUTY(0, "화장품"),
    CLOTHING(1, "의류"),
    COMPUTER(2, "전자제품"),
    DUTY_FREE(3, "면세점"),
    FOOD(4, "식료품"),
    INTERIOR(5, "인테리어"),
    MULTI_SHOP(6, "멀티숍"),
    SHOPPING_MALL(7, "쇼핑몰"),
    SPORTS(8, "스포츠")
    ;

    private Integer id;
    private String title;

}
