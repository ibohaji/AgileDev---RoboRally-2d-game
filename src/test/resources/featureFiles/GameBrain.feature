Feature: GameBrain

  Scenario: GameBrain shuffles and assigns cards
    Given 1 players
    When a round starts
    Then GameBrain shuffle and assign cards to players

  Scenario: GameBrain gets the current game phase
    Given a GameBrain and a game instance
    When during "ROUND_START"
    Then GameBrain get the current game phase

  Scenario: GameBrain puts robots at their starting positions
    Given a GameBrain a game board and robots
    When a new game starts
    Then GameBrain give robots their starting positions

  Scenario: GameBrain gets obstacles on the game board and their properties
    Given a GameBrain and game board filled with tiles
    When a new game board is generated
    Then GameBrain get obstacles and their properties

  Scenario: GameBrain gets the relative position of a robot and an obstacle
    Given a GameBrain a robot and a tile on the game board
    When a robot makes movement on the game board
    Then GameBrain detect if a robot encounters an obstacle

  Scenario: GameBrain gets and sets the icon image of a tile
    Given a GameBrain a tile and an icon image
    When an explosive tile affects nearby tiles
    Then GameBrain change graphics of a tile

  Scenario: GameBrain traces the status of a robot
    Given a GameBrain and a robot
    When a robot touches a checkpoint
    Then GameBrain check how many checkpoints a robot has reached

  Scenario: GameBrain detects if a robot has fallen out of the game board
    Given a GameBrain a game board and a robot
    When a robot falls into a pit
    Then GameBrain remove a robot from the game

  Scenario: GameBrain detects if a player has been defeated
    Given a GameBrain and a player
    When a player's robot has no life left
    Then GameBrain remove a player from the game

  Scenario: GameBrain activate an explosive tile
    Given a GameBrain and a tile on the game board
    When a robot stands on an explosive tile
    Then GameBrain make a bomb obstacle explode
