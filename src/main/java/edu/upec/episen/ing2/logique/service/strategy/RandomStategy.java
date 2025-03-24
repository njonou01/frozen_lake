package edu.upec.episen.ing2.logique.service.strategy;

import java.util.List;
import java.util.Random;

import edu.upec.episen.ing2.logique.service.board.Board;
import edu.upec.episen.ing2.logique.service.board.BoardAction;

public class RandomStategy implements Strategy {
  private static final Random RANDOM = new Random();

  @Override
  public BoardAction righAction(Board board) {
    List<BoardAction> possibleActions = board.getAvailableActions().stream().toList();
    return possibleActions.get(RANDOM.nextInt(possibleActions.size()));
  }
}