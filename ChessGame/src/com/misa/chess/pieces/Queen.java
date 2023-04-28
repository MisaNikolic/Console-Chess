package com.misa.chess.pieces;


import com.misa.chess.board.Board;
import com.misa.chess.board.Square;
import com.misa.chess.play.Move;

public class Queen extends Piece {
//    private Square square;

    public Queen(String color) {
        super(color, "Q");
    }

    @Override
    public String toString() {
        return super.toString();
    }

    public boolean move(Square startingSquare, Square endingSquare, Board board, Move lastMove) {
        Rook rook =new Rook("any");
        Bishop bishop =new Bishop("any");
        Queen queen = (Queen) startingSquare.getPiece();
        if( board.isEndingSquareValid(startingSquare, endingSquare) &&
                (rook.move(startingSquare, endingSquare, board, lastMove) || bishop.move(startingSquare, endingSquare, board, lastMove)) ) {
            startingSquare.setPiece(queen);
            startingSquare.moveToSquare(endingSquare);
            System.out.println("Queen has moved from " + startingSquare.getName() +
                    " to " + endingSquare.getName());
            return true;
        }
        System.out.println("2nd error msg");
        return false;
    }

    @Override
    public boolean canMoveToASquare(Square startingSquare, Square endingSquare, Board board, Move lastMove) {
        Rook rook =new Rook("any");
        Bishop bishop =new Bishop("any");
        Queen queen = (Queen) startingSquare.getPiece();
        if( board.isEndingSquareValid(startingSquare, endingSquare) &&
                (rook.move(startingSquare, endingSquare, board, lastMove) || bishop.move(startingSquare, endingSquare, board, lastMove)) ) {
            startingSquare.setPiece(queen);
            startingSquare.moveToSquare(endingSquare);

            return true;
        }

        return false;
    }

}