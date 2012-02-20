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
    public void testIntegerArrayDesc(){
        Integer[] array = new Integer[20];
        for (int i = 0; i < array.length; i++) {
            array[i] = (int)(Math.random()*45);
        }
        System.out.print("before:");
        for (Integer anArray : array) {
            System.out.print(anArray+",");
        }
        System.out.println();
        System.out.print("sort  :");
        BubbleSort.sort(array,"desc");
        for (Integer anArray : array) {
            System.out.print(anArray+",");
        }
        System.out.println();
    }

    /**
     * 测试
     */
    public void testIntegerArrayAsc(){
        Integer[] array = new Integer[20];
        for (int i = 0; i < array.length; i++) {
            array[i] = (int)(Math.random()*45);
        }
        System.out.print("before:");
        for (Integer anArray : array) {
            System.out.print(anArray+",");
        }
        System.out.println();
        System.out.print("sort  :");
        BubbleSort.sort(array,"asc");
        for (Integer anArray : array) {
            System.out.print(anArray+",");
        }
        System.out.println();
    }

    /**
     * 测试
     */
    public void testDoubleArrayDesc(){
        Double[] array = new Double[20];
        for (int i = 0; i < array.length; i++) {
            array[i] = Math.random()*45;
        }
        System.out.print("before:");
        for (Double anArray : array) {
            System.out.print(anArray + ",");
        }
        System.out.println();
        System.out.print("sort  :");
        BubbleSort.sort(array,"desc");
        for (Double anArray : array) {
            System.out.print(anArray + ",");
        }
        System.out.println();
    }

    /**
     * 测试
     */
    public void testDoubleArrayAsc(){
        Double[] array = new Double[20];
        for (int i = 0; i < array.length; i++) {
            array[i] = Math.random()*45;
        }
        System.out.print("before:");
        for (Double anArray : array) {
            System.out.print(anArray + ",");
        }
        System.out.println();
        System.out.print("sort  :");
        BubbleSort.sort(array,"asc");
        for (Double anArray : array) {
            System.out.print(anArray + ",");
        }
        System.out.println();
    }
}
