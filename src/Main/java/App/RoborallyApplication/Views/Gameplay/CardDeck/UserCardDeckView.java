package App.RoborallyApplication.Views.Gameplay.CardDeck;

import App.RoborallyApplication.Controllers.GameController;
import App.RoborallyApplication.Model.AbCardProgramming;
import App.RoborallyApplication.Model.LGameBrain;
import App.RoborallyApplication.Views.Gameplay.GameView;
import App.RoborallyApplication.Views.Gameplay.ProgrammingPhaseView;
import Utils.GridBagConstraintsBuilder;
import Utils.ImageUtils;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetAdapter;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class UserCardDeckView extends GameView {
    private ArrayList<AbCardProgramming> cards;
    private JPanel cardPanel;
    private JPanel orderedPanel;

    public UserCardDeckView(GameController controller, LGameBrain gameBrain) {
        super(controller, gameBrain);
        this.cards = gameBrain.getPlayerWithoutCardSequence().getProgrammingCards();
        setLayout(new GridBagLayout());

        // 初始化两个卡片面板
        cardPanel = new JPanel();
        cardPanel.setLayout(new GridBagLayout());

        orderedPanel = new JPanel();
        orderedPanel.setLayout(new BoxLayout(orderedPanel, BoxLayout.Y_AXIS));

        int counter = 0;
        for (AbCardProgramming card : cards) {
            CardPanel cardPanel = new CardPanel(card);
            cardPanel.addMouseListener(new CardMouseListener());
            cardPanel.setTransferHandler(new CardTransferHandler(card));
            this.cardPanel.add(cardPanel, new GridBagConstraintsBuilder(0, counter).weightX(1).fill(GridBagConstraints.HORIZONTAL).build());
            counter += 1;
        }

        // 设置面板边框和名称
        setBorder(new LineBorder(Color.BLACK));
        JLabel nameForDeck = new JLabel("Player Deck");
        JLabel nameForOrdered = new JLabel("Ordered Deck");
        add(nameForDeck, new GridBagConstraintsBuilder(0, 0).weightX(1).inset(0, 50, 0, 50).fill(GridBagConstraints.HORIZONTAL).build());
        add(nameForOrdered, new GridBagConstraintsBuilder(1, 0).weightX(1).inset(0, 50, 0, 50).fill(GridBagConstraints.HORIZONTAL).build());
        add(cardPanel, new GridBagConstraintsBuilder(0, 1).weightX(1).fill(GridBagConstraints.HORIZONTAL).build());
        add(orderedPanel, new GridBagConstraintsBuilder(1, 1).weightX(1).fill(GridBagConstraints.HORIZONTAL).build());

        // 添加orderedPanel的DropTargetListener
        orderedPanel.setDropTarget(new DropTarget(orderedPanel, new CardDropTargetAdapter()));
    }

    // 拖拽完成后将卡片添加到orderedPanel中，并检查卡片数量是否达到了5张
    private class CardDropTargetAdapter extends DropTargetAdapter {
        public void drop(DropTargetDropEvent event) {
            try {
                Transferable tr = event.getTransferable();
                AbCardProgramming card = (AbCardProgramming) tr.getTransferData(CardTransferable.PROGRAMMING_CARD);

                // 获取鼠标释放时的位置
                Point dropPoint = event.getDropTargetContext().getComponent().getMousePosition();
                // 将卡片添加到orderedPanel中
                orderedPanel.add(new CardPanel(card), getDropIndex(dropPoint, orderedPanel.getComponentCount()));
                // 判断卡片数量是否达到了5张
                if (orderedPanel.getComponentCount() == 5) {
                    ProgrammingPhaseView programmingPhaseView = controller.getProgrammingPhaseView();
                    if (programmingPhaseView != null) {
                        programmingPhaseView.enableSubmitSequenceButton(true);
                    }
                }
                // 刷新orderedPanel以显示新的卡片顺序
                orderedPanel.revalidate();
                orderedPanel.repaint();
                event.dropComplete(true);
            } catch (Exception e) {
                e.printStackTrace();
                event.rejectDrop();
            }
        }

        // 获取拖拽卡片放置的位置索引
        private int getDropIndex(Point dropPoint, int numComponents) {
            int index = 0;
            Component[] components = orderedPanel.getComponents();
            for (int i = 0; i < numComponents; i++) {
                Rectangle bounds = components[i].getBounds();
                if (dropPoint.y < bounds.y + bounds.height / 2) {
                    index = i;
                    break;
                } else {
                    index = i + 1;
                }
            }
            return index;
        }
    }

    private class SubmitSequenceListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            // 获取orderedPanel中的卡片顺序并提交到服务器
            ArrayList<AbCardProgramming> sequence = new ArrayList<>();
            Component[] components = orderedPanel.getComponents();
            for (Component component : components) {
                if (component instanceof CardPanel) {
                    sequence.add(((CardPanel) component).getCard());
                }
            }
            gameBrain.getPlayerWithoutCardSequence().setProgrammingCards(sequence);
            controller.submitCardSequence();
            // 清空orderedPanel中的卡片
            orderedPanel.removeAll();
            ProgrammingPhaseView programmingPhaseView = controller.getProgrammingPhaseView();
            if (programmingPhaseView != null) {
                programmingPhaseView.enableSubmitSequenceButton(false);
            }
            orderedPanel.revalidate();
            orderedPanel.repaint();
        }
    }
}
