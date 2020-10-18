package com.volsahin.stringgenerator;

import com.google.gson.annotations.SerializedName;

import java.util.Map;

public class StringsJsonEntity {

    @SerializedName("string")
    private Map<String, String> strings;

    public Map<String, String> getStrings() {
        return strings;
    }
}
