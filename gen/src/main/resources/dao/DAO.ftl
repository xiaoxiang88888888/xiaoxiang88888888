package ${pojo.getPackageName()}.${pojo.getDeclarationName()?lower_case}.dao;

import com.xiaoxiang.base.dao.BaseDAO;
import ${pojo.getPackageName()}.model.${pojo.getDeclarationName()};

/**
 * ${pojo.getDeclarationName()}dao层的接口
 * ${clazz.table.comment}
 * @author xiaoxiang
 */
public interface ${pojo.getDeclarationName()}DAO extends BaseDAO<${pojo.getDeclarationName()}, String> {
}
