package App.DTO;

import App.Domain.GameBrain;
import App.Domain.GameConfiguration;
import App.Domain.GamePhase;
import App.Domain.Gameboard;

public class GameBrainDTO {
    public GameboardDTO gameboardDTO;
    public GameConfiguration gameConfiguration;
    public GamePhase gamePhase;
    public GameBrainDTO(){

    }

    public GameBrainDTO(Gameboard gameboard, GameConfiguration gameConfig, GamePhase gamePhase){
        this.gameboardDTO = new GameboardDTO(gameboard);
        this.gameConfiguration = gameConfig;
        this.gamePhase = gamePhase;
    }
}
