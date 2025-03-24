package edu.upec.episen.ing2.logique.service.strategy;

import edu.upec.episen.ing2.logique.service.board.Board;
import edu.upec.episen.ing2.logique.service.board.BoardAction;
import edu.upec.episen.ing2.logique.service.board.BoardCursor;

public class ManhattanStrategy implements Strategy {
  @Override
  public BoardAction righAction(Board board) {

    BoardAction bestAction = BoardAction.BOTTOM;
    Integer min = Integer.MAX_VALUE;

    for (BoardAction action : board.getAvailableActions()) {

      Integer dist = computeDistance(action, board.getCursor(), board.getGoalCursor());

      if (dist < min) {
        min = dist;
        bestAction = action;
      }
    }

    return bestAction;
  }

  private Integer computeDistance(BoardAction action, BoardCursor cursor, BoardCursor reference) {
    switch (action) {
      case TOP:
        return cursor.findDistanceIncreaseRowWith(reference);
      case BOTTOM:
        return cursor.findDistanceDecreaseRowWith(reference);
      case RIGHT:
        return cursor.findDistanceIncreaseColWith(reference);
      case LEFT:
        return cursor.findDistanceDecreaseColWith(reference);
    }
    throw new IllegalArgumentException("Impossible d'avoir ca comme valeur");
  }

}