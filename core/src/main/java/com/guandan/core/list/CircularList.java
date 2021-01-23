package com.guandan.core.list;

import java.util.ArrayList;


/**
 * @Author: qixiangyang.121
 * @Description:
 * @Date: 18:48 2020/1/14
 */
public class CircularList<E> extends ArrayList<E> {

    private int index = 0;


    public E element() {
        return this.get(index);
    }

    public E poll() {

        if (index == (this.size() - 1)) {
            index = 0;
        } else {
            index++;
        }
        return this.get(index);
    }
}