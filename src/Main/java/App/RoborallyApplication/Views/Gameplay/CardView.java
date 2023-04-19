package App.RoborallyApplication.Views.Gameplay;

import App.RoborallyApplication.Model.AbCardProgramming;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class CardView extends JPanel {
    private final int CARD_WIDTH = 80;
    private final int CARD_HEIGHT = 120;
    private final AbCardProgramming card;

    public CardView(AbCardProgramming card) {
        this.card = card;
        setPreferredSize(new Dimension(CARD_WIDTH, CARD_HEIGHT));
        setOpaque(false);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        BufferedImage image = null;
        String imagePath = card.getCardImageFileName();
        try {
            image = ImageIO.read(new File(imagePath));
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (image != null) {
            g.drawImage(image, 0, 0, CARD_WIDTH, CARD_HEIGHT, null);
        }
    }
}

