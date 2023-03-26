package App.DTO;

import App.Model.GameRunning.GameBrain;
import App.Model.GameRunning.GameConfiguration;
import App.Model.Enums.GamePhase;
import App.Model.GameRunning.Gameboard;

import java.util.UUID;

public class GameBrainDTO {

    public UUID id;

    public UUID gameBoardId;
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
    }
}
