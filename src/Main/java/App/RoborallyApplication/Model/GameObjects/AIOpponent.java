package App.RoborallyApplication.Model.GameObjects;


import App.RoborallyApplication.Model.GameRunning.DifficultyEnum;
import javafx.util.Pair;

import java.awt.*;
import java.util.ArrayList;


public class AIOpponent extends Player {
    private ArrayList<Tile> tiles;
    private Point dimensions;


    AIOpponent(ArrayList<Tile> tiles, DifficultyEnum difficulty){
        this.tiles = tiles;
        dimensions.x =difficulty.getDimensions().first();
        dimensions.y = difficulty.getDimensions().second();
    }
}
