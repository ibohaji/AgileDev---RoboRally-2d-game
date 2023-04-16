//package CucumberTests;
//
//import App.RoborallyApplication.Model.GameRunning.DifficultyEnum;
//import App.RoborallyApplication.Views.Menus.LobbyViewHost;
//import io.cucumber.java.en.Given;
//import io.cucumber.java.en.Then;
//import io.cucumber.java.en.When;
//import org.testng.Assert;
//
//public class stepdef_user {
//
//    /*
//        Test steps for user
//     */
//
//    // User chooses how many players will be in a game
//    @Given("a user who wants to choose players")
//    public void a_user_who_wants_to_choose_players() {
//        game = new Game();
//    }
//
//    @When("before a new game starts with specified players")
//    public void before_a_new_game_starts_with_specified_players() {
//        game.setNumPlayers(3);
//    }
//
//    @Then("user choose the number of players")
//    public void user_choose_the_number_of_players() {
//        Assert.assertEquals(3, game.setNumPlayers);
//    }
//
//    // User chooses the difficulty level
//    @Given("a user who wants to choose difficulty level")
//    public void a_user_who_wants_to_choose_difficulty_level() {
//        game = new Game();
//    }
//
//    @When("before a new game starts with specified difficulty level")
//    public void before_a_new_game_starts_with_specified_difficulty_level() {
//        game.setDifficulty(DifficultyEnum.MEDIUM);
//    }
//
//    @Then("user choose the difficulty level")
//    public void user_choose_the_difficulty_level() {
//        Assert.assertEquals(DifficultyEnum.MEDIUM, game.getDifficulty());
//    }
//
//    // User create a single lobby
//    @Given("a user who wants to create lobby")
//    public void a_user_who_wants_to_create_lobby() {
//        game = new Game();
//    }
//
//    @When("before a new game starts with specified lobby")
//    public void before_a_new_game_starts_with_specified_lobby() {
//        game.setLobbyType(LobbyType.SINGLE);
//    }
//
//    @Then("user create a single lobby")
//    public void user_create_a_single_lobby() {
//        Assert.assertEquals(LobbyType.SINGLE, game.getLobbyType());
//    }
//
//    // User create a new game where the other players are controlled by AI
//    @Given("a user who wants to create new game")
//    public void a_user_who_wants_to_create_new_game() {
//        game = new Game();
//    }
//
//    @When("before a new game starts with AI")
//    public void before_a_new_game_starts_with_AI() {
//        game.setNumAIPlayers(2);
//    }
//
//    @Then("user play a single_player game")
//    public void user_player_a_single_player_game() {
//        Assert.assertEquals(2, game.getNumAIPlayers());
//    }
//
//}
