package com.guandan.guandanserver.cardlib;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum CardFaceValueEnum {

    Two(2,"2"),
    Three(3,"3"),
    four(4,"4"),
    five(5,"5"),
    six(6,"6"),
    seven(7,"7"),
    eight(8,"8"),
    nine(9,"9"),
    ten(10,"10"),
    J(11,"J"),
    Q(12,"Q"),
    K(13,"K"),
    A(14,"A"),
    Small_joker(30,"小王"),
    Big_joker(40,"大王");

    private Integer code;
    private String desc;
}
