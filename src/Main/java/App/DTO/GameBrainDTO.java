package App.DTO;

import App.RoborallyApplication.Model.GameObjects.Player;
import App.RoborallyApplication.Model.GameObjects.Robot;
import App.RoborallyApplication.Model.GameRunning.GameBrain;
import App.RoborallyApplication.Model.GameRunning.GameConfiguration;
import App.RoborallyApplication.Model.Enums.GamePhase;
import App.RoborallyApplication.Model.GameRunning.Gameboard;

import java.util.ArrayList;
import java.util.UUID;

public class GameBrainDTO implements iFromDTO{
    public UUID id;
    public UUID gameBoardId;
    public ArrayList<PlayerDTO> playerDTOS;
    public GameboardDTO gameboardDTO;
    public GameConfiguration gameConfiguration;
    public GamePhase gamePhase;
    public GameBrainDTO(){

    }

    public GameBrainDTO(Gameboard gameboard, GameConfiguration gameConfig, GamePhase gamePhase, GameBrain gameBrain){
        this.gameboardDTO = new GameboardDTO(gameboard);
        this.gameConfiguration = gameConfig;
        this.gamePhase = gamePhase;
        this.id = gameBrain.getID();
        this.gameBoardId = gameboard.getID();
        playerDTOS = new ArrayList<>();
        for (Player player: gameBrain.getPlayers()) {
            playerDTOS.add(new PlayerDTO(player));
        }
    }

    @Override
    public GameBrain returnObjectFromDTO() {
        GameBrain gameBrain = new GameBrain();
        Gameboard gameboard = gameboardDTO.returnObjectFromDTO();
        ArrayList<Player> players = new ArrayList<>();
        ArrayList<Robot> robots = new ArrayList<>();
        for (PlayerDTO playerDTO: playerDTOS) {
            players.add(playerDTO.returnObjectFromDTO());
        }

        gameBrain.restore(this.gameConfiguration, players ,this.gamePhase, gameboard,
                gameboard.getRobots(), gameboard.getTiles());
        return gameBrain;
    }
}
