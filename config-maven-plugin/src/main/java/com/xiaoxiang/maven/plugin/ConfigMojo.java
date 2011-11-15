package com.xiaoxiang.maven.plugin;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;

/**
 * 说明
 *
 * @author xiang.xiaox
 * @goal config
 */

public class ConfigMojo extends AbstractMojo {

    /**
     * 自定义的属性文件,用于动态生成其它文件的数据源
     *
     * @parameter expression="${propertiesPath}" default-value="${user.home}"
     */
     String propertiesPath;

    /**
     * 模块类型 freemarker 或者 velocity
     *
     * @parameter expression="${templateType}" default-value="freemarker"
     */
     String templateType;

    /**
     * 文件编码
     *
     * @parameter expression="${encoding}" default-value = "UTF-8"
     */
    String encoding;

    @Override
    public void execute() throws MojoExecutionException, MojoFailureException {
        getLog().info("//////////////propertiesPath=" + propertiesPath);
        getLog().info("//////////////templateType=" + templateType);
        getLog().info("//////////////encoding=" + encoding);
    }
}
