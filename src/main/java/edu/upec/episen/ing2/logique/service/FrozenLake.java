package edu.upec.episen.ing2.logique.service;

import java.util.Random;
import java.util.Set;

import edu.upec.episen.ing2.logique.service.board.Board;
import edu.upec.episen.ing2.logique.service.board.BoardAction;
import edu.upec.episen.ing2.logique.service.strategy.RandomStategy;
import edu.upec.episen.ing2.logique.service.strategy.Strategy;

public class FrozenLake {
  private static final String[][] DEFAULT_GRID = {
      { "S", "F", "F", "F", "F" },
      { "F", "F", "F", "H", "F" },
      { "F", "H", "F", "F", "F" },
      { "F", "F", "F", "H", "F" },
      { "F", "F", "F", "F", "G" }
  };

  private static final Random RANDOM = new Random();
  private static final double SLIP_PROBABILITY = 0.20;

  private Strategy strategy;
  private Board board;
  private boolean shouldPrintResult;

  public FrozenLake() throws Exception {
    this(true, new RandomStategy(), DEFAULT_GRID);
  }

  public FrozenLake(Strategy strategy) throws Exception {
    this(true, strategy, DEFAULT_GRID);
  }

  public FrozenLake(boolean shouldPrintResult) throws Exception {
    this(shouldPrintResult, new RandomStategy(), DEFAULT_GRID);
  }

  public FrozenLake(boolean shouldPrintResult, Strategy strategy) throws Exception {
    this(shouldPrintResult, strategy, DEFAULT_GRID);
  }

  public FrozenLake(Strategy strategy, String[][] board) throws Exception {
    this(true, strategy, board);
  }

  public FrozenLake(boolean shouldPrintResult, Strategy strategy, String[][] board) throws Exception {
    this.strategy = strategy;
    this.board = new Board(board);
    this.shouldPrintResult = shouldPrintResult;
  }

  public void setStrategy(Strategy strategy) {
    this.strategy = strategy;
  }

  public void move() {
    Set<BoardAction> availableActions = board.getAvailableActions();
    BoardAction bestAction = strategy.righAction(board);
    availableActions.remove(bestAction);

    BoardAction selectedAction = RANDOM.nextDouble() < SLIP_PROBABILITY
        ? availableActions.stream().toList().get(RANDOM.nextInt(availableActions.size()))
        : bestAction;

    board.go(selectedAction);

    if (shouldPrintResult) {
      System.out.println(board.getCursor());
      board.print();
    }
  }

  public boolean playFullGame() {
    while (board.canContinue()) {
      move();
    }
    return board.isCursorOnWin();
  }
}