package App.DTO;

import App.RoborallyApplication.Model.GameObjects.Player;
import App.RoborallyApplication.Model.GameObjects.Robot;
import App.RoborallyApplication.Model.GameRunning.GameBrain;
import App.RoborallyApplication.Model.GameRunning.GameConfiguration;
import App.RoborallyApplication.Model.Enums.GamePhase;
import App.RoborallyApplication.Model.GameRunning.Gameboard;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GameBrainDTOJunitTest {
    private GameBrainDTO gameBrainDTO;
    private Gameboard gameboard;
    private GameConfiguration gameConfig;
    private GamePhase gamePhase;
    private GameBrain gameBrain;
    private ArrayList<Player> players;
    private ArrayList<Robot> robots;

    @BeforeEach
    void setUp() {
        gameboard = new Gameboard();
        gameConfig = new GameConfiguration();
        gamePhase = GamePhase.PLAY;
        gameBrain = new GameBrain();
        players = new ArrayList<>();
        robots = new ArrayList<>();
        gameBrainDTO = new GameBrainDTO(gameboard, gameConfig, gamePhase, gameBrain);
    }

    @Test
    void testGameBrainDTOConstructor() {
        assertEquals(gameboard.getID(), gameBrainDTO.gameBoardId);
        assertEquals(gamePhase, gameBrainDTO.gamePhase);
        assertEquals(gameConfig, gameBrainDTO.gameConfiguration);
        assertEquals(gameBrain.getID(), gameBrainDTO.id);
    }

    @Test
    void testReturnObjectFromDTO() {
        GameBrain resultGameBrain = gameBrainDTO.returnObjectFromDTO();
        assertEquals(gameboard.getID(), resultGameBrain.getGameboard().getID());
        assertEquals(gameConfig.getGameType(), resultGameBrain.getGameConfig().getGameType());
        assertEquals(gamePhase, resultGameBrain.getGamePhase());
        assertEquals(gameBrain.getID(), resultGameBrain.getID());
    }
}
