package edu.upec.episen.utils;

import edu.upec.episen.ing2.logique.service.board.BoardCursor;
import edu.upec.episen.ing2.logique.service.board.BoardState;

public class Utils {
    private static final String ANSI_RESET = "\u001B[0m";
    private static final String ANSI_RED_BOLD = "\u001B[1;31m";
    public static final String ANSI_GREEN_BOLD = "\033[1;32m";
    public static final String ANSI_HIGHLIGHT = "\033[93m";

    public static void printBoard(BoardState[][] board, BoardCursor cursor) {
        int size = board.length;

        for (int i = 0; i < size; i++) {
            printHorizontalBorder(size);
            printRow(board, cursor, i, size);
        }
        printHorizontalBorder(size);
    }

    private static void printHorizontalBorder(int size) {
        System.out.print("+");
        for (int j = 0; j < size; j++) {
            System.out.print("---+");
        }
        System.out.println();
    }

    private static void printRow(BoardState[][] board, BoardCursor cursor, int row, int size) {
        System.out.print("|");
        for (int j = 0; j < size; j++) {
            String cellState = board[row][j].getState();
            if (cursor.col() == j && cursor.row() == row) {
                String ANSI_STYLE = cellState == BoardState.GOAL.getState()
                        ? ANSI_GREEN_BOLD
                        : cellState == BoardState.HOLE.getState()
                                ? ANSI_RED_BOLD
                                : ANSI_HIGHLIGHT;

                cellState = ANSI_STYLE + cellState + ANSI_RESET;
            }
            System.out.printf(" %s |", cellState);
        }
        System.out.println();
    }

    public static void clearScreen() {
        for (int i = 0; i < 100; i++) {
            System.out.println();
        }
    }
}