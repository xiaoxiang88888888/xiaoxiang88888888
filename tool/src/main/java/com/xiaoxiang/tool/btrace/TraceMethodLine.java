package com.xiaoxiang.tool.btrace;

import com.sun.btrace.annotations.*;

import static com.sun.btrace.BTraceUtils.println;
import static com.sun.btrace.BTraceUtils.strcat;

/**
 * 有没有人调用AreaController中的哪一行代码
 */
@BTrace
public class TraceMethodLine {
    @OnMethod(clazz = "com.xiaoxiang.area.service.AreaServiceImpl", location = @Location(value = Kind.LINE, line = 5))
    public static void traceExecute(@ProbeClassName String pcn, @ProbeMethodName String pmn, int line) {
        println(strcat(strcat(strcat("call ", pcn), "."), pmn));
    }
}

