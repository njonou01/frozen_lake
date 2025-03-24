package edu.upec.episen.ing2.logique.service.strategy;

import edu.upec.episen.ing2.logique.service.board.Board;
import edu.upec.episen.ing2.logique.service.board.BoardAction;

public interface Strategy {
  public BoardAction righAction(Board board);
}
