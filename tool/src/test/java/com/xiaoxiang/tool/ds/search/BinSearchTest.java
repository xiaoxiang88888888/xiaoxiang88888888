package com.xiaoxiang.tool.ds.search;

import com.xiaoxiang.tool.ds.sort.QuickSort;
import junit.framework.TestCase;

/**
 * 折半查找
 *
 * @author xiang.xiaox
 */

public class BinSearchTest extends TestCase {

    public int itemNum = 20;

    /**
     * 测试
     */
    public void testIntegerArrayAsc() {
        //Integer[] array = {26,39,21,42,37,43,32,40,37,5,1,42,13,38,3,40,31,5,0,20};
        Integer[] array = {0, 0, 1, 2, 2, 4, 5, 7, 11, 14, 18, 20, 20, 22, 25, 29, 30, 33, 36, 44};
        System.out.print("Array before:");
        for (Integer anArray : array) {
            System.out.print(anArray + ",");
        }
        System.out.println();
        boolean result = BinSearch.find(array, 8);
        System.out.print("find result:" + result);
        System.out.println();
    }

    /**
     * 测试
     */
    public void testDoubleArrayDesc() {
        Double[] array = new Double[itemNum];
        for (int i = 0; i < array.length; i++) {
            array[i] = Math.random() * 45;
        }
        System.out.print("Array before:");
        for (Double anArray : array) {
            System.out.print(anArray + ",");
        }
        System.out.println();
        QuickSort.sort(array, "desc");
        System.out.print("Array sort D:");
        for (Double anArray : array) {
            System.out.print(anArray + ",");
        }
        System.out.println();
    }


}
