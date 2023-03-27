package App.RoborallyApplication.Model.GameRunning;

import App.DTO.GameBrainDTO;
import App.RoborallyApplication.Model.Cards.ProgrammingCards.AgainCard;
import App.RoborallyApplication.Model.Cards.ProgrammingCards.ChangeDirectionCard;
import App.RoborallyApplication.Model.Cards.ProgrammingCards.MovementCard;
import App.RoborallyApplication.Model.Cards.ProgrammingCards.ProgrammingCard;
import App.RoborallyApplication.Model.Enums.GamePhase;
import App.RoborallyApplication.Model.Enums.TurnEnum;
import App.RoborallyApplication.Model.GameObjects.Player;
import App.RoborallyApplication.Model.GameObjects.Robot;
import App.RoborallyApplication.Model.GameObjects.Tile;
import App.RoborallyApplication.Model.iToDTO;
import Utils.JsonHelper;

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
        addRobotsToGameboard(robots);
        currentGamePhase = GamePhase.ROUND_START;
    }

    private void startGame(){
        // TODO
        // 1st -> assign robots their starting position
        // 2nd -> start playing the round
    }

    private void playRound(){
        //TODO
        // 1st -> give players their cards for the round
        // 2nd -> let players choose the order of their cards
        // 3rd -> make the moves (move robots, do damage, change tiles(if someone goes to explosive tile)
        givePlayersCardsForRound();
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
                int choiceForCard = rnd.nextInt(3);
                int choiceForSpecific = rnd.nextInt(3);
                if(choiceForCard == 0){ // againCard
                    player.assignCardToPlayer(new AgainCard());
                } else if (choiceForCard == 1) { // movementCard
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

    private void getProgrammingCardOrderFromPlayer(Player player){
        // TODO
        // 1st -> player chooses the ordering of the cards from the cards that he was given
    }

    private void makeMove(Player player, ProgrammingCard card){
        // TODO
        //card.useCard(player.getRobot(), this);
    }

    public boolean isPositionOnBoard(Point point){
        return (point.x > -1 && point.x < gameConfig.getBoardDimensions().first() &&
                point.y > -1 && point.y < gameConfig.getBoardDimensions().second());
    }

    private void movePlayerWithCollision(DirectionEnum hitDirection){
        // if overboard then back to beginning
        // TODO
    }

    private void getMovesFromPlayers(){
        // TODO
    }

    public GameConfiguration getGameConfig(){
        return this.gameConfig;
    }

    public void setGameConfig(GameConfiguration gameConfiguration){
        this.gameConfig = gameConfiguration;
    }

    private void createGameboard(){
        this.gameboard = new Gameboard(this);
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
            newRobot.SetDirection(DirectionEnum.NORTH);
            player.assignRobot(newRobot);
            robots.add(newRobot);
        }
        return robots;
    }

    private void addRobotsToGameboard(ArrayList<Robot> robots){
        this.gameboard.addRobots(robots);
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

    public Tile getTileFromCoordinate(Integer x, Integer y){
        for (Tile tile: gameboard.getTiles()) {
            if(tile.getCoordinates().first().equals(x) && tile.getCoordinates().second().equals(y)){
                return tile;
            }
        }
        return null;
    }

    public Robot getRobotFromCoordinate(Integer x, Integer y){
        for (Robot robot: gameboard.getRobots()) {
            if(robot.getCords().x == x && robot.getCords().y == y){
                return robot;
            }
        }
        return null;
    }

    public boolean isTileOccupiedByRobot(int xCoordinate, int yCoordinate){
        for (Robot robot:this.gameboard.getRobots()) {
            Point location = robot.getCords();
            if(location.x == xCoordinate && location.y == yCoordinate){
                return true;
            }
        }
        return false;
    }

    public void restore(GameConfiguration gameConfig, ArrayList<Player> players,
                        GamePhase gamePhase, Gameboard gameboard, ArrayList<Robot> robots, ArrayList<Tile> tiles){

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

    public void putRobotToRandomStartPoint(Robot robot){
        // get all available start points
        // randomly choose one
    }
}
