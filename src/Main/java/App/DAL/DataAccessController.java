package App.DAL;

import App.DTO.GameboardDTO;
import App.RoborallyApplication.Model.GameRunning.GameBrain;
import App.RoborallyApplication.Model.GameRunning.Gameboard;

import java.util.UUID;

public class DataAccessController {
    public static void saveGame(GameBrain gameBrain){
        Gameboard gameboard = gameBrain.getGameboard();
    }
    public static void loadGame(UUID id){

    }

    public static void initializeGameboardFromDTO(GameboardDTO gameboardDTO){

    }
    public static void getAvailableGames(){

    }
    public static void loadAllAvailableMaps(){

    }
    public static void loadSpecificMap(UUID id){

    }


}
