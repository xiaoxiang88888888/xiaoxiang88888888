import static com.sun.btrace.BTraceUtils.*;
import com.sun.btrace.annotations.*;

/**
 * execute方法执行耗时是多久
 */
@BTrace
public class TraceMethodExecuteTime {
    @TLS
    static long startTime;

    @OnMethod(clazz = "com.xiaoxiang.area.service.AreaServiceImpl", method = "insertAndDeleteArea")
    public static void onCall(){
        println("method start");
        startTime=timeMillis();
    }

    @OnMethod(clazz = "com.xiaoxiang.area.service.AreaServiceImpl", method = "insertAndDeleteArea", location = @Location(Kind.RETURN))
    public static void onReturn(){
        println("method end!");
        println(strcat("Time taken ms",str(timeMillis()-startTime)));
    }
}