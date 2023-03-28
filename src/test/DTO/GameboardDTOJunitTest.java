
import java.util.ArrayList;

import App.DTO.GameboardDTO;
import App.DTO.TileDTO;
import App.RoborallyApplication.Model.GameRunning.DifficultyEnum;
import App.RoborallyApplication.Model.GameRunning.GameConfiguration;
import App.RoborallyApplication.Model.GameRunning.Gameboard;
import App.RoborallyApplication.Model.GameObjects.Robot;
import org.junit.jupiter.api.Assertions;
import org.testng.annotations.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

public class GameboardDTOJunitTest {
    private Gameboard gameboard;
    private GameboardDTO gameboardDTO;

    //Create an object of Gameboard and an object of GameboardDTO
    @BeforeEach
    void setUp() {
        gameboard = new Gameboard(new GameConfiguration(2, DifficultyEnum.EASY));
        gameboardDTO = new GameboardDTO(gameboard);
    }


    //Test Gameboard object properties to GameboardDTO object properties
    @Test
    void testConstructor() {
        Assertions.assertNotNull(gameboardDTO);
        Assertions.assertEquals(gameboard.getGameboardDimensions(), gameboardDTO.dimensions);
        Assertions.assertEquals(gameboard.getGameConfig(), gameboardDTO.gameConfig);
        Assertions.assertEquals(gameboard.getTiles().size(), gameboardDTO.tiles.size());
        Assertions.assertEquals(gameboard.getRobots().size(), gameboardDTO.robots.size());
    }

    //Test GameboardDTO object properties to Gameboard object properties
    @Test
    void testToGameboard() {
        ArrayList<TileDTO> tiles = new ArrayList<>();
        ArrayList<Robot> robots = new ArrayList<>();
        Gameboard newGameboard = gameboardDTO.toGameboard(tiles, robots);
        Assertions.assertNotNull(newGameboard);
        Assertions.assertEquals(gameboard.getGameConfig(), newGameboard.getGameConfig());
    }

}

