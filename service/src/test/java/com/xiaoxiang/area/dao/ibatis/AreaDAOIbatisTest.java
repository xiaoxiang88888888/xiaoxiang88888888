package com.xiaoxiang.area.dao.ibatis;

import com.xiaoxiang.area.dao.AreaDAO;
import com.xiaoxiang.model.Area;
import org.jtester.testng.JTester;
import org.jtester.unitils.spring.SpringBeanFor;
import org.testng.annotations.Test;
import org.unitils.spring.annotation.SpringApplicationContext;
import org.unitils.spring.annotation.SpringBeanByName;
import org.unitils.spring.annotation.SpringBeanByType;

import java.util.List;

/**
 * 地区 dao测试
 *
 * @author xiang.xiaox
 */
@SpringApplicationContext({"bean/spring-datasource.xml", "classpath:bean/spring-area.xml"})
public class AreaDAOIbatisTest extends JTester {

    @SpringBeanByType
    private AreaDAO areaDAO;

    @Test
    public void getAllEntityTest() {
        List<Area> list = areaDAO.getAllEntity();
        System.out.println(list.size());
    }
}
