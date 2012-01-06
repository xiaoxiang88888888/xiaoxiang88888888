package com.xiaoxiang.gen;

import junit.framework.TestCase;
import org.junit.Test;

import java.sql.*;

/**
 * 说明
 *
 * @author xiang.xiaox
 */

public class GenTest extends TestCase {
    @Test
    public void testJdbc() throws Exception {
        Class.forName("com.mysql.jdbc.Driver");
        String url = "jdbc:mysql://10.20.147.196:3306/xiaoxiang88888888?user=root&password=4389758";
        Connection conn = DriverManager.getConnection(url);

        DatabaseMetaData databaseMetaData = conn.getMetaData();

        // 获取所有表
        ResultSet tableSet = databaseMetaData.getTables(null, "%", "area",
                new String[]{"TABLE"});
        while (tableSet.next()) {
            String tableName = tableSet.getString("TABLE_NAME");
            String tableComment = tableSet.getString("REMARKS");
            //String identifierProperty = tableSet.getString("SELF_REFERENCING");
            System.out.println("tableName=" + tableName);
            System.out.println("tableComment=" + tableComment);
            //System.out.println("identifierProperty=" + identifierProperty);

            // 获取tableName表列信息
            ResultSet columnSet = databaseMetaData.getColumns(null, "%",
                    tableName, "%");
            while (columnSet.next()) {
                String columnName = columnSet.getString("COLUMN_NAME");
                String columnComment = columnSet.getString("REMARKS");
                String sqlType = columnSet.getString("DATA_TYPE");
                String TYPE_NAME = columnSet.getString("TYPE_NAME");
                System.out.println(columnName);
                System.out.println(columnComment);
                System.out.println(sqlType);
                System.out.println(TYPE_NAME);
            }
        }
        //获取主键
        ResultSet primaryRS = databaseMetaData.getPrimaryKeys(null, null, "area");
        if (primaryRS.next()) {
            String identifierProperty = primaryRS.getString("COLUMN_NAME");
            System.out.println("identifierProperty=" + identifierProperty);
        }

        //获取表注释
        String comment = getCommentByTableName(conn, "area");
        System.out.println(comment);


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
}
