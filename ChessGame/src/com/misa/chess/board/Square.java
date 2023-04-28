package com.misa.chess.board;


import com.misa.chess.pieces.*;

import java.util.Objects;

public class Square {
    public static final String TEXT_GREEN = "\u001B[32m";
    public static final String TEXT_RESET = "\u001B[0m";
    private final String name;
    private final Index index;
    private Piece piece = null;
    private boolean possibleUnPassant;

    public Square(String name, Index index) {
        this.name =name;
        this.index =index;
    }

    public String getName() {
        return name;
    }

    public Index getIndex() {
        return index;
    }

    public void setPiece(Piece piece) {
        this.piece =piece;
    }

    public Piece getPiece() {
        return piece;
    }

    public void setPossibleUnPassant(boolean possibleUnPassant) {
        this.possibleUnPassant = possibleUnPassant;
    }

    public boolean isPossibleUnPassant() {
        return possibleUnPassant;
    }

    @Override
    public String toString() {
        //            return "[" + name + "]";
        return "[" + Objects.requireNonNullElse(piece, " ") + "]";
    }

    public void moveToSquare(Square square) {
        if (this.getPiece() !=null) {
            square.setPiece(this.getPiece());
            this.piece =null;
            System.out.println(TEXT_GREEN + square.getPiece().getName() + " has moved from " + this.getName() +
                    " to " + square.getName() + TEXT_RESET);
        }
        else
            System.out.println("Error, target piece doesn't exist.");
    }




}