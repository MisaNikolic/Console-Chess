package com.misa.chess.pieces;


import com.misa.chess.board.Board;
import com.misa.chess.board.Square;
import com.misa.chess.play.Move;


public class Piece{
    public static final String TEXT_RESET = "\u001B[0m";
    public static final String TEXT_BLUE = "\u001B[34m";
    public static final String TEXT_YELLOW = "\u001B[33m";
    private final String color;
    private final String name;

    public Piece(String color, String name) {
        this.color =color;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getColor() {
        return color;
    }

    @Override
    public String toString() {
        if(color.compareToIgnoreCase("black") ==0){
            if(name.compareToIgnoreCase("knight") ==0)
                return TEXT_BLUE + "N" + TEXT_RESET;
            else
                return TEXT_BLUE + name.toCharArray()[0] + TEXT_RESET;
        }
        else {
            if(name.compareToIgnoreCase("knight") ==0)
                return TEXT_YELLOW + "N" + TEXT_RESET;
            else
                return TEXT_YELLOW + name.toCharArray()[0] + TEXT_RESET;
        }
    }


    public boolean move(Square startingSquare, Square endingSquare, Board board, Move lastMove) {
        return true;
    }

    public boolean canMoveToASquare(Square startingSquare, Square endingSquare, Board board, Move lastMove) {
        return false;
    }

    }

