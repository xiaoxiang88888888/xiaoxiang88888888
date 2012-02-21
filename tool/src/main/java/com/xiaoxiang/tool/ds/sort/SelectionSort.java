package com.xiaoxiang.tool.ds.sort;


import java.util.List;

/**
 * 选择排序
 *
 * 注意外层循环的减1和内层循环的加1，减少一次多余的循环
 *
 * 选择排序改进了冒泡排序，将必要的O(N*N)的交换次数减少到O(N)次，
 * 但是比较次数仍保持在O(N*N)次，有些情况是大量的记录需要在内存中
 * 移动，此时交换时间相对比较时间起来更为重要（在java中一般不会有这样的
 * 情况，因为java只是改变了引用位置，而时间对象的位置并没有发生改变）
 *
 * @author xiang.xiaox
 */

public class SelectionSort {

    /**
     * * 对array数组元素排序
     *
     * @param array
     * @param order desc从大到小 asc从小到大
     * @param <T>
     * @return
     */
    public static <T extends Comparable> T[] sort(T[] array, String order) {
        int min;
        int size = array.length - 1;
        if (order == null || !"desc".equalsIgnoreCase(order)) { //升序
            for (int out = size; out > 0; out--) {
                min=out;
                for (int in = 0; in < out; in++) {
                    if (array[in].compareTo(array[min]) > 0) {
                        min=in;
                    }
                }
                SortUtil.swap(array, out, min);
            }
        } else {//降序
            for (int out = 0; out < array.length - 1; out++) {
                min=out;
                for (int in = size; in > out; in--) {
                    if (array[in].compareTo(array[min]) > 0) {
                        min=in;
                    }
                }
                SortUtil.swap(array, out, min);
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
        int min;
        int size = list.size() - 1;
        if (order == null || !"desc".equalsIgnoreCase(order)) { //升序
            for (int out = size; out > 0; out--) {
                min = out;
                for (int in = 0; in < out; in++) {
                    if (list.get(in).compareTo(list.get(min)) > 0) {
                        min=in;
                    }
                }
                SortUtil.swap(list, out, min);
            }
        } else {//降序
            for (int out = 0; out < list.size() - 1; out++) {
                min = out;
                for (int in = size; in > out; in--) {
                    if (list.get(in).compareTo(list.get(min)) > 0) {
                        min=in;
                    }
                }
                SortUtil.swap(list, out, min);
            }
        }
        return list;
    }


}