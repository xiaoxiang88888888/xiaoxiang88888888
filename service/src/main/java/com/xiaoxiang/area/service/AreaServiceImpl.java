package com.xiaoxiang.area.service;

import com.xiaoxiang.area.dao.AreaDAO;
import com.xiaoxiang.area.dao.ibatis.AreaDAOIbatis;
import com.xiaoxiang.base.service.BaseServiceImpl;
import com.xiaoxiang.model.Area;

/**
 * Area的服务层接口的实现类
 * 地区
 *
 * @author xiaoxiang
 */
public class AreaServiceImpl extends BaseServiceImpl<Area, String> implements AreaService {
    private AreaDAO areaDAOJTA;
    private AreaDAOIbatis areaDAOJTA2;

    /**
     * id通过外部 如uuid
     *
     * @param id
     * @return
     */
    public boolean insertAndDeleteArea(String id) {
        Area area = new Area();
        area.setId(id);
        area.setAreaId(id);
        area.setCode("id");
        area.setName("id");
        area.setOrderNo(0);
        area.setParentId("0");
        area.setRemark("测试事务");
        //插入
        getDao().addEntity(area);
        //修改
        area.setRemark("测试事务2");
        getDao().updateEntity(area);
        //删除
        getDao().deleteEntity(area);
        /*if (areaDAOJTA != null) {
            areaDAOJTA.addEntity(area);
        }
        if (areaDAOJTA2 != null) {
        	//area.setId("01");
            areaDAOJTA2.addEntity(area);
        }*/
        return false;
    }

    public void setAreaDAOJTA(AreaDAO areaDAOJTA) {
        this.areaDAOJTA = areaDAOJTA;
    }

    public void setAreaDAOJTA2(AreaDAOIbatis areaDAOJTA2) {
        this.areaDAOJTA2 = areaDAOJTA2;
    }
}
