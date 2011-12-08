package com.xiaoxiang.area.service;


import com.xiaoxiang.model.Area;
import com.xiaoxiang.util.StringUtil;
import org.jtester.annotations.Transactional;
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
    @Transactional(Transactional.TransactionMode.COMMIT)
    public void insertAndDeleteAreaTest() {
        String id = StringUtil.getUUID();
        System.out.println(id);
        boolean result = areaService.insertAndDeleteArea(id);
        System.out.println("result===" + result);
        System.out.println(areaService.exists("8"));
        Area area = areaService.findEntityBykey(id);
        System.out.println(area.getAreaId());
        want.object(area).notNull();
    }


}
