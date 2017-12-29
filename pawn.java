package chessai;

import java.util.ArrayList;
import java.util.Scanner;

public class pawn extends pieces {

    int value = 1;
    Scanner kbd = new Scanner(System.in);
    boolean initial = true; //checks if the pawn has moved from initial position

    
    
    
    public pawn(int x, int y, int direction, char name, int team, int value, int AiControl) {
        super(x, y, direction, name, team, value, AiControl);
    }

    public ArrayList<char[][]> move(char[][] board, int x, int y, ArrayList<pieces> HumanList, ArrayList<pieces> AiList) {
        return Available_Moves(board, HumanList, AiList);
    }//x and y describes location of piece

    public ArrayList<char[][]> Available_Moves(char[][] board, ArrayList<pieces> HumanList, ArrayList<pieces> AiList) {

        ArrayList<char[][]> moveList = new ArrayList();

        if (AiControl == 0) {
            System.out.println("Selct an available move(s): ");
        }

        char[][] aBoard = new char[board.length][board.length];
        aBoard = copyBoard(board);

        if (inBounds(x + 1 * direction, y)) {
            if (board[x + 1 * direction][y] == ' ') {
                if (AiControl == 0) {
                    System.out.println("(" + (moveList.size() + 1) + ") " + (x + (1 * direction)) + ":" + (y)); //show user the available moves
                }

                aBoard[x + 1 * direction][y] = this.name; //move the pawn to new location

                aBoard[x][y] = ' '; //remove old location
                int[] XY = {x + 1 * direction, y};
                this.updatedXY.add(XY);
                moveList.add(aBoard);
            }

        }//in bounds check

        aBoard = new char[board.length][board.length];
        aBoard = copyBoard(board);

        if (initial == true) {
            if (inBounds(x + 2 * direction, y)) {
                if (board[x + 2 * direction][y] == ' ') {
                    if (AiControl == 0) {
                        System.out.println("(" + (moveList.size() + 1) + ") " + (x + (2 * direction)) + ":" + (y));
                    }

                    aBoard[x + 2 * direction][y] = this.name;

                    aBoard[x][y] = ' ';
                    int[] XY = {x + 2 * direction, y};
                    this.updatedXY.add(XY);
                    moveList.add(aBoard);

                    initial = false;

                }
            }
        }

        aBoard = new char[board.length][board.length];
        aBoard = copyBoard(board);

        if (inBounds(x + 2 * direction, y - 1)) {
            if (board[x + 1 * direction][y - 1] != ' ') {
                pieces p = checkPiece(x + 1 * direction, y - 1, AiList, HumanList);
                if (p != null) {
                    if (this.team != p.team) {
                        if (AiControl == 0) {
                            System.out.println("(" + (moveList.size() + 1) + ") " + (x + (1 * direction)) + ":" + (y - 1));
                        }
                        aBoard[x + 1 * direction][y - 1] = this.name;
                        aBoard[x][y] = ' ';
                        int[] XY = {x + 1 * direction, y - 1};
                        this.updatedXY.add(XY);
                        moveList.add(aBoard);

                    }
                }
            }//checks to attack left
        }//checks bounds

        aBoard = new char[board.length][board.length];
        aBoard = copyBoard(board);

        if (inBounds(x + 2 * direction, y + 1)) {
            if (board[x + 1 * direction][y + 1] != ' ') {
                pieces p = checkPiece(x + 1 * direction, y + 1, AiList, HumanList);
                if (p != null) {
                    if (this.team != p.team) {
                        if (AiControl == 0) {
                            System.out.println("(" + (moveList.size() + 1) + ") " + (x + (1 * direction)) + ":" + (y + 1));
                        }
                        aBoard[x + 1 * direction][y + 1] = this.name;
                        aBoard[x][y] = ' ';
                        int[] XY = {x + 1 * direction, y + 1};
                        this.updatedXY.add(XY);
                        moveList.add(aBoard);

                    }
                }
            }//checks to attack right
        }//checks bounds

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
