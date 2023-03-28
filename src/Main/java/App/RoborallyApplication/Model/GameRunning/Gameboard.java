package App.RoborallyApplication.Model.GameRunning;

import App.DTO.GameboardDTO;
import App.RoborallyApplication.Model.Enums.GraphicalElementEnum;
import App.RoborallyApplication.Model.Enums.TileTypeEnum;
import App.RoborallyApplication.Model.GameObjects.Obstacle;
import App.RoborallyApplication.Model.GameObjects.Robot;
import App.RoborallyApplication.Model.GameObjects.Tile;
import App.RoborallyApplication.Model.iToDTO;
import Utils.JsonHelper;
import Utils.Tuple;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.UUID;

public class Gameboard implements iToDTO {
    private UUID id;
    private UUID gameBrainId;
    private ArrayList<Tile> tilesOnBoard = new ArrayList<>();
    private ArrayList<Robot> robots = new ArrayList<>();
    private ArrayList<Obstacle> obstacles = new ArrayList<>();
    private Tuple<Integer, Integer> dimensions;
    //private GameConfiguration gameConfig;
    private GameBrain gameBrain;
    int mapTile[][];

    public Gameboard(){

    }
    public Gameboard(GameBrain brain){
        gameBrain = brain;
        id = UUID.randomUUID();
        gameBrainId = gameBrain.getID();
        dimensions = brain.getGameConfig().getBoardDimensions();
        initializeGameboard();
    }

    /**
     * Initialize default gameboard spaces
     */

    private void initializeGameboard() {
        for (int x = 0; x < dimensions.first(); x++) {
            for (int y = 0; y < dimensions.second(); y++) {
                Tile nextTile = new Tile(x, y, TileTypeEnum.DEFAULT_FLOOR);
                nextTile.graphicalElement.changeGraphicalElement(GraphicalElementEnum.DEFAULT_FLOOR,
                        this.gameBrain.getGameConfig().difficulty);
                tilesOnBoard.add(nextTile);
            }
        }
    }



    /*private void initializeMap(){
        int col = 0;
        int row = 0;
        Tuple<Integer,Integer> dim = DifficultyEnum.EASY.getDimensions();
        int width = dim.first();
        int height = dim.second();

        while (col < width && row < height){
            int tileNum = mapTile[col][row];
            Tile nextTile = new Tile(col,row);
            tilesOnBoard.add(nextTile);
        }*/

        /*
         while (col < gp.getMaxScreenCol() && row < gp.getMaxScreenRow()) {
            int tileNum = mapTileNum[col][row];
            g2.drawImage(tile[tileNum].image, x, y, gp.getTileSize(), gp.getTileSize(), null);
            col++;
            x += gp.getTileSize();


            if (col == gp.getMaxScreenCol()) {
                col = 0;
                x = 0;
                row++;
                y += gp.getTileSize();
            }
        }
         */
/*
    }*/

    public void loadMap() {
        try {
            InputStream is = getClass().getResourceAsStream("/map/Map.txt");
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            int col = 0;
            int row = 0;
            Tuple<Integer,Integer> dim = DifficultyEnum.EASY.getDimensions();
            int width = dim.first();
            int height = dim.second();

            while (col < width && row < height) {
                String line = br.readLine();

                while (col < width) {
                    String numbers[] = line.split(" ");
                    int num = Integer.parseInt(numbers[col]);

                    mapTile[col][row] = num;
                    col++;
                }
                if (col == width) {
                    col = 0;
                    row++;
                }

            }
            br.close();
        } catch (Exception e) {
            e.printStackTrace();

        }
    }

    /*public void initMap(){
        int col = 0;
        int row = 0;
        int x = 0;
        int y = 0;
        Tuple<Integer,Integer> dim = DifficultyEnum.EASY.getDimensions();
        int screenCol = dim.first();
        int screenRow = dim.second();

        while (col < screenCol && row < screenCol) {
            int tileNum = mapTile[col][row];




            g2.drawImage(tile[tileNum].image, x, y, gp.getTileSize(), gp.getTileSize(), null);
            col++;
            x += gp.getTileSize();


            if (col == gp.getMaxScreenCol()) {
                col = 0;
                x = 0;
                row++;
                y += gp.getTileSize();
            }
        }
    }*/


    public Tuple<Integer, Integer> getGameboardDimensions(){
        return this.dimensions;
    }

    public GameBrain getGameBrain(){
        return this.gameBrain;
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

    public ArrayList<Tile> getTilesOnBoard(){
        return this.tilesOnBoard;
    }

    public ArrayList<Robot> getRobots(){
        return this.robots;
    }

    @Override
    public String DTOasJson() {
        GameboardDTO gameboardDTO = new GameboardDTO(this);
        return JsonHelper.serializeObjectToJson(gameboardDTO);
    }

    @Override
    public UUID getID() {
        return this.id;
    }
}
