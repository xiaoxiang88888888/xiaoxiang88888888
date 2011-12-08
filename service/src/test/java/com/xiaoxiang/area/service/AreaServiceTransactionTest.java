package com.xiaoxiang.area.service;


import com.xiaoxiang.model.Area;
import com.xiaoxiang.util.StringUtil;
import org.jtester.testng.JTester;
import org.testng.annotations.Test;
import org.unitils.spring.annotation.SpringApplicationContext;
import org.unitils.spring.annotation.SpringBeanByType;

/**
 * 事务测试
 *
 * @author xiang.xiaox
 */
@SpringApplicationContext({"bean/spring-datasource.xml", "classpath:bean/spring-area.xml"})
public class AreaServiceTransactionTest extends JTester {
    @SpringBeanByType
    private AreaService areaService;

    @Test
    public void insertAndDeleteAreaTest() {
        String id = StringUtil.getUUID();
        Area area = new Area();
        area.setId(id);
        area.setAreaId(id);
        area.setAreacode("id");
        area.setAreaname("id");
        area.setOrderno(0);
        area.setParentAreaId("0");
        area.setRemark("测试事务");

        boolean result = areaService.insertAndDeleteArea(id);
        System.out.println("result===" + result);
        System.out.println(areaService.exists("8"));
    }


}
