package App.RoborallyApplication.Views.Gameplay.CardDeck;

import App.RoborallyApplication.Controllers.CardDeckController;
import App.RoborallyApplication.Controllers.GameController;
import App.RoborallyApplication.Model.AbCardProgramming;
import App.RoborallyApplication.Model.LCardSequence;
import App.RoborallyApplication.Model.LGameBrain;
import App.RoborallyApplication.Views.Gameplay.GameView;
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

public class UserCardDeckView extends GameView {
    private ArrayList<AbCardProgramming> cards;
    private JPanel cardPanel;
    private UserOrderedCardDeckView userOrderedDeckView;

    private CardDeckController cardDeckController;
    private LCardSequence cardSequence;

    public UserCardDeckView(CardDeckController cardDeckController, LGameBrain gameBrain, UserOrderedCardDeckView userOrderedDeckView) {
        super(cardDeckController.getGameController(), gameBrain);
        this.cardSequence = new LCardSequence(gameBrain.getPlayerWithoutCardSequence());
        this.cardDeckController = cardDeckController;
        this.cards = gameBrain.getPlayerWithoutCardSequence().getProgrammingCards();
        this.userOrderedDeckView = userOrderedDeckView;
        setLayout(new GridBagLayout());
        cardPanel = new JPanel();
        cardPanel.setLayout(new GridBagLayout());
        int counter = 0;
        for (AbCardProgramming card : cards) {
            CardPanel cardPanel = new CardPanel(card);
            cardPanel.addMouseListener(new CardMouseListener());
            cardPanel.setTransferHandler(new CardTransferHandler(card));
            cardPanel.setDropTarget(new CardDropTarget(cardPanel, userOrderedDeckView));
            this.cardPanel.add(cardPanel, new GridBagConstraintsBuilder(0, counter).weightX(1).fill(GridBagConstraints.HORIZONTAL).build());
            counter += 1;
        }
        setBorder(new LineBorder(Color.BLACK));
        JLabel nameForDeck = new JLabel("Player Deck");
        add(nameForDeck, new GridBagConstraintsBuilder(0, 0).weightX(1).inset(0, 50, 0, 50).fill(GridBagConstraints.HORIZONTAL).build());
        add(cardPanel);
    }

    public void removeCard(AbCardProgramming card) {
        cardSequence.removeCard(card);
        for (Component component : cardPanel.getComponents()) {
            CardPanel cardPanel = (CardPanel) component;
            if (cardPanel.card != null && cardPanel.card.equals(card)) {
                this.cardPanel.remove(cardPanel);
                break;
            }
        }
        cardDeckController.updateCardDecks();
    }


    private class CardPanel extends JPanel {
        private AbCardProgramming card;

        private ImageIcon image;

        public CardPanel(AbCardProgramming card) {
            this.card = card;
            // add card view to the panel
            this.image = card.getCardImageIcon();
            add(new JLabel(ImageUtils.scaledImageWithPercent(this.image, 40)));
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

    private class CardDropTarget extends DropTarget {
        private JPanel panel;
        private UserOrderedCardDeckView userOrderedDeckView;

        public CardDropTarget(JPanel panel, UserOrderedCardDeckView userOrderedDeckView) {
            this.panel = panel;
            this.userOrderedDeckView = userOrderedDeckView;
        }

        public synchronized void drop(DropTargetDropEvent event) {
            try {
                Transferable transferable = event.getTransferable();
                if (transferable.isDataFlavorSupported(CardTransferable.PROGRAMMING_CARD)) {
                    AbCardProgramming card = (AbCardProgramming) transferable.getTransferData(CardTransferable.PROGRAMMING_CARD);
                    if (userOrderedDeckView.getCardSequence().getSize() <= 5) {
                        System.out.println("Size of ordered deck: " + userOrderedDeckView.getCardSequence().getSize());
                        System.out.println("Mouse position: " + panel.getMousePosition());
                        cardDeckController.addCardToOrdered(card);
                        cardDeckController.removeCardFromPlayerDeck(card);
                        panel.remove(panel.getComponentAt(panel.getMousePosition()));
                        cardDeckController.updateCardDecks(); // revalidate and repaint
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
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

        public boolean canImport(TransferHandler.TransferSupport support) {
            // check if importing data is allowed
            if (!support.isDrop()) {
                return false;
            }

            // check if the data type is ProgrammingCard
            if (!support.isDataFlavorSupported(CardTransferable.PROGRAMMING_CARD)) {
                return false;
            }

            // check if it has dragged 5 cards
            JComponent component = (JComponent) support.getComponent();
            int currentCardCount = component.getComponentCount();
            if (currentCardCount >= 5) {
                return false;
            }

            return true;
        }

        public int getSourceActions(JComponent c) {
            return TransferHandler.COPY;
        }

        public boolean importData(TransferHandler.TransferSupport support) {
            if (!canImport(support)) {
                return false;
            }

            // get card data
            Transferable t = support.getTransferable();
            AbCardProgramming card;
            try {
                card = (AbCardProgramming) t.getTransferData(CardTransferable.PROGRAMMING_CARD);
            } catch (Exception e) {
                return false;
            }

            // get the drop point
            Point dropPoint = support.getDropLocation().getDropPoint();

            // get the insert point
            int insertIndex = 0;
            Component[] components = cardPanel.getComponents();
            for (int i = 0; i < components.length; i++) {
                Component component = components[i];
                Rectangle bounds = component.getBounds();
                if (dropPoint.y >= bounds.y && dropPoint.y <= bounds.y + bounds.height) {
                    insertIndex = i;
                    break;
                }
            }

            // insert the card
            CardPanel newCardPanel = new CardPanel(card);
            newCardPanel.setTransferHandler(new CardTransferHandler(card));
            newCardPanel.addMouseListener(new CardMouseListener());
            userOrderedDeckView.addCard(card);
            cardPanel.add(newCardPanel, new GridBagConstraintsBuilder(0, insertIndex).weightX(1).fill(GridBagConstraints.HORIZONTAL).build());
            cardPanel.revalidate();
            cardPanel.repaint();

            return true;
        }
    }

    private class CardTransferable implements Transferable {
        private AbCardProgramming card;
        private DataFlavor flavor;
        public static final DataFlavor PROGRAMMING_CARD = new DataFlavor(AbCardProgramming.class, "ProgrammingCard");


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

    public ArrayList<AbCardProgramming> getCards(){
        return this.cards;
    }
}

