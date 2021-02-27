package com.guandan.guandanserver.cardlib.check;


import com.guandan.guandanserver.cardlib.CardColorEnum;
import com.guandan.guandanserver.cardlib.CardFaceValueEnum;
import com.guandan.guandanserver.cardlib.PokerPOJO;
import lombok.Data;

import java.util.List;

@Data
public class CommonChecker {


    /**
     * 判断是否为对子
     * @param cards
     * @param currentRank
     * @return
     */
    public static boolean isOneCouple(List<PokerPOJO> cards, int currentRank){
        if (BaseChecker.checkIsSatisfyPorkerNum(cards,2)){
            return BaseChecker.isSameFaceValue(cards, currentRank);
        }
        return false;
    }


    /**
     * 判断是否为三张
     * @param cards
     * @param currentRank
     * @return
     */
    public static boolean isThreePiece(List<PokerPOJO> cards, int currentRank){
        if (BaseChecker.checkIsSatisfyPorkerNum(cards,2)){
            return BaseChecker.isSameFaceValue(cards, currentRank);
        }
        return false;
    }

    /**
     * 判断是否为顺子
     * @param cards
     * @param currentRank
     * @return
     */
    public static boolean isCommonFlush(List<PokerPOJO> cards, int currentRank){
        if (BaseChecker.checkIsSatisfyPorkerNum(cards,5)){

            int rankCardNum = checkBefore(cards,currentRank);

            //1.先排序
            BaseChecker.baseSort(cards);
            //2.判断特殊情况，以及级牌充当空位的情况
            int beginValue = 0;
            for (PokerPOJO card:cards){
                if (card.getValue() == -1){
                    //恢复数据
                    card.setValue(currentRank);
                    continue;
                }else if (beginValue == 0){
                    beginValue = card.getValue();
                }else {
                    //1.判断 A 2 3 4 5的情况
                    if (card.getValue().equals(CardFaceValueEnum.A.getCode())){
                        if (beginValue == CardFaceValueEnum.Three.getCode() && rankCardNum == 2){
                            return true;
                        }
                        if (beginValue == CardFaceValueEnum.four.getCode() && rankCardNum == 1){
                            return true;
                        }
                        if (beginValue == CardFaceValueEnum.five.getCode()){
                            return true;
                        }
                    }
                    int cnt = card.getValue() - beginValue;
                    //2.剩下的其余情况
                    if (cnt > 3){
                        return false;
                    }else if (cnt > 1){
                        if (cnt <= rankCardNum){
                            cnt--;
                            rankCardNum -= cnt;
                        }else {
                            return false;
                        }
                        beginValue = card.getValue();
                    }else {
                        beginValue = card.getValue();
                    }
                }
            }

            return false;
        }
        return false;
    }


    /**
     * 是否是三代二
     * @param cards
     * @param currentRank
     * @return
     */
    public static boolean isThreeBringTwo(List<PokerPOJO> cards, int currentRank){
        if (!BaseChecker.checkIsSatisfyPorkerNum(cards,5)){
            int rankCardNum = checkBefore(cards,currentRank);
            int[] number = new int[5];
            int i = 0;
            BaseChecker.baseSort(cards);
            for (PokerPOJO card:cards){
                if (card.getValue() == -1){
                    card.setValue(currentRank);
                    number[i++]=-1;
                    continue;
                }else {
                    number[i++]=card.getValue();
                }
            }
            //3代2，极端情况下也只有一张逢人配,除非。。。。
            if (rankCardNum == 1){
                //1张逢人配，则需要两个对子
                boolean isSame1 = number[1] == number[2];
                boolean isSame2 = number[3] == number[4];
                return isSame1 && isSame2;
            } else if (rankCardNum == 2){
                //脑残出发
                boolean isSame1 = number[3] == number[4];
                boolean isSame2 = number[3] == number[2];
                return isSame1 || isSame2;
            }else {
                //一个对子和一个三张
                boolean isSame1 = number[0] == number[1];
                boolean isSame2 = number[3] == number[4];
                boolean isSame3 = (number[2] == number[1] || number[2] == number[3]);
                return isSame1 && isSame2 && isSame3;

            }
        }
        return false;
    }


    /**
     * 是否是三连对
     * @param cards
     * @param currentRank
     * @return
     */
    public static boolean isFlyOfThreePair(List<PokerPOJO> cards, int currentRank){
        if (!BaseChecker.checkIsSatisfyPorkerNum(cards,6)) {

            int rankCardNum = checkBefore(cards, currentRank);

            BaseChecker.baseSort(cards);
            int[] number = new int[6];
            int i = 0;
            for (PokerPOJO card:cards){
                if (card.getValue().equals(-1)){
                    number[i++] = -1;
                    card.setValue(currentRank);
                    continue;
                }
                number[i++] = card.getValue();
            }

            //两张级牌
            if (rankCardNum == 2){
                //三连对的话，抛出两张逢人配，必须有一个对子，并且是连着的
                boolean isDouble = (number[2] == number[3] || number[3] == number[4] || number[4] == number[5]);
                boolean lens = (number[5]-number[2] == 2);
                return isDouble && lens;
            }else if (rankCardNum == 1){
                //三连对一张逢人配，必须两个对子，并且连着
                boolean isDouble = (number[2] == number[3] && number[4] == number[5]) ||
                        (number[1] == number[2] && number[4] == number[5]) || (number[1] == number[2] && number[4] == number[3]);
                boolean lens = (number[5]-number[1] == 2);
                return isDouble && lens;
            }else {
                boolean isDouble =  (number[0] == number[1]) && (number[2] == number[3]) && number[4] == number[5];
                boolean lens = (number[5] - number[0] == 2);
                return isDouble && lens;
            }
        }
        return false;
    }


    /**
     * 是否是两个三张
     * @param cards
     * @param currentRank
     * @return
     */
    public static boolean isTwoContinueThreePiece(List<PokerPOJO> cards, int currentRank){
        if (!BaseChecker.checkIsSatisfyPorkerNum(cards,6)) {

            int rankCardNum = checkBefore(cards, currentRank);

            BaseChecker.baseSort(cards);
            int[] number = new int[6];
            int i = 0;
            for (PokerPOJO card : cards) {
                if (card.getValue().equals(-1)) {
                    number[i++] = -1;
                    card.setValue(currentRank);
                    continue;
                }
                number[i++] = card.getValue();
            }

            //两张级牌
            if (rankCardNum == 2) {
                //两个三张，两个逢人配的话，需要保证两个对子，或者一个三张一个单
                boolean lens = (number[5] - number[2] == 1);
                boolean isThree = (number[2] == number[3] && number[4] == number[5]) ||
                        (number[2] == number[3] && number[3] == number[4]) || (number[4] == number[3] && number[5] == number[4]);
                return lens && isThree;
            }else if (rankCardNum == 1){
                //两个三张，一个逢人配的话，需要保证一个对子，一个三张
                boolean lens = (number[5] - number[2] == 1);
                boolean isThree = (number[1] == number[2] && number[2] == number[3] && number[4] == number[5]) ||
                        (number[1] == number[2] && number[4] == number[3] && number[4] == number[5]);
                return lens && isThree;
            }else {
                //两个三张，0个逢人配的话，需要保证2个三张
                boolean lens = (number[5] - number[2] == 1);
                boolean isThree = (number[0] == number[1] && number[1] == number[2] && number[4] == number[3] && number[4] == number[5]);
                return lens && isThree;
            }
        }
        return false;
    }


    /**
     * 是否是同花顺
     * @param cards
     * @param currentRank
     * @return
     */
    public static boolean isSameColorFlush(List<PokerPOJO> cards, int currentRank){
        return isCommonFlush(cards,currentRank) && BaseChecker.isSameColor(cards,currentRank);
    }




    private static int checkBefore(List<PokerPOJO> cards, int currentRank){
        int rankCardNum = 0;
        //0.移除级牌，记录数量
        for (PokerPOJO card : cards) {
            if (card.getValue().equals(currentRank) && card.getColor().equals(CardColorEnum.HEARTS.getCode())) {
                card.setValue(-1);
                rankCardNum++;
            }
        }

        if (rankCardNum > 2) {
            throw new RuntimeException("牌面错误");
        }
        return rankCardNum;
    }



}
