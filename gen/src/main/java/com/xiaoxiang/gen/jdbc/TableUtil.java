package com.xiaoxiang.gen.jdbc;

import com.xiaoxiang.gen.pojo.Field;
import com.xiaoxiang.gen.pojo.Table;
import com.xiaoxiang.gen.util.StringUtil;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * 说明
 *
 * @author xiang.xiaox
 */

public class TableUtil {
    private String driver;
    private String url;
    private String user;
    private String password;

    /**
     * 根据表名得到表
     *
     * @param tableName
     * @return
     * @throws Exception
     */
    public Table getTable(String tableName) throws Exception {
        Table table = null;
        Class.forName(driver); //"com.mysql.jdbc.Driver"
        //String url = "jdbc:mysql://10.20.147.196:3306/xiaoxiang88888888?user=root&password=4389758";
        Connection conn = DriverManager.getConnection(url, user, password);

        DatabaseMetaData databaseMetaData = conn.getMetaData();

        // 获取所有表
        //catalog - 数据库目录名称，可设为null，（具体JDBC驱动的实现不一样在MySQL中指数据库名）。
        //schemaPattern - 方案名称的样式，可设为null，（ 具体JDBC驱动的实现不一样， 在Oracle中指用户名）。
        //tableNamePattern - 表名称的样式，可以包含匹配符比如："TEST%"
        //types - 要包括的表类型组成的列表，可设为null，表示所有的。types的常量值为："TABLE","VIEW", "SYSTEM TABLE", "GLOBAL TEMPORARY", "LOCAL TEMPORARY", "ALIAS", "SYNONYM"
        ResultSet tableSet = databaseMetaData.getTables(null, "%", tableName, new String[]{"TABLE"});
        if (tableSet.next()) {
            table = new Table();
            table.setName(StringUtil.underlineToCamel(tableSet.getString("TABLE_NAME")));
            table.setDesc(tableSet.getString("REMARKS"));

            // 获取tableName表列信息
            ResultSet columnSet = databaseMetaData.getColumns(null, "%", table.getName(), "%");
            List<Field> fields = new ArrayList<Field>();
            while (columnSet.next()) {
                Field field = new Field();
                field.setName(StringUtil.underlineToCamel(columnSet.getString("COLUMN_NAME")));
                field.setDesc(columnSet.getString("REMARKS"));
                field.setType(columnSet.getString("DATA_TYPE"));
                fields.add(field);
            }
            table.setFields(fields);
            //break;//只获取第一个符合的表名
        }
        return table;
    }

    public String getDriver() {
        return driver;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
