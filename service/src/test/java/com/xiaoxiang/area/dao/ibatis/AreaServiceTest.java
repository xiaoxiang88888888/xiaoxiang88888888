package com.xiaoxiang.area.dao.ibatis;


import com.xiaoxiang.area.service.AreaService;
import com.xiaoxiang.model.Area;
import org.jtester.testng.JTester;
import org.testng.annotations.Test;
import org.unitils.spring.annotation.SpringApplicationContext;
import org.unitils.spring.annotation.SpringBeanByType;

import java.util.List;

/**
 * 地区 service测试
 *
 * @author xiang.xiaox
 */
@SpringApplicationContext({"bean/spring-datasource.xml", "classpath:bean/spring-area.xml"})
public class AreaServiceTest extends JTester {

    @SpringBeanByType
    private AreaService areaService;

   /* @Mocked
    @MockBean
    private AreaDAO dao;*/

    @Test
    public void getAllEntityTest() {
       /* new Expectations() {
            {
                dao.getAllEntity();
                result = new ArrayList<Area>();
            }
        };*/
        List<Area> list = areaService.getAllEntity();
        System.out.println("servcie===" + list.size());
    }
}
