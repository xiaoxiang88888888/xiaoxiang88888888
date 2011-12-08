package com.xiaoxiang.area.service;


import com.xiaoxiang.area.dao.AreaDAO;
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
    //不指定具体的方法，将全部mock
    @Mocked(methods = {"getAllEntity","exists"})
    @SpringBeanFor(value = "areaDAO")
    private AreaDAO dao;

    @Test
    public void getAllEntityTest() {
        for (int i = 0; i < 10; i++) {
            Area area = new Area();
            areas.add(area);
        }

        new Expectations() {
            {   //这里面的顺序,跟你调用的顺序要一致,不然会报Missing invocation错误
                dao.getAllEntity();
                result = areas;
                dao.exists(anyString);
                result = true;
                dao.findEntityBykey(anyString);
                result = "reslut";
            }
        };

        List<Area> list = areaService.getAllEntity();
        System.out.println("servcie===" + list.size());
        want.number(10).isEqualTo(list.size());
        System.out.println(areaService.exists("8"));
        System.out.println(dao.findEntityBykey("999"));
    }

}
