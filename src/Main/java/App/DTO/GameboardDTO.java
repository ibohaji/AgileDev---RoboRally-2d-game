package App.DTO;

import App.RoborallyApplication.Model.LGameBrain;
import App.RoborallyApplication.Model.LGameboard;
import App.RoborallyApplication.Model.LRobot;
import App.RoborallyApplication.Model.LTile;

import java.util.ArrayList;
import java.util.UUID;

public class GameboardDTO implements iFromDTO{

    private UUID id;
    private UUID gamebrainID;
    private ArrayList<TileDTO> tiles = new ArrayList<>();
    private ArrayList<RobotDTO> robots = new ArrayList<>();
    private LGameBrain gamebrain;

    public GameboardDTO(LGameboard gameboard){
        for (LTile tile : gameboard.getTiles()){
            this.tiles.add(new TileDTO(tile));
        }
        for (LRobot robot: gameboard.getRobots()) {
            this.robots.add(new RobotDTO(robot));
        }
        this.id = gameboard.getID();
        this.gamebrainID = gameboard.getGameBrainId();
        this.gamebrain = gameboard.getGameBrain();
        this.id = UUID.randomUUID();
    }


    @Override
    public LGameboard returnObjectFromDTO() {
        //TODO
        ArrayList<LRobot> robotsForBoard = new ArrayList<>();
        ArrayList<LTile> tilesForBoard = new ArrayList<>();
        LGameboard gameboard = new LGameboard(gamebrain);
        for (RobotDTO robotDTO: this.robots) {
            robotsForBoard.add(robotDTO.returnObjectFromDTO());
        }
        for (TileDTO tileDTO: this.tiles) {
            tilesForBoard.add(tileDTO.returnObjectFromDTO());
        }
        gameboard.setRobots(robotsForBoard);
        gameboard.setTiles(tilesForBoard);
        return gameboard;
    }
}
