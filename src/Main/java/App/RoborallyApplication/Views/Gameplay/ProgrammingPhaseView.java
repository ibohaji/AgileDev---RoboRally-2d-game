package App.RoborallyApplication.Views.Gameplay;

import App.RoborallyApplication.Controllers.GameController;
import App.RoborallyApplication.Model.AbCardProgramming;
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

    private UserCardDeckView userDeckView;

    private UserOrderedCardDeckView userOrderedDeckView;

    private ArrayList<AbCardProgramming> programmingCards;

    private GameBoardView gameBoardView;
    private LPlayer player;
    public ProgrammingPhaseView(GameController gameController, LGameBrain gameBrain) {
        super(gameController, gameBrain);
        this.gameBrain = gameBrain;
        this.gameController = gameController;
        this.player = gameController.getPlayerWithoutCardSequence();
        this.programmingCards = this.player.getProgrammingCards();
        createView();
    }

    private void createView() {
        setLayout(new GridBagLayout());
        this.userDeckView = new UserCardDeckView(gameController, gameBrain);
        this.userOrderedDeckView = new UserOrderedCardDeckView(gameController, gameBrain);
        this.gameBoardView = new GameBoardView(gameController, gameBrain);
        add(userOrderedDeckView, new GridBagConstraintsBuilder(0, 0).inset(0,0,0,50).fill(GridBagConstraints.BOTH).build());
        add(gameBoardView, new GridBagConstraintsBuilder(1, 0).fill(GridBagConstraints.BOTH).build());
        add(userDeckView, new GridBagConstraintsBuilder(2, 0).inset(0,50,0,0).fill(GridBagConstraints.BOTH).build());

        // Submit button
        JButton submitButton = new JButton("SUBMIT SEQUENCE");
        submitButton.setFont(Fonts.LARGE);
        submitButton.addActionListener(e -> {
            if(this.userOrderedDeckView.getCardSequence().getSize() == 5){
                //TODO
                // if not all cards in ordered deck, throw error popup
            } else {
                this.gameController.setPlayerCardSequence(this.player, this.userOrderedDeckView.getCardSequence());
            }

        });
        add(submitButton, new GridBagConstraintsBuilder(1, 1).inset(50,0,0,0).fill(GridBagConstraints.BOTH).build());


    }


}
