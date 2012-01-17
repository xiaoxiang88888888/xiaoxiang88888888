package com.xiaoxiang.base.service;

import com.xiaoxiang.base.dao.BaseDAO;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * 服务层基类接口的实现
 *
 * @author xiaoxiang
 */
public class BaseServiceImpl<T, PK extends Serializable> implements BaseService<T, PK> {

    static String classPath = BaseServiceImpl.class.toString();

    /**
     * DAO接口
     */
    private BaseDAO<T, PK> dao;

    /**
     * 获得设置DAO接口
     *
     * @return DAO接口
     */
    public BaseDAO<T, PK> getDao() {
        return dao;
    }

    /**
     * 设置DAO接口
     *
     * @param dao DAO接口
     */
    public void setDao(BaseDAO<T, PK> dao) {
        this.dao = dao;
    }


    @Override
    public void addEntity(T entity) {
        dao.addEntity(entity);
    }

    @Override
    public void saveOrUpdateEntity(T entity) {
        dao.saveOrUpdateEntity(entity);
    }

    @Override
    public void saveOrUpdateBatchEntity(List<T> entitys) {
        dao.saveOrUpdateBatchEntity(entitys);
    }

    @Override
    public void deleteEntity(T entity) {
        dao.deleteEntity(entity);
    }

    @Override
    public void deleteEntityByKey(PK key) {
        dao.deleteEntityByKey(key);
    }

    @Override
    public void deleteBatchEntity(List<T> entitys) {
        dao.deleteBatchEntity(entitys);
    }

    @Override
    public void deleteBatchEntityByKeys(PK[] keys) {
        dao.deleteBatchEntityByKeys(keys);
    }

    @Override
    public void deleteBatchEntityByKeys(PK[] keys, Class<T> entityClass) {
        dao.deleteBatchEntityByKeys(keys, entityClass);
    }

    @Override
    public void updateEntity(T entity) {
        dao.updateEntity(entity);
    }


    @Override
    public T findEntityBykey(PK key) {
        return dao.findEntityBykey(key);
    }

    @Override
    public List<T> getAllEntity() {
        return dao.getAllEntity();
    }

    @Override
    public List<T> getAllEntityDistinct() {
        return dao.getAllEntityDistinct();
    }

    @Override
    public boolean exists(PK key) {
        return dao.exists(key);
    }

    @Override
    public List<T> findByParamQuery(Map<String, Object> queryParams) {
        return dao.findByParamQuery(queryParams);
    }

    @Override
    public int findByParamQueryCount(Map<String, Object> queryParams) {
        return dao.findByParamQueryCount(queryParams);
    }

    @Override
    public Class<T> getEntityClass() {
        return dao.getEntityClass();
    }

}
