package App.RoborallyApplication.Views.Gameplay;

import App.RoborallyApplication.Controllers.GameController;
import App.RoborallyApplication.Model.LGameBrain;
import App.RoborallyApplication.Views.Gameplay.CardDeck.UserCardDeckView;
import App.RoborallyApplication.Views.Gameplay.CardDeck.UserOrderedCardDeckView;
import Utils.Fonts;
import Utils.GridBagConstraintsBuilder;

import javax.swing.*;
import java.awt.*;

public class Options extends GameView {
    GameController gameController;
    LGameBrain gameBrain;

    public Options(GameController gameController, LGameBrain gameBrain){
        super(gameController,gameBrain);
        this.gameController = gameController;
        this.gameBrain = gameBrain;
        createOptions();
    }

    public void createOptions(){
        JButton saveGameButton = saveGameButton();
        JButton exitGameButton = exitGameButton();
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        add(saveGameButton,new GridBagConstraintsBuilder(0, 1).inset(0,0,0,0).fill(GridBagConstraints.LAST_LINE_END).build());
        add(exitGameButton,new GridBagConstraintsBuilder(-1,0).inset(0,0,0,0).fill(GridBagConstraints.FIRST_LINE_START).build());
    }

    protected JButton saveGameButton(){
        JButton saveGame = new JButton("Save game");
        saveGame.setFont(Fonts.LARGE);
        saveGame.addActionListener(e -> {
            gameController.saveGame();
        });
        return saveGame;

    }
    protected  JButton exitGameButton(){
        JButton exitGame = new JButton("Exit");
        exitGame.setFont(Fonts.LARGE);
        exitGame.addActionListener(e -> {
            gameController.quitGame();
        });
        return exitGame;
    }
}
