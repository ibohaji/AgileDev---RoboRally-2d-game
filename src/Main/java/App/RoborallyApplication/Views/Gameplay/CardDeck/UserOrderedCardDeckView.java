package App.RoborallyApplication.Views.Gameplay.CardDeck;

import App.RoborallyApplication.Controllers.GameController;
import App.RoborallyApplication.Model.LGameBrain;
import App.RoborallyApplication.Views.Gameplay.GameView;
import Utils.GridBagConstraintsBuilder;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class UserOrderedCardDeckView extends GameView {

    public UserOrderedCardDeckView(GameController controller, LGameBrain gameBrain) {
        super(controller, gameBrain);
        createView();
    }
    private void createView() {
        setLayout(new GridBagLayout());
        setBorder(new LineBorder(Color.BLACK, 5));
        JLabel nameForDeck = new JLabel("Ordered Deck");
        add(nameForDeck, new GridBagConstraintsBuilder(0,0).weightX(1).inset(50).fill(GridBagConstraints.HORIZONTAL).build());
    }
}
