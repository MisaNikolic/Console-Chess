package com.misa.chess;

import com.misa.chess.board.Board;
import com.misa.chess.board.Square;
import com.misa.chess.pieces.Bishop;
import com.misa.chess.pieces.Knight;
import com.misa.chess.pieces.Queen;
import com.misa.chess.pieces.Rook;
import com.misa.chess.play.Game;

public class Main {

    public static final String TEXT_RESET = "\u001B[0m";
    public static final String TEXT_BLUE = "\u001B[34m";
    public static final String TEXT_YELLOW = "\u001B[33m";



    public static void main(String[] args) {
        Game game = new Game();
        game.play();
//        Board board = new Board();
//        board.setBoard();
//        board.getSquares()[1][1].setPiece(new Bishop("white"));
//        board.getSquares()[0][1].setPiece(new Rook("white"));
//        board.getSquares()[0][4].setPiece(new Knight("white"));
//
//        board.getSquares()[7][5].setPiece(new Knight("black"));
//        board.getSquares()[7][2].setPiece(new Rook("black"));
//        board.getSquares()[7][4].setPiece(new Bishop("black"));
//
//        Square[][] squares = board.getSquares();
//
//        board.showBoard();
//
//        for (int i = 7; i >=0; i--) {
//            for (int j = 0; j < 8; j++) {
//                if(board.isPieceOnSquare(i, j, new Rook("white"))) {
//                    System.out.print(TEXT_YELLOW + "[ R]  "  +TEXT_RESET);
//                    continue;
//                }
//                if(board.isPieceOnSquare(i, j, new Rook("black"))) {
//                    System.out.print(TEXT_BLUE + "[ R]  "  +TEXT_RESET);k
//                    continue;
//                }
//                if(board.isPieceOnSquare(i, j, new Bishop("white"))) {
//                    System.out.print(TEXT_YELLOW + "[ B]  " +TEXT_RESET);
//                    continue;
//                }
//                if(board.isPieceOnSquare(i, j, new Bishop("black"))) {
//                    System.out.print(TEXT_BLUE + "[ B]  " +TEXT_RESET);
//                    continue;
//                }
//                if(board.isPieceOnSquare(i, j, new Knight("white"))) {
//                    System.out.print(TEXT_YELLOW + "[ K]  " +TEXT_RESET);
//                    continue;
//                }
//                if(board.isPieceOnSquare(i, j, new Knight("black"))) {
//                    System.out.print(TEXT_BLUE + "[ K]  " +TEXT_RESET);
//                    continue;
//                }
//                if (board.isSquareInCheck(board.getSquares()[i][j], "black") && board.getSquares()[i][j] != null) {
//                    System.out.print(TEXT_BLUE + "[" + board.getSquares()[i][j].getName() + "]  " + TEXT_RESET);
//                }
//                else if (board.isSquareInCheck(board.getSquares()[i][j], "white") && board.getSquares()[i][j] != null) {
//                    System.out.print(TEXT_YELLOW + "[" + board.getSquares()[i][j].getName() + "]  " + TEXT_RESET);
//                }
//                else
//                    System.out.print("[" + board.getSquares()[i][j].getName() + "]  ");
//            }
//            System.out.println("\n\n");
//        }


//        System.out.println("\n\n");
//        if(board.isMyPieceOnASquare(1,1, "white"))
//            System.out.println("Correct");
//        else
//            System.out.println("Wrong");
//        if(board.isMyPieceOnASquare(2,2, "white"))
//            System.out.println("Correct");
//        else
//            System.out.println("Wrong");
//        if(board.isMyPieceOnASquare(1,1, "black"))
//            System.out.println("Correct");
//        else
//            System.out.println("Wrong");


    }

}
