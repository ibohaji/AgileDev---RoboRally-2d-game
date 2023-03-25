package Utils;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Objects;

public class ImageUtils {



    public static ImageIcon scaledImageIcon(ImageIcon imageIcon, int width, int height){
        Image image = imageIcon.getImage(); // transform it
        Image newimg = image.getScaledInstance(width, height,  java.awt.Image.SCALE_SMOOTH);
        return new ImageIcon(newimg);
    }

    public static Image getImage(String filepath) {
        //if (filepath.charAt(0) != '/') filepath = "/" + filepath;
        BufferedImage img = null;

        try {
            img = ImageIO.read(new File(filepath));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return img;
    }

    /**
     * Code taken from: https://stackoverflow.com/questions/20959796/rotate-90-degree-to-right-image-in-java
     * @param src image to rotate
     * @param rotationAngle angle to rotate by
     * @return rotated image
     */
    public static BufferedImage rotateImage(BufferedImage src, int rotationAngle) {
        double theta = (Math.PI * 2) / 360 * rotationAngle;
        int width = src.getWidth();
        int height = src.getHeight();
        BufferedImage dest;
        if (rotationAngle == 90 || rotationAngle == 270) {
            dest = new BufferedImage(src.getHeight(), src.getWidth(), src.getType());
        } else {
            dest = new BufferedImage(src.getWidth(), src.getHeight(), src.getType());
        }

        Graphics2D graphics2D = dest.createGraphics();

        if (rotationAngle == 90) {
            graphics2D.translate((height - width) / 2, (height - width) / 2);
            graphics2D.rotate(theta, height / 2, width / 2);
        } else if (rotationAngle == 270) {
            graphics2D.translate((width - height) / 2, (width - height) / 2);
            graphics2D.rotate(theta, height / 2, width / 2);
        } else {
            graphics2D.translate(0, 0);
            graphics2D.rotate(theta, width / 2, height / 2);
        }
        graphics2D.drawRenderedImage(src, null);
        return dest;
    }

    public static Tuple<Integer, Integer> getImageDimensions(ImageIcon image){
        return new Tuple<>(image.getIconWidth(), image.getIconHeight());
    }

}
