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
        createOptions();

    }
    public void createOptions(){
        JButton saveGameButton = saveGameButton();
        JButton ExitGameButton = exitGameButton();
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        add(saveGameButton,new GridBagConstraintsBuilder(0, 1).inset(0,0,0,0).fill(GridBagConstraints.LAST_LINE_END).build());
    }

    protected JButton saveGameButton(){
        JButton saveGame = new JButton("Save game");
        saveGame.setFont(Fonts.LARGE);
        return saveGame;

    }
    protected  JButton exitGameButton(){
        JButton exitGame = new JButton("Exit");
        exitGame.setFont(Fonts.LARGE);
        return exitGame;
    }

    private void createView(){
        setLayout(new GridBagLayout());
    }
}
