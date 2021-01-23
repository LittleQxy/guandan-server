package com.school.practice.market.web.context;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: qixiangyang.121
 * @Description:
 * @Date: 17:48 2020/12/22
 */
public abstract class AbstractBaseContext {

    private static final InheritableThreadLocal<Map<String,Object>> CONTEXT = new InheritableThreadLocal<Map<String, Object>>(){
        @Override
        protected Map<String, Object> initialValue() {
            return new HashMap<>();
        }
    };

    /**
     * 获取线程私有的变量
     * @param key
     * @return
     */
    public static Object get(String key){
        return CONTEXT.get().getOrDefault(key,null);
    }

    public static void set(String key,Object o){
        CONTEXT.get().put(key,o);
    }

    public static void remove(String key){
        CONTEXT.get().remove(key);
    }

    public static void clear(){
        CONTEXT.get().clear();
    }
}
