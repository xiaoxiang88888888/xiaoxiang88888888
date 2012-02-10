package com.xiaoxiang.concurrent;

/**
 * 不安全的序列获取
 * num++ 分三步执行 读取 +1 写入
 *
 * @author xiang.xiaox
 */

public class UnSafeSeq {
    private int num;

    public int getNextNum() {
        return num++;
    }

    synchronized public int getNextNumSafe() {
        return num++;
    }
}
