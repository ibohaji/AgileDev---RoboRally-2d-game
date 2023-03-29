package Utils;
import static org.junit.Assert.*;

import App.RoborallyApplication.Model.GameRunning.DifficultyEnum;
import App.RoborallyApplication.Model.GameRunning.GameBrain;
import org.junit.Test;

import App.RoborallyApplication.Model.GameRunning.Gameboard;
import Utils.MapGenerator;
import App.RoborallyApplication.Model.Enums.TileTypeEnum;

public class MapGeneratorJunitTest {

    @Test
    public void testGenerateEasyMap() {
        Gameboard gameboard = MapGenerator.generateEasyMap(new GameBrain(2, DifficultyEnum.EASY));
        // this should only generate tiles
        // Test the size of the gameboard
        //int rows = gameboard.getGameboardDimensions().first();
        //int columns = gameboard.getGameboardDimensions().second();
        //assertEquals(8, rows);
        //assertEquals(8, columns);

        // Test the tiles on the gameboard
        assertEquals(TileTypeEnum.DEFAULT_FLOOR, gameboard.getTiles().get(0).getTileTypeEnum());
        assertEquals(TileTypeEnum.OBSTACLE, gameboard.getTiles().get(14).getTileTypeEnum());

    }

}
