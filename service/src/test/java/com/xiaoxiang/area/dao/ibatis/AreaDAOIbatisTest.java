package com.xiaoxiang.area.dao.ibatis;

import com.xiaoxiang.area.dao.AreaDAO;
import com.xiaoxiang.model.Area;
import org.jtester.annotations.Transactional;
import org.jtester.testng.JTester;
import org.jtester.unitils.dbfit.DbFit;
import org.testng.annotations.Test;
import org.unitils.spring.annotation.SpringApplicationContext;
import org.unitils.spring.annotation.SpringBeanByType;

import java.util.List;

/**
 * 地区 dao测试
 *
 * @author xiang.xiaox
 */
@SpringApplicationContext({"bean/spring-datasource.xml",
        "classpath:bean/spring-area.xml"})
public class AreaDAOIbatisTest extends JTester {
    @SpringBeanByType
    private AreaDAO areaDAO;

    @Test
    public void getAllEntityTest() {
        List<Area> list = areaDAO.getAllEntity();
        System.out.println(list.size());
    }

    @Test
    public void existsTest() {
        String id = "sdf";
        boolean isExists = areaDAO.exists(id);
        System.out.println(isExists);
    }

    /**
     * 在testcase执行之前，jtester框架会先执行when里面指定的内容，when通常用于准备测试所用的数据。
     * 在testcase执行之后，jtester框架会执行then里面指定的内容，then通常
     * 用于验证单元测试执行后的数据状态。虽然when和then在功能上有上面的所说的区别，但在语法上都是一样的
     */
    /**
     * @Transactional(TransactionMode.COMMIT) 最后commit数据
     * @Transactional(TransactionMode.ROLLBACK) 最后rollback数据
     * @Transactional(TransactionMode.DISABLED) jtester框架不在使用spring的事务管理，
     * 程序员必须显式的在dbfit文件中自己commit或rollback数据。但spring bean仍然接受spring transaction的管理
     *
     * wiki中写的commit优先此处的事务声明
     */
    @Test
    @Transactional(Transactional.TransactionMode.COMMIT)
    @DbFit(when = "wiki/area/area-insert-when.wiki", then = "wiki/area/area-insert-then.wiki")
    public void addEntityTest() {
        boolean isExists = areaDAO.exists("999");
        System.out.println(isExists);
    }

}
