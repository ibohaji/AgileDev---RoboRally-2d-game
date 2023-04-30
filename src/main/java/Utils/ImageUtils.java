package Utils;

import App.RoborallyApplication.RoboRally;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.font.TextAttribute;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.text.AttributedString;
import java.util.Objects;

public class ImageUtils {


    public static ImageIcon mergeTwoImages(ImageIcon background, ImageIcon foreground){
        Tuple<Integer, Integer> dimension = getImageDimensions(background);
        foreground = scaledImageIcon(foreground, dimension.first(), dimension.second());
        Image backgroundImage = background.getImage();
        Image foregroundImage = foreground.getImage();
        BufferedImage bufferedBack = toBufferedImage(backgroundImage);
        BufferedImage bufferedFront = toBufferedImage(foregroundImage);
        BufferedImage combinedImage = new BufferedImage(
                bufferedBack.getWidth(),
                bufferedBack.getHeight(),
                BufferedImage.TYPE_INT_ARGB );
        Graphics2D g = combinedImage.createGraphics();
        g.drawImage(bufferedBack,0,0,null);
        g.drawImage(bufferedFront,0,0,null);
        g.dispose();
        return new ImageIcon(combinedImage);
    }

    public static ImageIcon scaledImageIcon(ImageIcon imageIcon, int width, int height){
        Image image = imageIcon.getImage(); // transform it
        Image newimg = image.getScaledInstance(width, height,  java.awt.Image.SCALE_SMOOTH);
        return new ImageIcon(newimg);
    }

    public static ImageIcon scaledImageWithPercent(ImageIcon imageIcon, int percent){
        Image image = imageIcon.getImage(); // transform it
        Image newimg = image.getScaledInstance((imageIcon.getIconWidth() * percent/100), imageIcon.getIconHeight() * percent/100,  java.awt.Image.SCALE_SMOOTH);
        return new ImageIcon(newimg);
    }

    public static Image getImage(String filepath) {
        //if (filepath.charAt(0) != '/') filepath = "/" + filepath;
        BufferedImage img = null;
        try {
            img = ImageIO.read(RoboRally.class.getResourceAsStream(filepath));
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

    public static BufferedImage toBufferedImage(Image img)
    {
        if (img instanceof BufferedImage)
        {
            return (BufferedImage) img;
        }

        // Create a buffered image with transparency
        BufferedImage bimage = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_ARGB);

        // Draw the image on to the buffered image
        Graphics2D bGr = bimage.createGraphics();
        bGr.drawImage(img, 0, 0, null);
        bGr.dispose();

        // Return the buffered image
        return bimage;
    }

    public static ImageIcon addTextToImage(ImageIcon imageIcon, Font font, String text){
        Image img = imageIcon.getImage();
        BufferedImage image = toBufferedImage(img);
        Graphics g = image.getGraphics();

        Font boldFont = font.deriveFont(Font.BOLD);
        AttributedString attributedText = new AttributedString(text);
        attributedText.addAttribute(TextAttribute.FONT, boldFont);
        attributedText.addAttribute(TextAttribute.FOREGROUND, Color.CYAN);

        FontMetrics metrics = g.getFontMetrics(font);
        int positionX = (image.getWidth() - metrics.stringWidth(text)) / 2;
        int positionY = (image.getHeight() - metrics.getHeight()) / 2 + metrics.getAscent();
        g.drawString(attributedText.getIterator(), positionX, positionY);
        g.dispose();
        return new ImageIcon(image);
    }
}
