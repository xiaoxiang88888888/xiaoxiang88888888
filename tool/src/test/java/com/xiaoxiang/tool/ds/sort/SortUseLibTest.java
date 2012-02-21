package com.xiaoxiang.tool.ds.sort;

import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.List;

/**
 * 使用系统类库
 *
 * @author xiang.xiaox
 */

public class SortUseLibTest extends TestCase {
    public int itemNum = 20;

    /**
     * 测试
     */
    public void testIntegerListLibAsc() {
        List<Integer> list = new ArrayList<Integer>(20);
        for (int i = 0; i < itemNum; i++) {
            list.add(i, (int) (Math.random() * 45));
        }
        System.out.print("List before:");
        for (Integer anArray : list) {
            System.out.print(anArray + ",");
        }
        System.out.println();
        System.out.print("List sort  :");
        SortUseLib.sortUseLib(list, "asc");
        for (Integer anArray : list) {
            System.out.print(anArray + ",");
        }
        System.out.println();
    }

    /**
     * 测试
     */
    public void testDoubleListLibDesc() {
        List<Double> list = new ArrayList<Double>(20);
        for (int i = 0; i < itemNum; i++) {
            list.add(i, (Math.random() * 45));
        }
        System.out.print("List before:");
        for (Double anArray : list) {
            System.out.print(anArray + ",");
        }
        System.out.println();
        System.out.print("List sort  :");
        SortUseLib.sortUseLib(list, "desc");
        for (Double anArray : list) {
            System.out.print(anArray + ",");
        }
        System.out.println();
    }

    /**
     * 测试
     */
    public void testIntegerArrayLibDesc() {
        Integer[] array = new Integer[itemNum];
        for (int i = 0; i < array.length; i++) {
            array[i] = (int) (Math.random() * 45);
        }
        System.out.print("Array before:");
        for (Integer anArray : array) {
            System.out.print(anArray + ",");
        }
        System.out.println();
        System.out.print("Array sort  :");
        SortUseLib.sortUseLib(array, "desc");
        for (Integer anArray : array) {
            System.out.print(anArray + ",");
        }
        System.out.println();
    }

    /**
     * 测试
     */
    public void testIntegerArrayLibAsc() {
        Integer[] array = new Integer[itemNum];
        for (int i = 0; i < array.length; i++) {
            array[i] = (int) (Math.random() * 45);
        }
        System.out.print("Array before:");
        for (Integer anArray : array) {
            System.out.print(anArray + ",");
        }
        System.out.println();
        System.out.print("Array sort  :");
        SortUseLib.sortUseLib(array, "asc");
        for (Integer anArray : array) {
            System.out.print(anArray + ",");
        }
        System.out.println();
    }

    /**
     * 测试
     */
    public void testIntegerArrayAsc() {
        int[] array = new int[itemNum];
        for (int i = 0; i < array.length; i++) {
            array[i] = (int) (Math.random() * 45);
        }
        System.out.print("Array before:");
        for (Integer anArray : array) {
            System.out.print(anArray + ",");
        }
        System.out.println();
        System.out.print("Array sort  :");
        SortUseLib.sort(array, "asc");
        for (Integer anArray : array) {
            System.out.print(anArray + ",");
        }
        System.out.println();
    }

    /**
     * 测试
     */
    public void testIntegerArrayDesc() {
        int[] array = new int[itemNum];
        for (int i = 0; i < array.length; i++) {
            array[i] = (int) (Math.random() * 45);
        }
        System.out.print("Array before:");
        for (Integer anArray : array) {
            System.out.print(anArray + ",");
        }
        System.out.println();
        System.out.print("Array sort  :");
        SortUseLib.sort(array, "desc");
        for (Integer anArray : array) {
            System.out.print(anArray + ",");
        }
        System.out.println();
    }

    /**
     * 测试
     */
    public void testIntegerArrayAscTwo() {
        int[] array = new int[itemNum];
        for (int i = 0; i < array.length; i++) {
            array[i] = (int) (Math.random() * 45);
        }
        System.out.print("Array before:");
        for (Integer anArray : array) {
            System.out.print(anArray + ",");
        }
        System.out.println();
        System.out.print("Array sort  :");
        SortUseLib.sortTwo(array, "asc");
        for (Integer anArray : array) {
            System.out.print(anArray + ",");
        }
        System.out.println();
    }

    /**
     * 测试
     */
    public void testIntegerArrayDescTwo() {
        int[] array = new int[itemNum];
        for (int i = 0; i < array.length; i++) {
            array[i] = (int) (Math.random() * 45);
        }
        System.out.print("Array before:");
        for (Integer anArray : array) {
            System.out.print(anArray + ",");
        }
        System.out.println();
        System.out.print("Array sort  :");
        SortUseLib.sortTwo(array, "desc");
        for (Integer anArray : array) {
            System.out.print(anArray + ",");
        }
        System.out.println();
    }
}
