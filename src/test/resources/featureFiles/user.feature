Feature: User interaction

  Scenario: User chooses how many players will be in a game
    Given a user who wants to choose players
    When before a new game starts with specified players
    Then user choose the number of players

  Scenario: User chooses the difficulty level
    Given a user who wants to choose difficulty level
    When before a new game starts with specified difficulty level
    Then user choose the difficulty level

  Scenario: User create a single lobby
    Given a user who wants to create lobby
    When before a new game starts with specified lobby
    Then user create a single lobby

  Scenario: User create a new game where the other players are controlled by AI
    Given a user who wants to create new game
    When before a new game starts with AI
    Then user play a single_player game
