package App.RoborallyApplication.Views.Gameplay.CardDeck;

import App.RoborallyApplication.Controllers.GameController;
import App.RoborallyApplication.Model.Cards.ProgrammingCards.ProgrammingCard;
import App.RoborallyApplication.Model.GameRunning.GameBrain;
import App.RoborallyApplication.Views.Gameplay.CardView;
import App.RoborallyApplication.Views.Gameplay.GameView;
import Utils.GridBagConstraintsBuilder;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class UserCardDeckView extends GameView {
    private ArrayList<ProgrammingCard> cards;
    private JPanel cardPanel;

    public UserCardDeckView(GameController controller, GameBrain gameBrain, ArrayList<ProgrammingCard> cards) {
        super(controller, gameBrain);
        this.cards = cards;
        setLayout(new GridBagLayout());
        cardPanel = new JPanel(new GridLayout(1, 5));
        for (ProgrammingCard card : cards) {
            CardPanel cardPanel = new CardPanel(card);
            cardPanel.addMouseListener(new CardMouseListener());
            cardPanel.setTransferHandler(new CardTransferHandler(card));
            this.cardPanel.add(cardPanel);
        }
        setBorder(new LineBorder(Color.BLACK));
        JLabel nameForDeck = new JLabel("Player Deck");
        add(nameForDeck, new GridBagConstraintsBuilder(0,0).weightX(1).inset(0,50,0,50).fill(GridBagConstraints.HORIZONTAL).build());
        add(cardPanel);
    }

    private class CardPanel extends JPanel {
        private ProgrammingCard card;

        public CardPanel(ProgrammingCard card) {
            this.card = card;
            // add card view to the panel
        }

        // implement other methods to handle rendering, sizing, etc.
    }

    private class CardMouseListener extends MouseAdapter {
        public void mousePressed(MouseEvent e) {
            JComponent c = (JComponent) e.getSource();
            TransferHandler handler = c.getTransferHandler();
            handler.exportAsDrag(c, e, TransferHandler.COPY);
        }
    }

    private class CardTransferHandler extends TransferHandler {
        private ProgrammingCard card;

        public CardTransferHandler(ProgrammingCard card) {
            this.card = card;
        }

        protected Transferable createTransferable(JComponent c) {
            return new CardTransferable(card);
        }
    }

    private class CardTransferable implements Transferable {
        private ProgrammingCard card;
        private DataFlavor flavor;

        public CardTransferable(ProgrammingCard card) {
            this.card = card;
            flavor = new DataFlavor(ProgrammingCard.class, "ProgrammingCard");
        }

        public Object getTransferData(DataFlavor flavor) {
            if (flavor.equals(this.flavor)) {
                return card;
            } else {
                return null;
            }
        }

        public DataFlavor[] getTransferDataFlavors() {
            return new DataFlavor[]{flavor};
        }

        public boolean isDataFlavorSupported(DataFlavor flavor) {
            return flavor.equals(this.flavor);
        }
    }
}


