package Utils;

import App.Model.GameRunning.DifficultyEnum;
import App.Model.GameObjects.Tile;

import java.util.ArrayList;

public class MapLoadingHelper {


    /*public static int[][] loadMap2(DifficultyEnum diff) {
        int mapTile[][];
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
    }*/

    public static ArrayList<Tile> loadMap(DifficultyEnum difficulty){
        ArrayList<Tile> tileList = new ArrayList<>();

        return tileList;
    }


}
