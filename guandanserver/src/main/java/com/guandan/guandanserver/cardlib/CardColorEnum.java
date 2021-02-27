package com.guandan.guandanserver.cardlib;


import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum CardColorEnum {

    JOKER(0,"大小王牌"),
    HEARTS(1, "红桃"),
    SPADE(2,"黑桃"),
    CLUB(3,"梅花"),
    DIAMOND(4,"方片");

    private Integer code;
    private String desc;
}
