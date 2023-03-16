package App.Domain;

import jdk.jshell.spi.ExecutionControl;

public class SpaceOnBoard {
    
    private GraphicalElementEnum graphicalElement;
    public SpaceOnBoard(int xCoordiante, int yCoordinate){
        
    }

    protected void putGraphicElementOnSpace(GraphicalElementEnum graphicalElement){
        this.graphicalElement = graphicalElement;
    }
}
