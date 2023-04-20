package App.DTO;

import App.RoborallyApplication.Model.LPlayer;
import App.RoborallyApplication.Model.LRobot;
import App.RoborallyApplication.Model.LGameBrain;
import App.RoborallyApplication.Model.LGameConfiguration;
import App.RoborallyApplication.Model.EnumGamePhase;
import App.RoborallyApplication.Model.LGameboard;

import java.util.ArrayList;
import java.util.UUID;

public class GameBrainDTO implements iFromDTO{
    public UUID id;
    public UUID gameBoardId;
    public ArrayList<PlayerDTO> playerDTOS;
    public GameboardDTO gameboardDTO;
    public LGameConfiguration gameConfiguration;
    public EnumGamePhase enumGamePhase;
    public GameBrainDTO(){

    }

    public GameBrainDTO(LGameboard gameboard, LGameConfiguration gameConfig, EnumGamePhase enumGamePhase, LGameBrain gameBrain){
        this.gameboardDTO = new GameboardDTO(gameboard);
        this.gameConfiguration = gameConfig;
        this.enumGamePhase = enumGamePhase;
        this.id = gameBrain.getID();
        this.gameBoardId = gameboard.getID();
        playerDTOS = new ArrayList<>();
        for (LPlayer player: gameBrain.getPlayers()) {
            playerDTOS.add(new PlayerDTO(player));
        }
    }

    @Override
    public LGameBrain returnObjectFromDTO() {
        LGameBrain gameBrain = new LGameBrain();
        LGameboard gameboard = gameboardDTO.returnObjectFromDTO();
        ArrayList<LPlayer> players = new ArrayList<>();
        ArrayList<LRobot> robots = new ArrayList<>();
        for (PlayerDTO playerDTO: playerDTOS) {
            players.add(playerDTO.returnObjectFromDTO());
        }

        gameBrain.restoreGameboard(this.gameConfiguration, players ,this.enumGamePhase, gameboard,
                gameboard.getRobots(), gameboard.getTiles());
        return gameBrain;
    }
}
