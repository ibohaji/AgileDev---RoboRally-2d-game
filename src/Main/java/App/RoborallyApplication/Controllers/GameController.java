package App.RoborallyApplication.Controllers;

import App.RoborallyApplication.Model.*;
import App.RoborallyApplication.Views.Gameplay.GameBoardView;
import App.RoborallyApplication.Views.Gameplay.GameView;
import App.RoborallyApplication.Views.Gameplay.Options;
import App.RoborallyApplication.Views.Gameplay.ProgrammingPhaseView;

import java.util.Timer;

public class GameController {
    private final ApplicationController applicationController;
    protected final LGameBrain gameBrain;
    private GameView view;

    public GameController(ApplicationController applicationController, LGameBrain gameBrain){
        this.applicationController = applicationController;
        this.gameBrain = gameBrain;

        if(gameBrain.getCurrentGamePhase().equals(EnumGamePhase.PROGRAMMING_PHASE)){
            this.view = new ProgrammingPhaseView(this, gameBrain);
        } else if (gameBrain.getCurrentGamePhase().equals(EnumGamePhase.MOVEMENT_PHASE)) {
            this.view = new GameBoardView(this, gameBrain);
            makeMovements();
        } else if (gameBrain.getCurrentGamePhase().equals(EnumGamePhase.ROUND_END)){
            // Give a screen where you can save game or continue with next round
            this.view = new Options(this,gameBrain);
        }

    }

    private void makeMovements(){
        while(this.gameBrain.areThereMovementsLeftInThisRound()){
            this.gameBrain.makeMovement();
            this.view = new GameBoardView(this, gameBrain);
            display();
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                throw new RuntimeException("Interrupted sleep in GameController method makeMovements()");
            }
        }
        this.gameBrain.setCurrentGamePhase(EnumGamePhase.ROUND_END);
    }

    protected void display() {
        applicationController.changePanel(view);
    }

    private void changeView(GameView viewToChangeTo){
        this.view = viewToChangeTo;
    }

    public LPlayer getPlayerWithoutCardSequence(){
        return gameBrain.getPlayerWithoutCardSequence();
    }

    public void setPlayerCardSequence(LPlayer player, LCardSequence cardSequence){
        gameBrain.setCardSequenceForPlayer(player, cardSequence);
        if(gameBrain.haveAllPlayersSubmittedSequence()){
            gameBrain.setCurrentGamePhase(EnumGamePhase.MOVEMENT_PHASE);
        } else {
            if(gameBrain.getPlayerWithoutCardSequence().isHuman()){
                changeView(new ProgrammingPhaseView(this, gameBrain));
            } else {
                gameBrain.setCardSequencesForAi();
                gameBrain.setCurrentGamePhase(EnumGamePhase.MOVEMENT_PHASE);
            }

        }
    }

    // Test purpose only
    public LGameBrain getGameBrain() {
        return this.gameBrain;
    }

}
