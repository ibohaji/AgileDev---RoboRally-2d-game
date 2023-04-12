package App.RoborallyApplication.Model.GameRunning;

import App.DTO.GameBrainDTO;
import App.RoborallyApplication.Model.Cards.ProgrammingCards.AgainCard;
import App.RoborallyApplication.Model.Cards.ProgrammingCards.ChangeDirectionCard;
import App.RoborallyApplication.Model.Cards.ProgrammingCards.MovementCard;
import App.RoborallyApplication.Model.Cards.ProgrammingCards.ProgrammingCard;
import App.RoborallyApplication.Model.Enums.GamePhase;
import App.RoborallyApplication.Model.Enums.ObstacleEnum;
import App.RoborallyApplication.Model.Enums.ObstacleTypeEnum;
import App.RoborallyApplication.Model.Enums.TurnEnum;
import App.RoborallyApplication.Model.GameObjects.Obstacle;
import App.RoborallyApplication.Model.GameObjects.Player;
import App.RoborallyApplication.Model.GameObjects.Robot;
import App.RoborallyApplication.Model.GameObjects.Tile;
import App.RoborallyApplication.Model.iToDTO;
import Utils.JsonHelper;
import Utils.MapGenerator;
import Utils.Tuple;
import java.awt.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.UUID;

public class GameBrain implements iToDTO {
    private UUID id;
    private GameConfiguration gameConfig;
    private Gameboard gameboard = null;
    private ArrayList<Player> players;
    private GamePhase currentGamePhase;

    public GameBrain(){

    }
    public GameBrain(int nrOfPlayers, DifficultyEnum difficulty){
        this.id = UUID.randomUUID();
        gameConfig = new GameConfiguration(nrOfPlayers, difficulty);
        createGameboard();
        this.players = createPlayers();
        ArrayList<Robot> robots = createRobots(players);
        this.gameboard.setRobots(robots);
        setupRobots();
        startGame();

        // Testing
        players.get(1).getRobot().setCords(new Point(1, 4));
        players.get(0).getRobot().setCords(new Point(2, 2));
        players.get(1).getRobot().setDirection(DirectionEnum.EAST);
        players.get(0).getRobot().setDirection(DirectionEnum.WEST);
    }

    private void startGame(){
        setupRobots();
        currentGamePhase = GamePhase.ROUND_START;
        givePlayersCardsForRound();
    }

    private void setupRobots(){
        ArrayList<Tile> availableStartPoints = getAllStartPoints();
        for (int i = 0; i < this.players.size(); i++) {
            players.get(i).getRobot().setCords(availableStartPoints.get(i).getCoordinates());
        }
    }

    private void playRound(){
        //TODO
        currentGamePhase = GamePhase.ROUND_START;
        // 1st -> give players their cards for the round
        givePlayersCardsForRound();
        // 2nd -> let players choose the order of their cards
        currentGamePhase = GamePhase.PROGRAMMING_PHASE;
        // 3rd -> move the players, constantly checking for winner
        currentGamePhase = GamePhase.MOVEMENT_PHASE;
        /*ArrayList<Tuple<ProgrammingCard, Integer>> playerCardOrderingList = new ArrayList<>();
        Tuple<ProgrammingCard, Integer> playerChoiceFor1 = new Tuple<>(new MovementCard(1), 0);
        Tuple<ProgrammingCard, Integer> playerChoiceFor2 = new Tuple<>(new MovementCard(1), 1);
        Tuple<ProgrammingCard, Integer> playerChoiceFor3 = new Tuple<>(new MovementCard(1), 2);
        Tuple<ProgrammingCard, Integer> playerChoiceFor4 = new Tuple<>(new MovementCard(1), 3);
        Tuple<ProgrammingCard, Integer> playerChoiceFor5 = new Tuple<>(new MovementCard(1), 4);*/
    }

    protected void givePlayersCardsForRound(){
        Random rnd = new Random();
        for (Player player : players) {
            for (int i = 0; i < 5; i++) {
                int choiceForCard = rnd.nextInt(12);
                int choiceForSpecific = rnd.nextInt(3);
                if(choiceForCard < 2){ // againCard
                    player.assignCardToPlayer(new AgainCard());
                } else if (choiceForCard < 7) { // movementCard
                    if(choiceForSpecific == 0) {
                        player.assignCardToPlayer(new MovementCard(1));
                    } else if (choiceForSpecific == 1) {
                        player.assignCardToPlayer(new MovementCard(2));
                    } else {
                        player.assignCardToPlayer(new MovementCard(3));
                    }
                } else { // turn
                    if(choiceForSpecific == 0) { // LEFT
                        player.assignCardToPlayer(new ChangeDirectionCard(TurnEnum.LEFT));
                    } else if (choiceForSpecific == 1) { // RIGHT
                        player.assignCardToPlayer(new ChangeDirectionCard(TurnEnum.RIGHT));
                    } else { // U-TURN
                        player.assignCardToPlayer(new ChangeDirectionCard(TurnEnum.U_TURN));
                    }
                }
            }
        }
    }

    /**
     * @param player Player whose card ordering is being asked for
     * @return List containing tuples where first item is the ProgrammingCard and the second item is the number
     * in the ordering
     */
    private ArrayList<Tuple<ProgrammingCard, Integer>> getCardSequenceFromPlayer(Player player){
        ArrayList<Tuple<ProgrammingCard, Integer>> playerMoves = new ArrayList<>();


        return playerMoves;
    }

    private void makeMove(Player player, ProgrammingCard card){
        card.useCard(player.getRobot(), this);
    }

    public boolean isPositionOnBoard(Point point){
        return (point.x > -1 && point.x < gameConfig.getBoardDimensions().first() &&
                point.y > -1 && point.y < gameConfig.getBoardDimensions().second());
    }

    private void movePlayerWithCollision(DirectionEnum hitDirection){
        // if overboard then back to beginning
        // TODO
    }

    public GameConfiguration getGameConfig(){
        return this.gameConfig;
    }

    public void setGameConfig(GameConfiguration gameConfiguration){
        this.gameConfig = gameConfiguration;
    }

    private void createGameboard(){
        this.gameboard = MapGenerator.generateEasyMap(this);
    }

    private ArrayList<Player> createPlayers(){
        ArrayList<Player> players = new ArrayList<>();
        for (int i = 0; i < this.gameConfig.getNrOfPlayers(); i++) {
            players.add(new Player("player" + (Integer.toString(i + 1))));
        }
        return players;
    }
    private ArrayList<Robot> createRobots(ArrayList<Player> players){
        ArrayList<Robot> robots = new ArrayList<>();
        for (Player player: players) {
            Robot newRobot = new Robot();
            newRobot.setDirection(DirectionEnum.NORTH);
            player.assignRobot(newRobot);
            robots.add(newRobot);
        }
        return robots;
    }

    @Override
    public String DTOasJson() {
        GameBrainDTO gameBrainDTO = new GameBrainDTO(this.gameboard, this.gameConfig, this.currentGamePhase, this);
        return JsonHelper.serializeObjectToJson(gameBrainDTO);
    }

    @Override
    public UUID getID() {
        return this.id;
    }

    public Gameboard getGameboard(){
        return this.gameboard;
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public GamePhase getGamePhase(){
        return this.currentGamePhase;
    }

    public void restore(GameConfiguration gameConfig, ArrayList<Player> players,
                        GamePhase gamePhase, Gameboard gameboard, ArrayList<Robot> robots, ArrayList<Tile> tiles){
        this.gameConfig = gameConfig;
        this.gameboard = gameboard;
        this.currentGamePhase = gamePhase;
        this.players = players;
        gameboard.setRobots(robots);
        gameboard.setTiles(tiles);

    }

    public void setCurrentGamePhase(GamePhase phase){
        this.currentGamePhase = phase;
    }

    public ProgrammingCard getLastCardUsedByRobot(Robot robot){
        // TODO
        return new MovementCard(1);
    }

    public void pushRobot(Robot robotBeingPushed, DirectionEnum directionOfPushOrigin){
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
        } else {
            // check if pushes another robot
        }
    }

    protected void pushRobotOffBoard(Robot robot){
        int nrOfLives = robot.getNrOfLives();
        if(nrOfLives == 1){
            removeRobot(robot);
            removePlayer(findPlayerByRobot(robot));
        } else {
            putRobotToRandomStartPoint(robot);
            robot.setNrOfLives(nrOfLives - 1);
        }
    }

    public void putRobotToRandomStartPoint(Robot robot){
        // get all available start points (tiles with startpoint enum)
        // randomly choose one
    }

    public Obstacle chooseUnkownObstacle(Tile tile){
        // TODO
        Random rnd = new Random();
        float chance = rnd.nextFloat(1);
        if(chance < 0.6){ // acid
            return new Obstacle(ObstacleEnum.ACID, ObstacleTypeEnum.KNOWN_OBSTACLE);
        } else if (chance < 0.8) { // radiation
            return new Obstacle(ObstacleEnum.RADIATION, ObstacleTypeEnum.KNOWN_OBSTACLE);
        } else { // pit
            return new Obstacle(ObstacleEnum.PIT, ObstacleTypeEnum.KNOWN_OBSTACLE);
        }
    }

    public Player findPlayerByRobot(Robot robot){
        for (Player player: players) {
            if (player.getRobot().equals(robot)){
                return player;
            }
        }
        return null;
    }

    private void removePlayer(Player playerToRemove) {
        this.players.remove(playerToRemove);
    }

    private void removeRobot(Robot robot){
        this.gameboard.removeRobot(robot);
    }

    private ArrayList<Tile> getAllStartPoints(){
        ArrayList<Tile> startPoints = new ArrayList<>();
        for (Tile tile: gameboard.getTiles()) {
            if (tile.isTileStartPoint()){
                startPoints.add(tile);
            }
        }
        return startPoints;
    }

    private ArrayList<Tile> getAllFreeStartPoints(){
        ArrayList<Tile> allStartPoints = getAllStartPoints();
        ArrayList<Tile> availableStartPoints = new ArrayList<>();
        ArrayList<Robot> robots = gameboard.getRobots();
        for (Tile startpoint: allStartPoints) {
            boolean isTaken = false;
            for (Robot robot: robots) {
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


}
