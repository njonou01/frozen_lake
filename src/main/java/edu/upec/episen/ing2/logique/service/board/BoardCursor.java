package edu.upec.episen.ing2.logique.service.board;

public record BoardCursor(Integer row, Integer col) {
  public Integer getIncreasingRow() {
    return row() + 1;
  }

  public Integer getIncreasingCol() {
    return col() + 1;
  }

  public Integer getDecreasingRow() {
    return row() - 1;
  }

  public Integer getDecreasingCol() {
    return col() - 1;
  }

  public Integer findDistanceWith(BoardCursor cursor) {
    return Math.abs(row() - cursor.row()) + Math.abs(col() - cursor.col());
  }

  public Integer findDistanceIncreaseRowWith(BoardCursor cursor) {
    return Math.abs((row() + 1) - cursor.row()) + Math.abs(col() - cursor.col());
  }

  public Integer findDistanceDecreaseRowWith(BoardCursor cursor) {
    return Math.abs((row() - 1) - cursor.row()) + Math.abs(col() - cursor.col());
  }

  public Integer findDistanceIncreaseColWith(BoardCursor cursor) {
    return Math.abs(row() - cursor.row()) + Math.abs((col() + 1) - cursor.col());
  }

  public Integer findDistanceDecreaseColWith(BoardCursor cursor) {
    return Math.abs(row() - cursor.row()) + Math.abs((col() - 1) - cursor.col());
  }

  @Override
  public String toString() {
    return String.format("BoardCursor(row=%d, col=%d)", row + 1, col + 1);
  }
}