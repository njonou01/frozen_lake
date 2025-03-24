package edu.upec.episen.utils;

import edu.upec.episen.ing2.logique.service.board.BoardCursor;
import edu.upec.episen.ing2.logique.service.board.BoardState;

public class BoardPrintUtils {
    private static final String RESET = "\u001B[0m";
    private static final String ROUGE = "\u001B[31m";

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
                cellState = ROUGE + cellState + RESET;
            }
            System.out.printf(" %s |", cellState);
        }
        System.out.println();
    }
}