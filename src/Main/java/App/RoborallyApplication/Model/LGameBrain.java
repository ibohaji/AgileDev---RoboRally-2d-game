package App.RoborallyApplication.Model;

import App.DTO.GameBrainDTO;
import Utils.JsonHelper;
import Utils.MapGenerator;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.UUID;

public class LGameBrain implements IToDTO {
    private UUID id;
    private LGameConfiguration gameConfig;
    private LGameboard gameboard = null;
    private ArrayList<LPlayer> players;
    private EnumGamePhase currentEnumGamePhase;

    /**
     * Constructor for restoring
     */
    public LGameBrain(){

    }

    /**
     * @param gameConfiguration game configuration
     * Constructor for starting game from lobby
     */
    public LGameBrain(LGameConfiguration gameConfiguration){
        this.id = UUID.randomUUID();
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
                int choiceForCard = rnd.nextInt(12);
                int choiceForSpecific = rnd.nextInt(3);
                if(choiceForCard < 2){ // againCard
                    player.assignCardToPlayer(new LCardAgainProgramming());
                } else if (choiceForCard < 7) { // movementCard
                    if(choiceForSpecific == 0) {
                        player.assignCardToPlayer(new LCardMovementProgramming(1));
                    } else if (choiceForSpecific == 1) {
                        player.assignCardToPlayer(new LCardMovementProgramming(2));
                    } else {
                        player.assignCardToPlayer(new LCardMovementProgramming(3));
                    }
                } else { // turn
                    if(choiceForSpecific == 0) { // LEFT
                        player.assignCardToPlayer(new LCardChangeDirectionProgramming(EnumTurnType.LEFT));
                    } else if (choiceForSpecific == 1) { // RIGHT
                        player.assignCardToPlayer(new LCardChangeDirectionProgramming(EnumTurnType.RIGHT));
                    } else { // U-TURN
                        player.assignCardToPlayer(new LCardChangeDirectionProgramming(EnumTurnType.U_TURN));
                    }
                }
            }
        }
    }
    public void makeMovement(){
        LPlayer player = getPlayerWhoIsCurrentlyMoving();
        AbCardProgramming card = player.getNextCardFromOrderedDeck();
        moveRobotWithCard(player, card);
    }

    /**
     * This method is to ensure that the game logic knows when to stop making movements
     * @return boolean whether there are still movements left to make
     */
    public boolean areThereMovementsLeftInThisRound(){
        for (LPlayer player : players) {
            if (!(player.getCardSequence() == null)){
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
    }

    /**
     * @return Return true if there is a player on finish that has reached checkpoints and is now on finish tile
     */
    public boolean isThereAWinner(){
        // After a player gets to finish tile, check if they have collected checkpoints aswell
        // TODO
        return false;
    }
    public LPlayer getPlayerWhoWon(){
        // TODO
        return null;
    }


    // -------------------------------------------------------------------------//
    // CARD SEQUENCE LOGIC
    public void setCardSequenceForPlayer(LPlayer player, LCardSequence cardSequence ){
        player.setOrderedCardSequence(cardSequence);
    }
    public boolean haveAllPlayersSubmittedSequence(){
        boolean haveSubmitted = true;
        for (LPlayer player: players) {
            if(player.getCardSequence() == null){
                haveSubmitted = false;
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
    public LObstacle chooseUnknownObstacle(LTile tile){

        boolean robot_on_unknown = false;

        for (LPlayer player : this.players) {
            if (player.getRobot().getCords().equals(tile.getCoordinates())) {
                robot_on_unknown = true;
            }
        }

        if (robot_on_unknown) {
            Random rnd = new Random();
            float chance = rnd.nextFloat(1);
            if (chance < 0.6) { // acid
                return new LObstacle(EnumObstacle.ACID, EnumObstacleType.KNOWN_OBSTACLE);
            } else if (chance < 0.8) { // radiation
                return new LObstacle(EnumObstacle.RADIATION, EnumObstacleType.KNOWN_OBSTACLE);
            } else { // pit
                return new LObstacle(EnumObstacle.PIT, EnumObstacleType.KNOWN_OBSTACLE);
            }
        }

        return tile.getObstacle();
    }
    public LObstacle getObstacleFromCoordinate(Integer x, Integer y) {
        return this.gameboard.getObstacleFromCoordinate(x, y);
    }

    public void explodeObstacleToTiles(ArrayList<LTile> tiles, EnumObstacle obstacle){
        //TODO

    }


    // -------------------------------------------------------------------------//
    // PLAYER METHODS
    public void removePlayer(LPlayer playerToRemove) {
        this.players.remove(playerToRemove);
    }
    public LPlayer findPlayerByRobot(LRobot robot){
        for (LPlayer player: players) {
            if (player.getRobot().equals(robot)){
                return player;
            }
        }
        return null;
    }

    public LPlayer getPlayerWhoIsCurrentlyMoving(){
        // Once a player has made all his movements, set cardsequence to null
        // TODO
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
                removeRobot(robotBeingPushed);
                removePlayer(robotBeingPushed.getPlayer());
            } else {
                putRobotToRandomStartPoint(robotBeingPushed);
            }
        } else if(isPositionOnBoard(pos)){
            if(gameboard.isTileOccupiedByRobot(pos.x, pos.y)){
                LRobot robot2 = gameboard.getRobotFromCoordinate(pos.x, pos.y);
                pushRobot(robot2, directionOfPushOrigin);
            } else {
                robotBeingPushed.setCords(pos);
            }
        }
    }
    public AbCardProgramming getLastCardUsedByRobot(LRobot robot){
        LPlayer player = robot.getPlayer();
        return player.getCardSequence().getLastCard();
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
        } else {
            player.setCardSequenceToNull();
        }
    }

    protected void robotStepOnObstacle(LRobot robot, LObstacle obstacle, Point pos){
        if(obstacle.getObstacleTypeEnum().equals(EnumObstacleType.KNOWN_OBSTACLE)){
            obstacle.DoDamage(robot);
        } else if (obstacle.getObstacleTypeEnum().equals(EnumObstacleType.EXPLOSIVE_KNOWN)) {
            obstacle.DoDamage(robot);
            explodeObstacleToTiles(gameboard.getTilesSurroundingCoordinate(pos.x, pos.y), obstacle.getObstacleEnum());
        } else if (obstacle.getObstacleTypeEnum().equals(EnumObstacleType.EXPLOSIVE_UNKNOWN)) {
            //TODO
            // choose unknown
            // change obstacle properties
            obstacle.DoDamage(robot);
            explodeObstacleToTiles(gameboard.getTilesSurroundingCoordinate(pos.x, pos.y), obstacle.getObstacleEnum());
        }
        if(robot.getNrOfLives() < 1){
            removePlayer(robot.getPlayer());
            removeRobot(robot);
        }
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
    // STARTPOINT METHODS
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
        ArrayList<LTile> available_startpoints = this.getAllFreeStartPoints();
        Random rnd = new Random();
        int index = rnd.nextInt(available_startpoints.size());
        robot.setCords(available_startpoints.get(index).getCoordinates());
    }

    // -------------------------------------------------------------------------//
    // GAMESTATE METHODS
    public void restoreGameboard(LGameConfiguration gameConfig, ArrayList<LPlayer> players,
                                 EnumGamePhase enumGamePhase, LGameboard gameboard,
                                 ArrayList<LRobot> robots, ArrayList<LTile> tiles){
        this.gameConfig = gameConfig;
        this.gameboard = gameboard;
        this.currentEnumGamePhase = enumGamePhase;
        this.players = players;
        gameboard.setRobots(robots);
        gameboard.setTiles(tiles);
    }
    public EnumGamePhase getCurrentGamePhase(){
        return this.currentEnumGamePhase;
    }
    @Override
    public String DTOasJson() {
        GameBrainDTO gameBrainDTO = new GameBrainDTO(this.gameboard, this.gameConfig, this.currentEnumGamePhase, this);
        return JsonHelper.serializeObjectToJson(gameBrainDTO);
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
    @Override
    public UUID getID() {
        return this.id;
    }

    // -------------------------------------------------------------------------//
    // RANDOM, TO BE DELETED?
    private void push(Point newPos, EnumDirection directionOfRobot) {
        LRobot robotAtCoordinate = getGameboard().getRobotFromCoordinate(newPos.x, newPos.y);
        // push robotAtCoordinate
        switch (directionOfRobot){
            case NORTH -> pushRobot(robotAtCoordinate, EnumDirection.SOUTH);
            case SOUTH -> pushRobot(robotAtCoordinate, EnumDirection.NORTH);
            case EAST -> pushRobot(robotAtCoordinate, EnumDirection.WEST);
            case WEST -> pushRobot(robotAtCoordinate, EnumDirection.EAST);
        }
    }
    public void setRobotChekcpointDone(LRobot robot){
        if (this.getGameboard().getTileFromCoordinate(
                robot.getCords().x, robot.getCords().y).getTileTypeEnum() ==
                EnumTileType.CHECKPOINT) {
            //TODO
            // check ordering of checkpoints
            robot.addCheckpoint(robot.getCords());
        }
    }
    public boolean checkRobotposition(LRobot robot) {
        //TODO
        // use isTileOccupiedByRobot from gameboard
        int pos_x = robot.getCords().x;
        int pos_y = robot.getCords().y;
        LTile tile = this.gameboard.getTileFromCoordinate(pos_x, pos_y);
        return !tile.doesTileHaveObstacle();
    }
    public void activateExplosive(LTile tile) {
        boolean robot_on_explosive = false;
        for (LPlayer player : this.players) {
            if (player.getRobot().getCords().equals(tile.getCoordinates())) {
                robot_on_explosive = true;
            }
        }

        ArrayList<LTile> acidpool = this.gameboard.getTilesSurroundingCoordinate(tile.getCoordinates().x, tile.getCoordinates().y);
        if (robot_on_explosive) {
            for (LTile tilepool : acidpool) {
                tilepool.setObstacle(new LObstacle(EnumObstacle.ACID, EnumObstacleType.KNOWN_OBSTACLE));
                tilepool.setGraphicalElement(EnumGraphicalElementMain.OBSTACLE_ACID, this.gameConfig.getDifficulty());
            }
        }
    }
}
