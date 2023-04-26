package App.RoborallyApplication.Views.Gameplay.CardDeck;

import App.RoborallyApplication.Controllers.CardDeckController;
import App.RoborallyApplication.Controllers.GameController;
import App.RoborallyApplication.Model.AbCardProgramming;
import App.RoborallyApplication.Model.LCardSequence;
import App.RoborallyApplication.Model.LGameBrain;
import App.RoborallyApplication.Views.Gameplay.GameView;
import Utils.Fonts;
import Utils.GridBagConstraintsBuilder;
import Utils.ImageUtils;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Arrays;

public class UserCardDeckView extends GameView {
    private ArrayList<AbCardProgramming> cards;
    private JPanel cardPanel;

    private ArrayList<CardButton> cardButtons;
    private UserOrderedCardDeckView userOrderedDeckView;
    private CardDeckController cardDeckController;

    public UserCardDeckView(CardDeckController cardDeckController, LGameBrain gameBrain, UserOrderedCardDeckView userOrderedDeckView) {
        super(cardDeckController.getGameController(), gameBrain);
        this.cardDeckController = cardDeckController;
        this.cards = cardDeckController.getGameController().getPlayerWithoutCardSequence().getProgrammingCards();
        this.userOrderedDeckView = userOrderedDeckView;
        this.cardButtons = new ArrayList<>();
        setLayout(new GridBagLayout());
        createCardButtonsPanel();
        JLabel nameForDeck = new JLabel("Player Deck", SwingConstants.CENTER);
        nameForDeck.setFont(Fonts.LARGE);
        add(nameForDeck, new GridBagConstraintsBuilder(0, 0).weightX(1).inset(0, 50, 0, 50).fill(GridBagConstraints.HORIZONTAL).build());
        add(cardPanel, new GridBagConstraintsBuilder(0, 1).weightX(1).inset(20, 50, 0, 50).fill(GridBagConstraints.HORIZONTAL).build());
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
            this.cardPanel.add(cardButton.getButton(), new GridBagConstraintsBuilder(0, counter).weightX(1).inset(0, 0, 10, 0).fill(GridBagConstraints.BOTH).build());
            counter += 1;
        }
        add(cardPanel, new GridBagConstraintsBuilder(0, 1).weightX(1).inset(20, 50, 0, 50).fill(GridBagConstraints.HORIZONTAL).build());
    }
}
