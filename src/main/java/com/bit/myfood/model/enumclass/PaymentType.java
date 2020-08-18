package com.bit.myfood.model.enumclass;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum PaymentType {

    BANK_TRANSFER(0, "", ""),
    CARD(1, "", ""),
    CHECK_CARD(2, "", ""),
    CASH(3, "", "")
    ;

    private Integer id;
    private String title;
    private String description;
}
