Feature: Programming Phase

  Scenario: Player receives 5 cards in programming phase
    Given  a player in the game
    When the programming phase starts
    Then the player should receive 5 cards

  Scenario: Player can reorder cards in programming phase
    Given a player in the programming phase and has received 5 cards
    When the player reorders the cards
    Then the order of the cards should change accordingly


