package com.xiaoxiang.concurrent;

/**
 * 说明
 *
 * @author xiang.xiaox
 */
public class Main {
    final static UnSafeSeq seq = new UnSafeSeq();

    public static void main(String[] args) {
        seq.getThreadLocal().set("88");
        int threadNum = 60;
        Thread[] t = new Thread[threadNum];
        for (int i = 0; i < threadNum; i++) {
            t[i] = new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int j = 0; j < 1000; j++) {
                        seq.getNextaLong();
                        //seq.getNextNumSafe();
                        //seq.getNextNum();
                        System.out.println("线程 NextNum=" + seq.getaLong());

                    }
                }
            });
            t[i].start();
        }
       /* while (Thread.activeCount() >= 1) {
            Thread.yield();
        }

        System.out.println("合计="+seq.getNum());*/

    }
}
