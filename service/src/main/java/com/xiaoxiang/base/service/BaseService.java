package com.xiaoxiang.base.service;

import java.util.List;
import java.util.Map;

/**
 * 服务层基类接口
 *
 * @author xiaoxiang
 */
public interface BaseService<T, PK> {
    static String classPath = BaseService.class.toString();

    /**
     * 增加实体
     *
     * @param entity
     */
    public void addEntity(T entity);

    /**
     * 增加或修改实体
     *
     * @param entity
     */
    public void saveOrUpdateEntity(T entity);

    /**
     * 批量增加或修改实体
     *
     * @param entitys 实体集合
     */
    public void saveOrUpdateBatchEntity(List<T> entitys);


    /**
     * 删除实体
     *
     * @param entity
     */
    public void deleteEntity(T entity);

    /**
     * 根据主健删除实体
     *
     * @param key 主健
     */
    public void deleteEntityByKey(PK key);

    /**
     * 批量删除实体
     *
     * @param entitys 实体集合
     */
    public void deleteBatchEntity(List<T> entitys);

    /**
     * 批量删除实体，条件是主健
     *
     * @param keys 主健集合
     */
    public void deleteBatchEntityByKeys(PK[] keys);

    /**
     * 指定实体类型，根据主健批量删除实体
     *
     * @param keys        主健集合
     * @param entityClass 实体类型
     */
    public void deleteBatchEntityByKeys(PK[] keys, Class<T> entityClass);


    /**
     * 更新实体
     *
     * @param entity
     */
    public void updateEntity(T entity);

    /**
     * 根据主健查询实体对象
     *
     * @param key
     * @return 实体
     */
    public T findEntityBykey(PK key);

    /**
     * 查询所有实体集合
     *
     * @return 实体集合
     */
    public List<T> getAllEntity();

    /**
     * 查询所有实体集合(剔除重复的对象)
     *
     * @return
     */
    public List<T> getAllEntityDistinct();

    /**
     * 是否存在所提供的key主键的实体对象
     *
     * @param key
     * @return
     */
    public boolean exists(PK key);

    /**
     * 根据相关参数查询
     *
     * @param queryParams
     * @return
     */
    List<T> findByParamQuery( Map<String, Object> queryParams);

    /**
     * 根据相关参数查询记录大小
     *
     * @param queryParams
     * @return
     */
    int findByParamQueryCount( Map<String, Object> queryParams);

    /**
     * 获得实体类型
     *
     * @return 实体类型
     */
    public Class<T> getEntityClass();


}