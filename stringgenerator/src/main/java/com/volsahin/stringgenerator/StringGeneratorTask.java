package com.volsahin.stringgenerator;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import com.squareup.javapoet.FieldSpec;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.TypeSpec;

import org.gradle.api.DefaultTask;
import org.gradle.api.tasks.TaskAction;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;

import javax.lang.model.element.Modifier;


public class StringGeneratorTask extends DefaultTask {

    public static final String TASK_NAME = "generateStringResourceFile";

    private static final String FILE_PACKAGE_NAME = "com.volsahin";

    private static final String RESOURCE_CLASS_NAME = "R";

    private static final String STRING_CLASS_NAME = "string";

    private File stringsJsonFile;

    @TaskAction
    public void execute() {
        TypeSpec.Builder resourceClass = createResourceClass();
        TypeSpec.Builder stringClass = createStringClass();
        createKeyValueVariablesInClass(stringClass);
        resourceClass.addType(stringClass.build());
        generateStringResourceFile(resourceClass);
    }

    private TypeSpec.Builder createResourceClass() {
        return TypeSpec.classBuilder(RESOURCE_CLASS_NAME)
                .addModifiers(Modifier.PUBLIC, Modifier.FINAL);
    }

    private TypeSpec.Builder createStringClass() {
        return TypeSpec.classBuilder(STRING_CLASS_NAME)
                .addModifiers(Modifier.PUBLIC, Modifier.STATIC, Modifier.FINAL);
    }

    private void createKeyValueVariablesInClass(TypeSpec.Builder ownerClassBuilder) {
        final StringsJsonEntity entity = createStringsJsonEntity();

        for (Map.Entry<String, String> stringKeyValue : entity.getStrings().entrySet()) {
            ownerClassBuilder.addField(
                    FieldSpec.builder(String.class, stringKeyValue.getKey())
                            .addModifiers(Modifier.PUBLIC, Modifier.STATIC, Modifier.FINAL)
                            .initializer("$S", stringKeyValue.getValue())
                            .build()
            );
        }
    }

    private StringsJsonEntity createStringsJsonEntity() {
        /*
        String json = "{\n" +
                "  \"string\" : {\n" +
                "    \"true_music\": \"Long live heavy metal\",\n" +
                "    \"my_repo\": \"https://github.com/volsahin\",\n" +
                "    \"description\" : \"String generator plugin\",\n" +
                "    \"done\" : \"done\"\n" +
                "  }\n" +
                "}";

        return new Gson().fromJson(json, StringsJsonEntity.class);

         */


        final JsonReader reader;
        try {
            reader = new JsonReader(new FileReader(stringsJsonFile));
            return new Gson().fromJson(reader, StringsJsonEntity.class);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    private void generateStringResourceFile(TypeSpec.Builder classBuilder) {
        final JavaFile javaFile = JavaFile.builder(FILE_PACKAGE_NAME, classBuilder.build()).build();
        try {
            javaFile.writeTo(System.out);
        } catch (IOException exception) {

        }
    }

    public void setStringsJsonFile(File stringsJsonFile) {
        this.stringsJsonFile = stringsJsonFile;
    }
}