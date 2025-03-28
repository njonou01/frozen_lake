package edu.upec.episen.ing2.logique.service.board;

import java.util.EnumSet;
import java.util.Set;

import edu.upec.episen.ing2.logique.service.GameStatus;
import edu.upec.episen.utils.Utils;

public class Board {
  private final int size;
  private BoardState[][] board;
  private BoardCursor cursor;
  private final BoardCursor goalCursor;
  private GameStatus gameStatus;

  public Board(String[][] table) {
    this.size = table.length;
    this.gameStatus = GameStatus.ONGOING;
    this.board = new BoardState[size][size];
    BoardCursor tempGoalCursor = null;
    for (int i = 0; i < size; i++) {
      for (int j = 0; j < size; j++) {
        board[i][j] = BoardState.toBoadState(table[i][j]);
        if (board[i][j] == BoardState.GOAL) {
          tempGoalCursor = new BoardCursor(i, j);
        }
      }
    }
    this.goalCursor = tempGoalCursor;
    this.cursor = new BoardCursor(0, 0);
  }

  public boolean canContinue() {
    return gameStatus == GameStatus.ONGOING;
  }

  public Boolean isCursorOnWin() {
    return gameStatus == GameStatus.WIN;
  }

  private void updateStatus() {
    BoardState currentState = getCurrentBoardState();
    if (currentState == BoardState.HOLE) {
      this.gameStatus = GameStatus.LOST;
    } else if (currentState == BoardState.GOAL) {
      this.gameStatus = GameStatus.WIN;
    }
  }

  private BoardCursor getNewPosition(BoardAction action) {
    int newRow = cursor.row();
    int newCol = cursor.col();

    switch (action) {
      case BOTTOM:
        newRow = Math.max(0, newRow - 1);
        break;
      case TOP:
        newRow = Math.min(size - 1, newRow + 1);
        break;
      case LEFT:
        newCol = Math.max(0, newCol - 1);
        break;
      case RIGHT:
        newCol = Math.min(size - 1, newCol + 1);
        break;
    }

    return new BoardCursor(newRow, newCol);
  }

  public void go(BoardAction action) {
    if (!canContinue())
      return;

    cursor = getNewPosition(action);
    updateStatus();
  }

  public Set<BoardAction> getAvailableActions() {
    Set<BoardAction> actions = EnumSet.noneOf(BoardAction.class);
    int row = cursor.row();
    int col = cursor.col();

    if (col > 0)
      actions.add(BoardAction.LEFT);
    if (col < size - 1)
      actions.add(BoardAction.RIGHT);
    if (row > 0)
      actions.add(BoardAction.BOTTOM);
    if (row < size - 1)
      actions.add(BoardAction.TOP);

    return actions;
  }

  public void print() {
    Utils.printBoard(board, cursor);
  }

  public BoardState getCurrentBoardState() {
    return board[cursor.row()][cursor.col()];
  }

  public BoardCursor getCursor() {
    return cursor;
  }

  public BoardCursor getGoalCursor() {
    return goalCursor;
  }

  public GameStatus getGameStatus() {
    return gameStatus;
  }

  public int getSize() {
    return size;
  }

  public BoardState[][] getBoard() {
    return board;
  }

  public boolean isHoleAction(BoardAction action) {
    BoardCursor newPosition = getNewPosition(action);
    return board[newPosition.row()][newPosition.col()] == BoardState.HOLE;
  }
}