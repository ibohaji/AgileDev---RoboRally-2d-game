package Utils;

import App.DTO.GameboardDTO;
import App.DTO.TileDTO;
import App.Domain.Enums.DifficultyEnum;
import App.Domain.Enums.GraphicalElementEnum;
import App.Domain.GameConfiguration;
import App.Domain.Gameboard;
import App.Domain.Tile;
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
    public static void main(String[] args) {
        // GAMEBOARDS
        var gameboard = new Gameboard(new GameConfiguration(4, DifficultyEnum.EASY));
        var gameBoardJson = gameboard.toJson();
        System.out.println(gameBoardJson);
        var gameBoardDTO = JsonHelper.getObjectFromJson(gameBoardJson, GameboardDTO.class);
        System.out.println(gameBoardDTO);




    }
}
