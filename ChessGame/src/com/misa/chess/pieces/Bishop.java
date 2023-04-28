package com.misa.chess.pieces;


import com.misa.chess.board.Board;
import com.misa.chess.board.Index;
import com.misa.chess.board.Square;
import com.misa.chess.play.Move;

public class Bishop extends Piece {
    public static final String TEXT_RED = "\u001B[31m";
    public static final String TEXT_RESET = "\u001B[0m";

    public Bishop(String color) {
        super(color, "Bishop");
    }

    @Override
    public String toString() {
        return super.toString();
    }

    public boolean squaresBetweenAreEmpty(Square startingSquare, Square endingSquare, Board board) {
        int i1 = startingSquare.getIndex().getX(), j1 = startingSquare.getIndex().getY();
        int i2 = endingSquare.getIndex().getX(), j2 = endingSquare.getIndex().getY();
        Square[][] squares = board.getSquares();
        int k = 1;
        if (i2 > i1 && j2 > j1) {
            while (i1 + k < i2) {
                if (squares[i1 + k][j1 + k].getPiece() != null) // target square doesn't contains any piece
                    return false;
                else
                    k++;
            }
        } else if (i2 > i1 && j2 < j1) {
            while (i1 + k < i2) {
                if (squares[i1 + k][j1 - k].getPiece() != null) // target square doesn't contains any piece
                    return false;
                else
                    k++;
            }
        } else if (i2 < i1 && j2 > j1) {
            while (i1 - k > i2) {
                if (squares[i1 - k][j1 + k].getPiece() != null) // polje sa datim indexom ne sadrzi figure
                    return false;
                else
                    k++;
            }
        } else {
            while (i1 - k > i2) {
                if (squares[i1 - k][j1 - k].getPiece() != null) // polje sa datim indexom ne sadrzi figure
                    return false;
                else
                    k++;
            }
        }
        return true;
    }

    // metod za kretanje lovca, provera da lie moguce kretanje izmedju dva polja(arg. metode)
    @Override
    public boolean move(Square startingSquare, Square endingSquare, Board board, Move lastMove) {  // Pretp. da su polja validna
        Index indexOfStartingSquare = startingSquare.getIndex();
        Index indexOfEndingSquare = endingSquare.getIndex();
        System.out.println("Trying to move " + startingSquare.getPiece().getName() + "from " + startingSquare.getName()
                + " to " + endingSquare.getName());

        if (Math.abs(indexOfStartingSquare.getX() - indexOfEndingSquare.getX()) ==
                Math.abs(indexOfStartingSquare.getY() - indexOfEndingSquare.getY())) {

            if (!board.isEndingSquareValid(startingSquare, endingSquare)) { // square doesn't contain enemy King or friendly piece
                System.out.println(TEXT_RED + startingSquare.getPiece() + " can't move to " + endingSquare.getName()
                        + ", there is a Friendly piece or the enemy King on that square!" + TEXT_RESET);
                return false;
            } else if (!squaresBetweenAreEmpty(startingSquare, endingSquare, board)) {
                System.out.println(TEXT_RED + startingSquare.getPiece() + " can't reach " + endingSquare.getName() +
                        ", there is a piece in the way!" + TEXT_RESET);
                return false;
            } else {
                startingSquare.moveToSquare(endingSquare);
                return true;
            }
        }
        System.out.println(TEXT_RED + startingSquare.getPiece().getName() + " doesn't move that way!"
                + TEXT_RESET);
        return false;
    }


    @Override
    public boolean canMoveToASquare(Square startingSquare, Square endingSquare, Board board, Move lastMove) {  // Pretp. da su polja validna
        Index indexOfStartingSquare = startingSquare.getIndex();
        Index indexOfEndingSquare = endingSquare.getIndex();

        if (Math.abs(indexOfStartingSquare.getX() - indexOfEndingSquare.getX()) ==
                Math.abs(indexOfStartingSquare.getY() - indexOfEndingSquare.getY())) {

            if (!board.isEndingSquareValid(startingSquare, endingSquare)) { // square doesn't contain enemy King or friendly piece
                return false;
            } else if (!squaresBetweenAreEmpty(startingSquare, endingSquare, board)) {

                return false;
            } else {
                return true;
            }
        }

        return false;
    }
}
