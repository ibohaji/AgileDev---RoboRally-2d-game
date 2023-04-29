Feature: GameBoard function

  Scenario: Successfully change a tile with an acid obstacle
    Given an initialized gameboard with difficulty set to easy
    And a tile with an acid obstacle at coordinate x=3 and y =3
    When a request to change the current tile with the acid obstacle tile
    Then the gameboard's tile at coordinate x=3 and y =3 should have an acid obstacle

  Scenario: be able to detect robots
    Given gameboard and an initiated game
    And a robot is standing at coordinate x=5 and y=2
    And a request to detect if the tile is occupied is called on x=5 and y =2
    Then the gameboard should return True

  Scenario: a robot steps on an explosive tile
    Given an initialized game
    And a robot at coordinate x=2 and y =2
    And an explosive obstacle at coordinate x=3 and y =2
    When the robot steps on the obstacle at the coordinate x=3 and y=2
    Then the obstacle explodes and infectes all the surrodning tiles