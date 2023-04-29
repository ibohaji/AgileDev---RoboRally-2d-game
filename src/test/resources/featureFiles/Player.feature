Feature: Player

  Scenario: Player receives 5 cards in programming phase
    Given  a player waiting to receive programming cards
    When the programming phase starts
    Then the player receive 5 cards

  Scenario: Player reorder cards in programming phase
    Given a player has received 5 cards
    When the player reorders the cards
    Then the order of the cards change accordingly

  Scenario: Player watch the activation progress on the game board
    Given a player and its robot
    When during the activation phase
    Then the player see how many lives its robot currently has

  Scenario: Player use his Programming card for his robot
    Given A player has already reordered his cards
    When start activation phase
    Then the robot follow the card instruction

    Scenario: Check if the player is human
      Given one AI player and one human player
      When Check if the player is human
      Then The result should be one true and one false
