package com.volsahin.stringgenerator;

import org.gradle.api.Project;
import org.gradle.testfixtures.ProjectBuilder;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class StringGeneratorTaskTest {

    @Test
    public void testExecution() {
        Project project = ProjectBuilder.builder().build();
        project.getPluginManager().apply(StringGeneratorPlugin.class);
        assertTrue(project.getPlugins().hasPlugin(StringGeneratorPlugin.class));
    }
}