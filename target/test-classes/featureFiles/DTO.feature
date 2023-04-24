Feature: DTO

  Scenario: Object data transfer for Gameboard
    Given Data prepared in Gameboard
    When DTO for Gameboard is created
    Then Data exported out of Gameboard DTO

  Scenario: Object data transfer for GameBrain
    Given Data prepared in GameBrain
    When DTO for GameBrain is created
    Then Data exported out of GameBrain DTO

  Scenario: Object data transfer for Obstacle
    Given Data prepared in Obstacle
    When DTO for Obstacle is created
    Then Data exported out of Obstacle DTO

  Scenario: Object data transfer for Player
    Given Data prepared in Player
    When DTO for Player is created
    Then Data exported out of Player DTO

  Scenario: Object data transfer for ProgrammingCard
    Given Data prepared in ProgrammingCard
    When DTO for ProgrammingCard is created
    Then Data exported out of ProgrammingCard DTO

  Scenario: Object data transfer for Robot
    Given Data prepared in Robot
    When DTO for Robot is created
    Then Data exported out of Robot DTO

  Scenario: Object data transfer for Tile
    Given Data prepared in Tile
    When DTO for Tile is created
    Then Data exported out of Tile DTO
