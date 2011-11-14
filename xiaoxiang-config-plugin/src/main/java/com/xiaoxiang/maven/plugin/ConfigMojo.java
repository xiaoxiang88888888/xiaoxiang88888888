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

public class ConfigMojo extends AbstractMojo{
    @Override
    public void execute() throws MojoExecutionException, MojoFailureException {
       getLog().info("bye,world!");
    }
}
