Feature: Obstacle actions

  Scenario: Known Explosive obstacle explodes to surrounding tiles
    Given a tile with explosive known obstacle and default floor tiles surrounding it
    When robot steps on the explosive obstacle
    Then the surrounding tiles will get infected with that obstacle

  Scenario: Unknown explosive obstacle explodes to surrounding tiles
    Given a tile with explosive unknown obstacle and default floor tiles surrounding it
    When robot steps on the unknown explosive obstacle
    Then the surrounding tiles will get infected with a randomly chosen obstacle type

  Scenario: Treadmill moves robot
    Given a tile with a forward treadmill pointing NORTH obstacle on it
    When robot that has an initial direction of SOUTH steps on the treadmill
    Then the robot will get moved by the treadmill and the the robots direction will remain SOUTH

