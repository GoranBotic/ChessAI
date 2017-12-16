package chessai;

import java.util.ArrayList;
import java.util.Scanner;

public class pawn extends pieces {

    int value = 1;
    Scanner kbd = new Scanner(System.in);
    boolean intial = true; //checks if the pawn has moved from initial position

    public pawn(int x, int y, int direction, String name) {
        super(x, y, direction, name);
    }

    public char[][] move(char[][] board, int x, int y) {
        ArrayList<char[][]> movesList = new ArrayList();
        board = Available_Moves(board, movesList);
        return board;
    }//x and y describes location of piece

    public char[][] Available_Moves(char[][] board, ArrayList<char[][]> movesList) {

        ArrayList<char[][]> moveList = new ArrayList();
        ArrayList<int[]> updatedXY = new ArrayList(); //stores changed x and y in respect with board states

        System.out.println("Selct an available move(s): ");
        if (board[x + 1 * direction][y] == ' ') {
            System.out.println("(" + (moveList.size() + 1) + ") " + (x + (1 * direction)) + ":" + (y)); //show user the available moves
            char[][] aBoard = new char[board.length][board.length];
            aBoard = copyBoard(board);
            aBoard[x + 1 * direction][y] = 'P'; //move the pawn to new location
            aBoard[x][y] = ' '; //remove old location
            int[] XY = {x + 1 * direction, y};
            updatedXY.add(XY);
            moveList.add(aBoard);
        }

        if (intial == true) {
            if (board[x + 2 * direction][y] == ' ') {
                System.out.println("(" + (moveList.size() + 1) + ") " + (x + (2 * direction)) + ":" + (y));
                char[][] aBoard = new char[board.length][board.length];
                aBoard = copyBoard(board);
                aBoard[x + 2 * direction][y] = 'P';
                aBoard[x][y] = ' ';
                int[] XY = {x + 2 * direction, y};
                updatedXY.add(XY);
                moveList.add(aBoard);

            }
        }

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
