package Utils;

import com.google.gson.Gson;

public class JsonHelper {
    public static Gson gson = new Gson();

    public static String serializeObjectToJson(Object toSerialize){
        return gson.toJson(toSerialize);
    }

    public static <T> T getObjectFromJson(String jsonOfObject, Class<T> classToReturn){
        return gson.fromJson(jsonOfObject, classToReturn);
    }

    public static String readStringFromFile(String filepath){
        return "";
    }
}
