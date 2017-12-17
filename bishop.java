package chessai;

import java.util.ArrayList;
import java.util.Scanner;

public class bishop extends pieces {

    int value = 3;
    Scanner kbd = new Scanner(System.in);

    public bishop(int x, int y, int direction, String name) {
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
        
        int diagMove = 1;
        System.out.println("Selct an available move(s): ");

        while (inBounds(x + diagMove * direction, y - diagMove)) {
            if (board[x + diagMove * direction][y - diagMove] == ' ') {
                System.out.println("(" + (moveList.size() + 1) + ") " + (x + diagMove * direction) + ":" + (y - diagMove)); //show user the available moves
                char[][] aBoard = new char[board.length][board.length];
                aBoard = copyBoard(board);
                aBoard[x + diagMove * direction][y - diagMove] = 'B';
                aBoard[x][y] = ' ';
                int[] XY = { x + diagMove * direction, y - diagMove};
                updatedXY.add(XY);
                moveList.add(aBoard);
                diagMove++; //increment diagMove
            } else {
                break;
            }
        }//Checks for Upper-Left
        
        diagMove = 1; //reset diagMove
        
        while (inBounds(x + diagMove * direction, y + diagMove)) {
            if (board[x + diagMove * direction][y + diagMove] == ' ') {
                System.out.println("(" + (moveList.size() + 1) + ") " + (x + (diagMove * direction)) + ":" + (y + diagMove)); //show user the available moves
                char[][] aBoard = new char[board.length][board.length];
                aBoard = copyBoard(board);
                aBoard[x + diagMove * direction][y + diagMove] = 'B';
                aBoard[x][y] = ' ';
                int[] XY = { x + diagMove * direction, y + diagMove};
                updatedXY.add(XY);
                moveList.add(aBoard);
                diagMove++;
            } else {
                break;
            }
        }//Checks for Upper-Right
        
        diagMove = 1;
        
        while (inBounds(x + diagMove * -direction, y + diagMove)) {
            if (board[x + diagMove * -direction][y + diagMove ] == ' ') {
                System.out.println("(" + (moveList.size() + 1) + ") " + (x + diagMove * -direction) + ":" + (y + diagMove)); //show user the available moves
                char[][] aBoard = new char[board.length][board.length];
                aBoard = copyBoard(board);
                aBoard[x - diagMove * direction][y + diagMove] = 'B';
                aBoard[x][y] = ' ';
                int[] XY = { x + diagMove * -direction, y + diagMove};
                updatedXY.add(XY);
                moveList.add(aBoard);
                diagMove++;
            } else {
                break;
            }
        }//Checks for Down-Right
        
        diagMove = 1;
        
        while (inBounds(x + diagMove * -direction, y - diagMove)) {
            if (board[x + diagMove * -direction][y - diagMove] == ' ') {
                System.out.println("(" + (moveList.size() + 1) + ") " + (x + diagMove * -direction) + ":" + (y - diagMove)); //show user the available moves
                char[][] aBoard = new char[board.length][board.length];
                aBoard = copyBoard(board);
                aBoard[x + diagMove * -direction][y - diagMove] = 'B';
                aBoard[x][y] = ' ';
                int[] XY = { x +diagMove * -direction, y - diagMove};
                updatedXY.add(XY);
                moveList.add(aBoard);
                diagMove++;
            } else {
                break;
            }
        }//Checks for Down-Left

        if (moveList.size() != 0) {

            boolean validChoice = true;
            while (validChoice) {
                int choice = kbd.nextInt();
                if (choice == 0) {
                    System.out.println("You have deselected this piece");
                    validChoice = false;
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
