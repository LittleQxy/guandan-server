package com.guan.card.cardlib.check;

import com.guan.card.cardlib.CardColorEnum;
import com.guan.card.cardlib.CardFaceValueEnum;
import com.guan.card.cardlib.PokerPOJO;
import lombok.Data;

import java.util.List;

/**
 * 判断是否为炸弹
 */
@Data
public class BombChecker {


    /**
     * 是否是王炸
     * @param pokers
     * @param currentRank
     * @return
     */
    public static boolean isKingBomb(List<PokerPOJO> pokers, int currentRank){
        if (!BaseChecker.checkIsSatisfyPorkerNum(pokers,4)){
            return false;
        }

        int smallJokerNum = 0,bigJokerNum = 0;
        for (PokerPOJO pojo:pokers){
            if (pojo.getColor() != CardColorEnum.JOKER.getCode()){
                return false;
            }
            if (pojo.getValue().equals(CardFaceValueEnum.Small_joker.getCode())){
                smallJokerNum++;
            }else if (pojo.getValue().equals(CardFaceValueEnum.Big_joker.getCode())){
                bigJokerNum++;
            }
        }

        return smallJokerNum == bigJokerNum && smallJokerNum == 2;
    }

    /**
     * 是否是炸弹
     * @param pokers
     * @param currentRank  当前级牌，用于逢人配
     * @return
     */
    public static boolean isCommonBomb(List<PokerPOJO> pokers, int currentRank){
        if (!BaseChecker.checkIsSatisfyRangePorkerNum(pokers,4,10)){
            return false;
        }
        return BaseChecker.isSameFaceValue(pokers, currentRank);
    }




}
