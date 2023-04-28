package com.misa.chess.pieces;


import com.misa.chess.board.Board;
import com.misa.chess.board.Square;
import com.misa.chess.play.Move;

public class King extends Piece {
    private Square square;
    private boolean hasMoved =false;

    public King(String color, Square square) {
        super(color, "King");
        this.square =square;
    }

    public Square getSquare() {
        return square;
    }

    public void setSquare(Square square) {
        this.square = square;
    }

    @Override
    public String toString() {
        return super.toString();
    }

    @Override
    public boolean move(Square startingSquare, Square endingSquare, Board board, Move lastMove) {
        int i1 =startingSquare.getIndex().getX(), j1 =startingSquare.getIndex().getY();
        int i2 =endingSquare.getIndex().getX(), j2 =endingSquare.getIndex().getY();
        Square[][] squares = board.getSquares();
        String opponentsColor = this.getColor().equals("white") ? "black" : "white";

//regular move
        if(board.isEndingSquareValid(startingSquare,endingSquare) && (Math.abs(i1 -i2) ==1 || Math.abs(j1 -j2) ==1)
                && !board.isSquareInCheck(endingSquare, opponentsColor)) {
            startingSquare.moveToSquare(endingSquare);
            square =endingSquare;
            hasMoved =true;
            return true;
        }
//castling
        if(i1 ==i2 && !hasMoved && endingSquare.getPiece().getClass().equals(Rook.class) && !((Rook)endingSquare.getPiece()).HasMoved()) {
// short castle
            if(j2 >j1 && squares[i1][j1 +1].getPiece() ==null && !board.isSquareInCheck(squares[i1][j1 +1], opponentsColor)
                    && squares[i1][j1 +2].getPiece() ==null && !board.isSquareInCheck(squares[i1][j1 +2], opponentsColor) ) {
                System.out.println("Castling short");
                squares[i1][j1 +2].setPiece(this);
                squares[i1][j1 +1].setPiece(endingSquare.getPiece());
                startingSquare.setPiece(null);
                endingSquare.setPiece(null);
                square =squares[i1][j1 +2];
                return true;
// long castle
            } else if(j2 <j1 && squares[i1][j1 -1].getPiece() ==null && !board.isSquareInCheck(squares[i1][j1 -1], opponentsColor)
                    && squares[i1][j1 -2].getPiece() ==null && !board.isSquareInCheck(squares[i1][j1 -2], opponentsColor)
                    && squares[i1][j1 -3].getPiece() ==null) {
                System.out.println("Castling long");
                squares[i1][j1 -2].setPiece(this);
                squares[i1][j1 -1].setPiece(endingSquare.getPiece());
                startingSquare.setPiece(null);
                endingSquare.setPiece(null);
                square =squares[i1][j1 -2];
                return true;
            }

        }
        System.out.println("Illegal move!");
        return false;
    }
}