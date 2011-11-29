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

    public void testConfigMojo() throws Exception {
        File testPom = new File(getBasedir(), "src/test/resources/plugin-test.xml");
        ConfigMojo mojo = (ConfigMojo) lookupMojo("config", testPom);
        mojo.execute();
    }
}
