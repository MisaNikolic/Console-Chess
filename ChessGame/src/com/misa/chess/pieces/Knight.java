package com.misa.chess.pieces;


import com.misa.chess.board.Board;
import com.misa.chess.board.Square;
import com.misa.chess.play.Move;

public class Knight extends Piece {
    public static final String TEXT_RED = "\u001B[31m";
    public static final String TEXT_RESET = "\u001B[0m";

    public Knight(String color) {
        super(color, "Knight");
    }

    @Override
    public String toString() {
        return super.toString();
    }


    @Override
    public boolean move(Square startingSquare, Square endingSquare, Board board, Move lastMove) {
        if (!board.isEndingSquareValid(startingSquare, endingSquare)) {
            System.out.println(TEXT_RED + "Cannot jump on that piece!" + TEXT_RESET);
            return false;
        }
        int i1 = startingSquare.getIndex().getX(), j1 = startingSquare.getIndex().getY();
        int i2 = endingSquare.getIndex().getX(), j2 = endingSquare.getIndex().getY();

        if ((i2 == (i1 + 2) && j2 == (j1 + 1)) || (i2 == (i1 + 1) && j2 == (j1 + 2)) ||
                (i2 == (i1 + 2) && j2 == (j1 - 1)) || (i2 == (i1 + 1) && j2 == (j1 - 2)) ||
                (i2 == (i1 - 2) && j2 == (j1 + 1)) || (i2 == (i1 - 1) && j2 == (j1 + 2)) ||
                (i2 == (i1 - 2) && j2 == (j1 - 1)) || (i2 == (i1 - 1) && j2 == (j1 - 2))) {
            startingSquare.moveToSquare(endingSquare);
            return true;
        }
        else {
            System.out.println(startingSquare.getPiece().getName() + " don't move that way!");
            return false;
        }
    }

}
