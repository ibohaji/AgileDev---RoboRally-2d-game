package Utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class IOHelper {

    public static void writeContentToFile(String content, String filePath){
        try {
            FileWriter myWriter = new FileWriter(filePath);
            myWriter.write(content);
            myWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<String> readLinesFromFile(String filePath) throws IOException {
        ArrayList<String> lines = new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filePath));
            String line = reader.readLine();
            while (line != null) {
                line = reader.readLine();
                lines.add(line);
            }
            reader.close();
        } catch (IOException e) {
            throw new IOException("Problem reading from file");
        }
        return lines;
    }
}
