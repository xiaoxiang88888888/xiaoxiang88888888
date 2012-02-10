package com.xiaoxiang.concurrent;

/**
 * 说明
 *
 * @author xiang.xiaox
 */
public class Main {

    public static void main(String[] args) {
        UnSafeSeq seq = new UnSafeSeq();
        System.out.println(seq.getNextNum());
        Thread t = new Thread();

    }
}
