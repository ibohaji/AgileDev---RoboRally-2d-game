Feature: GameBrain

  Scenario: GameBrain shuffles and assigns cards
    Given a GameBrain and different types of cards
    When each round ends
    Then GameBrain must be able to shuffle and assign cards to players at the beginning of each round

  Scenario: GameBrain gets the current game phase
    Given a GameBrain
    When a game is running
    Then GameBrain must be able to know what the current game phase

  Scenario: GameBrain puts robots at their starting positions
    Given a GameBrain, a game board and robots
    When a new game starts
    Then GameBrain must be able to give robots their starting positions

  Scenario: GameBrain gets obstacles on the game board and their properties
    Given a GameBrain and game board filled with tiles
    When a new game board is generated
    Then GameBrain must be able to see which obstacles are on the game board and how they affect the robots

  Scenario: GameBrain gets the relative position of a robot and an obstacle
    Given a GameBrain, a robot and a tile on the game board
    When a robot makes movement on the game board
    Then GameBrain must be able to detect when a robot encounters an obstacle

  Scenario: GameBrain gets and sets the icon image of a tile
    Given a GameBrain, a tile and an icon image
    When an explosive tile affects nearby tiles
    Then GameBrain must be able to change graphics of a tile

  Scenario: GameBrain traces the status of a robot
    Given a GameBrain and a robot
    When a robot touches a checkpoint
    Then GameBrain must be able to see how many checkpoints a robot has reached

  Scenario: GameBrain detects if a robot has fallen out of the game board
    Given a GameBrain, a game board and a robot
    When a robot falls into a pit
    Then GameBrain must be able to remove a robot from the game

  Scenario: GameBrain detects if a player has been defeated
    Given a GameBrain and a player
    When a player's robot has no life left
    Then GameBrain must be able to remove a player from the game

  Scenario: GameBrain activate an explosive tile
    Given a GameBrain and a tile on the game board
    When a robot stands on an explosive tile
    Then GameBrain must be able to make a bomb obstacle explode
