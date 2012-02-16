package com.xiaoxiang.gen;

import junit.framework.TestCase;

/**
 * 主程序测试
 *
 * @author xiang.xiaox
 */

public class MainTest extends TestCase {

    public void testGen() throws Exception {
        String baseDir = "d:/gen/";
        String tableName="area";
        String templateDir="vo";
        String templateName="VO.vm";
        String templateType="velocity";
        String encoding="UTF-8";
        String destDir=baseDir+"vo/Area.java";
        String driver="com.mysql.jdbc.Driver";
        String url="jdbc:mysql://10.20.156.119:3306/xiaoxiang88888888";
        String user="root";
        String password="4389758";
        //目录相对类路径
        Main main = new Main();
        main.setTableName(tableName);
        main.setTemplateDir(templateDir);
        main.setTemplateName(templateName);
        main.setTemplateType(templateType);
        main.setEncoding(encoding);
        main.setDestDir(destDir);
        main.setDriver(driver);
        main.setUrl(url);
        main.setUser(user);
        main.setPassword(password);
        //开始生成
        main.execute();
    }
}
