import static com.sun.btrace.BTraceUtils.*;

import com.sun.btrace.annotations.*;

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