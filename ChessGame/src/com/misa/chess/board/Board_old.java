package com.misa.chess.board;

import com.misa.chess.pieces.*;


public class Board_old {
    public static final String TEXT_RESET = "\u001B[0m";
    public static final String TEXT_GREEN = "\u001B[32m";
    private final Square[][] squares = new Square[8][8];

    public Board_old() {
        for (int i = 0; i < 8; i++)
            for (int j = 0; j < 8; j++)
                squares[i][j] = new Square((char) (97 + j) + "" + (i + 1), new Index(i, j));
    }

    public Board_old(Board_old board) {
        Square[][] oldSquares = board.getSquares();
        for (int i = 0; i < 8; i++)
            for (int j = 0; j < 8; j++) {
                squares[i][j] = new Square((char) (97 + j) + "" + (i + 1), new Index(i, j));
                squares[i][j].setPiece(oldSquares[i][j].getPiece());
            }

    }

    public Square[][] getSquares() {
        return squares;
    }


    public void showBoard() {
        System.out.println("=========================================================\n");
        for (int i = 8; i >= 0; i--) {
            for (int j = 0; j < 9; j++) {
                if(i == 0) {
                    if(j == 0) {
                        System.out.print("  ");
                    }
                    if(j < 8)
                        System.out.print("   " + TEXT_GREEN + (char)(j + 65) + TEXT_RESET + "  ");
                }
                else if( j > 0) {
                    System.out.print("  " + squares[i-1][j-1].toString() + " ");
                }
                else {
                    System.out.print(TEXT_GREEN + i + TEXT_RESET +  " ");
                }

            }
            System.out.println("\n");
        }
//        for (int i = 7; i >= 0; i--) {
//            for (int j = 0; j < 8; j++) {
//                System.out.print("  " + squares[i][j].toString() + " ");
//            }
//            System.out.println("\n");
//        }
        System.out.println("=========================================================\n");
    }

    public void setBoard() {
//  setting up pawns
        for (int j = 0; j < 8; j++)
            squares[1][j].setPiece(new Pawn("white"));
        for (int j = 0; j < 8; j++)
            squares[6][j].setPiece(new Pawn("black"));
//  setting up knights
        squares[0][1].setPiece(new Knight("white"));
        squares[0][6].setPiece(new Knight("white"));
        squares[7][1].setPiece(new Knight("black"));
        squares[7][6].setPiece(new Knight("black"));
//  setting up rooks
        squares[0][0].setPiece(new Rook("white"));
        squares[0][7].setPiece(new Rook("white"));
        squares[7][0].setPiece(new Rook("black"));
        squares[7][7].setPiece(new Rook("black"));
//  setting up bishops
        squares[0][2].setPiece(new Bishop("white"));
        squares[0][5].setPiece(new Bishop("white"));
        squares[7][2].setPiece(new Bishop("black"));
        squares[7][5].setPiece(new Bishop("black"));
//  setting up Queens
        squares[0][3].setPiece(new Queen("white"));
        squares[7][3].setPiece(new Queen("black"));
//  setting up Kings
        squares[0][4].setPiece(new King("white", squares[0][4]));
        squares[7][4].setPiece(new King("black", squares[7][4]));
    }

    public Square validateSquareName(String squareName) {       // checking to see if the entered square name is correct
        for (int i = 0; i < 8; i++)
            for (int j = 0; j < 8; j++)
                if (squares[i][j].getName().compareToIgnoreCase(squareName) == 0) {
                    return squares[i][j];
                }
        return null;
    }

    public boolean isPieceOnSquare(int i, int j, Piece piece) {
// we are checking to see if the wanted piece is on the wanted square
        if (i < 0 || i > 7 || j < 0 || j > 7 || squares[i][j].getPiece() == null)
            return false;
        return squares[i][j].getPiece().getColor().equals(piece.getColor()) && squares[i][j].getPiece().getName().equalsIgnoreCase(piece.getName());
    }

    public boolean isMyPieceOnASquare(int i, int j, String color) {
        if(squares[i][j].getPiece() == null)
            return false;
            return squares[i][j].getPiece().getColor().equals(color);
    }

    public boolean isEndingSquareValid(Square staringSquare, Square endingSquare) {
//  returns true if square is valid, false otherwise
        if(staringSquare.getPiece() == null)
            return false;
        return !(endingSquare.getPiece() != null && (endingSquare.getPiece().getClass().equals(King.class)
                || endingSquare.getPiece().getColor().equals(staringSquare.getPiece().getColor())));
    }

    public boolean isSquareInCheck(Square square, String opponentsColor) {
        // we are checking if the square is attacked by any of the pieces with given color(if they can reach it with their movement)
        int i1 = square.getIndex().getX(), j1 = square.getIndex().getY();
        String myColor = opponentsColor.equals("white") ? "black" : "white";
//  checking for possible L-shaped attack on that square (+)
        if (    (i1 + 2 < 8 && j1 + 1 < 8 && isPieceOnSquare(i1 + 2, j1 + 1, new Knight(opponentsColor)))
                || (i1 + 1 < 8 && j1 + 2 < 8 && isPieceOnSquare(i1 + 1, j1 + 2, new Knight(opponentsColor)))
                || (i1 + 2 < 8 && j1 - 1 >= 0 && isPieceOnSquare(i1 + 2, j1 - 1, new Knight(opponentsColor)))
                || (i1 + 1 < 8 && j1 - 2 >= 0 && isPieceOnSquare(i1 + 1, j1 - 2, new Knight(opponentsColor)))
                || (i1 - 2 >= 0 && j1 + 1 < 8 && isPieceOnSquare(i1 - 2, j1 + 1, new Knight(opponentsColor)))
                || (i1 - 1 >= 0 && j1 + 2 < 8 && isPieceOnSquare(i1 - 1, j1 + 2, new Knight(opponentsColor)))
                || (i1 - 2 >= 0 && j1 - 1 >=0 && isPieceOnSquare(i1 - 2, j1 - 1, new Knight(opponentsColor)))
                || (i1 - 1 >= 0 && j1 - 2 >= 0 && isPieceOnSquare( i1 - 1, j1 - 2, new Knight(opponentsColor)))   )
            return true;
//  checking for possible vertical attacks on that square (-)
        int k = 1;
// going "up" the board
        while (i1 + k < 8) {
            if (squares[i1 + k][j1].getPiece() == null) {
                k++;
                continue;
            }
            else if (isMyPieceOnASquare(i1 + k, j1, myColor)) {
                break;
            }
            else if (  ( k == 1 && squares[i1 + k][j1].getPiece().getName().equals("King") )
                  || squares[i1 + k][j1].getPiece().getName().equals("Rook")
                  || squares[i1 + k][j1].getPiece().getName().equals("Queen") ) {
                return true;
            }
            else
                k++;
        }

        k = 1;
// going "down" the board
        while (i1 - k > -1) {
            if (squares[i1 - k][j1].getPiece() == null) {
                k++;
                continue;
            }
            else if (isMyPieceOnASquare(i1 - k, j1, myColor))
                break;
            else if ( (k == 1 && squares[i1 - k][j1].getPiece().getName().equals("King"))
                 || squares[i1 - k][j1].getPiece().getName().equals("Rook")
                 || squares[i1 - k][j1].getPiece().getName().equals("Queen")) {
                return true;
            } else
                k++;
        }
//  checking for possible horizontal attacks on that square
        k = 1;
//  going to the "right"
        while (j1 + k < 8) {
            if (squares[i1][j1 + k].getPiece() == null) {
                k++;
                continue;
            }
            else if (isMyPieceOnASquare(i1, j1 + k, myColor))
                break;
            else if (  (k == 1 && squares[i1][j1 + k].getPiece().getName().equals("King"))
                  || squares[i1][j1 + k].getPiece().getName().equals("Rook")
                  || squares[i1][j1 + k].getPiece().getName().equals("Queen")
                    ) {
                return true;
            } else
                k++;
        }
        k = 1;
// going to the "left"
        while (j1 - k > -1) {
            if (squares[i1][j1 - k].getPiece() == null) {
                k++;
                continue;
            }
            else if (isMyPieceOnASquare(i1, j1 - k, myColor))
                break;
            else if (  (k == 1 && squares[i1][j1 - k].getPiece().getName().equals("King"))
                  || squares[i1][j1 - k].getPiece().getName().equals("Rook")
                  || squares[i1][j1 - k].getPiece().getName().equals("Queen")) {
                return true;
            } else
                k++;
        }
//  checking for diagonal attacks on that square
        k = 1;
// going "up-right"
        while (i1 + k < 8 && j1 + k < 8) {
            if (squares[i1 + k][j1 + k].getPiece() == null) {
                k++;
                continue;
            }
            else if (isMyPieceOnASquare(i1 + k, j1 + k, myColor))
                break;
            else if (  (k == 1 && squares[i1 + k][j1 + k].getPiece().getName().equals("King"))
                  || (k == 1 && squares[i1 + k][j1 + k].getPiece().getName().equals("Pawn") && squares[i1 + k][j1 + k].getPiece().getColor().equals("black"))
                  || squares[i1 + k][j1 + k].getPiece().getName().equals("Bishop")
                  || squares[i1 + k][j1 + k].getPiece().getName().equals("Queen")) {
                return true;
            } else
                k++;
        }
        k = 1;
// going "up-left"
        while (i1 + k < 8 && j1 - k > -1) {
            if (squares[i1 + k][j1 - k].getPiece() == null) {
                k++;
                continue;
            }
            else if (isMyPieceOnASquare(i1 + k, j1 - k, myColor))
                break;
            else if (  (k == 1 && squares[i1 + k][j1 - k].getPiece().getName().equals("King"))
                    || (k == 1 && squares[i1 + k][j1 - k].getPiece().getName().equals("Pawn") && squares[i1 + k][j1 - k].getPiece().getColor().equals("black"))
                    || squares[i1 + k][j1 - k].getPiece().getName().equals("Bishop")
                    || squares[i1 + k][j1 - k].getPiece().getName().equals("Queen")) {
                return true;
            } else
                k++;
        }
        k = 1;
// going "down-right"
        while (i1 - k > -1 && j1 + k < 8) {
            if (squares[i1 - k][j1 + k].getPiece() == null) {
                k++;
                continue;
            }
            else if (isMyPieceOnASquare(i1 - k, j1 + k, myColor))
                break;
            else if (  (k == 1 && squares[i1 - k][j1 + k].getPiece().getName().equals("King"))
                    || (k == 1 && squares[i1 - k][j1 + k].getPiece().getName().equals("Pawn") && squares[i1 - k][j1 + k].getPiece().getColor().equals("white"))
                    || squares[i1 - k][j1 + k].getPiece().getName().equals("Bishop")
                    || squares[i1 - k][j1 + k].getPiece().getName().equals("Queen")) {
                return true;
            } else
                k++;
        }
        k = 1;
// going "down-left"
        while (i1 - k > -1 && j1 - k > -1) {
            if (squares[i1 - k][j1 - k].getPiece() == null) {
                k++;
                continue;
            }
            else if (isMyPieceOnASquare(i1 - k, j1 - k, myColor))
                break;
            else if (  (k == 1 && squares[i1 - k][j1 - k].getPiece().getName().equals("King"))
                    || (k == 1 && squares[i1 - k][j1 - k].getPiece().getName().equals("Pawn") && squares[i1 - k][j1 - k].getPiece().getColor().equals("white"))
                    || squares[i1 - k][j1 - k].getPiece().getName().equals("Bishop")
                    || squares[i1 - k][j1 - k].getPiece().getName().equals("Queen")) {
                return true;
            } else
                k++;
        }
        return false;
    }


    public int numberOfAttackersOnSquare(Square square, String color) {
        int attackers = 0;
        int i1 = square.getIndex().getX(), j1 = square.getIndex().getY();
//  checking for possible L-shaped attacks on that square
        if (isPieceOnSquare(i1 + 2, j1 + 1, new Knight(color))) attackers++;
        if (isPieceOnSquare(i1 + 1, j1 + 2, new Knight(color))) attackers++;
        if (isPieceOnSquare(i1 + 2, j1 - 1, new Knight(color))) attackers++;
        if (isPieceOnSquare(i1 + 1, j1 - 2, new Knight(color))) attackers++;
        if (isPieceOnSquare(i1 - 2, j1 + 1, new Knight(color))) attackers++;
        if (isPieceOnSquare(i1 - 1, j1 + 2, new Knight(color))) attackers++;
        if (isPieceOnSquare(i1 - 2, j1 - 1, new Knight(color))) attackers++;
        if (isPieceOnSquare(i1 - 1, j1 - 2, new Knight(color))) attackers++;

//  checking for possible vertical attacks on that square
        int k = 1;
        while (i1 + k < 8) {
            if (squares[i1 + k][j1].getPiece() == null) {
                k++;
                continue;
            }
            if (!squares[i1 + k][j1].getPiece().getColor().equals(color))
                break;
            if (squares[i1 + k][j1].getPiece().getClass().equals(Rook.class)
                    || squares[i1 + k][j1].getPiece().getClass().equals(Queen.class)
                    || (k == 1 && squares[i1 + k][j1].getPiece().getClass().equals(King.class))) {
                attackers++;
                break;
            } else
                k++;
        }
        k = 1;
        while (i1 - k > -1) {
            if (squares[i1 - k][j1].getPiece() == null) {
                k++;
                continue;
            }
            if (!squares[i1 - k][j1].getPiece().getColor().equals(color))
                break;
            if (squares[i1 - k][j1].getPiece().getClass().equals(Rook.class)
                    || squares[i1 - k][j1].getPiece().getClass().equals(Queen.class)
                    || (k == 1 && squares[i1 - k][j1].getPiece().getClass().equals(King.class))) {
                attackers++;
                break;
            } else
                k++;
        }
//  checking for possible horizontal attacks on that square
        k = 1;
        while (j1 + k < 8) {
            if (squares[i1][j1 + k].getPiece() == null) {
                k++;
                continue;
            }
            if (!squares[i1][j1 + k].getPiece().getColor().equals(color))
                break;
            if (squares[i1][j1 + k].getPiece().getClass().equals(Rook.class)
                    || squares[i1][j1 + k].getPiece().getClass().equals(Queen.class)
                    || (k == 1 && squares[i1][j1 + k].getPiece().getClass().equals(King.class))) {
                attackers++;
                break;
            } else
                k++;
        }
        k = 1;
        while (j1 - k > -1) {
            if (squares[i1][j1 - k].getPiece() == null) {
                k++;
                continue;
            }
            if (!squares[i1][j1 - k].getPiece().getColor().equals(color))
                break;
            if (squares[i1][j1 - k].getPiece().getClass().equals(Rook.class)
                    || squares[i1][j1 - k].getPiece().getClass().equals(Queen.class)
                    || (k == 1 && squares[i1][j1 - k].getPiece().getClass().equals(King.class))) {
                attackers++;
                break;
            } else
                k++;
        }
//  checking for diagonal attacks on that square
        k = 1;
        while (i1 + k < 8 && j1 + k < 8) {
            if (squares[i1 + k][j1 + k].getPiece() == null) {
                k++;
                continue;
            }
            if (!squares[i1 + k][j1 + k].getPiece().getColor().equals(color))
                break;
            if (squares[i1 + k][j1 + k].getPiece().getClass().equals(Bishop.class)
                    || squares[i1 + k][j1 + k].getPiece().getClass().equals(Queen.class)
                    || (k == 1 && squares[i1 + k][j1 + k].getPiece().getClass().equals(King.class))
                    || (k == 1 && squares[i1 + k][j1 + k].getPiece().getClass().equals(Pawn.class) && color.equals("black"))) {
                attackers++;
                break;
            } else
                k++;
        }
        k = 1;
        while (i1 + k < 8 && j1 - k > -1) {
            if (squares[i1 + k][j1 - k].getPiece() == null) {
                k++;
                continue;
            }
            if (!squares[i1 + k][j1 - k].getPiece().getColor().equals(color))
                break;
            if (squares[i1 + k][j1 - k].getPiece().getClass().equals(Bishop.class)
                    || squares[i1 + k][j1 - k].getPiece().getClass().equals(Queen.class)
                    || (k == 1 && squares[i1 + k][j1 - k].getPiece().getClass().equals(King.class))
                    || (k == 1 && squares[i1 + k][j1 - k].getPiece().getClass().equals(Pawn.class) && color.equals("black"))) {
                attackers++;
                break;
            } else
                k++;
        }
        k = 1;
        while (i1 - k > -1 && j1 + k < 8) {
            if (squares[i1 - k][j1 + k].getPiece() == null) {
                k++;
                continue;
            }
            if (!squares[i1 - k][j1 + k].getPiece().getColor().equals(color))
                break;
            if (squares[i1 - k][j1 + k].getPiece().getClass().equals(Bishop.class)
                    || squares[i1 - k][j1 + k].getPiece().getClass().equals(Queen.class)
                    || (k == 1 && squares[i1 - k][j1 + k].getPiece().getClass().equals(King.class))
                    || (k == 1 && squares[i1 - k][j1 + k].getPiece().getClass().equals(Pawn.class) && color.equals("white"))) {
                attackers++;
                break;
            } else
                k++;
        }
        k = 1;
        while (i1 - k > -1 && j1 - k > -1) {
            if (squares[i1 - k][j1 - k].getPiece() == null) {
                k++;
                continue;
            }
            if (!squares[i1 - k][j1 - k].getPiece().getColor().equals(color))
                break;
            if (squares[i1 - k][j1 - k].getPiece().getClass().equals(Bishop.class)
                    || squares[i1 - k][j1 - k].getPiece().getClass().equals(Queen.class)
                    || (k == 1 && squares[i1 - k][j1 - k].getPiece().getClass().equals(King.class))
                    || (k == 1 && squares[i1 - k][j1 - k].getPiece().getClass().equals(Pawn.class) && color.equals("white"))) {
                attackers++;
                break;
            } else
                k++;
        }
        return attackers;
    }

    public Square[] attackersOnSquare(Square square, String color) {
        Square[] attackers = new Square[numberOfAttackersOnSquare(square, color)];
        int current = 0;
        int i1 = square.getIndex().getX(), j1 = square.getIndex().getY();
//  checking for possible L-shaped attacks on that square
        if (isPieceOnSquare(i1 + 2, j1 + 1, new Knight(color))) attackers[current++] = squares[i1 + 2][j1 + 1];
        if (isPieceOnSquare(i1 + 1, j1 + 2, new Knight(color))) attackers[current++] = squares[i1 + 1][j1 + 2];
        if (isPieceOnSquare(i1 + 2, j1 - 1, new Knight(color))) attackers[current++] = squares[i1 + 2][j1 - 1];
        if (isPieceOnSquare(i1 + 1, j1 - 2, new Knight(color))) attackers[current++] = squares[i1 + 1][j1 - 2];
        if (isPieceOnSquare(i1 - 2, j1 + 1, new Knight(color))) attackers[current++] = squares[i1 - 2][j1 + 1];
        if (isPieceOnSquare(i1 - 1, j1 + 2, new Knight(color))) attackers[current++] = squares[i1 - 1][j1 + 2];
        if (isPieceOnSquare(i1 - 2, j1 - 1, new Knight(color))) attackers[current++] = squares[i1 - 2][j1 - 1];
        if (isPieceOnSquare(i1 - 1, j1 - 2, new Knight(color))) attackers[current++] = squares[i1 - 1][j1 - 2];

//  checking for possible vertical attacks on that square
        int k = 1;
        while (i1 + k < 8) {
            if (squares[i1 + k][j1].getPiece() == null) {
                k++;
                continue;
            }
            if (!squares[i1 + k][j1].getPiece().getColor().equals(color))
                break;
            if (squares[i1 + k][j1].getPiece().getClass().equals(Rook.class)
                    || squares[i1 + k][j1].getPiece().getClass().equals(Queen.class)
                    || (k == 1 && squares[i1 + k][j1].getPiece().getClass().equals(King.class))) {
                attackers[current++] = squares[i1 + k][j1];
                break;
            } else
                k++;
        }
        k = 1;
        while (i1 - k > -1) {
            if (squares[i1 - k][j1].getPiece() == null) {
                k++;
                continue;
            }
            if (!squares[i1 - k][j1].getPiece().getColor().equals(color))
                break;
            if (squares[i1 - k][j1].getPiece().getClass().equals(Rook.class)
                    || squares[i1 - k][j1].getPiece().getClass().equals(Queen.class)
                    || (k == 1 && squares[i1 - k][j1].getPiece().getClass().equals(King.class))) {
                attackers[current++] = squares[i1 - k][j1];
                break;
            } else
                k++;
        }
//  checking for possible horizontal attacks on that square
        k = 1;
        while (j1 + k < 8) {
            if (squares[i1][j1 + k].getPiece() == null) {
                k++;
                continue;
            }
            if (!squares[i1][j1 + k].getPiece().getColor().equals(color))
                break;
            if (squares[i1][j1 + k].getPiece().getClass().equals(Rook.class)
                    || squares[i1][j1 + k].getPiece().getClass().equals(Queen.class)
                    || (k == 1 && squares[i1][j1 + k].getPiece().getClass().equals(King.class))) {
                attackers[current++] = squares[i1][j1 + k];
                break;
            } else
                k++;
        }
        k = 1;
        while (j1 - k > -1) {
            if (squares[i1][j1 - k].getPiece() == null) {
                k++;
                continue;
            }
            if (!squares[i1][j1 - k].getPiece().getColor().equals(color))
                break;
            if (squares[i1][j1 - k].getPiece().getClass().equals(Rook.class)
                    || squares[i1][j1 - k].getPiece().getClass().equals(Queen.class)
                    || (k == 1 && squares[i1][j1 - k].getPiece().getClass().equals(King.class))) {
                attackers[current++] = squares[i1][j1 - k];
                break;
            } else
                k++;
        }
//  checking for diagonal attacks on that square
        k = 1;
        while (i1 + k < 8 && j1 + k < 8) {
            if (squares[i1 + k][j1 + k].getPiece() == null) {
                k++;
                continue;
            }
            if (!squares[i1 + k][j1 + k].getPiece().getColor().equals(color))
                break;
            if (squares[i1 + k][j1 + k].getPiece().getClass().equals(Bishop.class)
                    || squares[i1 + k][j1 + k].getPiece().getClass().equals(Queen.class)
                    || (k == 1 && squares[i1 + k][j1 + k].getPiece().getClass().equals(King.class))
                    || (k == 1 && squares[i1 + k][j1 + k].getPiece().getClass().equals(Pawn.class) && color.equals("black"))) {
                attackers[current++] = squares[i1 + k][j1 + k];
                break;
            } else
                k++;
        }
        k = 1;
        while (i1 + k < 8 && j1 - k > -1) {
            if (squares[i1 + k][j1 - k].getPiece() == null) {
                k++;
                continue;
            }
            if (!squares[i1 + k][j1 - k].getPiece().getColor().equals(color))
                break;
            if (squares[i1 + k][j1 - k].getPiece().getClass().equals(Bishop.class)
                    || squares[i1 + k][j1 - k].getPiece().getClass().equals(Queen.class)
                    || (k == 1 && squares[i1 + k][j1 - k].getPiece().getClass().equals(King.class))
                    || (k == 1 && squares[i1 + k][j1 - k].getPiece().getClass().equals(Pawn.class) && color.equals("black"))) {
                attackers[current++] = squares[i1 + k][j1 - k];
                break;
            } else
                k++;
        }
        k = 1;
        while (i1 - k > -1 && j1 + k < 8) {
            if (squares[i1 - k][j1 + k].getPiece() == null) {
                k++;
                continue;
            }
            if (!squares[i1 - k][j1 + k].getPiece().getColor().equals(color))
                break;
            if (squares[i1 - k][j1 + k].getPiece().getClass().equals(Bishop.class)
                    || squares[i1 - k][j1 + k].getPiece().getClass().equals(Queen.class)
                    || (k == 1 && squares[i1 - k][j1 + k].getPiece().getClass().equals(King.class))
                    || (k == 1 && squares[i1 - k][j1 + k].getPiece().getClass().equals(Pawn.class) && color.equals("white"))) {
                attackers[current++] = squares[i1 - k][j1 + k];
                break;
            } else
                k++;
        }
        k = 1;
        while (i1 - k > -1 && j1 - k > -1) {
            if (squares[i1 - k][j1 - k].getPiece() == null) {
                k++;
                continue;
            }
            if (!squares[i1 - k][j1 - k].getPiece().getColor().equals(color))
                break;
            if (squares[i1 - k][j1 - k].getPiece().getClass().equals(Bishop.class)
                    || squares[i1 - k][j1 - k].getPiece().getClass().equals(Queen.class)
                    || (k == 1 && squares[i1 - k][j1 - k].getPiece().getClass().equals(King.class))
                    || (k == 1 && squares[i1 - k][j1 - k].getPiece().getClass().equals(Pawn.class) && color.equals("white"))) {
                attackers[current] = squares[i1 - k][j1 - k];
                break;
            } else
                k++;
        }
        return attackers;
    }

    public String attackDirection(Square square, String color) {
        int i1 = square.getIndex().getX(), j1 = square.getIndex().getY();
//  checking for possible L-shaped attacks on that square
        if (isPieceOnSquare(i1 + 2, j1 + 1, new Knight(color))
                || isPieceOnSquare(i1 + 1, j1 + 2, new Knight(color))
                || isPieceOnSquare(i1 + 2, j1 - 1, new Knight(color))
                || isPieceOnSquare(i1 + 1, j1 - 2, new Knight(color))
                || isPieceOnSquare(i1 - 2, j1 + 1, new Knight(color))
                || isPieceOnSquare(i1 - 1, j1 + 2, new Knight(color))
                || isPieceOnSquare(i1 - 2, j1 - 1, new Knight(color))
                || isPieceOnSquare(i1 - 1, j1 - 2, new Knight(color)))
            return "L-shaped";

//  checking for possible vertical attacks on that square
        int k = 1;
        while (i1 + k < 8) {
            if (squares[i1 + k][j1].getPiece() == null) {
                k++;
                continue;
            }
            if (!squares[i1 + k][j1].getPiece().getColor().equals(color))
                break;
            if (squares[i1 + k][j1].getPiece().getClass().equals(Rook.class)
                    || squares[i1 + k][j1].getPiece().getClass().equals(Queen.class)
                    || (k == 1 && squares[i1 + k][j1].getPiece().getClass().equals(King.class))) {
                return "vertical-up";
            } else
                k++;
        }
        k = 1;
        while (i1 - k > -1) {
            if (squares[i1 - k][j1].getPiece() == null) {
                k++;
                continue;
            }
            if (!squares[i1 - k][j1].getPiece().getColor().equals(color))
                break;
            if (squares[i1 - k][j1].getPiece().getClass().equals(Rook.class)
                    || squares[i1 - k][j1].getPiece().getClass().equals(Queen.class)
                    || (k == 1 && squares[i1 - k][j1].getPiece().getClass().equals(King.class))) {
                return "vertical-down";
            } else
                k++;
        }
//  checking for possible horizontal attacks on that square
        k = 1;
        while (j1 + k < 8) {
            if (squares[i1][j1 + k].getPiece() == null) {
                k++;
                continue;
            }
            if (!squares[i1][j1 + k].getPiece().getColor().equals(color))
                break;
            if (squares[i1][j1 + k].getPiece().getClass().equals(Rook.class)
                    || squares[i1][j1 + k].getPiece().getClass().equals(Queen.class)
                    || (k == 1 && squares[i1][j1 + k].getPiece().getClass().equals(King.class))) {
                return "horizontal-right";
            } else
                k++;
        }
        k = 1;
        while (j1 - k > -1) {
            if (squares[i1][j1 - k].getPiece() == null) {
                k++;
                continue;
            }
            if (!squares[i1][j1 - k].getPiece().getColor().equals(color))
                break;
            if (squares[i1][j1 - k].getPiece().getClass().equals(Rook.class)
                    || squares[i1][j1 - k].getPiece().getClass().equals(Queen.class)
                    || (k == 1 && squares[i1][j1 - k].getPiece().getClass().equals(King.class))) {
                return "horizontal-left";
            } else
                k++;
        }
//  checking for diagonal attacks on that square
        k = 1;
        while (i1 + k < 8 && j1 + k < 8) {
            if (squares[i1 + k][j1 + k].getPiece() == null) {
                k++;
                continue;
            }
            if (!squares[i1 + k][j1 + k].getPiece().getColor().equals(color))
                break;
            if (squares[i1 + k][j1 + k].getPiece().getClass().equals(Bishop.class)
                    || squares[i1 + k][j1 + k].getPiece().getClass().equals(Queen.class)
                    || (k == 1 && squares[i1 + k][j1 + k].getPiece().getClass().equals(King.class))
                    || (k == 1 && squares[i1 + k][j1 + k].getPiece().getClass().equals(Pawn.class) && color.equals("black"))) {
                return "diagonal-upRight";
            } else
                k++;
        }
        k = 1;
        while (i1 + k < 8 && j1 - k > -1) {
            if (squares[i1 + k][j1 - k].getPiece() == null) {
                k++;
                continue;
            }
            if (!squares[i1 + k][j1 - k].getPiece().getColor().equals(color))
                break;
            if (squares[i1 + k][j1 - k].getPiece().getClass().equals(Bishop.class)
                    || squares[i1 + k][j1 - k].getPiece().getClass().equals(Queen.class)
                    || (k == 1 && squares[i1 + k][j1 - k].getPiece().getClass().equals(King.class))
                    || (k == 1 && squares[i1 + k][j1 - k].getPiece().getClass().equals(Pawn.class) && color.equals("black"))) {
                return "diagonal-upLeft";
            } else
                k++;
        }
        k = 1;
        while (i1 - k > -1 && j1 + k < 8) {
            if (squares[i1 - k][j1 + k].getPiece() == null) {
                k++;
                continue;
            }
            if (!squares[i1 - k][j1 + k].getPiece().getColor().equals(color))
                break;
            if (squares[i1 - k][j1 + k].getPiece().getClass().equals(Bishop.class)
                    || squares[i1 - k][j1 + k].getPiece().getClass().equals(Queen.class)
                    || (k == 1 && squares[i1 - k][j1 + k].getPiece().getClass().equals(King.class))
                    || (k == 1 && squares[i1 - k][j1 + k].getPiece().getClass().equals(Pawn.class) && color.equals("white"))) {
                return "diagonal-downRight";
            } else
                k++;
        }
        k = 1;
        while (i1 - k > -1 && j1 - k > -1) {
            if (squares[i1 - k][j1 - k].getPiece() == null) {
                k++;
                continue;
            }
            if (!squares[i1 - k][j1 - k].getPiece().getColor().equals(color))
                break;
            if (squares[i1 - k][j1 - k].getPiece().getClass().equals(Bishop.class)
                    || squares[i1 - k][j1 - k].getPiece().getClass().equals(Queen.class)
                    || (k == 1 && squares[i1 - k][j1 - k].getPiece().getClass().equals(King.class))
                    || (k == 1 && squares[i1 - k][j1 - k].getPiece().getClass().equals(Pawn.class) && color.equals("white"))) {
                return "diagonal-downLeft";
            } else
                k++;
        }
        return "none";
    }

    public boolean SurroundingSquaresAreUnavailable(Square square, String color) {
        int i = square.getIndex().getX(), j = square.getIndex().getY();

        return ((i == 7 || j == 7 || squares[i + 1][j + 1].getPiece() != null || isSquareInCheck(squares[i + 1][j + 1], color))
                && (i == 7 || squares[i + 1][j].getPiece() != null || isSquareInCheck(squares[i + 1][j], color))
                && (i == 7 || j == 0 || squares[i + 1][j - 1].getPiece() != null || isSquareInCheck(squares[i + 1][j - 1], color))
                && (j == 7 || squares[i][j + 1].getPiece() != null || isSquareInCheck(squares[i][j + 1], color))
                && (j == 0 || squares[i][j - 1].getPiece() != null || isSquareInCheck(squares[i][j - 1], color))
                && (i == 0 || j == 7 || squares[i - 1][j + 1].getPiece() != null || isSquareInCheck(squares[i - 1][j + 1], color))
                && (i == 0 || squares[i - 1][j].getPiece() != null || isSquareInCheck(squares[i - 1][j], color))
                && (i == 0 || j == 0 || squares[i - 1][j - 1].getPiece() != null || isSquareInCheck(squares[i - 1][j - 1], color)));
    }

    public boolean attackIsInterruptible(Square square, String color) {
        int i =square.getIndex().getX(), j =square.getIndex().getY();
        int k =1;
        String opponentColor =color.equals("white") ? "black" : "white";

        if (attackDirection(square, color).equals("vertical-up")) {
            while (squares[i +k][j] ==null) {
                if (isSquareInCheck(squares[i +k][j], opponentColor)) {
                    if(k==1 && numberOfAttackersOnSquare(squares[i +k][j], opponentColor) >1)
                        return true;
                    if(k>1)
                        return true;
                }
                k++;
            }
        }
        if (attackDirection(square, color).equals("vertical-down")) {
            while (squares[i -k][j] ==null) {
                if (isSquareInCheck(squares[i -k][j], opponentColor)) {
                    if(k==1 && numberOfAttackersOnSquare(squares[i -k][j], opponentColor) >1)
                        return true;
                    if(k>1)
                        return true;
                }
                k++;
            }
        }
        if (attackDirection(square, color).equals("horizontal-left")) {
            while (squares[i][j-k] ==null) {
                if (isSquareInCheck(squares[i][j-k], opponentColor)) {
                    if(k==1 && numberOfAttackersOnSquare(squares[i][j-k], opponentColor) >1)
                        return true;
                    if(k>1)
                        return true;
                }
                k++;
            }
        }
        if (attackDirection(square, color).equals("horizontal-right")) {
            while (squares[i][j+k] ==null) {
                if (isSquareInCheck(squares[i][j+k], opponentColor)) {
                    if(k==1 && numberOfAttackersOnSquare(squares[i][j+k], opponentColor) >1)
                        return true;
                    if(k>1)
                        return true;
                }
                k++;
            }
        }
        if (attackDirection(square, color).equals("diagonal-upright")) {
            while (squares[i +k][j+k] ==null) {
                if (isSquareInCheck(squares[i +k][j+k], opponentColor)) {
                    if(k==1 && numberOfAttackersOnSquare(squares[i +k][j+k], opponentColor) >1)
                        return true;
                    if(k>1)
                        return true;
                }
                k++;
            }
        }
        if (attackDirection(square, color).equals("diagonal-upLeft")) {
            while (squares[i +k][j-k] ==null) {
                if (isSquareInCheck(squares[i +k][j-k], opponentColor)) {
                    if(k==1 && numberOfAttackersOnSquare(squares[i +k][j-k], opponentColor) >1)
                        return true;
                    if(k>1)
                        return true;
                }
                k++;
            }
        }
        if (attackDirection(square, color).equals("diagonal-downLeft")) {
            while (squares[i -k][j-k] ==null) {
                if (isSquareInCheck(squares[i -k][j-k], opponentColor)) {
                    if(k==1 && numberOfAttackersOnSquare(squares[i -k][j-k], opponentColor) >1)
                        return true;
                    if(k>1)
                        return true;
                }
                k++;
            }
        }
        if (attackDirection(square, color).equals("diagonal-downRight")) {
            while (squares[i -k][j+k] ==null) {
                if (isSquareInCheck(squares[i -k][j+k], opponentColor)) {
                    if(k==1 && numberOfAttackersOnSquare(squares[i -k][j+k], opponentColor) >1)
                        return true;
                    if(k>1)
                        return true;
                }
                k++;
            }
        }
        return false;
    }

}



/*
    public void showEmptyTable() {
        for (int i = 7; i >= 0; i--) {
            for (int j = 0; j < 8; j++) {
                System.out.print(squares[i][j].getName() + "    ");
            }
            System.out.println();
            System.out.println();
        }
    }
*/

