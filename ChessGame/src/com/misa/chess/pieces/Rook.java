package com.misa.chess.pieces;


import com.misa.chess.board.Board;
import com.misa.chess.board.Square;
import com.misa.chess.play.Move;

public class Rook extends Piece{
    private boolean hasMoved =false;

    public Rook(String color) {
        super(color, "Rook");
    }

    @Override
    public String toString() {
        return super.toString();
    }

    public boolean HasMoved() {
        return hasMoved;
    }

    public boolean move(Square startingSquare, Square endingSquare, Board board, Move lastMove) {
        int i1 =startingSquare.getIndex().getX();
        int j1 =startingSquare.getIndex().getY();
        int i2 =endingSquare.getIndex().getX();
        int j2 =endingSquare.getIndex().getY();

        if((i2 >i1 && j2 ==j1) || (i2 <i1 && j2 ==j1) || (i2 ==i1 && j2 >j1) || (i2 ==i1 && j2 <j1)) {
// also possible: sSquare == eSquare i i1==i2 ili j1 ==j2
            if(board.isEndingSquareValid(startingSquare, endingSquare)
                    && squaresBetweenAreEmpty(startingSquare, endingSquare, board) ) {
                startingSquare.moveToSquare(endingSquare);
                hasMoved =true;
                return true;
            } else {
                System.out.println(startingSquare.getPiece().getName() + " cannot move to " + endingSquare.getName());
                return false;
            }

        } else {
            System.out.println(startingSquare.getPiece().getName() + " don't move that way!");
            return false;
        }

    }

    private boolean squaresBetweenAreEmpty(Square startingSquare, Square endingSquare, Board board) {
        int i1 =startingSquare.getIndex().getX();
        int j1 =startingSquare.getIndex().getY();
        int i2 =endingSquare.getIndex().getX();
        int j2 =endingSquare.getIndex().getY();
        Square[][] squares =board.getSquares();

        if(j2 ==j1) {
            int direction =i2 >i1 ? 1 : -1;
            for(int k = i1 +direction; k !=i2; k +=direction) {
                if(squares[k][j1].getPiece() !=null) {
                    System.out.println("There is a piece in the way!");
                    return false;
                }
            }
        }

        if(i2 ==i1) {
            int direction =j2 >j1 ? 1 : -1;
            for(int k = j1 +direction; k !=j2; k +=direction) {
                if(squares[i1][k].getPiece() !=null) {
                    System.out.println("There is a piece in the way!");
                    return false;
                }
            }
        }
        return true;
    }


    @Override
    public boolean canMoveToASquare(Square startingSquare, Square endingSquare, Board board, Move lastMove) {
        int i1 =startingSquare.getIndex().getX();
        int j1 =startingSquare.getIndex().getY();
        int i2 =endingSquare.getIndex().getX();
        int j2 =endingSquare.getIndex().getY();

        if((i2 >i1 && j2 ==j1) || (i2 <i1 && j2 ==j1) || (i2 ==i1 && j2 >j1) || (i2 ==i1 && j2 <j1)) {
// also possible: sSquare == eSquare i i1==i2 ili j1 ==j2
            return board.isEndingSquareValid(startingSquare, endingSquare) && squaresBetweenAreEmpty(startingSquare, endingSquare, board) ;
    }

        return false;
    }


}
