package com.guandan.common.porkerEnumSet;


import lombok.AllArgsConstructor;
import lombok.Getter;


/**
 * 扑克面值的枚举类，
 */
@AllArgsConstructor
@Getter
public enum FaceValueEnum {
	FaceValueNum_Null(0, "异常"),
	FaceValueEnum_2(2, "2"),
	FaceValueEnum_3(3, "3"),
	FaceValueEnum_4(4, "4"),
	FaceValueEnum_5(5, "5"),
	FaceValueEnum_6(6, "6"),
	FaceValueEnum_7(7, "7"),
	FaceValueEnum_8(8, "8"),
	FaceValueEnum_9(9, "9"),
	FaceValueEnum_10(10, "10"),
	FaceValueEnum_J(11, "J"),
	FaceValueEnum_Q(12, "Q"),
	FaceValueEnum_K(13, "K"),
	FaceValueEnum_A(14, "A"),
	FaceValueEnum_SmallJorker(30, "小王"),
	FaceValueEnum_BigJorker(40, "大王"),
	;
	
	// 数值
	public static final int FACE_VALUE_NULL = 0;
	
	public static final int FACE_VALUE_SMALL_JORKER = 30;  	//小王
	public static final int FACE_VALUE_BIG_JORKER   = 40;	//大王
	
	public static final int FACE_VALUE_2_VALUE = 2;
	public static final int FACE_VALUE_3_VALUE = 3;
	public static final int FACE_VALUE_4_VALUE = 4;
	public static final int FACE_VALUE_5_VALUE = 5;
	public static final int FACE_VALUE_6_VALUE = 6;
	public static final int FACE_VALUE_7_VALUE = 7;
	public static final int FACE_VALUE_8_VALUE = 8;
	public static final int FACE_VALUE_9_VALUE = 9;
	public static final int FACE_VALUE_10_VALUE = 10;
	public static final int FACE_VALUE_J_VALUE = 11;
	public static final int FACE_VALUE_Q_VALUE = 12;
	public static final int FACE_VALUE_K_VALUE = 13;
	public static final int FACE_VALUE_A_VALUE = 14;
	
	public static final int Greater_Result = 1;		//大于
	public static final int Equal_Result   = 0;		//等于
	public static final int Less_Result    = -1; 	//小于
	
	private final int value;
    private final String desc;


	/**
	 * 比较两个扑克面值的大小
	 * @param nFaceValueOne
	 * @param nFaceValueTwo
	 * @return
	 */
	public static int compareTwoFaceValue(int nFaceValueOne, int nFaceValueTwo){
		if(nFaceValueOne == nFaceValueTwo){
			return Equal_Result;
		}
		
		if(nFaceValueOne > nFaceValueTwo){
			return Greater_Result;
		}
		
		return Less_Result;
	}
	
   public static int getSpecficHeartByFaceValue(int nFaceValue){
	   if(FACE_VALUE_A_VALUE == nFaceValue){
		   return PorkerValueEnum.PORKER_HEART_A_VALUE;
	   }
	   
	   int nCardTypeActor = nFaceValue - 2;
	   int nSpecficHeartValue = (nCardTypeActor * 4) + 1;
	   return nSpecficHeartValue;
   } 
   
   public static boolean isBelongToKing(int nFaceValue){
	   if(FACE_VALUE_SMALL_JORKER == nFaceValue){
		   return true;
	   }
	   
	   if(FACE_VALUE_BIG_JORKER == nFaceValue){
		   return true;
	   }
	   
	   return false;
   }

	/**
	 * 是否是下一张类型
	 * @param nPreCardType
	 * @param nNextCardType
	 * @return
	 */
   public static boolean isNextCardType(int nPreCardType, int nNextCardType){
		if(nPreCardType < FACE_VALUE_2_VALUE){
			return false;
		}
		
		if(nPreCardType + 1 == nNextCardType){
			return true;
		}
		
		return false;
	}

	/**
	 * 获取当前扑克的下一张面值
	 * @param nCardType
	 * @return
	 */
	public static int getNextCardType(int nCardType){
		if((nCardType >= FACE_VALUE_A_VALUE) || (nCardType < FACE_VALUE_2_VALUE)){
			return 0;
		}
		
		int nNextCardType = nCardType + 1;
		return nNextCardType;
	}

	/**
	 * 获取当前扑克的后一张面值
	 * @param nCardType
	 * @return
	 */
	public static int getPreCardType(int nCardType){
		if((nCardType > FACE_VALUE_A_VALUE) || (nCardType <= FACE_VALUE_2_VALUE)){
			return 0;
		}
		
		int nPreCardType = nCardType - 1;
		return nPreCardType;
	}

	/**
	 * 最小的扑克面值
	 * @return
	 */
	public static int getMinCardType(){
		return FACE_VALUE_2_VALUE;
	}

	/**
	 * 最大的扑克面值
	 * @return
	 */
	public static int getMaxCardType(){
		return FACE_VALUE_A_VALUE;
	}

	/**
	 * 增加扑克大小
	 * @param i
	 * @return
	 */
	public static int incFaceValueEnum(int i){
		if(FACE_VALUE_A_VALUE == i){
			return FACE_VALUE_2_VALUE;
		}
		
		int nIncFaceValueNum = i + 1;
		return nIncFaceValueNum;
	}

	/**
	 * 添加扑克面值
	 * @param i
	 * @param nAddValue
	 * @return
	 */
	public static int addFaceValueEnum(int i, int nAddValue){
		if(FACE_VALUE_A_VALUE == i){
			int nValue = FACE_VALUE_2_VALUE + (nAddValue - 1);
			return nValue;
		}
		
		int nResultValue = i + nAddValue;
		return nResultValue;
	}

	/**
	 * 是否是小王
	 * @param nFaceValue
	 * @return
	 */
	public static boolean isBelongToSmallKing(int nFaceValue){
		if(FACE_VALUE_SMALL_JORKER == nFaceValue){
			return true;
		}
		   
		return false;
	}

	/**
	 * 是否是大王
	 * @param nFaceValue
	 * @return
	 */
	public static boolean isBelongToBigKing(int nFaceValue){
		if(FACE_VALUE_BIG_JORKER == nFaceValue){
			return true;
		}
		   
		return false;
	}
}
