package com.xiaoxiang.tool.btrace;

import com.sun.btrace.annotations.BTrace;
import com.sun.btrace.annotations.OnMethod;

import static com.sun.btrace.BTraceUtils.jstack;
import static com.sun.btrace.BTraceUtils.println;

/**
 * 谁调用了execute方法
 */
@BTrace
public class TraceMethodCallee {
    @OnMethod(clazz = "com.xiaoxiang.area.service.AreaServiceImpl", method = "insertAndDeleteArea")
    public static void traceExecute() {
        println("who call AreaController.insertAndDeleteArea :");
        jstack();
    }
}