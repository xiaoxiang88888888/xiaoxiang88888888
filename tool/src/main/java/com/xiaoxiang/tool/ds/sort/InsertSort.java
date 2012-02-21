package com.xiaoxiang.tool.ds.sort;


import java.util.List;

/**
 * 插入排序
 * <p/>
 * 注插入排序的时间复杂度还是为O(N*N)，交换次数和比较次数差不多，
 * 在一般情况下，它要比冒泡算法快一倍，比选择排序还要快一点
 *
 * @author xiang.xiaox
 */

public class InsertSort {

    /**
     * * 对array数组元素排序
     *
     * @param array
     * @param order desc从大到小 asc从小到大
     * @param <T>
     * @return
     */
    public static <T extends Comparable> T[] sort(T[] array, String order) {
        T temp;
        int size = array.length;
        if (order == null || !"desc".equalsIgnoreCase(order)) { //升序
            for (int out = 1; out < size; out++) {
                temp = array[out];
                int in = out;
                while (in > 0 && temp.compareTo(array[in - 1]) < 0) {
                    array[in] = array[in - 1];
                    in--;
                }
                array[in] = temp;
            }
        } else {//降序
            for (int out = 1; out < size; out++) {
                temp = array[out];
                int in = out;
                while (in > 0 && temp.compareTo(array[in - 1]) > 0) {
                    array[in] = array[in - 1];
                    in--;
                }
                array[in] = temp;
            }
        }
        return array;
    }

    /**
     * 对list列表元素排序
     *
     * @param list
     * @param order
     * @param <T>
     * @return
     */
    public static <T extends Comparable> List<T> sort(List<T> list, String order) {
        T temp;
        int size = list.size();
        if (order == null || !"desc".equalsIgnoreCase(order)) { //升序
            for (int out = 1; out < size; out++) {
                temp = list.get(out);
                int in = out;
                while (in > 0 && temp.compareTo(list.get(in - 1)) < 0) {
                    list.set(in, list.get(in - 1));
                    in--;
                }
                list.set(in, temp);
            }
        } else {//降序
            for (int out = 1; out < size; out++) {
                temp = list.get(out);
                int in = out;
                while (in > 0 && temp.compareTo(list.get(in - 1)) > 0) {
                    list.set(in, list.get(in - 1));
                    in--;
                }
                list.set(in, temp);
            }
        }
        return list;
    }


}