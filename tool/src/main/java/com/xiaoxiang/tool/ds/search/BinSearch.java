package com.xiaoxiang.tool.ds.search;

/**
 * 折半查找
 *
 * @author xiang.xiaox
 */

public class BinSearch {

    /**
     * 默认array是升序
     *
     * @param array
     * @param value
     * @param <T>
     * @return
     */
    public static <T extends Comparable> boolean find(T[] array, T value) {
        return find(array, value, "asc");
    }

    /**
     * 在数组元素查找某元素
     *
     * @param array
     * @param value 比较的值
     * @return
     */
    public static <T extends Comparable> boolean find(T[] array, T value, String order) {
        if (array == null) return false;
        //在有序数组中R[1..n]中进行二分查找，成功时返回结点的位置，失败时返回零
        int low = 1;
        int high = array.length;
        int mid; //置当前查找区间上、下界的初值
        while (low <= high) { //当前查找区间R[low..high]非空
            mid = (low + high) / 2;
            if (value.compareTo(array[mid]) == 0) return true; //查找成功返回
            if (order == null || !"desc".equalsIgnoreCase(order)) { //升序
                if (value.compareTo(array[mid]) < 0) {
                    high = mid - 1; //继续在R[low..mid-1]中查找
                } else {
                    low = mid + 1; //继续在R[mid+1..high]中查找
                }
            } else {
                if (value.compareTo(array[mid]) > 0) {
                    high = mid - 1; //继续在R[low..mid-1]中查找
                } else {
                    low = mid + 1; //继续在R[mid+1..high]中查找
                }
            }
        }
        return false; //当low>high时表示查找区间为空，查找失败

    }
}
