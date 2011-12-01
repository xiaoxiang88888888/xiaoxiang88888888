package com.xiaoxiang.util;

import mockit.Mocked;
import org.jtester.testng.JTester;

/**
 * 测试JSON
 *
 * @author xiang.xiaox
 */
public class JsonUtilTest extends JTester {
    @Mocked
    JsonUtil jsonUtil;

  /*  //日志测试
    @Test
    public void logTest() {
        new Expectations() {
            @Mocked
            Logger logger;
            {
                // 替换了原始服务类代码中的logger 对象
                setField(jsonUtil, "logger", logger);
                //hamcrest 框架的with 工具API，在判断各种参数方面非常方便
                logger.warn("传入的参数为null");
            }
        };
        // 入参的数组元素顺序和上面with 语法中的顺序并不相同，但是不影响其执行过程
        jsonUtil.ObjectToJson(null);
    }*/

}
