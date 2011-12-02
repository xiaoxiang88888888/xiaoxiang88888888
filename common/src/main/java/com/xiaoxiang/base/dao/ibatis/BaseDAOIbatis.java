package com.xiaoxiang.base.dao.ibatis;


import com.xiaoxiang.base.BaseVO;
import com.xiaoxiang.base.dao.BaseDAO;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import java.io.Serializable;
import java.util.*;

/**
 * IBatis Dao的泛型基类.
 * <p/>
 * 继承于Spring的SqlMapClientDaoSupport,对返回值作了泛型类型转换.
 *
 * @author xiang.xiaox
 * @see SqlMapClientDaoSupport
 */
@SuppressWarnings("unchecked")
public class BaseDAOIbatis<T extends BaseVO, PK extends Serializable> extends SqlMapClientDaoSupport implements BaseDAO<T, PK> {

    static String classPath = BaseDAOIbatis.class.toString();

    protected Class<T> entityClass;

    public static final String POSTFIX_INSERT = ".insert";

    public static final String POSTFIX_UPDATE = ".update";

    public static final String POSTFIX_DELETE = ".delete";

    public static final String POSTFIX_DELETE_PRIAMARYKEY = ".deleteByPrimaryKey";

    public static final String POSTFIX_SELECT = ".select";

    public static final String POSTFIX_SELECTBYID = ".existsById";


    @Override
    public Class<T> getEntityClass() {
        return entityClass;
    }

    @Override
    public void addEntity(T entity) {
        getSqlMapClientTemplate().insert(getEntityClass().getName() + POSTFIX_INSERT, entity);
    }

    @Override
    public void saveOrUpdateEntity(T entity) {
        updateEntity(entity);
    }

    @Override
    public void saveOrUpdateBatchEntity(List<T> entitys) {

    }

    @Override
    public void deleteEntity(T entity) {
        getSqlMapClientTemplate().delete(entity.getClass().getName() + POSTFIX_DELETE, entity);
    }

    @Override
    public void deleteEntityByKey(PK key) {
        getSqlMapClientTemplate().delete(getEntityClass().getName() + POSTFIX_DELETE_PRIAMARYKEY, key);
    }

    @Override
    public void deleteBatchEntity(List<T> entitys) {

    }

    @Override
    public void deleteBatchEntityByKeys(PK[] keys) {
        /* List<T> entitys = new ArrayList<T>();
        for (PK key : keys) {
            Object entity = getHibernateTemplate().get(getEntityClass(), key);
            entitys.add((T) entity);
        }
        getHibernateTemplate().deleteAll(entitys);*/
    }

    @Override
    public void deleteBatchEntityByKeys(PK[] keys, Class<T> entityClass) {
        /* List<T> entitys = new ArrayList<T>();
        for (PK key : keys) {
            Object entity = getHibernateTemplate().get(entityClass, key);
            entitys.add((T) entity);
        }
        getHibernateTemplate().deleteAll(entitys);*/
    }

    @Override
    public void updateEntity(T entity) {
        getSqlMapClientTemplate().update(entity.getClass().getName() + POSTFIX_UPDATE, entity);
    }

    @Override
    public T findEntityBykey(PK key) {
        T entity = (T) getSqlMapClientTemplate().queryForObject(getEntityClass().getName() + POSTFIX_SELECTBYID, key);
        // if (entity == null)
        //   throw new ObjectRetrievalFailureException(getEntityClass(), key);
        return entity;
    }

    @Override
    public List<T> getAllEntity() {
        //System.out.println(getEntityClass().getName() + POSTFIX_SELECT);
        return getSqlMapClientTemplate().queryForList(getEntityClass().getName() + POSTFIX_SELECT, null);
    }

    @Override
    public List<T> getAllEntityDistinct() {
        Collection<T> result = new LinkedHashSet<T>(getAllEntity());
        return new ArrayList<T>(result);
    }

    @Override
    public boolean exists(PK key) {
        T entity = findEntityBykey(key);
        return entity != null;
    }

    @Override
    public List<T> findByParamQuery(Map<String, Object> queryParams) {
        return null;
    }

    @Override
    public int findByParamQueryCount(Map<String, Object> queryParams) {
        return 0;
    }


    public BaseDAOIbatis() {

    }

    public BaseDAOIbatis(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

}
