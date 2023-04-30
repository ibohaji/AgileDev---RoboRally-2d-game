package App.RoborallyApplication.Views.Gameplay.CardDeck;

import App.RoborallyApplication.Controllers.CardDeckController;
import App.RoborallyApplication.Model.AbCardProgramming;
import App.RoborallyApplication.Model.LGameBrain;
import App.RoborallyApplication.Model.LPlayer;
import App.RoborallyApplication.Views.Gameplay.GameView;
import Utils.Fonts;
import Utils.GridBagConstraintsBuilder;
import Utils.ImageUtils;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class MovementCardDeckView extends GameView {
    private final ArrayList<AbCardProgramming> cards;
    private final LGameBrain gameBrain;

    private JPanel cardSlotsPanel;

    public MovementCardDeckView(CardDeckController cardDeckController, LGameBrain gameBrain) {
        super(cardDeckController.getGameController(),gameBrain);
        this.cards = gameBrain.getPlayerWhoIsCurrentlyMoving().getCardSequence().getCardSequence();
        this.gameBrain = gameBrain;
        createView();
    }

    private void createView() {
        setLayout(new GridBagLayout());
        JLabel nameForDeck = new JLabel("Movement Card Deck", SwingConstants.CENTER);
        nameForDeck.setFont(Fonts.LARGE);
        add(nameForDeck, new GridBagConstraintsBuilder(0,0).weightX(1).inset(10).fill(GridBagConstraints.HORIZONTAL).build());

        // Create a JPanel to hold the card slots
        this.cardSlotsPanel = new JPanel();
        cardSlotsPanel.setLayout(new BoxLayout(cardSlotsPanel, BoxLayout.Y_AXIS));
        cardSlotsPanel.setOpaque(false);
        add(cardSlotsPanel, new GridBagConstraintsBuilder(0, 1).weightY(1)
                .inset(10, 10, 10, 10).fill(GridBagConstraints.BOTH).build());
        createCardDeckView(gameBrain.getPlayerWhoIsCurrentlyMoving());
    }

    public ArrayList<AbCardProgramming> getCards(){
        return this.cards;
    }

    public void addCard(AbCardProgramming card) {
        ImageIcon img = ImageUtils.scaledImageWithPercent(card.getCardImageIcon(), 40);
        cardSlotsPanel.add(new JLabel(img), new GridBagConstraintsBuilder(0, cards.size() - 1)
                .inset(20,50,0,50).build());
    }

    public void createCardDeckView(LPlayer player) {
        for (AbCardProgramming card : player.getCardSequence().getCardSequence()) {
            addCard(card);
        }
    }
}
