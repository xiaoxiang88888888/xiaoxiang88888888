package com.xiaoxiang.maven.plugin;

import com.xiaoxiang.maven.properties.PropertiesUtil;
import com.xiaoxiang.maven.template.VelocityUtil;
import com.xiaoxiang.maven.util.StringUtil;
import com.xiaoxiang.maven.xml.Dom4jUtil;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.project.MavenProject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 说明
 *
 * @author xiang.xiaox
 * @goal config
 */

public class ConfigCompileMojo extends AbstractMojo {
    private final Logger logger = LoggerFactory.getLogger(ConfigCompileMojo.class);

    /**
     * @parameter expression="${project}"
     * @required
     * @readonly
     */
    MavenProject project;

    /**
     * 自定义的属性文件,用于动态生成其它文件的数据源
     *
     * @parameter expression="${propertiesPath}" default-value="${user.home}/autoConfig.properties"
     */
    String propertiesPath;

    /**
     * 自定义的配置文件,用于指定生成哪些文件
     *
     * @parameter expression="${configPath}" default-value="META-INF/autoconfig/autoConfig.xml"
     */
    String configPath;

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

    /**
     * 工程名称
     *
     * @parameter expression="${project.build.finalName}"
     */
    String finalName;

    @Override
    public void execute() throws MojoExecutionException, MojoFailureException {
        /* Properties propertie = System.getProperties();
      logger.info("//////////////finalName=" + propertie);*/
        getLog().info("//////////////propertiesPath=" + propertiesPath);
        getLog().info("//////////////templateType=" + templateType);
        getLog().info("//////////////encoding=" + encoding);
        getLog().info("//////////////finalName=" + finalName);
        genFiles();

    }

    /**
     * 生成文件
     */
    public void genFiles() {
        String prefixDir = this.project.getBasedir().getAbsolutePath();
        Dom4jUtil dom4jUtil;
        String templatePath= prefixDir +"/target/classes/";
        if (StringUtil.isEmpty(configPath)) {
            dom4jUtil = new Dom4jUtil(prefixDir);
        } else {
            dom4jUtil = new Dom4jUtil(templatePath,configPath, false);
        }
        PropertiesUtil propertiesUtil;
        if (StringUtil.isEmpty(propertiesPath)) {
            propertiesUtil = new PropertiesUtil();
        } else {
            propertiesUtil = new PropertiesUtil(propertiesPath);
        }
        if ("velocity".equalsIgnoreCase(templateType)) {
            VelocityUtil velocityUtil = new VelocityUtil();
            velocityUtil.setDom4jUtil(dom4jUtil);
            velocityUtil.setPropertiesUtil(propertiesUtil);
            velocityUtil.setClassPath(false);
            velocityUtil.setTemplatePath(templatePath);
            velocityUtil.init();
            velocityUtil.mergeTemplate(templatePath);
        } else {
            logger.error("配置的模板类型 " + templateType + " 不对!");
        }
        //将新的配置项写入autoConfig.properties文件中
        propertiesUtil.close(dom4jUtil.getPropDescMap());
    }

    public void setProject(MavenProject project) {
        this.project = project;
    }
}
