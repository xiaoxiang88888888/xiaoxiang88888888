package com.xiaoxiang.area.service;

import com.xiaoxiang.base.service.BaseService;
import com.xiaoxiang.model.Area;

/**
 * Area的服务层接口
 * 地区
 * @author xiaoxiang
 */
public interface AreaService extends BaseService<Area, String> {

    /**
     * id通过外部 如uuid
     * @param id
     * @return
     */
    public boolean insertAndDeleteArea(String id);
}
