package App.RoborallyApplication.Controllers;

import App.RoborallyApplication.Model.*;
import App.RoborallyApplication.Views.Gameplay.GameView;
import App.RoborallyApplication.Views.Gameplay.ProgrammingPhaseView;

public class GameController {
    private final ApplicationController applicationController;
    protected final LGameBrain gameBrain;
    private GameView view;

    public GameController(ApplicationController applicationController, LGameBrain gameBrain){
        this.applicationController = applicationController;
        this.gameBrain = gameBrain;
        if(gameBrain.getCurrentGamePhase().equals(EnumGamePhase.PROGRAMMING_PHASE)){
            this.view = new ProgrammingPhaseView(this, gameBrain);
        } else {
            System.out.println("TO MOVEMENT VIEW");
        }

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
