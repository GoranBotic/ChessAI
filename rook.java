package chessai;

import java.util.ArrayList;
import java.util.Scanner;

public class rook extends pieces {

    Scanner kbd = new Scanner(System.in);
    boolean hasMoved = false; //this is used for castling

    public rook(int x, int y, int direction, char name, int team, int value, int AiControl) {
        super(x, y, direction, name, team, value, AiControl);
    }

    public ArrayList<char[][]> move(char[][] board, int x, int y, ArrayList<pieces> HumanList, ArrayList<pieces> AiList) {

        return Available_Moves(board, HumanList, AiList);

    }//x and y describes location of piece

    public ArrayList<char[][]> Available_Moves(char[][] board, ArrayList<pieces> HumanList, ArrayList<pieces> AiList) {

        ArrayList<char[][]> moveList = new ArrayList();
        ArrayList<int[]> updatedXY = new ArrayList(); //stores changed x and y in respect with board states
        int aMove = 1;
        if (AiControl == 0) {
            System.out.println("Selct an available move(s): ");
        }
        while (inBounds(x + aMove * direction, y)) {
            char[][] aBoard = new char[board.length][board.length];
            aBoard = copyBoard(board);
            if (board[x + aMove * direction][y] == ' ') {
                if (AiControl == 0) {
                    System.out.println("(" + (moveList.size() + 1) + ") " + (x + aMove * direction) + ":" + y); //show user the available moves
                }
                aBoard[x + aMove * direction][y] = this.name;
                aBoard[x][y] = ' ';
                int[] XY = {x + aMove * direction, y};
                this.updatedXY.add(XY);
                moveList.add(aBoard);
                aMove++;

            } else {
                if (board[x + aMove * direction][y] != ' ') {
                    pieces p = checkPiece(x + aMove * direction, y, AiList, HumanList);
                    if (p != null) {
                        if (this.team != p.team) {
                            if (AiControl == 0) {
                                System.out.println("(" + (moveList.size() + 1) + ") Attack" + (x + aMove * direction) + ":" + y);
                            }
                            aBoard[x + aMove * direction][y] = this.name;
                            aBoard[x][y] = ' ';
                            int[] XY = {x + aMove * direction, y};
                            this.updatedXY.add(XY);
                            moveList.add(aBoard);

                        }
                    }
                }//checks to attack Down
                break;
            }//looks for moves Down
        }

        aMove = 1;
        while (inBounds(x + aMove * -direction, y)) {
            char[][] aBoard = new char[board.length][board.length];
            aBoard = copyBoard(board);
            if (board[x + aMove * -direction][y] == ' ') {
                if (AiControl == 0) {
                    System.out.println("(" + (moveList.size() + 1) + ") " + (x + aMove * -direction) + ":" + y); //show user the available moves
                }
                aBoard[x + aMove * -direction][y] = this.name;
                aBoard[x][y] = ' ';
                int[] XY = {x + aMove * -direction, y};
                this.updatedXY.add(XY);
                moveList.add(aBoard);
                aMove++;

            } else {
                if (board[x + aMove * -direction][y] != ' ') {
                    pieces p = checkPiece(x + aMove * -direction, y, AiList, HumanList);
                    if (p != null) {
                        if (this.team != p.team) {
                            if (AiControl == 0) {
                                System.out.println("(" + (moveList.size() + 1) + ") Attack" + (x + aMove * -direction) + ":" + y);
                            }
                            aBoard[x + aMove * -direction][y] = this.name;
                            aBoard[x][y] = ' ';
                            int[] XY = {x + aMove * -direction, y};
                            this.updatedXY.add(XY);
                            moveList.add(aBoard);

                        }
                    }
                }//checks to attack Up
                break;
            }//looks for moves Up
        }
        aMove = 1;
        while (inBounds(x, y + aMove * direction)) {
            char[][] aBoard = new char[board.length][board.length];
            aBoard = copyBoard(board);
            if (board[x][y + aMove * direction] == ' ') {
                if (AiControl == 0) {
                    System.out.println("(" + (moveList.size() + 1) + ") " + x + ":" + (y + aMove * direction)); //show user the available moves
                }
                aBoard[x][y + aMove * direction] = this.name;
                aBoard[x][y] = ' ';
                int[] XY = {x, y + aMove * direction};
                this.updatedXY.add(XY);
                moveList.add(aBoard);
                aMove++;
            } else {
                if (board[x][y + aMove * direction] != ' ') {
                    pieces p = checkPiece(x, y + aMove * direction, AiList, HumanList);
                    if (p != null) {
                        if (this.team != p.team) {
                            if (AiControl == 0) {
                                System.out.println("(" + (moveList.size() + 1) + ") Attack" + x + ":" + (y + aMove * direction));
                            }
                            aBoard[x][y + aMove * direction] = this.name;
                            aBoard[x][y] = ' ';
                            int[] XY = {x, y + aMove * direction};
                            this.updatedXY.add(XY);
                            moveList.add(aBoard);

                        }
                    }
                }//checks to attack Left
                break;
            }//looks for moves Left
        }
        aMove = 1;
        while (inBounds(x, y + aMove * -direction)) {
            char[][] aBoard = new char[board.length][board.length];
            aBoard = copyBoard(board);
            if (board[x][y + aMove * -direction] == ' ') {
                if (AiControl == 0) {
                    System.out.println("(" + (moveList.size() + 1) + ") " + x + ":" + (y + aMove * -direction)); //show user the available moves
                }
                aBoard[x][y + aMove * -direction] = this.name;
                aBoard[x][y] = ' ';
                int[] XY = {x, y + aMove * -direction};
                this.updatedXY.add(XY);
                moveList.add(aBoard);
                aMove++;
            } else {
                if (board[x][y + aMove * -direction] != ' ') {
                    pieces p = checkPiece(x, y + aMove * -direction, AiList, HumanList);
                    if (p != null) {
                        if (this.team != p.team) {
                            if (AiControl == 0) {
                                System.out.println("(" + (moveList.size() + 1) + ") Attack" + x + ":" + (y + aMove * -direction));
                            }
                            aBoard[x][y + aMove * -direction] = this.name;
                            aBoard[x][y] = ' ';
                            int[] XY = {x, y + aMove * -direction};
                            this.updatedXY.add(XY);
                            moveList.add(aBoard);

                        }
                    }
                }//checks to attack Right
                break;
            }//looks for moves Right
        }

        return moveList;
    }

    public char[][] copyBoard(char[][] someBoard) {
        char[][] resultBoard = new char[someBoard.length][someBoard.length];
        for (int i = 0; i < someBoard.length; i++) {
            for (int j = 0; j < someBoard[i].length; j++) {
                resultBoard[i][j] = someBoard[i][j];
            }

        }
        return resultBoard;
    }

}
