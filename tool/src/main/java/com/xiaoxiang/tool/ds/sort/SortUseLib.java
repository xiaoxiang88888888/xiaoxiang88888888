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
        List<T> list = Arrays.asList(array);
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
        return list.toArray(array);
    }
}
