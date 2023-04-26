package App.RoborallyApplication.Views.Gameplay.CardDeck;

import App.RoborallyApplication.Controllers.CardDeckController;
import App.RoborallyApplication.Model.AbCardProgramming;

import javax.swing.*;
import java.awt.*;
import Utils.ImageUtils;


public class CardButton extends JPanel {

    private AbCardProgramming card;
    private JButton buttonWithImage;
    private CardDeckController cardDeckController;
    public CardButton(AbCardProgramming card, CardDeckController cardDeckController){
        this.cardDeckController = cardDeckController;
        this.card = card;
        createButton();
    }

    private void createButton() {
        ImageIcon originalImageIcon = card.getCardImageIcon();
        ImageIcon scaledImageIcon = ImageUtils.scaledImageWithPercent(originalImageIcon, 30); // Scale the image to 50% of its original size
        this.buttonWithImage = new JButton(scaledImageIcon);
        this.buttonWithImage.addActionListener(e -> {
            cardDeckController.addCardToOrdered(this.card);
        });
    }


    protected JButton getButton(){
        return this.buttonWithImage;
    }

    public AbCardProgramming getCard() {
        return this.card;
    }
}
