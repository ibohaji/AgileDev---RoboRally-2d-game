package Utils;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Objects;

public class ImageUtils {

    public static Image scaledImage(String filepath, double scaleFactor) {
        if (filepath.charAt(0) != '/') filepath = "/" + filepath;

        BufferedImage img = null;
        try {
            img = ImageIO.read(Objects.requireNonNull(ImageUtils.class.getResourceAsStream(filepath)));
        } catch (IOException e) {
            e.printStackTrace();
        }

        assert img != null;

        int x = (int) (img.getWidth() * scaleFactor);
        int y = (int) (img.getHeight() * scaleFactor);
        return img.getScaledInstance(x, y, Image.SCALE_SMOOTH);
    }

    public static Image getImage(String filepath) {
        //if (filepath.charAt(0) != '/') filepath = "/" + filepath;
        BufferedImage img = null;
        var imageLocation = System.getProperty("user.dir") + File.separator + "src" + File.separator + "Main" + File.separator + "java";
        try {
            img = ImageIO.read(new File(imageLocation + File.separator + filepath));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return img;
    }

}
