package Utils;

import App.RoborallyApplication.Model.LGameBrain;
import App.RoborallyApplication.Model.EnumDifficulty;
import com.google.gson.Gson;

import java.io.File;

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
    public static void main(String[] args) {
        /*var gameBrain = new LGameBrain(4, EnumDifficulty.EASY);
        System.out.println(gameBrain.DTOasJson());
        var path = System.getProperty("user.dir") + File.separator + "src" + File.separator + "Main" + File.separator
                + "java" + File.separator + "App" + File.separator + "Resources" + File.separator + "savedGames.txt";
        IOHelper.writeContentToFile(gameBrain.DTOasJson(), path);*/




    }
}
