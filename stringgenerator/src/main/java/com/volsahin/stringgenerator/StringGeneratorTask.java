package com.volsahin.stringgenerator;

import com.google.gson.Gson;

import org.gradle.api.tasks.TaskAction;

public class StringGeneratorTask {

    @TaskAction
    public void execute() {
        String json = "{\n" +
                "  \"string\" : {\n" +
                "    \"true_music\": \"Long live heavy metal\",\n" +
                "    \"my_repo\": \"https://github.com/volsahin\",\n" +
                "    \"continue\" : \"Continue\",\n" +
                "    \"done\" : \"done\"\n" +
                "  }\n" +
                "}";

        Gson gson = new Gson();
        StringsJsonEntity entity = gson.fromJson(json, StringsJsonEntity.class);
    }
}