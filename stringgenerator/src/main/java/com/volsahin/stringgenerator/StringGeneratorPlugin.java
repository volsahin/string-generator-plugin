package com.volsahin.stringgenerator;

import com.android.build.gradle.AppExtension;

import org.gradle.api.Plugin;
import org.gradle.api.Project;
import org.gradle.api.Task;

import java.io.File;

import javax.annotation.Nonnull;

public class StringGeneratorPlugin implements Plugin<Project> {

    @Override
    public void apply(@Nonnull Project project) {
        final StringGeneratorExtension generatorExtension = project.getExtensions().create(
                StringGeneratorExtension.NAME, StringGeneratorExtension.class);

        AppExtension appExtension = project.getExtensions().findByType(AppExtension.class);

        if (appExtension != null) {
            appExtension.getApplicationVariants().all(variant -> {
                String outputDir = project.getBuildDir().getPath() + "/generated/source/" + variant.getName();
                String variantName = variant.getName().substring(0, 1).toUpperCase() + variant.getName().substring(1);
                String taskName = StringGeneratorTask.TASK_NAME + variantName;

                Task stringGeneratorTask = project.getTasks().create(taskName, StringGeneratorTask.class,
                        task -> {
                            task.setExtension(generatorExtension);
                            task.setGenerationPath(outputDir);
                        });

                variant.registerJavaGeneratingTask(stringGeneratorTask, new File(outputDir));
            });
        }
    }
}
