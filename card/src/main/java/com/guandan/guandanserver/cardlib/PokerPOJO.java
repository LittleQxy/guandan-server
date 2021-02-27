package com.guan.card.cardlib;


import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 扑克牌的对象类
 */
@Data
@AllArgsConstructor
public class PokerPOJO {

    /**
     * 面值
     */
    private Integer value;

    /**
     * 花色
     */
    private Integer color;

}
