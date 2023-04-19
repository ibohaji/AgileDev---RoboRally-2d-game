package App.RoborallyApplication.Views.Gameplay.CardDeck;

import App.RoborallyApplication.Controllers.GameController;
import App.RoborallyApplication.Model.AbCardProgramming;
import App.RoborallyApplication.Model.LGameBrain;
import App.RoborallyApplication.Views.Gameplay.GameView;
import Utils.GridBagConstraintsBuilder;
import Utils.ImageUtils;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class UserCardDeckView extends GameView {
    private ArrayList<AbCardProgramming> cards;
    private JPanel cardPanel;

    public UserCardDeckView(GameController controller, LGameBrain gameBrain, ArrayList<AbCardProgramming> cards) {
        super(controller, gameBrain);
        this.cards = cards;
        setLayout(new GridBagLayout());
        cardPanel = new JPanel(new GridLayout(1, 5));
        for (AbCardProgramming card : cards) {
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
        private AbCardProgramming card;

        private ImageIcon image;

        public CardPanel(AbCardProgramming card) {
            this.card = card;
            // add card view to the panel
            this.image = card.getCardImageIcon();
            add(new JLabel(this.image));
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
        private AbCardProgramming card;

        public CardTransferHandler(AbCardProgramming card) {
            this.card = card;
        }

        protected Transferable createTransferable(JComponent c) {
            return new CardTransferable(card);
        }
    }

    private class CardTransferable implements Transferable {
        private AbCardProgramming card;
        private DataFlavor flavor;

        public CardTransferable(AbCardProgramming card) {
            this.card = card;
            flavor = new DataFlavor(AbCardProgramming.class, "ProgrammingCard");
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


