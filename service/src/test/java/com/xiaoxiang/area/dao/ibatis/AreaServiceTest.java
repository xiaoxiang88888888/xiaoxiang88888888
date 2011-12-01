package com.xiaoxiang.area.dao.ibatis;

import com.xiaoxiang.area.service.AreaService;
import com.xiaoxiang.model.Area;
import org.jtester.testng.JTester;
import org.testng.annotations.Test;
import org.unitils.spring.annotation.SpringApplicationContext;
import org.unitils.spring.annotation.SpringBeanByName;

import java.util.List;

/**
 * 地区 service测试
 *
 * @author xiang.xiaox
 */
@SpringApplicationContext({"bean/spring-datasource.xml", "classpath:bean/spring-area.xml"})
public class AreaServiceTest extends JTester {

    @SpringBeanByName
    private AreaService areaService;

    @Test
    public void getAllEntityTest() {
        List<Area> list = areaService.getAllEntity();
        System.out.println("servcie===" + list.size());
    }
}
