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
     * @parameter expression="${length}" default-value="0"
     */
    private Integer length;

    @Override
    public void execute() throws MojoExecutionException, MojoFailureException {
        getLog().info("bye,world!"+length);
    }
}
