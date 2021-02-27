package com.guandan.guandanserver.cardlib.check;


import com.guandan.guandanserver.cardlib.CardColorEnum;
import com.guandan.guandanserver.cardlib.PokerPOJO;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.*;

@Data
@Slf4j
public class BaseChecker {



    /**
     * 判断是否满足出牌数量
     * @param PorkerList
     * @param nSatifyNum
     * @return
     */
    protected static boolean checkIsSatisfyPorkerNum(List<PokerPOJO> PorkerList, int nSatifyNum){
        if(null == PorkerList){
            log.error("BaseCardTypeChecker::checkIsSatisfyPorkerNum sumbitPorkerList Null error");
            return false;
        }

        if(nSatifyNum != PorkerList.size()){
            return false;
        }

        return true;
    }

    /**
     * 判断是否满足出牌范围
     * @param sumbitPorkerList
     * @param nMinNum
     * @param nMaxNum
     * @return
     */
    protected static boolean checkIsSatisfyRangePorkerNum(List<PokerPOJO> sumbitPorkerList, int nMinNum, int nMaxNum){
        if(null == sumbitPorkerList){
            log.error("BaseCardTypeChecker::checkIsSatisfyRangePorkerNum sumbitPorkerList Null error");
            return false;
        }

        if((sumbitPorkerList.size() < nMinNum) || (sumbitPorkerList.size() > nMaxNum)){
            return false;
        }

        return true;
    }


    /**
     * 是否是相同面值的扑克
     * @param cards
     * @param currentRank
     * @return
     */
    protected static boolean isSameFaceValue(List<PokerPOJO> cards, int currentRank){


        int valueCheck = -1;
        for (PokerPOJO card:cards){
            if (card.getColor().equals(CardColorEnum.HEARTS.getCode())  && card.getValue().equals(currentRank)){
                continue;
            }else if (valueCheck == -1){
                valueCheck = card.getValue();
            }else {
                if (card.getValue() != valueCheck){
                    return false;
                }
            }
        }
        return true;

    }


    /**
     * 判断是否为相同花色
     * @param cards
     * @param currentRank
     * @return
     */
    protected static boolean isSameColor(List<PokerPOJO> cards, int currentRank){
        int beginColor = -1;

        for (PokerPOJO card:cards){
            if (card.getValue().equals(currentRank) && card.getColor().equals(CardColorEnum.HEARTS.getCode())){
                continue;
            }else if (beginColor == -1){
                beginColor = card.getColor();
            }else {
                if (beginColor != card.getColor()){
                    return false;
                }
            }
        }
        return true;
    }



    /**
     * 排序
     * @param cards
     * @return
     */
    protected static void baseSort(List<PokerPOJO> cards){
        Collections.sort(cards, new Comparator<PokerPOJO>() {
            @Override
            public int compare(PokerPOJO o1, PokerPOJO o2) {
               return o1.getValue()-o2.getValue();
            }
        });
    }


    public static void main(String[] args) {
       List<PokerPOJO> list = new LinkedList<>();
       for (int i = 5;i < 9;i++){
           list.add(new PokerPOJO(i+2,1));
       }
       list.add(new PokerPOJO(3,1));
        list.add(new PokerPOJO(13,1));

        BaseChecker.baseSort(list);
        System.out.printf(list.toString());
    }

}
