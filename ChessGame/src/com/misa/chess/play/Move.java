package com.misa.chess.play;


import com.misa.chess.board.Square;
import com.misa.chess.pieces.Piece;

public class Move {
    private Square staringSquare, endingSquare;
    private Piece piece;

    public Move(Square staringSquare, Square endingSquare, Piece piece) {
        this.staringSquare = staringSquare;
        this.endingSquare = endingSquare;
        this.piece = piece;
    }

    public Square getStaringSquare() {
        return staringSquare;
    }

    public void setStaringSquare(Square staringSquare) {
        this.staringSquare = staringSquare;
    }

    public Square getEndingSquare() {
        return endingSquare;
    }

    public void setEndingSquare(Square endingSquare) {
        this.endingSquare = endingSquare;
    }

    public Piece getPiece() {
        return piece;
    }

    public void setPiece(Piece piece) {
        this.piece = piece;
    }
}
