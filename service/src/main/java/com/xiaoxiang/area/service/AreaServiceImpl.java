package com.xiaoxiang.area.service;

import com.xiaoxiang.base.service.BaseServiceImpl;
import com.xiaoxiang.model.Area;

/**
 * Area的服务层接口的实现类
 *  地区
 * @author xiaoxiang
 */
public class AreaServiceImpl extends BaseServiceImpl<Area, String> implements AreaService {

    /**
     * id通过外部 如uuid
     * @param id
     * @return
     */
    public boolean insertAndDeleteArea(String id){
        Area area = new Area();
        area.setId(id);
        area.setAreaId(id);
        area.setAreacode("id");
        area.setAreaname("id");
        area.setOrderno(0);
        area.setParentAreaId("0");
        area.setRemark("测试事务");
        //插入
        getDao().addEntity(area);
        //修改
        area.setRemark("测试事务2");
        getDao().updateEntity(area);
        //删除
        getDao().deleteEntity(area);
        return false;
    }
   
}
