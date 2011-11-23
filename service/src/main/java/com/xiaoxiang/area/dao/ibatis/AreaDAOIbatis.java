package com.xiaoxiang.area.dao.ibatis;


import com.xiaoxiang.base.dao.ibatis.BaseDAOIbatis;
import com.xiaoxiang.model.Area;
import com.xiaoxiang.area.dao.AreaDAO;

/**
 * Areadao层的ibatis实现
 * 地区
 *
 * @author xiaoxiang
 */
public class AreaDAOIbatis extends BaseDAOIbatis<Area, String> implements AreaDAO {

    /**
     * 获得实体类型
     *
     * @return 实体类型
     */
    @Override
    public Class<Area> getEntityClass() {
        return Area.class;
    }

}
