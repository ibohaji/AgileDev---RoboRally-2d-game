package App.RoborallyApplication.Model;

import App.DTO.GameBrainDTO;
import Utils.JsonHelper;
import Utils.MapGenerator;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.UUID;

public class LGameBrain implements iToDTO {
    private UUID id;
    private LGameConfiguration gameConfig;
    private LGameboard gameboard = null;
    private ArrayList<LPlayer> players;
    private EnumGamePhase currentEnumGamePhase;

    public LGameBrain(){

    }

    public LGameBrain(int nrOfPlayers, EnumDifficulty difficulty){
        this.id = UUID.randomUUID();
        gameConfig = new LGameConfiguration(nrOfPlayers, difficulty);

        createGameboard(difficulty);
        this.players = createPlayers();
        ArrayList<LRobot> robots = createRobots(players);
        this.gameboard.setRobots(robots);
        currentEnumGamePhase = EnumGamePhase.ROUND_START;
        startGame();
    }


    private void startGame(){
        setupRobots();
    }

    public void startRound(){
        currentEnumGamePhase = EnumGamePhase.ROUND_START;
        givePlayersCardsForRound();
        currentEnumGamePhase = EnumGamePhase.PROGRAMMING_PHASE;
    }

    public void setPlayerCardSequence(){

    }

    private void setupRobots(){
        ArrayList<LTile> availableStartPoints = getAllStartPoints();
        for (int i = 0; i < this.players.size(); i++) {
            players.get(i).getRobot().setCords(availableStartPoints.get(i).getCoordinates());
        }
    }

    private void playRound(){

        // 1st -> give players their cards for the round

        // 2nd -> let players choose the order of their cards
        currentEnumGamePhase = EnumGamePhase.PROGRAMMING_PHASE;
        // 3rd -> move the players, constantly checking for winner
        currentEnumGamePhase = EnumGamePhase.MOVEMENT_PHASE;
    }

    protected void givePlayersCardsForRound(){
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

    public ArrayList<AbCardProgramming> dealCardsForRound() {
        givePlayersCardsForRound();
        ArrayList<AbCardProgramming> roundCards = new ArrayList<>();
        for (LPlayer player : players) {
            roundCards.addAll(player.getProgrammingCards());
        }
        return roundCards;
    }


    /**
     * @param player Player whose card ordering is being asked for
     * @return List containing tuples where first item is the ProgrammingCard and the second item is the number
     * in the ordering
     */
    /*private ArrayList<Tuple<ProgrammingCard, Integer>> getCardSequenceFromPlayer(Player player){
        ArrayList<ProgrammingCard> playerCards = player.getProgrammingCards();
        ArrayList<Tuple<ProgrammingCard, Integer>> playerMoves = new ArrayList<>();

        // TODO: prompt player to select the card ordering via GUI drag-and-drop interface

        for (int i = 0; i < playerCards.size(); i++) {
            ProgrammingCard card = playerCards.get(i);
            int position = // TODO: get the position of the card in the player's selected ordering
                    playerMoves.add(new Tuple<>(card, position));
        }

        return playerMoves;
    }*/

    private void makeMove(LPlayer player, AbCardProgramming card){
        card.useCard(player.getRobot(), this);
    }

    public boolean isPositionOnBoard(Point point){
        return (point.x > -1 && point.x < gameConfig.getBoardDimensions().first() &&
                point.y > -1 && point.y < gameConfig.getBoardDimensions().second());
    }

    private void movePlayerWithCollision(EnumDirection hitDirection){
        // if overboard then back to beginning
        // TODO
    }

    public LObstacle getObstaclefromboard(Integer x, Integer y) {

        return this.gameboard.getObstacleFromCoordinate(x, y);
    }

    public LGameConfiguration getGameConfig(){
        return this.gameConfig;
    }

    public void setGameConfig(LGameConfiguration gameConfiguration){
        this.gameConfig = gameConfiguration;
    }

    private void createGameboard(EnumDifficulty difficulty){
        if (difficulty == EnumDifficulty.EASY){
            this.gameboard = MapGenerator.generateEasyMap(this);
        } else if (difficulty == EnumDifficulty.MEDIUM){
            this.gameboard = MapGenerator.generateMediumMap(this);
        } else {
            this.gameboard = MapGenerator.generateHardMap(this);
        }
    }



    private ArrayList<LPlayer> createPlayers(){
        ArrayList<LPlayer> players = new ArrayList<>();
        for (int i = 1; i <= this.gameConfig.getNrOfPlayers(); i++) {
            players.add(new LPlayer("player" + (Integer.toString(i ))));
        }
        return players;
    }

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

    @Override
    public String DTOasJson() {
        GameBrainDTO gameBrainDTO = new GameBrainDTO(this.gameboard, this.gameConfig, this.currentEnumGamePhase, this);
        return JsonHelper.serializeObjectToJson(gameBrainDTO);
    }

    @Override
    public UUID getID() {
        return this.id;
    }

    public LGameboard getGameboard(){
        return this.gameboard;
    }

    public ArrayList<LPlayer> getPlayers() {
        return players;
    }

    public EnumGamePhase getCurrentGamePhase(){
        return this.currentEnumGamePhase;
    }

    public void restore(LGameConfiguration gameConfig, ArrayList<LPlayer> players,
                        EnumGamePhase enumGamePhase, LGameboard gameboard, ArrayList<LRobot> robots, ArrayList<LTile> tiles){
        this.gameConfig = gameConfig;
        this.gameboard = gameboard;
        this.currentEnumGamePhase = enumGamePhase;
        this.players = players;
        gameboard.setRobots(robots);
        gameboard.setTiles(tiles);

    }

    public void setCurrentGamePhase(EnumGamePhase phase){
        this.currentEnumGamePhase = phase;
    }

    public AbCardProgramming getLastCardUsedByRobot(LRobot robot){
        // TODO
        return new LCardMovementProgramming(1);
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
                // TODO
                // remove robot from game
                // remove player aswell
            } else {
                putRobotToRandomStartPoint(robotBeingPushed);
            }
        } else if(isPositionOnBoard(pos)){
            robotBeingPushed.setCords(pos);

        }
        // check if pushes another robot


    }


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

    public void pushRobotOffBoard(LRobot robot){
        int nrOfLives = robot.getNrOfLives();
        if(nrOfLives == 1){
            removeRobot(robot);
            removePlayer(findPlayerByRobot(robot));
        } else {
            putRobotToRandomStartPoint(robot);
            robot.setNrOfLives(nrOfLives - 1);
        }
    }

    public void putRobotToRandomStartPoint(LRobot robot){
        // get all available start points (tiles with startpoint enum)
        // randomly choose one
    }

    public LObstacle chooseUnkownObstacle(LTile tile){
        // TODO
        Random rnd = new Random();
        float chance = rnd.nextFloat(1);
        if(chance < 0.6){ // acid
            return new LObstacle(EnumObstacle.ACID, EnumObstacleType.KNOWN_OBSTACLE);
        } else if (chance < 0.8) { // radiation
            return new LObstacle(EnumObstacle.RADIATION, EnumObstacleType.KNOWN_OBSTACLE);
        } else { // pit
            return new LObstacle(EnumObstacle.PIT, EnumObstacleType.KNOWN_OBSTACLE);
        }
    }

    public LPlayer findPlayerByRobot(LRobot robot){
        for (LPlayer player: players) {
            if (player.getRobot().equals(robot)){
                return player;
            }
        }
        return null;
    }

    public void removePlayer(LPlayer playerToRemove) {
        this.players.remove(playerToRemove);
    }

    public void removeRobot(LRobot robot){
        this.gameboard.removeRobot(robot);
    }

    private ArrayList<LTile> getAllStartPoints(){
        ArrayList<LTile> startPoints = new ArrayList<>();
        for (LTile tile: gameboard.getTiles()) {
            if (tile.isTileStartPoint()){
                startPoints.add(tile);
            }
        }
        return startPoints;
    }

    public boolean checkRobotposition(LRobot robot) {
        int pos_x = robot.getCords().x;
        int pos_y = robot.getCords().y;
        LTile tile = this.gameboard.getTileFromCoordinate(pos_x, pos_y);

        return !tile.doesTileHaveObstacle();

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
