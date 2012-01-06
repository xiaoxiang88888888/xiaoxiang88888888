package com.xiaoxiang.gen.jdbc;

import com.xiaoxiang.gen.pojo.Field;
import com.xiaoxiang.gen.pojo.Table;
import com.xiaoxiang.gen.util.StringUtil;

import java.sql.*;
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
        Table table = new Table();
        Class.forName(driver); //"com.mysql.jdbc.Driver"
        //String url = "jdbc:mysql://10.20.147.196:3306/xiaoxiang88888888?user=root&password=4389758";
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url, user, password);
            DatabaseMetaData databaseMetaData = conn.getMetaData();

            //获取主键
            ResultSet primaryRS = databaseMetaData.getPrimaryKeys(null, null, tableName);
            if (primaryRS.next()) {
                String identifierProperty = primaryRS.getString("COLUMN_NAME");
                //System.out.println("identifierProperty="+identifierProperty);
                table.setIdentifierProperty(StringUtil.underlineToCamel(identifierProperty));
            }

            // 获取相应表
            //catalog - 数据库目录名称，可设为null，（具体JDBC驱动的实现不一样在MySQL中指数据库名）。
            //schemaPattern - 方案名称的样式，可设为null，（ 具体JDBC驱动的实现不一样， 在Oracle中指用户名）。
            //tableNamePattern - 表名称的样式，可以包含匹配符比如："TEST%"
            //types - 要包括的表类型组成的列表，可设为null，表示所有的。types的常量值为："TABLE","VIEW", "SYSTEM TABLE", "GLOBAL TEMPORARY", "LOCAL TEMPORARY", "ALIAS", "SYNONYM"
            ResultSet tableSet = databaseMetaData.getTables(null, "%", tableName, new String[]{"TABLE"});
            if (tableSet.next()) {
                table.setName(StringUtil.underlineToCamel(tableSet.getString("TABLE_NAME")));
                table.setDesc(tableSet.getString("REMARKS"));

                // 获取tableName表列信息
                ResultSet columnSet = databaseMetaData.getColumns(null, "%", table.getName(), "%");
                List<Field> fields = new ArrayList<Field>();
                while (columnSet.next()) {
                    Field field = new Field();
                    String columnName = columnSet.getString("COLUMN_NAME");
                    field.setName(StringUtil.underlineToCamel(columnName));
                    field.setDesc(columnSet.getString("REMARKS"));
                    //field.setType(columnSet.getString("DATA_TYPE"));
                    field.setType(columnSet.getString("TYPE_NAME"));
                    //判断主键
                    if (StringUtil.isEmpty(table.getIdentifierProperty())) {
                        field.setPk(false);
                    } else {
                        if (columnName.equalsIgnoreCase(table.getIdentifierProperty())) {
                            field.setPk(true);
                        } else {
                            field.setPk(false);
                        }
                    }
                    fields.add(field);
                }
                table.setFields(fields);
                //break;//只获取第一个符合的表名
            }
            //表的注释,因一般jdbc没有把这个值带上
            //如果是mysql
            if (driver.contains("mysql")) {
                table.setDesc(getCommentByTableName(conn, tableName));
            }

        } finally {
            if (conn != null) conn.close();
        }

        return table;
    }


    public static String getCommentByTableName(Connection conn, String tableName) {
        Statement stmt = null;
        ResultSet rs = null;
        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery("SHOW CREATE TABLE " + tableName);
            if (rs != null && rs.next()) {
                String create = rs.getString(2);
                return parse(create);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                if (stmt != null) stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }


        return "";
    }

    public static String parse(String all) {
        String comment;
        int index = all.indexOf("COMMENT='");
        if (index < 0) {
            return "";
        }
        comment = all.substring(index + 9);
        comment = comment.substring(0, comment.length() - 1);
        return comment;
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
