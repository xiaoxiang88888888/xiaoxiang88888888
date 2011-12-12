package com.xiaoxiang.maven.plugin;

import org.apache.maven.artifact.repository.ArtifactRepository;
import org.apache.maven.artifact.repository.DefaultArtifactRepository;
import org.apache.maven.artifact.repository.layout.ArtifactRepositoryLayout;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.testing.AbstractMojoTestCase;
import org.apache.maven.project.MavenProject;
import org.apache.maven.project.MavenProjectBuilder;

import java.io.File;

/**
 * 测试
 *
 * @author xiang.xiaox
 */

public class ConfigMojoTest extends AbstractMojoTestCase {


  /*  public void testConfigMojo() throws Exception {
        File pom = new File(getBasedir(), "src/test/resources/plugin-test.xml");
        ConfigCompileMojo mojo;
        try {
            execute(pom, "config");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }*/

    /**
     * @return output from System.out during mojo execution
     */
    private void execute(File pom, String goal) throws Exception {

        ConfigCompileMojo mojo;
        mojo = (ConfigCompileMojo) lookupMojo(goal, pom);

        setUpProject(pom, mojo);

        MavenProject project = (MavenProject) getVariableValueFromObject(mojo, "project");    

        try {
            mojo.execute();
        } finally {
            // see testUncooperativeThread() for explaination
            Thread.sleep(300); // time seems about right
           
        }
    }

    private void setUpProject(File pomFile, AbstractMojo mojo)
            throws Exception {
        MavenProjectBuilder builder = (MavenProjectBuilder) lookup(MavenProjectBuilder.ROLE);

        ArtifactRepositoryLayout localRepositoryLayout =
                (ArtifactRepositoryLayout) lookup(ArtifactRepositoryLayout.ROLE, "default");

        String path = "src/test/repository";

        ArtifactRepository localRepository = new DefaultArtifactRepository("local", "file://" +
                new File(path).getAbsolutePath(), localRepositoryLayout);

        MavenProject project = builder.buildWithDependencies(pomFile, localRepository, null);
        // this gets the classes for these tests of this mojo (exec plugin) onto the project classpath for the test
        project.getBuild().setOutputDirectory(new File("target/test-classes").getAbsolutePath());
        setVariableValueToObject(mojo, "project", project);
    }
}
