package CucumberTests;

import App.RoborallyApplication.Model.*;
import App.RoborallyApplication.Model.LPlayer;
import App.RoborallyApplication.Model.LRobot;
import App.RoborallyApplication.Model.LTile;
import App.RoborallyApplication.Model.EnumDifficulty;
import App.RoborallyApplication.Model.LGameBrain;
import App.RoborallyApplication.Model.LGameboard;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import Utils.Tuple;
import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

import static org.junit.Assert.*;

public class stepdef_GameBrain {

    /*
        Test steps for GameBrain
     */
    private LGameBrain t_gamebrain;
    private LPlayer t_player;
    private LRobot t_robot;
    private LTile t_tile;

    private static int t_rndInt(int min, int max) {
        Random t_rnd = new Random();
        return t_rnd.nextInt((max - min) + 1) + min;
    }

    // GameBrain shuffles and assigns cards
    @Given("{int} players")
    public void create_players(Integer t_no_of_players) {
        LGameConfiguration t_gameconfiguration = new LGameConfiguration(t_no_of_players, EnumDifficulty.EASY, true);
        ArrayList<Tuple<String, Boolean>> t_playerInfo = new ArrayList<>();
        Tuple<String, Boolean> t_info;;
        for (int i = 0; i < t_no_of_players; i++) {
            t_info = new Tuple<>("player" + i, false);
            t_playerInfo.add(t_info);
        }
        t_gameconfiguration.createPlayersFromLobby(t_playerInfo);
        t_gamebrain = new LGameBrain(t_gameconfiguration);

    }

    @When("a round starts")
    public void before_a_round_starts() {
        t_gamebrain.setCurrentGamePhase(EnumGamePhase.ROUND_START);
    }

    @Then("GameBrain shuffle and assign cards to players")
    public void GameBrain_shuffle_and_assign_cards_to_players() {
        ArrayList<LPlayer> t_players = t_gamebrain.getPlayers();
        assertEquals(1, t_players.size());
        for (LPlayer t_currentplayer : t_players) {
            ArrayList<AbCardProgramming> t_playerscards = t_currentplayer.getProgrammingCards();
            assertEquals(5, t_playerscards.size());
        }

    }

    // GameBrain gets the current game phase
    @Given("a GameBrain and a game instance")
    public void a_GameBrain_and_a_game_instance() {
        t_gamebrain = new LGameBrain(new LGameConfiguration(1, EnumDifficulty.EASY, true));
    }

    @When("during {string}")
    public void a_game_is_running(String round) {
        t_gamebrain.setCurrentGamePhase(EnumGamePhase.valueOf(round));
    }

    @Then("GameBrain get the current game phase")
    public void GameBrain_get_the_current_game_phase() {
        assertEquals(EnumGamePhase.ROUND_START, t_gamebrain.getCurrentGamePhase());
    }

    // GameBrain puts robots at their starting positions
    @Given("a GameBrain a game board and robots")
    public void a_GameBrain_a_game_board_and_robots() {
        t_gamebrain = new LGameBrain(new LGameConfiguration(1, EnumDifficulty.EASY, true));
    }

    @When("a new game starts")
    public void a_new_game_starts() {

    }

    @Then("GameBrain give robots their starting positions")
    public void GameBrain_give_robots_their_starting_positions() {
        ArrayList<LPlayer> t_players = t_gamebrain.getPlayers();

        for (LPlayer t_player : t_players) {
            t_gamebrain.putRobotToRandomStartPoint(t_player.getRobot());
            assertTrue(
                    t_robot.getCords().equals(new Point(1, 7)) ||
                    t_robot.getCords().equals(new Point(3, 7)) ||
                    t_robot.getCords().equals(new Point(5, 7)) ||
                    t_robot.getCords().equals(new Point(6, 7))
            );
        }

    }

    // GameBrain gets obstacles on the game board and their properties
    @Given("a GameBrain and game board filled with tiles")
    public void a_GameBrain_and_game_board_filled_with_tiles() {
        int t_no_of_players = 1;
        LGameConfiguration t_gameconfiguration = new LGameConfiguration(t_no_of_players, EnumDifficulty.EASY, true);
        ArrayList<Tuple<String, Boolean>> t_playerInfo = new ArrayList<>();
        Tuple<String, Boolean> t_info;;
        for (int i = 0; i < t_no_of_players; i++) {
            t_info = new Tuple<>("player" + i, false);
            t_playerInfo.add(t_info);
        }
        t_gameconfiguration.createPlayersFromLobby(t_playerInfo);
        t_gamebrain = new LGameBrain(t_gameconfiguration);
    }

    @When("a new game board is generated")
    public void a_new_game_board_is_generated() {

    }

    @Then("GameBrain get obstacles and their properties")
    public void GameBrain_get_obstacles_and_their_properties() {
        LGameboard t_gameboard = t_gamebrain.getGameboard();

        int x = t_rndInt(0, t_gamebrain.getGameConfig().getBoardDimensions().first());
        int y = t_rndInt(0, t_gamebrain.getGameConfig().getBoardDimensions().second());

        AbObstacle t_obstacle = t_gamebrain.getObstacleFromCoordinateNEW(x, y);

        assertEquals(t_gameboard.getObstacleFromCoordinateNEW(x, y), t_obstacle);
    }

    // GameBrain gets the relative position of a robot and an obstacle
    @Given("a GameBrain a robot and a tile on the game board")
    public void a_GameBrain_a_robot_and_a_tile_on_the_game_board() {
        int t_no_of_players = 1;
        LGameConfiguration t_gameconfiguration = new LGameConfiguration(t_no_of_players, EnumDifficulty.EASY, true);
        ArrayList<Tuple<String, Boolean>> t_playerInfo = new ArrayList<>();
        Tuple<String, Boolean> t_info;;
        for (int i = 0; i < t_no_of_players; i++) {
            t_info = new Tuple<>("player" + i, false);
            t_playerInfo.add(t_info);
        }
        t_gameconfiguration.createPlayersFromLobby(t_playerInfo);
        t_gamebrain = new LGameBrain(t_gameconfiguration);

        ArrayList<LPlayer> t_players = t_gamebrain.getPlayers();
        t_player = t_players.get(t_rndInt(0, t_players.size()-1));
        t_robot = t_player.getRobot();

    }

    @When("a robot makes movement on the game board")
    public void a_robot_makes_movement_on_the_game_board() {
        t_robot.setCords(new Point(1, 1));
    }

    @Then("GameBrain detect if a robot encounters an obstacle")
    public void GameBrain_detect_if_a_robot_encounters_an_obstacle() {
        int t_pos_x = t_robot.getCords().x;
        int t_pos_y = t_robot.getCords().y;
        LTile t__tile = t_gamebrain.getGameboard().getTileFromCoordinate(t_pos_x, t_pos_y);
        assertEquals(t__tile.doesTileHaveObstacle(), t_robot.getCords().equals(t__tile.getCoordinates()));

        t_player = null;
        t_robot = null;
    }

    // GameBrain gets and sets the icon image of a tile
    @Given("a GameBrain with at least medium difficulty")
    public void a_GameBrain_with_at_least_medium_difficulty() {
        int t_no_of_players = 1;
        LGameConfiguration t_gameconfiguration = new LGameConfiguration(t_no_of_players, EnumDifficulty.MEDIUM, true);
        ArrayList<Tuple<String, Boolean>> t_playerInfo = new ArrayList<>();
        Tuple<String, Boolean> t_info;;
        for (int i = 0; i < t_no_of_players; i++) {
            t_info = new Tuple<>("player" + i, false);
            t_playerInfo.add(t_info);
        }
        t_gameconfiguration.createPlayersFromLobby(t_playerInfo);
        t_gamebrain = new LGameBrain(t_gameconfiguration);
    }

    @When("an explosive tile affects nearby tiles")
    public void an_explosive_tile_affects_nearby_tiles() {
        ArrayList<LTile> t_explosives = new ArrayList<>();
        t_explosives.add(t_gamebrain.getGameboard().getTileFromCoordinate(4, 9));

        t_tile = t_explosives.get(t_rndInt(0, t_explosives.size()-1));

        t_gamebrain.explodeObstacleToTilesNEW(
                t_gamebrain.getGameboard().getTilesSurroundingCoordinate(t_tile.getCoordinates().x, t_tile.getCoordinates().y),
                EnumObstacleType.ACID);

    }

    @Then("GameBrain change graphics of a tile")
    public void GameBrain_change_graphics_of_a_tile() {
        LTile t__tile = t_gamebrain.getGameboard().getTileFromCoordinate(4, 10);
        assertEquals(EnumImageGraphics.OBSTACLE_ACID.toString(), t__tile.getGraphicalElement().getElementName());


        t_tile = null;
        t_robot = null;
    }

    // GameBrain traces the status of a robot
    @Given("a GameBrain with at least medium difficulty_")
    public void a_GameBrain_with_at_least_medium_difficulty_() {
        int t_no_of_players = 1;
        LGameConfiguration t_gameconfiguration = new LGameConfiguration(t_no_of_players, EnumDifficulty.MEDIUM, true);
        ArrayList<Tuple<String, Boolean>> t_playerInfo = new ArrayList<>();
        Tuple<String, Boolean> t_info;;
        for (int i = 0; i < t_no_of_players; i++) {
            t_info = new Tuple<>("player" + i, false);
            t_playerInfo.add(t_info);
        }
        t_gameconfiguration.createPlayersFromLobby(t_playerInfo);
        t_gamebrain = new LGameBrain(t_gameconfiguration);
    }

    @When("a robot touches a checkpoint")
    public void a_robot_touches_a_checkpoint() {
        ArrayList<LPlayer> t_players = t_gamebrain.getPlayers();
        t_player = t_players.get(t_rndInt(0, t_players.size()-1));
        t_robot = t_player.getRobot();
        ArrayList<LTile> t__tiles = t_gamebrain.getGameboard().getTiles();
        ArrayList<LTile> t_checkpoints = new ArrayList<>();
        for (LTile t__tile : t__tiles) {
            if (t__tile.getTileTypeEnum().equals(EnumTileType.CHECKPOINT)) {
                t_checkpoints.add(t__tile);
            }
        }
        t_tile = t_checkpoints.get(t_rndInt(0, t_checkpoints.size()-1));
        t_robot.setCords(new Point(1, 3));
        t_robot.setDirection(EnumDirection.SOUTH);
        LCardSequence t_cards = new LCardSequence(t_player);
        t_cards.addCard(new LCardMovementProgramming(1));
        t_player.setOrderedCardSequence(t_cards);
        assertTrue(t_gamebrain.areThereMovementsLeftInThisRound());
        t_gamebrain.makeMovement();
    }

    @Then("GameBrain check how many checkpoints a robot has reached")
    public void GameBrain_check_how_many_checkpoints_a_robot_has_reached() {
        assertEquals(1, t_robot.getCords().x);
        assertEquals(4, t_robot.getCords().y);
        assertEquals(1, t_robot.getCheckpointsDone().size());
        assertTrue(t_robot.getCheckpointsDone().contains(t_tile.getCoordinates()));

        t_robot = null;
        t_player = null;
        t_tile = null;
    }

    // GameBrain detects if a robot has fallen out of the game board
    @Given("a GameBrain a game board and a robot")
    public void a_GameBrain_a_game_board_and_a_robot() {
        int t_no_of_players = 1;
        LGameConfiguration t_gameconfiguration = new LGameConfiguration(t_no_of_players, EnumDifficulty.EASY, true);
        ArrayList<Tuple<String, Boolean>> t_playerInfo = new ArrayList<>();
        Tuple<String, Boolean> t_info;;
        for (int i = 0; i < t_no_of_players; i++) {
            t_info = new Tuple<>("player" + i, false);
            t_playerInfo.add(t_info);
        }
        t_gameconfiguration.createPlayersFromLobby(t_playerInfo);
        t_gamebrain = new LGameBrain(t_gameconfiguration);
        ArrayList<LPlayer> t_players = t_gamebrain.getPlayers();
        t_player = t_players.get(t_rndInt(0, t_players.size()-1));
        t_robot = t_player.getRobot();
    }

    @When("a robot falls into a pit")
    public void a_robot_falls_into_a_pit() {
        t_robot.setCords(new Point(4, 2));
    }

    @Then("GameBrain remove a robot from the game")
    public void GameBrain_remove_a_robot_from_the_game() {
        t_gamebrain.removeRobot(t_robot);
        assertFalse(t_gamebrain.getGameboard().getRobots().contains(t_robot));

        t_player = null;
        t_robot = null;
    }

    // GameBrain detects if a player has been defeated
    @Given("a GameBrain and a player")
    public void a_GameBrain_and_a_player() {
        int t_no_of_players = 1;
        LGameConfiguration t_gameconfiguration = new LGameConfiguration(t_no_of_players, EnumDifficulty.EASY, true);
        ArrayList<Tuple<String, Boolean>> t_playerInfo = new ArrayList<>();
        Tuple<String, Boolean> t_info;;
        for (int i = 0; i < t_no_of_players; i++) {
            t_info = new Tuple<>("player" + i, false);
            t_playerInfo.add(t_info);
        }
        t_gameconfiguration.createPlayersFromLobby(t_playerInfo);
        t_gamebrain = new LGameBrain(t_gameconfiguration);
        ArrayList<LPlayer> t_players = t_gamebrain.getPlayers();
        t_player = t_players.get(t_rndInt(0, t_players.size()-1));
    }

    @When("a player's robot has no life left")
    public void a_player_s_robot_has_no_life_left() {
        t_player.getRobot().setNrOfLives(0);
    }

    @Then("GameBrain remove a player from the game")
    public void GameBrain_remove_a_player_from_the_game() {
        t_gamebrain.removePlayer(t_player);
        assertFalse(t_gamebrain.getPlayers().contains(t_player));

        t_player = null;
    }

    // GameBrain activate an explosive tile
    @Given("a GameBrain with at least medium difficulty__")
    public void a_GameBrain_with_at_least_medium_difficulty__() {
        int t_no_of_players = 1;
        LGameConfiguration t_gameconfiguration = new LGameConfiguration(t_no_of_players, EnumDifficulty.MEDIUM, true);
        ArrayList<Tuple<String, Boolean>> t_playerInfo = new ArrayList<>();
        Tuple<String, Boolean> t_info;;
        for (int i = 0; i < t_no_of_players; i++) {
            t_info = new Tuple<>("player" + i, false);
            t_playerInfo.add(t_info);
        }
        t_gameconfiguration.createPlayersFromLobby(t_playerInfo);
        t_gamebrain = new LGameBrain(t_gameconfiguration);
    }

    @When("a robot stands on an explosive tile")
    public void a_robot_stands_on_an_explosive_tile() {
        ArrayList<LPlayer> t_players = t_gamebrain.getPlayers();
        t_robot = t_players.get(t_rndInt(0, t_players.size()-1)).getRobot();
        t_robot.setCords(new Point(4, 9));
        t_gamebrain.explodeObstacleToTilesNEW(
                t_gamebrain.getGameboard().getTilesSurroundingCoordinate(t_robot.getCords().x, t_robot.getCords().y),
                EnumObstacleType.ACID);
    }

    @Then("GameBrain make a bomb obstacle explode")
    public void GameBrain_make_a_bomb_obstacle_explode() {
        assertEquals(EnumImageGraphics.OBSTACLE_ACID.toString(),
                t_gamebrain.getGameboard().getTileFromCoordinate(4, 10).getGraphicalElement().getElementName());
    }

    // GameBrain determine an unknown explosive tile
    @Given("a GameBrain with {string} difficulty")
    public void a_GameBrain_with_specified_difficulty(String string) {
        int t_no_of_players = 1;
        LGameConfiguration t_gameconfiguration = new LGameConfiguration(t_no_of_players, EnumDifficulty.HARD, true);
        ArrayList<Tuple<String, Boolean>> t_playerInfo = new ArrayList<>();
        Tuple<String, Boolean> t_info;;
        for (int i = 0; i < t_no_of_players; i++) {
            t_info = new Tuple<>("player" + i, false);
            t_playerInfo.add(t_info);
        }
        t_gameconfiguration.createPlayersFromLobby(t_playerInfo);
        t_gamebrain = new LGameBrain(t_gameconfiguration);
    }

    @When("a robot stands on an unknown explosive tile")
    public void a_robot_stands_on_an_unknown_explosive_tile() {
        ArrayList<LPlayer> t_players = t_gamebrain.getPlayers();
        t_robot = t_players.get(t_rndInt(0, t_players.size()-1)).getRobot();
        t_robot.setCords(new Point(2, 7));
        t_tile = t_gamebrain.getGameboard().getTileFromCoordinate(t_robot.getCords().x, t_robot.getCords().y);
        LObstacleRegular t_obstacle = (LObstacleRegular)t_tile.getNewObstacle();
        t_obstacle.applyEffect(t_robot, t_gamebrain);
    }

    @Then("GameBrain set the unknown explosive tile to a known one")
    public void GameBrain_set_the_unknown_explosive_tile_a_known_one() {
        assertEquals(
                EnumObstacleClassification.KNOWN_OBSTACLE,
                ((LObstacleRegular)(t_gamebrain.getGameboard().getTileFromCoordinate(t_robot.getCords().x, t_robot.getCords().y).getNewObstacle())).getObstacleClassification()
                );
        t_robot = null;
        t_tile = null;
    }




    //TODO
    // 1) get damage test from EnumObstacleType
    // 2) graphicalElement getImage test -> check that image not null
    // 3) againcCard class useCard method test all if cases
    // 4) CardChangeDirection test getTurnType correct
    // 5) CardMovement test pushIfOccupied method works
    // 6) CardMovement test getSteps and getStepsMade -> create new MovementCard and check if getStepsMade == 0 and getSteps != 0
    // 7) GameBrain
    //      7.1) Test makeMovement
    //      7.2) Test areThereMovementsLeft -> Start round, give players cards, setSequences and check if there are movements left
    //      7.3) Test endRound -> check gamePhase is ROUND_END
    //      7.4) Test canGameContinue ->
    //      7.5) Test all winner methods -> Set a random player to winner, see if isThereAWinner() and getPlayerWhoWon are correct
    //      7.6) Test haveAllPlayersSubmittedSequences()
    //      7.7) Test setCardSequencesForAi -> Create game with 1 real, 1 AI player, give them cards and call this method,
    //                                          AI should have a sequence after setCardSequenceForAi()
    //      7.8) Test getRandomObstacleTypeToExplode() -> do a for loop (maybe 10 loops) where you and check that you
    //                                          can get both ACID and RADIATION
    //      7.9) Test pushRobot
    //      7.10) Test moveRobotWithCard
}
