package Utils;

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
        //var gameboard = new Gameboard(new GameConfiguration(4, DifficultyEnum.EASY));
        var tile = new Tile(10, 10);
        tile.graphicalElement.changeGraphicalElement(GraphicalElementEnum.DEFAULT_FLOOR);
        var tileDTO = new TileDTO(tile);
        var jsonString = serializeObjectToJson(tileDTO);
        tileDTO = getObjectFromJson(jsonString, tileDTO.getClass());
        System.out.println(tileDTO.xCoordinate);
        System.out.println(tileDTO.yCoordinate);
        System.out.println(tileDTO.graphicalElementPath);
    }
}
