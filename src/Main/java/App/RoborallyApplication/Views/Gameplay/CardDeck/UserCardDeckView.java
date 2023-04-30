package App.RoborallyApplication.Views.Gameplay.CardDeck;

import App.RoborallyApplication.Controllers.CardDeckController;
import App.RoborallyApplication.Model.AbCardProgramming;
import App.RoborallyApplication.Model.EnumGamePhase;
import App.RoborallyApplication.Model.LGameBrain;
import App.RoborallyApplication.Views.Gameplay.GameView;
import Utils.Fonts;
import Utils.GridBagConstraintsBuilder;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class UserCardDeckView extends GameView {
    private final ArrayList<AbCardProgramming> cards;
    private JPanel cardPanel;
    private ArrayList<CardButton> cardButtons;
    private UserOrderedCardDeckView userOrderedDeckView;
    private final CardDeckController cardDeckController;

    public UserCardDeckView(CardDeckController cardDeckController, LGameBrain gameBrain, UserOrderedCardDeckView userOrderedDeckView) {
        super(cardDeckController.getGameController(), gameBrain);
        this.cardDeckController = cardDeckController;
        if(gameBrain.getCurrentGamePhase().equals(EnumGamePhase.MOVEMENT_PHASE)){
            this.cards = gameBrain.getPlayerWhoIsCurrentlyMoving().getCardSequence().getCardSequence();
        } else {
            this.cards = cardDeckController.getGameController().getPlayerWithoutCardSequence().getProgrammingCards();
        }
        this.userOrderedDeckView = userOrderedDeckView;
        this.cardButtons = new ArrayList<>();
        setLayout(new GridBagLayout());
        createCardButtonsPanel();
        JLabel nameForDeck = new JLabel("Player Deck", SwingConstants.CENTER);
        nameForDeck.setFont(Fonts.LARGE);
        add(nameForDeck, new GridBagConstraintsBuilder(0, 0).weightX(1).inset(0, 50, 0, 50).fill(GridBagConstraints.HORIZONTAL).build());
        add(cardPanel, new GridBagConstraintsBuilder(0, 1).weightX(1).weightY(1).inset(20, 50, 0, 50).fill(GridBagConstraints.HORIZONTAL).build());
    }

    public void removeCard(AbCardProgramming card) {
        cards.remove(card);
        CardButton buttonToRemove = null;
        for (CardButton button: cardButtons) {
            if(button.getCard().equals(card)){
                buttonToRemove = button;
            }
        }
        if(buttonToRemove != null){
            cardButtons.remove(buttonToRemove);
        }
        createCardButtonsPanel();
        revalidate();
        repaint();
    }

    public void addCard(AbCardProgramming card) {
        //TODO
        // CREATE cardButton again and add to panel
        this.cards.add(card);
        createCardButtonsPanel();
        revalidate();
        repaint();
    }
    public ArrayList<AbCardProgramming> getCards(){
        return this.cards;
    }

    private void createCardButtonsPanel(){
        this.cardButtons = new ArrayList<>();
        if(cardPanel != null){
            remove(cardPanel);
        }
        cardPanel = new JPanel();
        cardPanel.setLayout(new GridBagLayout());
        int counter = 0;
        for (AbCardProgramming card : cards) {
            CardButton cardButton =  new CardButton(card, cardDeckController);
            this.cardButtons.add(cardButton);
            this.cardPanel.add(cardButton.getButton(), new GridBagConstraintsBuilder(0, counter).weightX(1).inset(0, 0, 5, 0).fill(GridBagConstraints.BOTH).build());
            counter += 1;
        }
        add(cardPanel, new GridBagConstraintsBuilder(0, 1).weightY(1).inset(20, 50, 0, 50).fill(GridBagConstraints.HORIZONTAL).build());
    }
}

