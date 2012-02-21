package com.xiaoxiang.tool.ds.sort;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * 使用系统类库
 *
 * @author xiang.xiaox
 */

public class SortUseLib {
    /**
     * 使用系统类库
     *
     * @param list
     * @param order
     * @param <T>
     * @return
     */
    public static <T extends Comparable> List<T> sortUseLib(List<T> list, final String order) {
        Collections.sort(list, new Comparator<T>() {
            @Override
            public int compare(T o1, T o2) {
                if (order == null || !"desc".equalsIgnoreCase(order)) {
                    return o1.compareTo(o2);
                } else {
                    return o2.compareTo(o1);
                }
            }
        });
        return list;
    }

    /**
     * 使用系统类库
     *
     * @param array
     * @param order
     * @param <T>
     * @return
     */
    public static <T extends Comparable> T[] sortUseLib(T[] array, final String order) {
        Arrays.sort(array, new Comparator<T>() {
            @Override
            public int compare(T o1, T o2) {
                if (order == null || !"desc".equalsIgnoreCase(order)) {
                    return o1.compareTo(o2);
                } else {
                    return o2.compareTo(o1);
                }
            }
        });
        return array;
    }

    public static void swap(int[] array, int one, int two) {
        int temp = array[one];
        array[one] = array[two];
        array[two] = temp;
    }

    public static int[] sort(int[] array, String order) {
        if (array == null) return array;
        int size = array.length;
        if (order == null || !"desc".equalsIgnoreCase(order)) {//从大到小
            //先从最右边排起
            for (int out = size - 1; out > 1; out--) {
                for (int in = 0; in < out; in++) {
                    if (array[in] > array[in + 1]) {
                        swap(array, in, in + 1);
                    }
                }
            }
        } else {//从小到大
            //先从最右边排起
            for (int out = size - 1; out > 1; out--) {
                for (int in = 0; in < out; in++) {
                    if (array[in] < array[in + 1]) {
                        swap(array, in, in + 1);
                    }
                }

            }
        }
        return array;
    }

    public static int[] sortTwo(int[] array, String order) {
        if (array == null) return array;
        int size = array.length;
        if (order == null || !"desc".equalsIgnoreCase(order)) {//从小到大
            //先从最左边排起
            for (int out = 0; out < size - 1; out++) {
                for (int in = size - 1; in > out; in--) {
                    if (array[in] < array[in - 1]) {
                        swap(array, in, in - 1);
                    }
                }
            }
        } else {//从大到小
            //先从最左边排起
            for (int out = 0; out < size - 1; out++) {
                for (int in = size - 1; in > out; in--) {
                    if (array[in] > array[in - 1]) {
                        swap(array, in, in - 1);
                    }
                }
            }
        }
        return array;
    }


}
