package App.Domain;

import App.DTO.GameboardDTO;
import App.Domain.Enums.DifficultyEnum;
import App.Domain.Enums.DirectionEnum;
import App.Domain.Enums.GraphicalElementEnum;
import App.Domain.Enums.TileTypeEnum;
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

    private void initializeGameboard() {
        for (int x = 0; x < dimensions.first(); x++) {
            for (int y = 0; y < dimensions.second(); y++) {
                Tile nextTile = new Tile(x, y, TileTypeEnum.DEFAULT_FLOOR);
                nextTile.graphicalElement.changeGraphicalElement(GraphicalElementEnum.DEFAULT_FLOOR,
                        this.gameConfig.difficulty);
                tilesOnBoard.add(nextTile);
            }
        }
    }



    private void initializeMap(){
        int col = 0;
        int row = 0;
        Tuple<Integer,Integer> dim = DifficultyEnum.EASY.getDimensions();
        int width = dim.first();
        int height = dim.second();

        while (col < width && row < height){
            int tileNum = mapTile[col][row];
            Tile nextTile = new Tile(col,row);
            tilesOnBoard.add(nextTile);
        }

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

    }

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
        for (int i = 0; i < robots.size(); i++) {
            robots.get(i).setCords(new Point(i, 1));
            Random rand = new Random();
            int r = rand.nextInt(4);
            switch (r){
                case 0 -> robots.get(i).SetDirection(DirectionEnum.NORTH);
                case 1 -> robots.get(i).SetDirection(DirectionEnum.WEST);
                case 2 -> robots.get(i).SetDirection(DirectionEnum.SOUTH);
                case 3 -> robots.get(i).SetDirection(DirectionEnum.EAST);
            }

        }
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
