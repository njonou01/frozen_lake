package edu.upec.episen.ing2.logique.service.strategy;

import edu.upec.episen.ing2.logique.service.board.Board;
import edu.upec.episen.ing2.logique.service.board.BoardAction;
import edu.upec.episen.ing2.logique.service.board.BoardCursor;

public class ManhattanStrategy implements Strategy {
  @Override
  public BoardAction righAction(Board board) {
    return board.getAvailableActions().stream()
        .min((a1, a2) -> computeDistance(a1, board.getCursor(), board.getGoalCursor())
            .compareTo(computeDistance(a2, board.getCursor(), board.getGoalCursor())))
        .orElseThrow(() -> new IllegalStateException("No available actions"));
  }

  private Integer computeDistance(BoardAction action, BoardCursor cursor, BoardCursor reference) {
    return switch (action) {
      case TOP -> cursor.findDistanceIncreaseRowWith(reference);
      case BOTTOM -> cursor.findDistanceDecreaseRowWith(reference);
      case RIGHT -> cursor.findDistanceIncreaseColWith(reference);
      case LEFT -> cursor.findDistanceDecreaseColWith(reference);
    };
  }
}