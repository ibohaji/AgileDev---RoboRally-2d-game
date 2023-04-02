Feature: Activation Phase
  Scenario: Robot can execute programming cards in order in activation phase
    Given a game board with a robot and 5 ordered cards
    When the activation phase starts
    Then the robot should execute the cards in the given order

    Scenario: Player watch the activation progress on the game board
      Given a player and its robot
      When during the activation phase
      Then the player must be able to see how many lives its robot has
