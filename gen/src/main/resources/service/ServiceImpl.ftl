package ${pojo.getPackageName()}.${pojo.getDeclarationName()?lower_case}.service;

import ${pojo.getPackageName()}.${pojo.getDeclarationName()?lower_case}.dao.${pojo.getDeclarationName()}Dao;
import ${pojo.getPackageName()}.${pojo.getDeclarationName()?lower_case}.dataobject.${pojo.getDeclarationName()};
import com.alibaba.service.common.util.StringUtil;
import org.apache.log4j.Logger;

import java.util.List;

/**
 * ${clazz.table.comment} spring
 *
 * @author xiang.xiaox
 */
public class ${pojo.getDeclarationName()}ServiceImpl {
    private static final Logger log = Logger.getLogger(${pojo.getDeclarationName()}Service.class);

    //注入dao层实现
    private ${pojo.getDeclarationName()}Dao ${pojo.getDeclarationName()?uncap_first}Dao;

    public ${pojo.getDeclarationName()}Dao get${pojo.getDeclarationName()}Dao() {
        return ${pojo.getDeclarationName()?uncap_first}Dao;
    }

    public void set${pojo.getDeclarationName()}Dao(${pojo.getDeclarationName()}Dao ${pojo.getDeclarationName()?uncap_first}Dao) {
        this.${pojo.getDeclarationName()?uncap_first}Dao = ${pojo.getDeclarationName()?uncap_first}Dao;
    }

    /**
     * 插入相关记录
     *
     * @param ${pojo.getDeclarationName()?uncap_first}
     * @return  string
     */
    public String insert${pojo.getDeclarationName()}(${pojo.getDeclarationName()}  ${pojo.getDeclarationName()?uncap_first}) {
        return ${pojo.getDeclarationName()?uncap_first}Dao.insert${pojo.getDeclarationName()}(${pojo.getDeclarationName()?uncap_first});
    }

    /**
     * 修改相关记录
     *
     * @param ${pojo.getDeclarationName()?uncap_first}
     * @return  string
     */
    public String update${pojo.getDeclarationName()}(${pojo.getDeclarationName()}  ${pojo.getDeclarationName()?uncap_first}) {
        return ${pojo.getDeclarationName()?uncap_first}Dao.update${pojo.getDeclarationName()}(${pojo.getDeclarationName()?uncap_first});
    }

    /**
     * 删除相关记录
     *
     * @param ${pojo.getDeclarationName()?uncap_first}
     * @return  string
     */
    public String delete${pojo.getDeclarationName()}(${pojo.getDeclarationName()}  ${pojo.getDeclarationName()?uncap_first}) {
        return ${pojo.getDeclarationName()?uncap_first}Dao.delete${pojo.getDeclarationName()}(${pojo.getDeclarationName()?uncap_first});
    }

    /**
     * 查看相关记录
     *
     * @param ${pojo.getDeclarationName()?uncap_first}
     * @return   ${pojo.getDeclarationName()}
     */
    public ${pojo.getDeclarationName()} view${pojo.getDeclarationName()}(${pojo.getDeclarationName()}  ${pojo.getDeclarationName()?uncap_first}) {
        return ${pojo.getDeclarationName()?uncap_first}Dao.view${pojo.getDeclarationName()}(${pojo.getDeclarationName()?uncap_first});
    }

    /**
     * 查找相关的模块列表
     *
     * @param  ${pojo.getDeclarationName()?uncap_first}
     * @return  list
     */
    public List<${pojo.getDeclarationName()}> get${pojo.getDeclarationName()}s(${pojo.getDeclarationName()}  ${pojo.getDeclarationName()?uncap_first}) {
         return  ${pojo.getDeclarationName()?uncap_first}Dao.get${pojo.getDeclarationName()}List(${pojo.getDeclarationName()?uncap_first});
    }
}
