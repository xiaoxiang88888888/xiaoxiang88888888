import static com.sun.btrace.BTraceUtils.*;

import com.sun.btrace.annotations.*;

/**
 * 调用此方法时传入的是什么参数，返回的是什么值，当时sleepTotalTime是多少
 */
@BTrace
public class TraceMethodArgsAndReturn {
    @OnMethod(clazz = "com.xiaoxiang.area.service.AreaServiceImpl", method = "insertAndDeleteArea", location = @Location(Kind.RETURN))
    public static void traceExecute(com.xiaoxiang.area.service.AreaServiceImpl instance, int sleepTime, @Return boolean result) {
        println("call com.xiaoxiang.area.service.AreaServiceImpl.insertAndDeleteArea");
        println(strcat("sleepTime is:", str(sleepTime)));
        println(strcat("sleepTotalTime is:", str(get(field("com.xiaoxiang.area.service.AreaServiceImpl", "sleepTotalTime"), instance))));
        println(strcat("return value is:", str(result)));
    }
}