package com.misa.chess.play;


import com.misa.chess.board.Board;
import com.misa.chess.board.Square;
import com.misa.chess.pieces.King;
import java.util.Scanner;

public class Game {
    private final Board board;


    public Game() {
        board =new Board();
        board.setBoard();
    }


    public void play() {
        Scanner scanner = new Scanner(System.in);
        String color = "white";
        Square[][] squares =board.getSquares();
        King whiteKing =(King)squares[0][4].getPiece();
        King blackKing =(King)squares[7][4].getPiece();
        King currentPlayerKing;
        int option, moveNumber =1;
        String move;
        Move lastMove = new Move(null, null, null);
        boolean quit = false;

        while (!quit) {
            board.showBoard();
            System.out.println(whiteKing.getColor() + " " + whiteKing.getName() + " is on the " + whiteKing.getSquare().toString() + " square.");
            System.out.println(blackKing.getColor() + " " + blackKing.getName() + " is on the " + blackKing.getSquare().toString() + " square.");
            System.out.println("Options: \t1) Play \t2) Offer Draw \t3) Resign");
            while (!scanner.hasNextInt()){
                scanner.next();
            }
            option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {
                case 1:
                    if (color.equals("white")) {
                        System.out.println("White's turn. Enter move -> " + moveNumber + ".");
                        currentPlayerKing =whiteKing;
                    }
                    else {
                        System.out.println("Black's turn. Enter move -> " + moveNumber + ".");
                        currentPlayerKing =blackKing;
                    }
                    move = scanner.nextLine();
                    while ( (move.length() < 5) || board.validateSquareName(move.substring(0, 2)) ==null
                            || board.validateSquareName(move.substring(3, 5)) ==null) {
                        move =scanner.nextLine();
                    }
                    Square staringSquare = board.validateSquareName(move.substring(0, 2));
                    Square endingSquare  = board.validateSquareName(move.substring(3, 5));

                    if (staringSquare.getPiece() == null || !staringSquare.getPiece().getColor().equals(color)) {
                        System.out.println("Enter valid move!");
                        break;
                    }
                    if(!board.isSquareInCheck(currentPlayerKing.getSquare(), color.equals("white") ? "black" : "white")) {
                        if (staringSquare.getPiece().move(staringSquare, endingSquare, board, lastMove)) {
                            if(color.equals("black"))
                                moveNumber++;
                            color =color.equals("white") ? "black" : "white";
                            lastMove.setStaringSquare(staringSquare);
                            lastMove.setEndingSquare(endingSquare);
                            lastMove.setPiece(endingSquare.getPiece());
                        }
                        break;
                    }
                    else {
                        if( board.SurroundingSquaresAreUnavailable(currentPlayerKing.getSquare(), color.equals("white") ? "black" : "white")
                                && (board.numberOfAttackersAOnSquare(currentPlayerKing.getSquare(), color.equals("white") ? "black" : "white") >1
                                || !board.isSquareInCheck(board.attackersOnSquare(currentPlayerKing.getSquare(), color.equals("white") ? "black" : "white")[0], color)
                                || board.attackDirection(currentPlayerKing.getSquare(), color.equals("white") ? "black" : "white").equals("L-shaped")) ) {  // potencijalni problem ako je figura koja napada kralja napadnuta od strane samog kralja!
                            System.out.println("Checkmate! \n" + (color.equals("white") ? " Black" : " White") + " wins!");
                            quit = true;
                            break;
                        }
                        if(board.SurroundingSquaresAreUnavailable(currentPlayerKing.getSquare(), color.equals("white") ? "black" : "white")
                                && board.numberOfAttackersAOnSquare(currentPlayerKing.getSquare(), color.equals("white") ? "black" : "white" ) ==1
                                && !board.attackIsInterruptible(currentPlayerKing.getSquare(), color)) {
                            System.out.println("Checkmate! \n" + (color.equals("white") ? " Black" : " White") + " wins!");
                            quit = true;
                            break;
                        }
                        do {   // repeat until there is no more check
                            Board testBoard =new Board(board);
                            move = scanner.nextLine();
                            while (testBoard.validateSquareName(move.substring(0, 2)) ==null
                                    || testBoard.validateSquareName(move.substring(3, 5)) ==null)
                                move =scanner.nextLine();
                            Square staringSquareOnTheTestBoard = testBoard.validateSquareName(move.substring(0, 2));
                            Square endingSquareOnTheTestBoard  = testBoard.validateSquareName(move.substring(3, 5));

                            if (staringSquareOnTheTestBoard.getPiece() == null || !staringSquareOnTheTestBoard.getPiece().getColor().equals(color)) {
                                System.out.println("Enter valid move!");
                                continue;
                            }
                            if( staringSquareOnTheTestBoard.getPiece().move(staringSquareOnTheTestBoard, endingSquareOnTheTestBoard, testBoard, lastMove)
                                    && !testBoard.isSquareInCheck(currentPlayerKing.getSquare(), color.equals("white") ? "black" : "white")) {
                                staringSquare = board.validateSquareName(move.substring(0, 2));
                                endingSquare  = board.validateSquareName(move.substring(3, 5));
                                if(staringSquare.getPiece() ==null)
                                    System.out.println("why???");
                                else
                                    staringSquare.getPiece().move(staringSquare, endingSquare, board, lastMove);
                                if(color.equals("black"))
                                    moveNumber++;
                                color =color.equals("white") ? "black" : "white";
                                break;
                            }

                        } while (board.isSquareInCheck(currentPlayerKing.getSquare(), color.equals("white") ? "black" : "white"));
                        break;
                    }

                case 2:
                    System.out.println(color + " offers draw. Do you accept? ");
                    String draw =scanner.nextLine();
                    if(draw.equalsIgnoreCase("Y")) {
                        System.out.println("Draw has been accepted. \n\tDRAW!");
                        quit =true;
                        scanner.close();
                        break;
                    }
                    System.out.println("Draw offer has been refused. Play on!");
                    break;

                case 3:
                    System.out.println(color + " resigns!");
                    if(color.equals("white"))
                        System.out.println("\tBlack WINS!");
                    else
                        System.out.println("\tWhite WINS!");
                    quit =true;
                    scanner.close();
                    break;

            }

        }

    }








}