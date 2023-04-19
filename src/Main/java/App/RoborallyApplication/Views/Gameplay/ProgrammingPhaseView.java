package App.RoborallyApplication.Views.Gameplay;

import App.RoborallyApplication.Controllers.GameController;
import App.RoborallyApplication.Model.LGameBrain;
import App.RoborallyApplication.Model.LPlayer;
import App.RoborallyApplication.Views.Gameplay.CardDeck.UserCardDeckView;
import App.RoborallyApplication.Views.Gameplay.CardDeck.UserOrderedCardDeckView;
import Utils.Fonts;
import Utils.GridBagConstraintsBuilder;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class ProgrammingPhaseView extends GameView{

    private GameController gameController;
    private LGameBrain gameBrain;

    private LPlayer player;
    public ProgrammingPhaseView(GameController gameController, LGameBrain gameBrain) {
        super(gameController, gameBrain);
        this.gameBrain = gameBrain;
        this.gameController = gameController;
        //this.player = gameBrain.
        createView();
    }

    private void createView() {
        setLayout(new GridBagLayout());
        add(new UserCardDeckView(gameController, gameBrain, new ArrayList<>()), new GridBagConstraintsBuilder(2, 0).inset(0,50,0,0).fill(GridBagConstraints.BOTH).build());
        add(new GameBoardView(gameController, gameBrain), new GridBagConstraintsBuilder(1, 0).fill(GridBagConstraints.BOTH).build());
        add(new UserOrderedCardDeckView(gameController, gameBrain), new GridBagConstraintsBuilder(0, 0).inset(0,0,0,50).fill(GridBagConstraints.BOTH).build());


        // Submit button
        JButton submitButton = new JButton("SUBMIT SEQUENCE");
        submitButton.setFont(Fonts.LARGE);
        submitButton.addActionListener(e -> {
            // if not all cards in ordered deck, throw error
            // check if last player -> move to showing the robots move
            // check if still some players haven't submitted, then take that player and their cards

        });


    }


}
