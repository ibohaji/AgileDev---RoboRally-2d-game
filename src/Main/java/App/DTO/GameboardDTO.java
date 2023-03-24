package App.DTO;

import App.Domain.GameConfiguration;
import App.Domain.Gameboard;
import App.Domain.Robot;
import App.Domain.Tile;
import Utils.Tuple;

import java.util.ArrayList;

public class GameboardDTO{
    public ArrayList<TileDTO> tiles = new ArrayList<>();
    public ArrayList<RobotDTO> robots = new ArrayList<>();
    public Tuple<Integer, Integer> dimensions;
    public GameConfiguration gameConfig;

    public GameboardDTO() {

    }

    public GameboardDTO(Gameboard gameboard){
        this.dimensions = gameboard.getGameboardDimensions();
        this.gameConfig = gameboard.getGameConfig();
        for (Tile tile : gameboard.getTiles()){
            this.tiles.add(new TileDTO(tile));
        }
        for (Robot robot: gameboard.getRobots()) {
            this.robots.add(new RobotDTO(robot));
        }

//        for (Robot robot: gameboard.getRobots()){
//            this.robots.add(new RobotDTO(robot))
//      }

    }

    public Gameboard toGameboard(ArrayList<TileDTO> tiles, ArrayList<Robot> robots) {
        Gameboard gameboard = new Gameboard(this.gameConfig);
        return gameboard;
    }

}
