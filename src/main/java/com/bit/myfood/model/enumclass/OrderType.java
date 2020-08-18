package com.bit.myfood.model.enumclass;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum OrderType {

    ALL(0, "일괄", "일괄 구매"),
    EACH(1, "개별", "개별 구매"),
    ;

    private Integer id;
    private String title;
    private String description;

}
