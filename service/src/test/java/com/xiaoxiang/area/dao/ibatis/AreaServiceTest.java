package com.xiaoxiang.area.dao.ibatis;


import com.xiaoxiang.area.dao.AreaDAO;
import com.xiaoxiang.area.service.AreaService;
import com.xiaoxiang.model.Area;
import mockit.Mocked;
import org.jtester.testng.JTester;
import org.jtester.unitils.spring.SpringBeanFor;
import org.testng.annotations.Test;
import org.unitils.spring.annotation.SpringApplicationContext;
import org.unitils.spring.annotation.SpringBeanByType;

import java.util.ArrayList;
import java.util.List;

/**
 * 地区 service测试
 *
 * @author xiang.xiaox
 */
@SpringApplicationContext({"bean/spring-datasource.xml", "classpath:bean/spring-area.xml"})
public class AreaServiceTest extends JTester {
    List<Area> areas = new ArrayList<Area>();


    @SpringBeanByType
    private AreaService areaService;

    //@Mocked + @SpringBeanFor 注解把id为 areaDAO 的bean对象在替换成Mock出来的实例，
    // 并注入到属性dao中
    @Mocked
    @SpringBeanFor(value = "areaDAO")
    private AreaDAO dao;

    @Test
    public void getAllEntityTest() {
        for (int i = 0; i < 10; i++) {
            Area area = new Area();
            areas.add(area);
        }

        new Expectations() {
            {
                dao.getAllEntity();
                result = areas;
            }
        };
        List<Area> list = areaService.getAllEntity();
        System.out.println("servcie===" + list.size());
        want.number(10).isEqualTo(list.size());
    }
}
