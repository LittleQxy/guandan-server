package com.guan.card.cardTypeChecker;

import java.util.List;


public class ThreePieceChecker extends BaseCardTypeChecker{
	public ThreePieceChecker(){
		super();
	}
	
	@Override
    public int isBelongToSpecficType(List<Integer> sumbitPorkerList, int nCurrentMajorCard){
		int nErrorResult = 0;
		boolean bCheckResult = this.checkIsSatisfyPorkerNum(sumbitPorkerList, 3);
		if(false == bCheckResult){
			return nErrorResult;
		}
		
		
		int nFaceValueResult = this.isSameFaceValueOfPorker(sumbitPorkerList, nCurrentMajorCard);
		return nFaceValueResult;
	}
}


