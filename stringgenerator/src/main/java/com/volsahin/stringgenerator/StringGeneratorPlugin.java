package com.volsahin.stringgenerator;

import org.gradle.api.Action;
import org.gradle.api.Plugin;
import org.gradle.api.Project;

import javax.annotation.Nonnull;

public class StringGeneratorPlugin implements Plugin<Project> {

    @Override
    public void apply(@Nonnull Project project) {
        final StringGeneratorExtension extension = project.getExtensions().create(
                StringGeneratorExtension.NAME, StringGeneratorExtension.class, project);

        project.getTasks().register(StringGeneratorTask.TASK_NAME, StringGeneratorTask.class,
                new Action<StringGeneratorTask>() {
                    @Override
                    public void execute(@Nonnull StringGeneratorTask task) {
                        task.setStringsJsonFile(extension.getStringsJsonFile());
                    }
                });
    }
}
