package App.RoborallyApplication.Views.Gameplay.CardDeck;

import App.RoborallyApplication.Controllers.GameController;
import App.RoborallyApplication.Model.LCardSequence;
import App.RoborallyApplication.Model.LGameBrain;
import App.RoborallyApplication.Views.Gameplay.GameView;
import Utils.GridBagConstraintsBuilder;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class UserOrderedCardDeckView extends GameView {

    private LCardSequence cardSequence;
    public UserOrderedCardDeckView(GameController controller, LGameBrain gameBrain) {
        super(controller, gameBrain);
        this.cardSequence = new LCardSequence(gameBrain.getPlayerWithoutCardSequence());
        createView();
    }
    private void createView() {
        setLayout(new GridBagLayout());
        setBorder(new LineBorder(Color.BLACK, 5));
        JLabel nameForDeck = new JLabel("Ordered Deck");
        add(nameForDeck, new GridBagConstraintsBuilder(0,0).weightX(1).inset(50).fill(GridBagConstraints.HORIZONTAL).build());
    }

    public LCardSequence getCardSequence(){
        return this.cardSequence;
    }
}
