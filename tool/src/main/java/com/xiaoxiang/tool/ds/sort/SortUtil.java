package com.xiaoxiang.tool.ds.sort;

import java.util.List;

/**
 * 说明
 *
 * @author xiang.xiaox
 */

public class SortUtil {

    /**
     * 数组交换
     *
     * @param array
     * @param one
     * @param two
     * @param <T>
     */
    public static <T extends Comparable> void swap(T[] array, int one, int two) {
        T temp = array[one];
        array[one] = array[two];
        array[two] = temp;
    }

    /**
     * 列表元素交换
     *
     * @param list
     * @param one
     * @param two
     * @param <T>
     */

    public static <T extends Comparable> void swap(List<T> list, int one, int two) {
        T temp = list.get(one);
        //不能用add 要用set
        list.set(one, list.get(two));
        list.set(two, temp);
    }
}
