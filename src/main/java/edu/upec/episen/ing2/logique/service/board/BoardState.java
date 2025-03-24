package edu.upec.episen.ing2.logique.service.board;

public enum BoardState {
  START("S"), GOAL("G"), FROZEN("F"), HOLE("H");

  private final String state;

  BoardState(String state) {
    this.state = state;
  }

  public String getState() {
    return state;
  }

  public static BoardState toBoadState(String value) {
    for (BoardState b : values()) {
      if (b.state.equals(value)) {
        return b;
      }
    }
    throw new IllegalArgumentException("Invalid board state: " + value);
  }
}