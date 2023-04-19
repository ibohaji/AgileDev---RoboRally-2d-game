//package CucumberTests;
//
//import App.RoborallyApplication.Model.*;
//import io.cucumber.java.en.And;
//import io.cucumber.java.en.Given;
//import io.cucumber.java.en.Then;
//import io.cucumber.java.en.When;
//import io.cucumber.java.ParameterType;
//import org.junit.Before;
//
//import java.awt.Point;
//import java.util.ArrayList;
//
//import static org.junit.Assert.*;
//
//
//public class stepdef_Robot {
//    LCardMovementProgramming movementProgramming;
//    LCardChangeDirectionProgramming changeDirectionProgramming;
//    LCardAgainProgramming againProgramming;
//    LGameBrain brain;
//    EnumDirection direction;
//    LRobot robot;
//    Point startCords;
//    LRobot robotOne;
//    LRobot robotTwo;
//
//    @Before
//    public void setup(){
//        brain = new LGameBrain(1, EnumDifficulty.EASY);
//        robot = brain.getPlayers().get(0).getRobot();
//        startCords = robot.getCords(); // Currently the only starting position available is (2,0)
//        robot.setDirection(EnumDirection.NORTH);
//    }
//
//    @ParameterType("NORTH|SOUTH|WEST|EAST")
//    public EnumDirection direction(String value) {
//        return EnumDirection.valueOf(value);
//    }
//
//    @Given("the robot's initial direction as NORTH")
//    public void the_robot_s_initial_direction_as_north() {
//        setup();
//    }
//
//    @When("the robot get the LEFT direction card")
//    public void the_robot_get_the_left_direction_card() {
//        changeDirectionProgramming = new LCardChangeDirectionProgramming(EnumTurnType.LEFT);
//        changeDirectionProgramming.useCard(robot,brain);
//    }
//    @Then("the expected direction get updated to {direction}")
//    public void the_expected_direction_get_updated_to(EnumDirection direction) {
//        assertEquals(direction, robot.getCurrentDirection());
//    }
//
//    @Given("a robot placed at position 2,2 and direction NORTH")
//    public void a_robot_placed_at_position_and_direction_north() {
//        setup();
//        robot.setCords(new Point(2,2));
//    }
//
//    @When("the robot receives the MovementCard with \\{int} steps")
//    public void the_robot_receives_the_movement_card_with_steps(Integer int1){
//        movementProgramming = new LCardMovementProgramming(int1);
//        movementProgramming.useCard(robot,brain);
//    }
//
//    @Then("the robot should move the specific point 2,5 ")
//    public void the_robot_should_move_the_specific_number_of_steps_in_its_current_direction() {
//        assertEquals(new Point(2,5),robot.getCords());
//    }
//
//
//    @Given("the robot has previously moved a certain number of steps in a certain direction")
//    public void the_robot_has_previously_moved_a_certain_number_of_steps_in_a_certain_direction() {
//        setup();
//        movementProgramming = new LCardMovementProgramming(1);
//        movementProgramming.useCard(robot,brain);
//
//    }
//
//
//    @And("an AGAIN card is played")
//    public void an_again_card_is_played() {
//        AbCardProgramming againCard = new LCardAgainProgramming();
//        againCard.useCard(robot,brain);
//    }
//
//    //This should only re-play the last played cards and not ALL THE STEPS taken.
//    @Then("the robot should move the same number of steps in the same direction as its previous movement")
//    public void the_robot_should_move_the_same_number_of_steps_in_the_same_direction_as_its_previous_movement() {
//        assertEquals(new Point(2,2),robot.getCords());
//    }
//
//    @Given("the game board is set up with robots at positions \\({int}, {int}) and \\({int}, {int})")
//    public void the_game_board_is_set_up_with_robots_at_positions_and(Integer int1, Integer int2, Integer int3, Integer int4) {
//        brain = new LGameBrain(2, EnumDifficulty.EASY);
//        robotOne = brain.getPlayers().get(0).getRobot();
//        robotTwo = brain.getPlayers().get(1).getRobot();
//        robotTwo.setDirection(EnumDirection.SOUTH);
//        robotOne.setCords(new Point(int1,int2));
//        robotTwo.setCords(new Point(int3,int4));
//
//    }
//
//    @And("the first robot is facing NORTH")
//    public void the_first_robot_is_facing_north() {
//        robotOne.setDirection(EnumDirection.NORTH);
//
//    }
//    @And("the first robot uses a movement card with {int} steps")
//    public void the_first_robot_uses_a_movement_card_with_steps(Integer int1) {
//        movementProgramming = new LCardMovementProgramming(int1);
//        movementProgramming.useCard(robotOne,brain);
//
//    }
//    @Then("the second robot should be pushed one tile in the direction of the first robot")
//    public void the_second_robot_should_be_pushed_one_tile_in_the_direction_of_the_first_robot() {
//        Point expected = new Point(2,1);
//        assertEquals(expected,robotTwo.getCords());
//    }
//    @Then("the first robot should end up in the tile previously occupied by the second robot")
//    public void the_first_robot_should_end_up_in_the_tile_previously_occupied_by_the_second_robot() {
//        Point expected = new Point(2,3);
//        assertEquals(expected,robotOne.getCords());
//    }
//
//    @Given("a game board with difficulty Easy")
//    public void a_game_board_with_difficulty_easy() {
//        setup();
//        robotOne = brain.getPlayers().get(0).getRobot();
//    }
//    @And("Robot1 at position x={int},y={int}")
//    public void robot1_at_position_x_y(Integer int1, Integer int2) {
//    robot.setCords(new Point(int1,int2));
//    }
//    @Given("Robot1 is facing EAST")
//    public void robot1_is_facing_east() {
//        robot.setDirection(EnumDirection.EAST);
//    }
//    @When("Robot1 moves forward one step")
//    public void robot1_moves_forward_one_step() {
//        movementProgramming = new LCardMovementProgramming(1);
//        movementProgramming.useCard(robot,brain);
//    }
//    @Then("Robot1 should fall off the board")
//    public void robot1_should_fall_off_the_board() {
//
//    }
//    @Then("Robot1 should lose a life")
//    public void robot1_should_lose_a_life() {
//        int lives = robot.getNrOfLives();
//        int expected = 4;
//        assertEquals(expected,lives);
//    }
//    @Then("Robot1 should be restored to a random position on the board")
//    public void robot1_should_be_restored_to_a_random_position_on_the_board() {
//        assertNotEquals(robot.getCords(),new Point(8,8));
//    }
//
//    @Then("if Robot1 has no lives left, Robot1 should be removed from the game")
//    public void if_robot1_has_no_lives_left_robot1_should_be_removed_from_the_game() {
//        robot.setNrOfLives(1);
//        robot.setCords(new Point(8,8));
//        robot.setDirection(EnumDirection.EAST);
//        movementProgramming = new LCardMovementProgramming(1);
//        movementProgramming.useCard(robot,brain);
//        LGameboard board = brain.getGameboard();
//        ArrayList<LRobot> robots = board.getRobots();
//        assertFalse(robots.contains(robot));
//
//    }
//
//}
//
