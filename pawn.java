package chessai;

import java.util.ArrayList;
import java.util.Scanner;

public class pawn extends pieces {

    int value = 1;
    Scanner kbd = new Scanner(System.in);
    boolean initial = true; //checks if the pawn has moved from initial position
   

    public pawn(int x, int y, int direction, String name, int team) {
        super(x, y, direction, name, team);
    }

    public char[][] move(char[][] board, int x, int y, ArrayList<pieces> HumanList, ArrayList<pieces> AiList) {
        ArrayList<char[][]> movesList = new ArrayList();
        board = Available_Moves(board, movesList, HumanList, AiList);
        return board;
    }//x and y describes location of piece

    public char[][] Available_Moves(char[][] board, ArrayList<char[][]> movesList, ArrayList<pieces> HumanList, ArrayList<pieces> AiList) {

        ArrayList<char[][]> moveList = new ArrayList();
        ArrayList<int[]> updatedXY = new ArrayList(); //stores changed x and y in respect with board states

        System.out.println("Selct an available move(s): ");
        char[][] aBoard = new char[board.length][board.length];
        aBoard = copyBoard(board);

        if (inBounds(x + 1 * direction, y)) {
            if (board[x + 1 * direction][y] == ' ') {
                System.out.println("(" + (moveList.size() + 1) + ") " + (x + (1 * direction)) + ":" + (y)); //show user the available moves
                aBoard[x + 1 * direction][y] = 'P'; //move the pawn to new location
                aBoard[x][y] = ' '; //remove old location
                int[] XY = {x + 1 * direction, y};
                updatedXY.add(XY);
                moveList.add(aBoard);
            }

        }//in bounds check

        aBoard = new char[board.length][board.length];
        aBoard = copyBoard(board);

        if (initial == true) {
            if (inBounds(x + 2 * direction, y)) {
                if (board[x + 2 * direction][y] == ' ') {
                    System.out.println("(" + (moveList.size() + 1) + ") " + (x + (2 * direction)) + ":" + (y));
                    aBoard[x + 2 * direction][y] = 'P';
                    aBoard[x][y] = ' ';
                    int[] XY = {x + 2 * direction, y};
                    updatedXY.add(XY);
                    moveList.add(aBoard);
                    initial = false;
                }
            }
        }
        
        aBoard = new char[board.length][board.length];
        aBoard = copyBoard(board);
        
        if (inBounds(x + 2 * direction, y-1)) {
            if (board[x + 1 * direction][y - 1] != ' ') {
                pieces p = checkPiece(x + 1 * direction, y - 1, AiList, HumanList);
                if (p != null) {
                    if (this.team != p.team) {
                        System.out.println("(" + (moveList.size() + 1) + ") " + (x + (1 * direction)) + ":" + (y - 1));
                        aBoard[x + 1 * direction][y - 1] = 'P';
                        aBoard[x][y] = ' ';
                        int[] XY = {x + 1 * direction, y - 1};
                        updatedXY.add(XY);
                        moveList.add(aBoard);
                        this.removePiece(p, AiList, HumanList);
                    }
                }
            }//checks to attack left
        }//checks bounds
        
        aBoard = new char[board.length][board.length];
        aBoard = copyBoard(board);
        
        if (inBounds(x + 2 * direction, y+1)) {
            if (board[x + 1 * direction][y + 1] != ' ') {
                pieces p = checkPiece(x + 1 * direction, y + 1, AiList, HumanList);
                if (p != null) {
                    if (this.team != p.team) {
                        System.out.println("(" + (moveList.size() + 1) + ") " + (x + (1 * direction)) + ":" + (y + 1));
                        aBoard[x + 1 * direction][y + 1] = 'P';
                        aBoard[x][y] = ' ';
                        int[] XY = {x + 1 * direction, y + 1};
                        updatedXY.add(XY);
                        moveList.add(aBoard);
                        this.removePiece(p, AiList, HumanList);
                    }
                }
            }//checks to attack right
        }//checks bounds

        if (moveList.size() != 0) {

            boolean validChoice = true; //this will flag if user picked an integer available from the list
            while (validChoice) {
                int choice = kbd.nextInt();
                if (choice == 0) {
                    System.out.println("You have deselected this piece");
                    validChoice = false; //this breaks out of loop and nothing happens to board
                } else {
                    if (choice > 0 && choice <= moveList.size()) {
                        validChoice = false;
                        int[] XY = updatedXY.get(choice - 1);
                        x = XY[0];
                        y = XY[1];
                        return moveList.get(choice - 1);
                    } else {
                        System.out.println("That was an invalid choice,please try again");
                    }
                }
            }
        } else {
            System.out.println("No moves available");
        }//check if there are no available moves
        return board;
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
