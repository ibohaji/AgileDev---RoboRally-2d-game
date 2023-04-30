package App.RoborallyApplication.Model;

import Utils.MapGenerator;
import Utils.MusicPlayer;
import java.awt.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.stream.Collectors;

public class LGameBrain{
    private final LGameConfiguration gameConfig;
    private LGameboard gameboard = null;
    private final ArrayList<LPlayer> players;
    private EnumGamePhase currentEnumGamePhase;
    public LPlayer winner;
    /**
     * @param gameConfiguration game configuration
     * Constructor for starting game from lobby
     */
    public LGameBrain(LGameConfiguration gameConfiguration){
        gameConfig = gameConfiguration;
        createGameboard(gameConfig.getDifficulty());
        this.players = gameConfiguration.getPlayers();
        ArrayList<LRobot> robots = createRobots(players);
        this.gameboard.setRobots(robots);
        currentEnumGamePhase = EnumGamePhase.ROUND_START;
        startGame();
        startRound();
    }

    // -------------------------------------------------------------------------//
    // GAME RUNNING LOGIC
    public void setCurrentGamePhase(EnumGamePhase phase){
        this.currentEnumGamePhase = phase;
    }
    private void startGame(){
        setupRobots();
    }
    public void startRound(){
        currentEnumGamePhase = EnumGamePhase.ROUND_START;
        givePlayersCardsForRound();
        currentEnumGamePhase = EnumGamePhase.PROGRAMMING_PHASE;
    }
    public void givePlayersCardsForRound(){
        Random rnd = new Random();
        for (LPlayer player : players) {
            for (int i = 0; i < 5; i++) {
                int choiceForCard = rnd.nextInt(10);
                int choiceForSpecific = rnd.nextInt(10);
                if(choiceForCard < 1){ // againCard
                    player.assignCardToPlayer(new LCardAgainProgramming());
                } else if (choiceForCard < 6) { // movementCard
                    if(choiceForSpecific <5) {
                        player.assignCardToPlayer(new LCardMovementProgramming(1));
                    } else if (choiceForSpecific < 8) {
                        player.assignCardToPlayer(new LCardMovementProgramming(2));
                    } else {
                        player.assignCardToPlayer(new LCardMovementProgramming(3));
                    }
                } else { // turn
                    if(choiceForSpecific < 4) { // LEFT
                        player.assignCardToPlayer(new LCardChangeDirectionProgramming(EnumTurnType.LEFT));
                    } else if (choiceForSpecific < 8) { // RIGHT
                        player.assignCardToPlayer(new LCardChangeDirectionProgramming(EnumTurnType.RIGHT));
                    } else { // U-TURN
                        player.assignCardToPlayer(new LCardChangeDirectionProgramming(EnumTurnType.U_TURN));
                    }
                }
            }
        }
    }
    public void makeMovement(){
        if(this.gameboard.getRobots().isEmpty()){
            setCurrentGamePhase(EnumGamePhase.GAME_OVER);
        } else {
            LPlayer player = getPlayerWhoIsCurrentlyMoving();
            AbCardProgramming card = player.getNextCardFromOrderedDeck();
            moveRobotWithCard(player, card);
            if(player.getRobot().getNrOfLives() < 1){
                removePlayer(player);
                removeRobot(player.getRobot());
                if(!areThereMovementsLeftInThisRound()){
                    endRound();
                }
            } else {
                Point newPos = player.getRobot().getCords();
                LTile tile = this.gameboard.getTileFromCoordinate(newPos.x, newPos.y);
                if(tile.doesTileHaveCheckpoint()){
                    if(canRobotCollectCheckpoint(player)){
                        player.getRobot().addCheckpoint(new Point(tile.getCoordinates().x, tile.getCoordinates().y));
                    }
                } else if (tile.isTileFinishPoint()) {
                    if(player.getRobot().getCheckpointsDone().size() == gameboard.getCheckpointsInOrder().size()){
                        setCurrentGamePhase(EnumGamePhase.GAME_OVER);
                        setWinner(player);
                    }
                }
            }
        }
    }

    /**
     * This method is to ensure that the game logic knows when to stop making movements
     * @return boolean whether there are still movements left to make
     */
    public boolean areThereMovementsLeftInThisRound(){
        for (LPlayer player : players) {
            if(player.doesPlayerHaveMovesLeft()){
                return true;
            }
        }
        return false;
    }

    /**
     * Called once no movements left for any player.
     */
    public void endRound(){
        currentEnumGamePhase = EnumGamePhase.ROUND_END;
        if(!canGameContinue()){
            currentEnumGamePhase = EnumGamePhase.GAME_OVER;
        } else {
            for (LPlayer player: players) {
                player.setCardSequenceToNull();
            }
        }
    }

    public boolean canGameContinue(){
        if(this.currentEnumGamePhase.equals(EnumGamePhase.GAME_OVER)){
            return false;
        } else if (isThereAWinner()) {
            return false;
        } else return !this.gameboard.getRobots().isEmpty();
    }

    public boolean canRobotCollectCheckpoint(LPlayer player){
        ArrayList<Point> robotsCheckpoints = player.getRobot().getCheckpointsDone();
        ArrayList<Point> gameBrainCheckpoints = gameboard.getCheckpointsInOrder().stream()
                .map(x -> new Point(x.getCoordinates().x, x.getCoordinates().y)).collect(Collectors
                        .toCollection(ArrayList::new));
        if (!gameBrainCheckpoints.isEmpty()) {
            if (robotsCheckpoints.size() != gameBrainCheckpoints.size()){
                boolean orderCorrect = true;
                for (int i = 0; i < robotsCheckpoints.size(); i++) {
                    boolean xCheck = robotsCheckpoints.get(i).x == gameBrainCheckpoints.get(i).x;
                    boolean yCheck = robotsCheckpoints.get(i).y == gameBrainCheckpoints.get(i).y;
                    if (!xCheck || !yCheck) {
                        orderCorrect = false;
                        break;
                    }
                }
                return orderCorrect;
            }
        }
        return false;
    }


// -> Winner methods <-

// ---------------------------------------
    public void setWinner(LPlayer player){
        this.winner = player;
    }
    public String getPlayerWhoWon(){
        return winner.getDisplayName();
    }

    public boolean isThereAWinner(){
        return winner != null;
    }

// ---------------------------------------

    // -------------------------------------------------------------------------//
    // CARD SEQUENCE LOGIC
    public void setCardSequenceForPlayer(LPlayer player, LCardSequence cardSequence ){
        player.setOrderedCardSequence(cardSequence);
    }
    public boolean haveAllPlayersSubmittedSequence(){
        boolean haveSubmitted = true;
        for (LPlayer player: players) {
            if (player.getCardSequence() == null) {
                haveSubmitted = false;
                break;
            }
        }
        return haveSubmitted;
    }
    public LPlayer getPlayerWithoutCardSequence(){
        for (LPlayer player: players) {
            if(player.getCardSequence() == null){
                return player;
            }
        }
        return null;
    }
    public void setCardSequencesForAi(){
        while(!haveAllPlayersSubmittedSequence()){
            LPlayer player = getPlayerWithoutCardSequence();
            LCardSequence newSeq = new LCardSequence(player);
            for (AbCardProgramming card: player.getProgrammingCards()) {
                newSeq.addCard(card);
            }
            this.setCardSequenceForPlayer(player, newSeq);
        }
    }

    // -------------------------------------------------------------------------//
    // OBSTACLE METHODS
    public EnumObstacleType getRandomObstacleTypeToExplode(){
        Random rnd = new Random();
        float val = rnd.nextFloat(1);
        if (val < 0.5) { // ACID
            return EnumObstacleType.ACID;
        } else {
            return EnumObstacleType.RADIATION;
        }
    }
    public AbObstacle getObstacleFromCoordinateNEW(Integer x, Integer y) {
        return this.gameboard.getObstacleFromCoordinateNEW(x, y);
    }

    public void explodeObstacleToTilesNEW(ArrayList<LTile> tiles, EnumObstacleType obstacleType){
        for (LTile tile: tiles) {
            if(!tile.doesTileHaveObstacle()){
                if(obstacleType.getDeclaringClass().isInstance(EnumObstacleType.ACID)){
                    tile.setNewObstacle(new LObstacleRegular(obstacleType, EnumObstacleClassification.KNOWN_OBSTACLE));
                    tile.setGraphicalElement(EnumImageGraphics.OBSTACLE_ACID, gameConfig.getDifficulty());
                } else if (obstacleType.getDeclaringClass().isInstance(EnumObstacleType.RADIATION)) {
                    tile.setNewObstacle(new LObstacleRegular(obstacleType, EnumObstacleClassification.KNOWN_OBSTACLE));
                    tile.setGraphicalElement(EnumImageGraphics.OBSTACLE_RADIATION, gameConfig.getDifficulty());
                }
                updateGraphicalElementOnTile(tile);
            }
        }
    }

    private void updateGraphicalElementOnTile(LTile tileToUpdate){
        LObstacleRegular obs = (LObstacleRegular)tileToUpdate.getNewObstacle();
        EnumDifficulty diff = gameConfig.getDifficulty();
        if(obs.getObstacleClassification().equals(EnumObstacleClassification.KNOWN_OBSTACLE) ||
                obs.getObstacleClassification().equals(EnumObstacleClassification.EXPLOSIVE_KNOWN)){ // Known and Explosive known
            if(obs.getObstacleType().equals(EnumObstacleType.PIT)){
                tileToUpdate.setGraphicalElement(EnumImageGraphics.OBSTACLE_PIT, diff);
            } else if (obs.getObstacleType().equals(EnumObstacleType.RADIATION)) {
                if (obs.getObstacleClassification().equals(EnumObstacleClassification.KNOWN_OBSTACLE)) {
                    tileToUpdate.setGraphicalElement(EnumImageGraphics.OBSTACLE_RADIATION, diff);
                } else {
                    tileToUpdate.setGraphicalElement(EnumImageGraphics.OBSTACLE_EXPLOSIVE_RADIATION, diff);
                }
            } else if (obs.getObstacleType().equals(EnumObstacleType.HEALING)) {
                if (obs.getObstacleClassification().equals(EnumObstacleClassification.KNOWN_OBSTACLE)) {
                    tileToUpdate.setGraphicalElement(EnumImageGraphics.OBSTACLE_HEALING, diff);
                }
            } else { // ACID
                if (obs.getObstacleClassification().equals(EnumObstacleClassification.KNOWN_OBSTACLE)) {
                    tileToUpdate.setGraphicalElement(EnumImageGraphics.OBSTACLE_ACID, diff);
                } else {
                    tileToUpdate.setGraphicalElement(EnumImageGraphics.OBSTACLE_EXPLOSIVE_ACID, diff);
                }
            }
        } else{ // Explosive unknown
            tileToUpdate.setGraphicalElement(EnumImageGraphics.OBSTACLE_UNKNOWN_EXPLOSIVE, gameConfig.getDifficulty());
        }
    }


    // -------------------------------------------------------------------------//
    // PLAYER METHODS
    public void removePlayer(LPlayer playerToRemove) {
        this.players.remove(playerToRemove);
    }
    public LPlayer getPlayerWhoIsCurrentlyMoving(){
        for (LPlayer player: players) {
            if (player.doesPlayerHaveMovesLeft()){
                return player;
            }
        }
        return null;
    }


    // -------------------------------------------------------------------------//
    // ROBOT METHODS
    private ArrayList<LRobot> createRobots(ArrayList<LPlayer> players){
        ArrayList<LRobot> robots = new ArrayList<>();
        for (LPlayer player: players) {
            LRobot newRobot = new LRobot();
            newRobot.setDirection(EnumDirection.NORTH);
            player.assignRobot(newRobot);
            robots.add(newRobot);
        }
        return robots;
    }
    public void removeRobot(LRobot robot){
        this.gameboard.removeRobot(robot);
        if(this.gameboard.getRobots().isEmpty()){
            setCurrentGamePhase(EnumGamePhase.GAME_OVER);
        }
    }
    public void pushRobot(LRobot robotBeingPushed, EnumDirection directionOfPushOrigin){
        Point pos = robotBeingPushed.getCords();
        switch (directionOfPushOrigin){
            case WEST -> pos.x += 1;
            case EAST -> pos.x -= 1;
            case SOUTH -> pos.y -= 1;
            case NORTH -> pos.y += 1;
        }
        if(!isPositionOnBoard(pos)){
            robotBeingPushed.setNrOfLives(robotBeingPushed.getNrOfLives() - 1);
            if(robotBeingPushed.getNrOfLives() < 1){
                removePlayer(robotBeingPushed.getPlayer());
                removeRobot(robotBeingPushed);
            } else {
                putRobotToRandomStartPoint(robotBeingPushed);
            }
        } else if(isPositionOnBoard(pos)){
            if(gameboard.isTileOccupiedByRobot(pos.x, pos.y)){
                LRobot robot2 = gameboard.getRobotFromCoordinate(pos.x, pos.y);
                if(!robot2.equals(robotBeingPushed)){
                    pushRobot(robot2, directionOfPushOrigin);
                }
            } else {
                robotBeingPushed.setCords(pos);
            }
            int x = robotBeingPushed.getCords().x;
            int y = robotBeingPushed.getCords().y;
            AbObstacle obs = getObstacleFromCoordinateNEW(x, y);
            if(obs != null){
                obs.applyEffect(robotBeingPushed, this);
            } else if (gameboard.getTileFromCoordinate(x, y).isTileFinishPoint()){
                if(robotBeingPushed.getCheckpointsDone().size() == gameboard.getCheckpointsInOrder().size()){
                    setWinner(robotBeingPushed.getPlayer());
                    setCurrentGamePhase(EnumGamePhase.GAME_OVER);
                }
            } else if (gameboard.getTileFromCoordinate(x, y).doesTileHaveCheckpoint()) {
                if(canRobotCollectCheckpoint(robotBeingPushed.getPlayer())){
                    robotBeingPushed.addCheckpoint(new Point(x, y));
                }
            }
        }
    }
    private void setupRobots(){
        ArrayList<LTile> availableStartPoints = getAllStartPoints();
        for (int i = 0; i < this.players.size(); i++) {
            players.get(i).getRobot().setCords(availableStartPoints.get(i).getCoordinates());
        }
    }
    private void moveRobotWithCard(LPlayer player, AbCardProgramming card){
        if (player.getRobot().getNrOfLives() > 0){
            player.useProgrammingCard(card, this);
            if(card instanceof LCardMovementProgramming){
                if(((LCardMovementProgramming) card).getSteps() == ((LCardMovementProgramming) card).getStepsMade()){
                    player.addCardToUsedSequence(card);
                    player.removeFirstCardFromOrderedSequence();
                }
            } else {
                player.addCardToUsedSequence(card);
                player.removeFirstCardFromOrderedSequence();
            }
        } else {
            player.setCardSequenceToNull();
            removePlayer(player);
            removeRobot(player.getRobot());
        }
    }
    protected void robotStepOnObstacleNEW(AbObstacle obstacle, LRobot robot){
        obstacle.applyEffect(robot, this);
    }

    // -------------------------------------------------------------------------//
    // GAMEBOARD METHODS
    private void createGameboard(EnumDifficulty difficulty){
        if (difficulty == EnumDifficulty.EASY){
            this.gameboard = MapGenerator.generateEasyMap(this);
        } else if (difficulty == EnumDifficulty.MEDIUM){
            this.gameboard = MapGenerator.generateMediumMap(this);
        } else {
            this.gameboard = MapGenerator.generateHardMap(this);
        }
    }
    public boolean isPositionOnBoard(Point point){
        return (point.x > -1 && point.x < gameConfig.getBoardDimensions().first() &&
                point.y > -1 && point.y < gameConfig.getBoardDimensions().second());
    }

    // -------------------------------------------------------------------------//
    // STARTPOINT, CHECKPOINT METHODS
    private ArrayList<LTile> getAllStartPoints(){
        ArrayList<LTile> startPoints = new ArrayList<>();
        for (LTile tile: gameboard.getTiles()) {
            if (tile.isTileStartPoint()){
                startPoints.add(tile);
            }
        }
        return startPoints;
    }
    private ArrayList<LTile> getAllFreeStartPoints(){
        ArrayList<LTile> allStartPoints = getAllStartPoints();
        ArrayList<LTile> availableStartPoints = new ArrayList<>();
        ArrayList<LRobot> robots = gameboard.getRobots();
        for (LTile startpoint: allStartPoints) {
            boolean isTaken = false;
            for (LRobot robot: robots) {
                if(robot.getCords().x == startpoint.getCoordinates().x &&
                        robot.getCords().y == startpoint.getCoordinates().y){
                    isTaken = true;
                }
            }
            if(!isTaken){
                availableStartPoints.add(startpoint);
            }
        }
        return availableStartPoints;
    }
    public void putRobotToRandomStartPoint(LRobot robot){
        MusicPlayer.getInstance().play("App/Resources/Music/offBoardSound.wav");
        ArrayList<LTile> available_startpoints = this.getAllFreeStartPoints();
        Random rnd = new Random();
        if(available_startpoints.size() == 0){
            robot.setCords(robot.getCords());
        } else {
            int index = rnd.nextInt(available_startpoints.size());
            robot.setCords(available_startpoints.get(index).getCoordinates());
        }
        robot.setDirection(EnumDirection.NORTH);

    }
    // -------------------------------------------------------------------------//
    public EnumGamePhase getCurrentGamePhase(){
        return this.currentEnumGamePhase;
    }
    // -------------------------------------------------------------------------//
    // GETTERS
    public LGameConfiguration getGameConfig(){
        return this.gameConfig;
    }
    public ArrayList<LPlayer> getPlayers() {
        return players;
    }
    public LGameboard getGameboard(){
        return this.gameboard;
    }

}
