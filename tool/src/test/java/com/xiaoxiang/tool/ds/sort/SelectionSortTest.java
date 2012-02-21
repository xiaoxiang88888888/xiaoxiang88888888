package com.xiaoxiang.tool.ds.sort;

import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.List;

/**
 * 说明
 *
 * @author xiang.xiaox
 */

public class SelectionSortTest extends TestCase {

    public int itemNum = 20;

    /**
     * 测试
     */
    public void testIntegerArrayDesc() {
        Integer[] array = new Integer[itemNum];
        for (int i = 0; i < array.length; i++) {
            array[i] = (int) (Math.random() * 45);
        }
        System.out.print("Array before:");
        for (Integer anArray : array) {
            System.out.print(anArray + ",");
        }
        System.out.println();
        System.out.print("Array sort D:");
        SelectionSort.sort(array, "desc");
        for (Integer anArray : array) {
            System.out.print(anArray + ",");
        }
        System.out.println();
    }

    /**
     * 测试
     */
    public void testIntegerArrayAsc() {
        Integer[] array = new Integer[itemNum];
        for (int i = 0; i < array.length; i++) {
            array[i] = (int) (Math.random() * 45);
        }
        //Integer[] array = {26,39,21,42,37,43,32,40,37,5,1,42,13,38,3,40,31,5,0,20};
        System.out.print("Array before:");
        for (Integer anArray : array) {
            System.out.print(anArray + ",");
        }
        System.out.println();
        System.out.print("Array sort A:");
        SelectionSort.sort(array, "asc");
        for (Integer anArray : array) {
            System.out.print(anArray + ",");
        }
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
        System.out.print("Array sort D:");
        SelectionSort.sort(array, "desc");
        for (Double anArray : array) {
            System.out.print(anArray + ",");
        }
        System.out.println();
    }

    /**
     * 测试
     */
    public void testDoubleArrayAsc() {
        Double[] array = new Double[itemNum];
        for (int i = 0; i < array.length; i++) {
            array[i] = Math.random() * 45;
        }
        System.out.print("Array before:");
        for (Double anArray : array) {
            System.out.print(anArray + ",");
        }
        System.out.println();
        System.out.print("Array sort A:");
        SelectionSort.sort(array, "asc");
        for (Double anArray : array) {
            System.out.print(anArray + ",");
        }
        System.out.println();
    }

    /**
     * 测试
     */
    public void testIntegerListDesc() {
        List<Integer> list = new ArrayList<Integer>(20);
        for (int i = 0; i < itemNum; i++) {
            list.add(i, (int) (Math.random() * 45));
        }
        System.out.print("List before:");
        for (Integer anArray : list) {
            System.out.print(anArray + ",");
        }
        System.out.println();
        System.out.print("List sort D:");
        SelectionSort.sort(list, "desc");
        for (Integer anArray : list) {
            System.out.print(anArray + ",");
        }
        System.out.println();
    }

    /**
     * 测试
     */
    public void testIntegerListAsc() {
        List<Integer> list = new ArrayList<Integer>(20);
        for (int i = 0; i < itemNum; i++) {
            list.add(i, (int) (Math.random() * 45));
        }
        System.out.print("List before:");
        for (Integer anArray : list) {
            System.out.print(anArray + ",");
        }
        System.out.println();
        System.out.print("List sort A:");
        SelectionSort.sort(list, "asc");
        for (Integer anArray : list) {
            System.out.print(anArray + ",");
        }
        System.out.println();
    }

    /**
     * 测试
     */
    public void testDoubleListDesc() {
        List<Double> list = new ArrayList<Double>(20);
        for (int i = 0; i < itemNum; i++) {
            list.add(i, (Math.random() * 45));
        }
        System.out.print("List before:");
        for (Double anArray : list) {
            System.out.print(anArray + ",");
        }
        System.out.println();
        System.out.print("List sort D:");
        SelectionSort.sort(list, "desc");
        for (Double anArray : list) {
            System.out.print(anArray + ",");
        }
        System.out.println();
    }

    /**
     * 测试
     */
    public void testDoubleListAsc() {
        List<Double> list = new ArrayList<Double>(20);
        for (int i = 0; i < itemNum; i++) {
            list.add(i, (Math.random() * 45));
        }
        System.out.print("List before:");
        for (Double anArray : list) {
            System.out.print(anArray + ",");
        }
        System.out.println();
        System.out.print("List sort A:");
        SelectionSort.sort(list, "asc");
        for (Double anArray : list) {
            System.out.print(anArray + ",");
        }
        System.out.println();
    }

}
