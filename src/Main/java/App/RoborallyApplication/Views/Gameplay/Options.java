package App.RoborallyApplication.Views.Gameplay;

import App.RoborallyApplication.Controllers.GameController;
import App.RoborallyApplication.Model.LGameBrain;
import Utils.Fonts;
import Utils.GridBagConstraintsBuilder;

import javax.swing.*;
import java.awt.*;

public class Options extends GameView {

    Options(GameController gameController, LGameBrain gameBrain){
        super(gameController,gameBrain);
        createOptions();
    }


    public void createOptions(){
        JButton saveGameButton = saveGameButton();
        JButton ExitGameButton = exitGameButton();
        setLayout(new GridBagLayout());
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        add(saveGameButton,new GridBagConstraintsBuilder(0, 0).inset(0,0,0,50).fill(GridBagConstraints.PAGE_START));

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
}
