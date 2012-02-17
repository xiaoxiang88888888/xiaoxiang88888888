import static com.sun.btrace.BTraceUtils.*;
import com.sun.btrace.annotations.*;

/**
 * execute方法执行耗时是多久
 */
@BTrace
public class TraceMethodExecuteTime {
    @TLS
    static long beginTime;

    @OnMethod(clazz = "com.xiaoxiang.area.service.AreaServiceImpl", method = "insertAndDeleteArea")
    public static void traceExecuteBegin() {
        beginTime = timeMillis();
    }

    @OnMethod(clazz = "com.xiaoxiang.area.service.AreaServiceImpl", method = "insertAndDeleteArea", location = @Location(Kind.RETURN))
    public static void traceExecute(int sleepTime, @Return boolean result) {
        println("------------------------------------------------------------");
        println(strcat(strcat("AreaController.insertAndDeleteArea time is:", str(timeMillis() - beginTime)), "ms"));
    }
}