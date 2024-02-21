package config;

import com.google.gson.JsonObject;
import utils.JsonUtils;

public class Url {

    public static String URL;
    public static String DOMAIN;
    public static String ORIGIN;
    public static String REFERER;

    public Url(String env) {
        JsonObject map;
        map = JsonUtils.readJsonFile("url.json");
        JsonObject environment = map.getAsJsonObject(env);
        URL = environment.get("URL").getAsString();
        DOMAIN = environment.get("DOMAIN").getAsString();
        ORIGIN = environment.get("ORIGIN").getAsString();
        REFERER = environment.get("REFERER").getAsString();
    }
}