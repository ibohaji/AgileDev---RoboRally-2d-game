package App.RoborallyApplication.Model;

import java.awt.*;
import java.util.ArrayList;


public class AIOpponent extends LPlayer {
    private ArrayList<LTile> tiles;
    private Point dimensions;


    AIOpponent(ArrayList<LTile> tiles, EnumDifficulty difficulty){
        this.tiles = tiles;
        dimensions.x =difficulty.getDimensions().first();
        dimensions.y = difficulty.getDimensions().second();
    }
}
