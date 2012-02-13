package com.xiaoxiang.concurrent;

import java.math.BigDecimal;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;

/**
 * 不安全的序列获取
 * num++ 分三步执行 读取 +1 写入
 *
 * @author xiang.xiaox
 */

public class UnSafeSeq {
    private ThreadLocal threadLocal = new ThreadLocal();

    private AtomicLong aLong = new AtomicLong();
    private int num;

    private AtomicReference<BigDecimal> bigDecimalAtomicReference= new AtomicReference<BigDecimal>();

    public int getNextNum() {
        return num++;
    }

    public int getNum() {
        return num;
    }

    public ThreadLocal getThreadLocal() {
        return threadLocal;
    }

    synchronized public int getNextNumSafe() {
        return num++;
    }

    public long getaLong() {
        return aLong.get();
    }

    public long getNextaLong() {
        return aLong.incrementAndGet();
    }

    public AtomicReference<BigDecimal> getBigDecimalAtomicReference() {
        bigDecimalAtomicReference.get();
        return bigDecimalAtomicReference;
    }

    public AtomicReference<BigDecimal> getNextBigDecimalAtomicReference() {
        return bigDecimalAtomicReference;
    }
}
