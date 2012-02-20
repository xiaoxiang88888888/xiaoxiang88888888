package com.xiaoxiang.tool.ds.sort;

import junit.framework.TestCase;

/**
 * 说明
 *
 * @author xiang.xiaox
 */

public class BubbleSortTest  extends TestCase {
    /**
     * 测试
     */
    public void testDesc(){
        int[] array = new int[20];
        for (int i = 0; i < array.length; i++) {
            array[i] = (int)(Math.random()*45);
        }
        BubbleSort sort = new BubbleSort();
        sort.bubbleSort(array);
        for (int i = 0; i < array.length; i++) {
            System.out.println(array[i]);
        }
    }
}
