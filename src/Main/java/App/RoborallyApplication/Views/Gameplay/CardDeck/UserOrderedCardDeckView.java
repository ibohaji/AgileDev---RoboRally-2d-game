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

public class UserOrderedCardDeckView extends GameView {

    private LCardSequence cardSequence;

    private CardDeckController cardDeckController;
    private JPanel cardPanel;

    public UserOrderedCardDeckView(CardDeckController cardDeckController, LGameBrain gameBrain) {
        super(cardDeckController.getGameController(), gameBrain);
        this.cardDeckController = cardDeckController;
        this.cardSequence = new LCardSequence(gameBrain.getPlayerWithoutCardSequence());
        createView();
    }


    private void createView() {
        setLayout(new GridBagLayout());
        setBorder(new LineBorder(Color.BLACK, 5));

        JLabel nameForDeck = new JLabel("Ordered Deck");
        add(nameForDeck, new GridBagConstraintsBuilder(0,0).weightX(1).inset(50).fill(GridBagConstraints.HORIZONTAL).build());

        // Create a JPanel to hold the card slots
        JPanel cardSlotsPanel = new JPanel();
        cardSlotsPanel.setLayout(new BoxLayout(cardSlotsPanel, BoxLayout.Y_AXIS));
        cardSlotsPanel.setOpaque(false);
        add(cardSlotsPanel, new GridBagConstraintsBuilder(1, 0).weightY(1).fill(GridBagConstraints.VERTICAL).build());

        // Add card slots to the card slots panel
        for (int i = 0; i < 5; i++) {
            CardPanel cardSlotPanel = new CardPanel();
            cardSlotPanel.setPreferredSize(new Dimension(AbCardProgramming.CARD_WIDTH, AbCardProgramming.CARD_HEIGHT));
            cardSlotPanel.setTransferHandler(new CardTransferHandler(null));
            cardSlotPanel.addMouseListener(new CardMouseListener());
            cardSlotsPanel.add(cardSlotPanel);
        }
    }




    public void addCard(AbCardProgramming card) {
        cardSequence.addCard(card);
        CardPanel newCardPanel = new CardPanel(card);
        cardPanel.addMouseListener(new CardMouseListener());
        cardPanel.setTransferHandler(new CardTransferHandler(card));
        cardPanel.setDropTarget(new CardDropTarget(cardPanel));
        this.cardPanel.add(cardPanel, new GridBagConstraintsBuilder(0, this.cardSequence.getSize() - 1).weightX(1).fill(GridBagConstraints.HORIZONTAL).build());
    }

    public LCardSequence getCardSequence(){
        return this.cardSequence;
    }

    class CardPanel extends JPanel {
        private AbCardProgramming card;

        private ImageIcon image;

        public CardPanel() {
            // add an empty label to represent a card slot
            add(new JLabel());
            setBorder(new LineBorder(Color.BLACK, 1)); // add border to the card panel
        }

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

        public CardDropTarget(JPanel panel) {
            this.panel = panel;
        }

        public synchronized void drop(DropTargetDropEvent event) {
            try {
                Transferable transferable = event.getTransferable();
                if (transferable.isDataFlavorSupported(CardTransferable.PROGRAMMING_CARD)) {
                    AbCardProgramming card = (AbCardProgramming) transferable.getTransferData(CardTransferable.PROGRAMMING_CARD);
                    if (cardDeckController.getOrderedCardSequence().getSize() < 5) {
                        cardDeckController.addCardToOrdered(card);
                        panel.remove(panel.getComponentAt(panel.getMousePosition()));
                        cardDeckController.updateCardDecks(); // revalidate and repaint
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
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

    private class CardTransferHandler extends TransferHandler {
        private AbCardProgramming card;

        public CardTransferHandler(AbCardProgramming card) {
            this.card = card;
        }

        protected Transferable createTransferable(JComponent c) {
            return new CardTransferable(card);
        }

        public boolean canImport(TransferHandler.TransferSupport support) {
            // 检查是否允许导入数据
            if (!support.isDrop()) {
                return false;
            }

            // 检查数据类型是否是ProgrammingCard
            if (!support.isDataFlavorSupported(CardTransferable.PROGRAMMING_CARD)) {
                return false;
            }

            // 检查是否已经拖拽了5张卡片
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

            // 获取卡片数据
            Transferable t = support.getTransferable();
            AbCardProgramming card;
            try {
                card = (AbCardProgramming) t.getTransferData(CardTransferable.PROGRAMMING_CARD);
            } catch (Exception e) {
                return false;
            }

            // 获取鼠标释放时的位置
            Point dropPoint = support.getDropLocation().getDropPoint();

            // 获取插入位置
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

            // 插入卡片
            CardPanel cardPanel = new CardPanel(card);
            cardPanel.setTransferHandler(new CardTransferHandler(card));
            cardPanel.addMouseListener(new CardMouseListener());
            cardDeckController.addCard(card);
            cardPanel.add(cardPanel, new GridBagConstraintsBuilder(0, insertIndex).weightX(1).fill(GridBagConstraints.HORIZONTAL).build());
            cardPanel.revalidate();
            cardPanel.repaint();

            return true;


        }
    }
}
