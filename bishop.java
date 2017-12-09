package chessai;

import java.util.ArrayList;
import java.util.Scanner;

public class bishop extends pieces {

    int value = 3;
    Scanner kbd = new Scanner(System.in);

    public bishop(int x, int y, int direction) {
        super(x, y, direction);
    }

    public char[][] move(char[][] board, int x, int y) {
        ArrayList<char[][]> movesList = new ArrayList();
        board = Available_Moves(board, movesList);
        return board;
    }//x and y describes location of piece

    public char[][] Available_Moves(char[][] board, ArrayList<char[][]> movesList) {

        ArrayList<char[][]> moveList = new ArrayList();
        int diagMove = 1;
        System.out.println("Selct an available move(s): ");

        while (board[x + diagMove * direction][y + diagMove * direction] == ' ') {
            System.out.println("(" + (moveList.size() + 1) + ") " + (x + (diagMove * direction)) + ":" + (y + (diagMove * direction))); //show user the available moves
            char[][] aBoard = new char[board.length][board.length];
            aBoard = copyBoard(board);
            aBoard[x + diagMove * direction][y + diagMove * direction] = 'B';
            aBoard[x][y] = ' ';
            moveList.add(aBoard);
        }//Checks for Upper-Left

        while (board[x + diagMove * direction][y - diagMove * direction] == ' ') {
            System.out.println("(" + (moveList.size() + 1) + ") " + (x + (diagMove * direction)) + ":" + (y - (diagMove * direction))); //show user the available moves
            char[][] aBoard = new char[board.length][board.length];
            aBoard = copyBoard(board);
            aBoard[x + diagMove * direction][y - diagMove * direction] = 'B';
            aBoard[x][y] = ' ';
            moveList.add(aBoard);
        }//Checks for Upper-Right

        /*while (board[x - diagMove * direction][y - diagMove * direction] == ' ') {
            System.out.println("(" + (moveList.size() + 1) + ") " + (x - (diagMove * direction)) + ":" + (y - (diagMove * direction))); //show user the available moves
            char[][] aBoard = new char[board.length][board.length];
            aBoard = copyBoard(board);
            aBoard[x - diagMove * direction][y - diagMove * direction] = 'B';
            aBoard[x][y] = ' ';
            moveList.add(aBoard);
        }//Checks for Down-Right

        while (board[x - diagMove * direction][y + diagMove * direction] == ' ') {
            System.out.println("(" + (moveList.size() + 1) + ") " + (x - (diagMove * direction)) + ":" + (y + (diagMove * direction))); //show user the available moves
            char[][] aBoard = new char[board.length][board.length];
            aBoard = copyBoard(board);
            aBoard[x - diagMove * direction][y + diagMove * direction] = 'B';
            aBoard[x][y] = ' ';
            moveList.add(aBoard);
        }//Checks for Down-Left
        
        *///TODO:MAKE OUT OF BOUNDS EXCEPTION
        
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
