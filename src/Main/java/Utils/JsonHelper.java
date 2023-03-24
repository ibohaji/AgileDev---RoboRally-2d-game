package Utils;

import App.DTO.GameboardDTO;
import App.DTO.TileDTO;
import App.Domain.Enums.DifficultyEnum;
import App.Domain.Enums.GraphicalElementEnum;
import com.google.gson.Gson;

import java.io.File;
import java.util.ArrayList;

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
        var gameBrain = new GameBrain(4, DifficultyEnum.EASY);
        System.out.println(gameBrain.toJson());
        var path = System.getProperty("user.dir") + File.separator + "src" + File.separator + "Main" + File.separator
                + "java" + File.separator + "App" + File.separator + "Resources" + File.separator + "savedGames.txt";
        GameStateHelper.writeContentToFile(gameBrain.toJson(), path);




    }
}
