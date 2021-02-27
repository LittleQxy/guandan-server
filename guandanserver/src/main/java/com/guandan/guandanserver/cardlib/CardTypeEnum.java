package com.guandan.guandanserver.cardlib;


import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum CardTypeEnum {
	Null(0, "空"),
	OnePiece(1, "单跟"),
	OneCouple(2, "一对"),
	ThreePiece(3, "三跟"),
	CommonFlush(4, "顺子"),
	
	ThreeBringTwo(5, "三带二"),
	FlyOfThreePair(6, "三连对"),
	TwoContinueThreePiece(7, "三顺"),
	
	SmallCommonOfBomb(8, "4-5根普通炸"),
	SameColorFlush(9, "同花顺"),
	BigCommonOfBomb(10, "6-10普通炸"),
	KingOfBomb(11, "王炸"),
	;

	
	private int  value;
	private String  desc;


    /**
     * 是否满足炸弹
	 * @param nPorkerNum
     * @return
     */
	public static boolean isStatisfyBomb(int nPorkerNum){
		if(nPorkerNum < 4){
			return false;
		}
		
		return true;
	}
}
