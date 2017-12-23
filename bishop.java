package chessai;

import java.util.ArrayList;
import java.util.Scanner;

public class bishop extends pieces {

    int value = 3;
    Scanner kbd = new Scanner(System.in);

    public bishop(int x, int y, int direction, String name, int team) {
        super(x, y, direction, name, team);
    }

    public ArrayList<char[][]> move(char[][] board, int x, int y, ArrayList<pieces> HumanList, ArrayList<pieces> AiList) {
       
        return Available_Moves(board, HumanList, AiList);
        
    }//x and y describes location of piece

    public ArrayList<char[][]> Available_Moves(char[][] board, ArrayList<pieces> HumanList, ArrayList<pieces> AiList) {

        ArrayList<char[][]> moveList = new ArrayList();
        

        int diagMove = 1;
        if (this.team == 0) {
            System.out.println("Selct an available move(s): ");
        }

        while (inBounds(x + diagMove * direction, y - diagMove)) {
            char[][] aBoard = new char[board.length][board.length];
            aBoard = copyBoard(board); //reset the board pieces
            if (board[x + diagMove * direction][y - diagMove] == ' ') {
                if (this.team == 0) {
                    System.out.println("(" + (moveList.size() + 1) + ") " + (x + diagMove * direction) + ":" + (y - diagMove)); //show user the available moves
                }
                aBoard[x + diagMove * direction][y - diagMove] = 'B';
                aBoard[x][y] = ' ';
                int[] XY = {x + diagMove * direction, y - diagMove};
                this.updatedXY.add(XY);
                moveList.add(aBoard);
                diagMove++; //increment diagMove
            } else {
                if (board[x + diagMove * direction][y - diagMove] != ' ') {
                    pieces p = checkPiece(x + diagMove * direction, y - diagMove, HumanList, AiList);
                    if (p != null) {
                        if (this.team != p.team) {
                            if (this.team == 0) {
                                System.out.println("(" + (moveList.size() + 1) + ") " + (x + diagMove * direction) + ":" + (y - diagMove));
                            }
                            aBoard[x + diagMove * direction][y - diagMove] = 'B';
                            aBoard[x][y] = ' ';
                            int[] XY = {x + diagMove * direction, y - diagMove};
                            this.updatedXY.add(XY);
                            moveList.add(aBoard);
                        }
                    }
                }//checks to attack Upper-left
                break;
            }
        }//Checks for Upper-Left

        diagMove = 1; //reset diagMove

        while (inBounds(x + diagMove * direction, y + diagMove)) {
            char[][] aBoard = new char[board.length][board.length];
            aBoard = copyBoard(board);
            if (board[x + diagMove * direction][y + diagMove] == ' ') {
                if (this.team == 0) {
                    System.out.println("(" + (moveList.size() + 1) + ") " + (x + (diagMove * direction)) + ":" + (y + diagMove)); //show user the available moves
                }
                aBoard[x + diagMove * direction][y + diagMove] = 'B';
                aBoard[x][y] = ' ';
                int[] XY = {x + diagMove * direction, y + diagMove};
                this.updatedXY.add(XY);
                moveList.add(aBoard);
                diagMove++;
            } else {
                if (board[x + diagMove * direction][y + diagMove] != ' ') {
                    pieces p = checkPiece(x + diagMove * direction, y + diagMove, HumanList, AiList);
                    if (p != null) {
                        if (this.team != p.team) {
                            if (this.team == 0) {
                                System.out.println("(" + (moveList.size() + 1) + ") " + (x + diagMove * direction) + ":" + (y + diagMove));
                            }
                            aBoard[x + diagMove * direction][y + diagMove] = 'B';
                            aBoard[x][y] = ' ';
                            int[] XY = {x + diagMove * direction, y + diagMove};
                            this.updatedXY.add(XY);
                            moveList.add(aBoard);
                        }
                    }
                }//checks to attack Upper-Right
                break;
            }
        }//Checks for Upper-Right

        diagMove = 1;

        while (inBounds(x + diagMove * -direction, y + diagMove)) {
            char[][] aBoard = new char[board.length][board.length];
            aBoard = copyBoard(board);
            if (board[x + diagMove * -direction][y + diagMove] == ' ') {
                if (this.team == 0) {
                    System.out.println("(" + (moveList.size() + 1) + ") " + (x + diagMove * -direction) + ":" + (y + diagMove)); //show user the available moves
                }
                aBoard[x - diagMove * direction][y + diagMove] = 'B';
                aBoard[x][y] = ' ';
                int[] XY = {x + diagMove * -direction, y + diagMove};
                this.updatedXY.add(XY);
                moveList.add(aBoard);
                diagMove++;
            } else {
                if (board[x + diagMove * -direction][y + diagMove] != ' ') {
                    pieces p = checkPiece(x + diagMove * -direction, y + diagMove, HumanList, AiList);
                    if (p != null) {
                        if (this.team != p.team) {
                            if (this.team == 0) {
                                System.out.println("(" + (moveList.size() + 1) + ") " + (x + diagMove * -direction) + ":" + (y + diagMove));
                            }
                            aBoard[x + diagMove * -direction][y + diagMove] = 'B';
                            aBoard[x][y] = ' ';
                            int[] XY = {x + diagMove * -direction, y + diagMove};
                            this.updatedXY.add(XY);
                            moveList.add(aBoard);
                        }
                    }
                }//checks to attack Down-Right
                break;
            }
        }//Checks for Down-Right

        diagMove = 1;

        while (inBounds(x + diagMove * -direction, y - diagMove)) {
            char[][] aBoard = new char[board.length][board.length];
            aBoard = copyBoard(board);
            if (board[x + diagMove * -direction][y - diagMove] == ' ') {
                if (this.team == 0) {
                    System.out.println("(" + (moveList.size() + 1) + ") " + (x + diagMove * -direction) + ":" + (y - diagMove)); //show user the available moves
                }
                aBoard[x + diagMove * -direction][y - diagMove] = 'B';
                aBoard[x][y] = ' ';
                int[] XY = {x + diagMove * -direction, y - diagMove};
                this.updatedXY.add(XY);
                moveList.add(aBoard);
                diagMove++;
            } else {
                if (board[x + diagMove * -direction][y - diagMove] != ' ') {
                    pieces p = checkPiece(x + diagMove * -direction, y - diagMove, HumanList, AiList);
                    if (p != null) {
                        if (this.team != p.team) {
                            if (this.team == 0) {
                                System.out.println("(" + (moveList.size() + 1) + ") " + (x + diagMove * -direction) + ":" + (y - diagMove));
                            }
                            aBoard[x + diagMove * -direction][y - diagMove] = 'B';
                            aBoard[x][y] = ' ';
                            int[] XY = {x + diagMove * -direction, y - diagMove};
                            this.updatedXY.add(XY);
                            moveList.add(aBoard);
                        }
                    }
                }//checks to attack Down-Right
                break;
            }
        }//Checks for Down-Left

        
        return moveList;
    }//

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
