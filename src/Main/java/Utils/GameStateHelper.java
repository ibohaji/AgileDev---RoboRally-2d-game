package Utils;

import java.io.FileWriter;
import java.io.IOException;

public class GameStateHelper {

    public static void writeContentToFile(String content, String filePath){
        try {
            FileWriter myWriter = new FileWriter(filePath);
            myWriter.write(content);
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred in file writing.");
            e.printStackTrace();
        }
    }
}
