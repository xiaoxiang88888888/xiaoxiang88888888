package com.xiaoxiang.tool.btrace;

import com.sun.btrace.annotations.*;

import static com.sun.btrace.BTraceUtils.*;

/**
 * 调用此方法时传入的是什么参数，返回的是什么值，当时sleepTotalTime是多少
 */
@BTrace
public class TraceMethodArgsAndReturn {
    @OnMethod(clazz = "com.xiaoxiang.area.service.AreaServiceImpl", method = "insertAndDeleteArea", location = @Location(Kind.RETURN))
    public static void traceExecute( int sleepTime, @Return boolean result) {
        println("call com.xiaoxiang.area.service.AreaServiceImpl.insertAndDeleteArea");
        println(strcat("sleepTime is:", str(sleepTime)));
        println(strcat("return value is:", str(result)));
    }
}