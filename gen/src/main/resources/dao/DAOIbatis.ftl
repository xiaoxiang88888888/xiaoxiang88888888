package ${pojo.getPackageName()}.${pojo.getDeclarationName()?lower_case}.dao;

import ${pojo.getPackageName()}.${pojo.getDeclarationName()?lower_case}.dataobject.${pojo.getDeclarationName()};
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import java.util.List;

/**
 * ${clazz.table.comment} ibatis
 *
 * @author xiang.xiaox
 */
public class ${pojo.getDeclarationName()}Dao extends SqlMapClientDaoSupport {

    /**
     * 插入相关记录
     *
     * @param ${pojo.getDeclarationName()?uncap_first}
     * @return  String
     */
    public String insert${pojo.getDeclarationName()}(${pojo.getDeclarationName()} ${pojo.getDeclarationName()?uncap_first}) {
        getSqlMapClientTemplate().insert("${pojo.getDeclarationName()?lower_case}.insert${pojo.getDeclarationName()}", ${pojo.getDeclarationName()?uncap_first});
        return "插入成功";
    }

    /**
     * 修改相关记录
     *
     * @param ${pojo.getDeclarationName()?uncap_first}
     * @return  String
     */
    public String update${pojo.getDeclarationName()}(${pojo.getDeclarationName()} ${pojo.getDeclarationName()?uncap_first}) {
        getSqlMapClientTemplate().update("${pojo.getDeclarationName()?lower_case}.update${pojo.getDeclarationName()}", ${pojo.getDeclarationName()?uncap_first});
        return "ok";
    }

    /**
     * 删除相关记录
     *
     * @param ${pojo.getDeclarationName()?uncap_first}
     * @return  String
     */
    public String delete${pojo.getDeclarationName()}(${pojo.getDeclarationName()} ${pojo.getDeclarationName()?uncap_first}) {
        getSqlMapClientTemplate().delete("${pojo.getDeclarationName()?lower_case}.delete${pojo.getDeclarationName()}", ${pojo.getDeclarationName()?uncap_first});
        return "删除成功";
    }

    /**
     * 查看相关记录
     *
     * @param ${pojo.getDeclarationName()?uncap_first}
     * @return   ${pojo.getDeclarationName()}
     */
    public ${pojo.getDeclarationName()} view${pojo.getDeclarationName()}(${pojo.getDeclarationName()} ${pojo.getDeclarationName()?uncap_first}) {
        return (${pojo.getDeclarationName()}) getSqlMapClientTemplate().queryForObject("${pojo.getDeclarationName()?lower_case}.queryById", ${pojo.getDeclarationName()?uncap_first});
    }

    /**
     * 根据条件查询列表
     *
     * @param ${pojo.getDeclarationName()?uncap_first}
     * @return   ${pojo.getDeclarationName()}
     */
    @SuppressWarnings("unchecked")
    public List<${pojo.getDeclarationName()}> get${pojo.getDeclarationName()}List(${pojo.getDeclarationName()} ${pojo.getDeclarationName()?uncap_first}) {
        if (${pojo.getDeclarationName()?uncap_first} != null) {
            return getSqlMapClientTemplate().queryForList("${pojo.getDeclarationName()?lower_case}.queryList", ${pojo.getDeclarationName()?uncap_first});
        } else {
            return null;
        }
    }

}