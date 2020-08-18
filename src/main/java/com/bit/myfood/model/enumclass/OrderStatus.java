package com.bit.myfood.model.enumclass;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum OrderStatus {

    COMPLETE(0, "완료", "주문 완료"),
    CONFIRM(1, "예정", "주문 예정"),
    ORDERING(2, "주문중", "주문 중"),
    ;

    private Integer id;
    private String title;
    private String description;

}
