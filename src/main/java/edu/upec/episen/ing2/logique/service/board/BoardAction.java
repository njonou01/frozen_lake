package edu.upec.episen.ing2.logique.service.board;

public enum BoardAction {
  TOP, BOTTOM, LEFT, RIGHT;

  public BoardAction getOpposite() {
    switch (this) {
      case TOP:
        return BOTTOM;
      case BOTTOM:
        return TOP;
      case LEFT:
        return RIGHT;
      case RIGHT:
        return LEFT;
      default:
        throw new IllegalArgumentException("Invalid BoardAction: " + this);
    }
  }
}
