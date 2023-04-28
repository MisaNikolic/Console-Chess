package com.misa.chess.pieces;


import com.misa.chess.board.Board;
import com.misa.chess.board.Square;
import com.misa.chess.play.Move;

import java.util.Scanner;

public class Pawn extends Piece {


    public Pawn(String color) {
        super(color, "Pawn");
    }

    @Override
    public String toString() {
        return super.toString();
    }


    @Override
    public boolean move(Square staringSquare, Square endingSquare, Board board, Move lastMove) {
        int i1 = staringSquare.getIndex().getX(), i2 = endingSquare.getIndex().getX();
        int j1 = staringSquare.getIndex().getY(), j2 = endingSquare.getIndex().getY();
        int direction = this.getColor().equalsIgnoreCase("white") ? 1 : -1;
        Square[][] squares =board.getSquares();

        if (j1 ==j2 && endingSquare.getPiece() ==null) {    // the endingSquare is empty and it is on the same file as the startingSquare
// moving pawn two squares up(down)
            if ((i1 ==1 && direction >0 && i2 ==3) || (i1 ==6 && direction< 0 && i2 ==4)
                    && (squares[i1 + direction][j1].getPiece() ==null)) {   // pawn is on the starting square and the next two squares are empty
                staringSquare.moveToSquare(endingSquare);
                return true;
            }
// moving pawn one square up(down)
            if (i2 - i1 == direction) { // move one square
                if (i2 != 0 && i2 != 7) { // move one square and promote
                    staringSquare.moveToSquare(endingSquare);
                    return true;
                } else {
                    Piece promotingPiece;
                    System.out.println("Promote into: B-bishop  R-rook  N-knight  any-queen");
                    Scanner scanner = new Scanner(System.in);
                    String option = scanner.nextLine();
                    switch (option) {
                        case "B":
                            System.out.println("Promoting pawn into a Bishop");
                            promotingPiece = direction > 0 ? new Bishop("white") : new Bishop("black");
                            break;
                        case "R":
                            System.out.println("Promoting pawn into a Rook");
                            promotingPiece = direction > 0 ? new Rook("white") : new Rook("black");
                            break;
                        case "N":
                            System.out.println("Promoting pawn into a Knight");
                            promotingPiece = direction > 0 ? new Knight("white") : new Knight("black");
                            break;
                        default:
                            System.out.println("Promoting pawn into a Queen");
                            promotingPiece = direction > 0 ? new Queen("white") : new Queen("black");
                            break;
                    }
                    staringSquare.setPiece(null);
                    endingSquare.setPiece(promotingPiece);
                    return true;
                }
            }
        }

        if(i2- i1==direction && Math.abs(j2 -j1) ==1 && endingSquare.getPiece() !=null
                && board.isEndingSquareValid(staringSquare, endingSquare)) {
            //  endingSquare is one square diagonally from the startingSquare, in the right direction
            if (i2 != 0 && i2 != 7) { // move one square and "eat" the opponent's piece
                staringSquare.moveToSquare(endingSquare);
                return true;
            } else {    // eat and promote
                Piece promotingPiece;
                System.out.println("Promote into: B-bishop  R-rook  N-knight  any-queen");
                Scanner scanner = new Scanner(System.in);
                String option = scanner.nextLine();
                switch (option) {
                    case "B":
                        System.out.println("Promoting pawn into a Bishop");
                        promotingPiece = direction > 0 ? new Bishop("white") : new Bishop("black");
                        break;
                    case "R":
                        System.out.println("Promoting pawn into a Rook");
                        promotingPiece = direction > 0 ? new Rook("white") : new Rook("black");
                        break;
                    case "N":
                        System.out.println("Promoting pawn into a Knight");
                        promotingPiece = direction > 0 ? new Knight("white") : new Knight("black");
                        break;
                    default:
                        System.out.println("Promoting pawn into a Queen");
                        promotingPiece = direction > 0 ? new Queen("white") : new Queen("black");
                        break;
                }
                staringSquare.setPiece(null);
                endingSquare.setPiece(promotingPiece);
                return true;
            }
        }
// lastMove.getPiece().getName().equals("Pawn")
        if(i2 - i1 ==direction && Math.abs(j2 - j1) ==1 && lastMove.getPiece().getName().equals("Pawn")) {
            int x_lastMStartingS = lastMove.getStaringSquare().getIndex().getX();
            int y_lastMStartingS = lastMove.getStaringSquare().getIndex().getY();
            int x_lastMEndingS = lastMove.getEndingSquare().getIndex().getX();
            int y_lastMEndingS = lastMove.getEndingSquare().getIndex().getY();

            if( (x_lastMStartingS == i1 + 2*direction && j2 == y_lastMStartingS) && (x_lastMEndingS == i1 && y_lastMEndingS == j2)) {
                System.out.println("Un passant!");
                staringSquare.moveToSquare(endingSquare);
                squares[x_lastMEndingS][y_lastMEndingS].setPiece(null);
                return true;
            }

        }

        System.out.println("Pawns don't move that way");
        return false;
    }



    @Override
    public boolean canMoveToASquare(Square staringSquare, Square endingSquare, Board board, Move lastMove) {
        int i1 = staringSquare.getIndex().getX(), i2 = endingSquare.getIndex().getX();
        int j1 = staringSquare.getIndex().getY(), j2 = endingSquare.getIndex().getY();
        int direction = this.getColor().equalsIgnoreCase("white") ? 1 : -1;
        Square[][] squares =board.getSquares();

        if (j1 ==j2 && endingSquare.getPiece() ==null) {    // the endingSquare is empty and it is on the same file as the startingSquare
// moving pawn two squares up(down)
            if ((i1 ==1 && direction >0 && i2 ==3) || (i1 ==6 && direction< 0 && i2 ==4)
                    && (squares[i1 + direction][j1].getPiece() ==null)) {   // pawn is on the starting square and the next two squares are empty
                return true;
            }
// moving pawn one square up(down)
            if (i2 - i1 == direction) { // move one square
                if (i2 != 0 && i2 != 7) { // move one square and promote
                    return true;
                }
            }
        }

        if(i2- i1==direction && Math.abs(j2 -j1) ==1 && endingSquare.getPiece() !=null
                && board.isEndingSquareValid(staringSquare, endingSquare)) {
            //  endingSquare is one square diagonally from the startingSquare, in the right direction
            if (i2 != 0 && i2 != 7) { // move one square and "eat" the opponent's piece
                staringSquare.moveToSquare(endingSquare);
                return true;
            } else {    // eat and promote
                Piece promotingPiece = null;
                System.out.println("Promote into: B-bishop  R-rook  N-knight  any-queen");
                Scanner scanner = new Scanner(System.in);
                String option = scanner.nextLine();

                staringSquare.setPiece(null);
                endingSquare.setPiece(promotingPiece);
                return true;
            }
        }
// lastMove.getPiece().getName().equals("Pawn")
        if(i2 - i1 ==direction && Math.abs(j2 - j1) ==1 && lastMove.getPiece().getName().equals("Pawn")) {
            int x_lastMStartingS = lastMove.getStaringSquare().getIndex().getX();
            int y_lastMStartingS = lastMove.getStaringSquare().getIndex().getY();
            int x_lastMEndingS = lastMove.getEndingSquare().getIndex().getX();
            int y_lastMEndingS = lastMove.getEndingSquare().getIndex().getY();

            if( (x_lastMStartingS == i1 + 2*direction && j2 == y_lastMStartingS) && (x_lastMEndingS == i1 && y_lastMEndingS == j2)) {
                System.out.println("Un passant!");
                staringSquare.moveToSquare(endingSquare);
                squares[x_lastMEndingS][y_lastMEndingS].setPiece(null);
                return true;
            }

        }

        return false;
    }
}