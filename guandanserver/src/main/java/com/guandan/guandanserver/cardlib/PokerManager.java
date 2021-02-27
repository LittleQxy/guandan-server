package com.guandan.guandanserver.cardlib;


import java.util.Collections;
import java.util.LinkedList;

public class PokerManager {

    private LinkedList<PokerPOJO> pokers = new LinkedList<>();


    /**
     * 初始化
     */
    PokerManager(){
        for (int i = 2;i <= 14; i++){
            for (int j = 1;j <= 4; j++){
                pokers.add(new PokerPOJO(i,j));
                pokers.add(new PokerPOJO(i,j));
            }
        }
        pokers.add(new PokerPOJO(CardFaceValueEnum.Small_joker.getCode(), CardColorEnum.JOKER.getCode()));
        pokers.add(new PokerPOJO(CardFaceValueEnum.Big_joker.getCode(), CardColorEnum.JOKER.getCode()));
        pokers.add(new PokerPOJO(CardFaceValueEnum.Big_joker.getCode(), CardColorEnum.JOKER.getCode()));
        pokers.add(new PokerPOJO(CardFaceValueEnum.Small_joker.getCode(), CardColorEnum.JOKER.getCode()));
    }

    /**
     * 洗牌
     */
    public void shuffle() {
        Collections.shuffle(pokers);
    }

    /**
     * 发牌
     * @return
     */
    protected PokerPOJO get() {
        return pokers.pollFirst();
    }

}
