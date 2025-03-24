package edu.upec.episen.ing2.logique.service;

public enum GameStatus {
  WIN("Gagné"), LOST("Perdu"), ONGOING("En Cours");

  private String status;

  private GameStatus(String status) {
    this.status = status;
  }

  @Override
  public String toString() {
    return this.status;
  }
}
