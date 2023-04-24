Feature: GameBoard function
  Scenario: be able to give GameBrain access to tiles
    Given The point (5,6) is an empty tile
    When GameBrain wants to know the status of this tile
    Then The GameBoard returns an empty tile

  Scenario: be able to give GameBrain access to obstacles
    Given There is an obstacle at point (3,4)
    When the GameBrain wants to know the status of this obstacle
    Then The GameBoard returns the information of this obstacle

  Scenario: be able to give GameBrain access to robots
    Given A robot is at point(2,2) standing on the GameBoard
    When GameBrain wants to move this robot
    Then The GameBoard returns the information of this robot

