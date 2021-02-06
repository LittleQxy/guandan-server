package com.guan.card.cardTypeChecker.manger;

import org.springframework.stereotype.Service;

/**
 * @Author: qixiangyang.121
 * @Description:
 * @Date: 19:53 2021/2/6
 */
@Service
public class PorkerMgr {

    public Boolean isBelongToBigKing(int value){
        return true;
    }


    public Boolean isBelongToSmallKing(int value){
        return true;
    }


    public int getFaceValue(int value){
        return value;
    }


    public int getColor(int value){
        return value;
    }
}
