package com.school.practice.market.web.context;

/**
 * @Author: qixiangyang.121
 * @Description:
 * @Date: 17:46 2020/12/22
 */
public class UserContext extends AbstractBaseContext{

    private static final String userKey = "market_user";

    public static void setLoginUserId(Long employeeId){
        set(userKey,employeeId);
    }

    public static Long getLoginUserId(){
        Object userId = get(userKey);
        return userId == null?null:(long)userId;
    }

    public static boolean isLonginState(){
        return getLoginUserId() != null;
    }
}
