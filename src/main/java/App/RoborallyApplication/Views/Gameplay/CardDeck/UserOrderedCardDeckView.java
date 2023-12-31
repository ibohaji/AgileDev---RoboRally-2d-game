package App.RoborallyApplication.Views.Gameplay.CardDeck;

import App.RoborallyApplication.Controllers.CardDeckController;
import App.RoborallyApplication.Model.AbCardProgramming;
import App.RoborallyApplication.Model.LCardSequence;
import App.RoborallyApplication.Model.LGameBrain;
import App.RoborallyApplication.Views.Gameplay.GameView;
import Utils.Fonts;
import Utils.GridBagConstraintsBuilder;
import Utils.ImageUtils;
import javax.swing.*;
import java.awt.*;

public class UserOrderedCardDeckView extends GameView {

    private final LCardSequence cardSequence;

    private final CardDeckController cardDeckController;

    private JPanel cardSlotsPanel;

    public UserOrderedCardDeckView(CardDeckController cardDeckController, LGameBrain gameBrain) {
        super(cardDeckController.getGameController(), gameBrain);
        this.cardDeckController = cardDeckController;
        this.cardSequence = new LCardSequence(gameBrain.getPlayerWithoutCardSequence());
        this.gameBrain = gameBrain;
        createView();
    }


    private void createView() {
        setLayout(new GridBagLayout());

        JLabel nameForDeck = new JLabel("Ordered Deck", SwingConstants.CENTER);
        nameForDeck.setFont(Fonts.LARGE);
        add(nameForDeck, new GridBagConstraintsBuilder(0,0).weightX(1).inset(0).fill(GridBagConstraints.HORIZONTAL).build());

        // Create a JPanel to hold the card slots
        this.cardSlotsPanel = new JPanel();
        cardSlotsPanel.setLayout(new GridBagLayout());
        cardSlotsPanel.setOpaque(false);
        add(cardSlotsPanel, new GridBagConstraintsBuilder(0, 1).weightY(1)
                .inset(10, 10, 10, 10).fill(GridBagConstraints.BOTH).build());
        add(getRemoveButton(), new GridBagConstraintsBuilder(0, 2)
                .inset(30, 10, 0, 10).fill(GridBagConstraints.HORIZONTAL).build());
    }

    public void addCard(AbCardProgramming card) {
        cardSequence.addCard(card);
        cardDeckController.updateCardDecks();
        ImageIcon img = ImageUtils.scaledImageWithPercent(card.getCardImageIcon(), 35);
        cardSlotsPanel.add(new JLabel(img), new GridBagConstraintsBuilder(0, cardSequence.getSize() - 1)
                .anchor(GridBagConstraints.CENTER)
                .inset(0,30,5,30).build());
    }


    public LCardSequence getCardSequence(){
        return this.cardSequence;
    }

    public void removeLastCard() {
        if(cardSequence.getSize() != 0){
            AbCardProgramming cardRemoved = this.cardSequence.getLastCardInSequence();
            this.cardSlotsPanel.remove(this.cardSlotsPanel.getComponentCount() - 1);
            this.cardDeckController.addCardToUnordered(cardRemoved);
            this.cardSequence.removeCard();
            revalidate();
            repaint();
        }
    }

    public JButton getRemoveButton(){
        JButton buttonToRemove = new JButton("REMOVE LAST CARD");
        buttonToRemove.setFont(Fonts.BOLDMEDIUM);
        buttonToRemove.addActionListener( e -> {
            removeLastCard();
        });
        return buttonToRemove;
    }

}
