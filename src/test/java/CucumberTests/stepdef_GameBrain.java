package CucumberTests;

import App.RoborallyApplication.Model.Cards.ProgrammingCards.ProgrammingCard;
import App.RoborallyApplication.Model.Enums.*;
import App.RoborallyApplication.Model.GameObjects.Obstacle;
import App.RoborallyApplication.Model.GameObjects.Player;
import App.RoborallyApplication.Model.GameObjects.Robot;
import App.RoborallyApplication.Model.GameObjects.Tile;
import App.RoborallyApplication.Model.GameRunning.DifficultyEnum;
import App.RoborallyApplication.Model.GameRunning.GameBrain;
import App.RoborallyApplication.Model.GameRunning.Gameboard;
import io.cucumber.java.Before;
import io.cucumber.java.ParameterType;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

import static org.junit.Assert.*;

public class stepdef_GameBrain {

    /*
        Test steps for GameBrain
     */
    private GameBrain t_gamebrain;
    private Player t_player;
    private Robot t_robot;
    private Tile t_tile;

    public static int t_rndInt(int min, int max) {
        Random t_rnd = new Random();
        int t_rndInt = t_rnd.nextInt((max - min) + 1) + min;
        return t_rndInt;
    }
/*
    @ParameterType("EASY|MEDIUM|HARD")
    public DifficultyEnum t_Difficulty(String value) {
        return DifficultyEnum.valueOf(value);
    }
*/
    @Before
    public void setup() {
        t_gamebrain = new GameBrain(1, DifficultyEnum.EASY);
    }


    // GameBrain shuffles and assigns cards
    @Given("{int} players")
    public void create_players(Integer t_no_of_players) {
        t_gamebrain = new GameBrain(t_no_of_players, DifficultyEnum.EASY);
    }

    @When("a round starts")
    public void before_a_round_starts() {
        t_gamebrain.setCurrentGamePhase(GamePhase.ROUND_START);
    }

    @Then("GameBrain shuffle and assign cards to players")
    public void GameBrain_shuffle_and_assign_cards_to_players() {
        t_gamebrain.startRound();
        ArrayList<Player> t_players = t_gamebrain.getPlayers();
        assertEquals(1, t_players.size());
        for (Player t_currentplayer : t_players) {
            ArrayList<ProgrammingCard> t_playerscards = t_currentplayer.getCards();
            assertEquals(5, t_playerscards.size());
        }

    }

    // GameBrain gets the current game phase
    @Given("a GameBrain and a game instance")
    public void a_GameBrain_and_a_game_instance() {

    }

    @When("during {string}")
    public void a_game_is_running(String round) {
        t_gamebrain.setCurrentGamePhase(GamePhase.valueOf(round));
    }

    @Then("GameBrain get the current game phase")
    public void GameBrain_get_the_current_game_phase() {
        assertEquals(GamePhase.ROUND_START, t_gamebrain.getCurrentGamePhase());
    }

    // GameBrain puts robots at their starting positions
    @Given("a GameBrain a game board and robots")
    public void a_GameBrain_a_game_board_and_robots() {

    }

    @When("a new game starts")
    public void a_new_game_starts() {

    }

    @Then("GameBrain give robots their starting positions")
    public void GameBrain_give_robots_their_starting_positions() {
        ArrayList<Player> t_players = t_gamebrain.getPlayers();

        for (Player t_player : t_players) {
            t_gamebrain.putRobotToRandomStartPoint(t_player.getRobot());
//            assertEquals(StartPointEnum.Point1.getCoordinates(), t_player.getRobot().getCords());
        }

    }

    // GameBrain gets obstacles on the game board and their properties
    @Given("a GameBrain and game board filled with tiles")
    public void a_GameBrain_and_game_board_filled_with_tiles() {

    }

    @When("a new game board is generated")
    public void a_new_game_board_is_generated() {

    }

    @Then("GameBrain get obstacles and their properties")
    public void GameBrain_get_obstacles_and_their_properties() {
        Gameboard t_gameboard = t_gamebrain.getGameboard();

        int x = t_rndInt(0, t_gamebrain.getGameConfig().getBoardDimensions().first());
        int y = t_rndInt(0, t_gamebrain.getGameConfig().getBoardDimensions().second());

        Obstacle t_obstacle = t_gamebrain.getObstaclefromboard(x, y);

        assertEquals(t_gameboard.getObstacleFromCoordinate(x, y), t_obstacle);
    }

    // GameBrain gets the relative position of a robot and an obstacle
    @Given("a GameBrain a robot and a tile on the game board")
    public void a_GameBrain_a_robot_and_a_tile_on_the_game_board() {
        ArrayList<Player> t_players = t_gamebrain.getPlayers();
        t_player = t_players.get(t_rndInt(0, t_players.size()-1));
        t_robot = t_player.getRobot();

    }

    @When("a robot makes movement on the game board")
    public void a_robot_makes_movement_on_the_game_board() {
        t_robot.setCords(new Point(
                t_rndInt(0, t_gamebrain.getGameConfig().getBoardDimensions().first()-1),
                t_rndInt(0, t_gamebrain.getGameConfig().getBoardDimensions().second()-1)
                )
        );
    }

    @Then("GameBrain detect if a robot encounters an obstacle")
    public void GameBrain_detect_if_a_robot_encounters_an_obstacle() {
        int t_pos_x = t_robot.getCords().x;
        int t_pos_y = t_robot.getCords().y;
        Tile t__tile = t_gamebrain.getGameboard().getTileFromCoordinate(t_pos_x, t_pos_y);
        assertEquals(!t__tile.doesTileHaveObstacle(), t_gamebrain.checkRobotposition(t_robot));

        t_player = null;
        t_robot = null;
    }

    // GameBrain gets and sets the icon image of a tile
    @Given("a GameBrain a tile and an icon image")
    public void a_GameBrain_a_tile_and_an_icon_image() {

    }

    @When("an explosive tile affects nearby tiles")
    public void an_explosive_tile_affects_nearby_tiles() {
        ArrayList<Tile> t_tiles = t_gamebrain.getGameboard().getTiles();
        int t_x = 0;
        int t_y = 0;
        for (Tile t__tile : t_tiles) {
            if (!t__tile.doesTileHaveObstacle()) {
                if (t__tile.getObstacle().getObstacleTypeEnum() == ObstacleTypeEnum.EXPLOSIVE_KNOWN) {
                    t_x = t__tile.getCoordinates().x;
                    t_y = t__tile.getCoordinates().y;
                    t_tile = t__tile;
                }
            }
        }

        ArrayList<Player> t_players = t_gamebrain.getPlayers();
        t_robot = t_players.get(t_rndInt(0, t_players.size()-1)).getRobot();
        t_robot.setCords(new Point(t_x, t_y));

        t_gamebrain.activateExplosive(t_tile);

    }

    @Then("GameBrain change graphics of a tile")
    public void GameBrain_change_graphics_of_a_tile() {
        ArrayList<Tile> t_tilepool = t_gamebrain.getGameboard().getTilesSurroundingCoordinate(
                t_tile.getCoordinates().x, t_tile.getCoordinates().y);
        for (Tile t__tile : t_tilepool) {
            assertEquals(GraphicalElementEnum.OBSTACLE_ACID.toString(), t__tile.getGraphicalElement().getElementName());
        }

        t_tile = null;
        t_robot = null;
    }

    // GameBrain traces the status of a robot
    @Given("a GameBrain and a robot")
    public void a_GameBrain_and_a_robot() {

    }

    @When("a robot touches a checkpoint")
    public void a_robot_touches_a_checkpoint() {

    }

    @Then("GameBrain check how many checkpoints a robot has reached")
    public void GameBrain_check_how_many_checkpoints_a_robot_has_reached() {


    }

    // GameBrain detects if a robot has fallen out of the game board
    @Given("a GameBrain a game board and a robot")
    public void a_GameBrain_a_game_board_and_a_robot() {
        ArrayList<Player> t_players = t_gamebrain.getPlayers();
        t_player = t_players.get(t_rndInt(0, t_players.size()-1));
        t_robot = t_player.getRobot();
    }

    @When("a robot falls into a pit")
    public void a_robot_falls_into_a_pit() {
        ArrayList<Tile> t_tiles = t_gamebrain.getGameboard().getTiles();
        int t_x = 0;
        int t_y = 0;
        for (Tile t__tile : t_tiles) {
            if (!t__tile.doesTileHaveObstacle()) {
                if (t__tile.getObstacle().getObstacleEnum() == ObstacleEnum.PIT) {
                    t_x = t__tile.getCoordinates().x;
                    t_y = t__tile.getCoordinates().y;
                }
            }
        }

        t_robot.setCords(new Point(t_x, t_y));
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
        ArrayList<Player> t_players = t_gamebrain.getPlayers();
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
    @Given("a GameBrain and a tile on the game board")
    public void a_GameBrain_and_a_tile_on_the_game_board() {

    }

    @When("a robot stands on an explosive tile")
    public void a_robot_stands_on_an_explosive_tile() {
        ArrayList<Tile> t_tiles = t_gamebrain.getGameboard().getTiles();
        int t_x = 0;
        int t_y = 0;
        for (Tile t__tile : t_tiles) {
            if (!t__tile.doesTileHaveObstacle()) {
                if (t__tile.getObstacle().getObstacleTypeEnum() == ObstacleTypeEnum.EXPLOSIVE_KNOWN) {
                    t_x = t__tile.getCoordinates().x;
                    t_y = t__tile.getCoordinates().y;
                    t_tile = t__tile;
                }
            }
        }

        ArrayList<Player> t_players = t_gamebrain.getPlayers();
        t_robot = t_players.get(t_rndInt(0, t_players.size()-1)).getRobot();
        t_robot.setCords(new Point(t_x, t_y));
    }

    @Then("GameBrain make a bomb obstacle explode")
    public void GameBrain_make_a_bomb_obstacle_explode() {
        t_gamebrain.activateExplosive(t_tile);

        ArrayList<Tile> t_tiles = t_gamebrain.getGameboard().getTilesSurroundingCoordinate(t_tile.getCoordinates().x, t_tile.getCoordinates().y);
        for (Tile t__tile : t_tiles) {
            assertEquals(ObstacleEnum.ACID, t__tile.getObstacle().getObstacleEnum());
        }

        t_robot = null;
        t_tile = null;
    }

}
