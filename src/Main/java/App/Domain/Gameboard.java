package App.Domain;

import App.DTO.GameboardDTO;
import App.Domain.Enums.GraphicalElementEnum;
import Utils.JsonHelper;
import Utils.Tuple;

import java.util.ArrayList;

public class Gameboard implements serializable {
    // List of obstacles used -> List (Obstacle + Coordinate)
    // List of robots -> List (Robot)
    private ArrayList<Tile> tilesOnBoard = new ArrayList<>();
    private ArrayList<Robot> robots = new ArrayList<>();
    private Tuple<Integer, Integer> dimensions;
    private GameConfiguration gameConfig;

    public Gameboard(){

    }
    public Gameboard(GameConfiguration config){
        gameConfig = config;
        dimensions = gameConfig.getBoardDimensions();
        initializeGameboard();
    }

    /**
     * Initialize default gameboard spaces
     */

    private void initializeGameboard(){
        for (int x = 0; x < dimensions.first(); x++) {
            for (int y = 0; y < dimensions.second(); y++) {
                Tile nextTile = new Tile(x, y);
                System.out.println(nextTile.graphicalElement == null);
                nextTile.graphicalElement.changeGraphicalElement(GraphicalElementEnum.DEFAULT_FLOOR, this.gameConfig.difficulty);
                tilesOnBoard.add(nextTile);
            }
        }
    }
    public Tuple<Integer, Integer> getGameboardDimensions(){
        return this.dimensions;
    }

    public GameConfiguration getGameConfig(){
        return this.gameConfig;
    }

    public ArrayList<Tile> getTiles(){
        return this.tilesOnBoard;
    }

    private void removeRobotFromBoard(Robot robotToRemove){
        this.robots.remove(robotToRemove);
    }

    public void addRobots(ArrayList<Robot> robots){
        this.robots = robots;
    }
    private ArrayList<Tile> getTilesOnBoard(){
        return this.tilesOnBoard;
    }

    public ArrayList<Robot> getRobots(){
        return this.robots;
    }

    @Override
    public String toJson() {
        GameboardDTO gameboardDTO = new GameboardDTO(this);
        return JsonHelper.serializeObjectToJson(gameboardDTO);
    }
}
