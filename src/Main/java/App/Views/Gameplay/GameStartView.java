package App.Views.Gameplay;

import App.Controllers.GameController.GameController;
import App.Domain.Enums.DifficultyEnum;
import App.Domain.Enums.GraphicalElementEnum;
import App.Domain.GameBrain;
import App.Domain.Tile;
import Utils.Fonts;
import Utils.GridBagConstraintsBuilder;
import Utils.NoScalingIcon;

import javax.swing.*;
import java.awt.*;

public class GameStartView extends GameView{
    public GameStartView(GameController controller, GameBrain gameBrain) {
        super(controller, gameBrain);
        createView();
    }

    private void createView(){
        setLayout(new GridBagLayout());
        int gridSize = super.gameConfiguration.getBoardDimensions().first();
        // GAME NAME
        add(generateGameNameLabel(), new GridBagConstraintsBuilder(0, 0).gridWidth(gridSize).build());
        // Create grid
        for (int x = 0; x < gridSize; x++) {
            for (int y = 0; y < gridSize; y++) {
                try{
                    Tile tileAtCoordinate = super.gameBrain.getTileFromCoordinate(x, y);
                    add(new JLabel(tileAtCoordinate.graphicalElement.getNoScalingIcon()),
                            new GridBagConstraintsBuilder(x + 1, y + 1).build());
                } catch (NullPointerException exception){
                    throw new RuntimeException("Will never happen");
                }
            }
        }

    }

    private JLabel generateGameNameLabel(){
        JLabel gameNameLabel = new JLabel("ROBORALLY");
        gameNameLabel.setFont(Fonts.TITLE);
        return gameNameLabel;
    }
}
