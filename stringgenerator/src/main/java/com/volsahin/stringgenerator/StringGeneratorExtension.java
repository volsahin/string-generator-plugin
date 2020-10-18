package com.volsahin.stringgenerator;

import org.gradle.api.tasks.InputFile;

import java.io.File;

import javax.annotation.Nullable;

public class StringGeneratorExtension {

    public static final String NAME = "stringGenerator";

    private File stringsJsonFile;

    public File getStringsJsonFile() {
        return stringsJsonFile;
    }

    @InputFile
    public void setStringsJsonFile(@Nullable File stringsJsonFile) {
        this.stringsJsonFile = stringsJsonFile;
    }
}
