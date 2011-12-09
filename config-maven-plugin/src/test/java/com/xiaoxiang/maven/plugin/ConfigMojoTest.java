package com.xiaoxiang.maven.plugin;

import org.apache.maven.plugin.testing.AbstractMojoTestCase;

import java.io.File;

/**
 * 测试
 *
 * @author xiang.xiaox
 */

public class ConfigMojoTest extends AbstractMojoTestCase {
    protected void setUp() throws Exception {
        super.setUp();

    }

    public void testConfigMojo() {
        File testPom = new File(getBasedir(), "src/test/resources/plugin-test.xml");
        ConfigCompileMojo mojo = null;
        try {
            mojo = (ConfigCompileMojo) lookupMojo("config", testPom);
            mojo.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /* public static String getBasedir() {
       return "Z:\\new_xiaoxiang\\config-maven-plugin\\pom.xml";
    }*/
}
