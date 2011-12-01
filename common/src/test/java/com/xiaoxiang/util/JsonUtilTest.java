package com.xiaoxiang.util;

import com.xiaoxiang.model.Area;
import org.jtester.testng.JTester;
import org.testng.annotations.Test;

/**
 * 测试JSON
 *
 * @author xiang.xiaox
 */
public class JsonUtilTest extends JTester {
    public final String str = "{" +
            "\"id\":null," +
            "\"areaId\":\"1\"," +
            "\"parentAreaId\":\"0\"," +
            "\"areacode\":\"sdfsdfwerwer\"," +
            "\"areaname\":\"测试代码\"," +
            "\"orderno\":888," +
            "\"remark\":\"<>@#$%^&*()_+=-}{|\\\\][';\"}";

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

    @Test
    public void objectToJsonTest() {
        Area area = new Area();
        area.setAreaId("1");
        area.setAreacode("sdfsdfwerwer");
        area.setAreaname("测试代码");
        area.setOrderno(888);
        area.setParentAreaId("0");
        area.setRemark("<>@#$%^&*()_+=-}{|\\][';");
        String str = JsonUtil.getInstance().ObjectToJson(area);
        want.string(str).isEqualTo(str);

    }

    @Test
    public void jsonToObjectTest() {
        Area area = (Area) JsonUtil.getInstance().jsonToObject(str, Area.class);
        want.object(area).notNull();
        want.string(area.getId()).isNull();
        want.string(area.getAreaId()).isEqualTo("1");
        want.string(area.getAreacode()).isEqualTo("sdfsdfwerwer");
        want.string(area.getAreaname()).isEqualTo("测试代码");
        want.number(area.getOrderno()).isEqualTo(888);
        want.string(area.getParentAreaId()).isEqualTo("0");
        want.string(area.getRemark()).isEqualTo("<>@#$%^&*()_+=-}{|\\][';");
    }
}
