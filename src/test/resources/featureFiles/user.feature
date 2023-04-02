Feature: User interaction

  Scenario: User chooses how many players will be in a game
    Given a user
    When before a new game starts
    Then user must be able to choose the number of players

  Scenario: User chooses the difficulty level
    Given a user
    When before a new game starts
    Then user must be able to choose the difficulty level

  Scenario: User create a single lobby
    Given a user
    When before a new game starts
    Then user must be able to create a single lobby

  Scenario: User create a new game where the other players are controlled by AI
    Given a user
    When before a new game starts
    Then user must be able to play a single-player game
