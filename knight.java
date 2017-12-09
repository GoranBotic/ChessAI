package chessai;

import java.util.ArrayList;
import java.util.Scanner;

public class knight extends pieces {
    int value = 3;
    Scanner kbd = new Scanner(System.in);
    public knight(int x,int y,int direction) {
        super(x,y,direction);
    }
    
     public char[][] move(char[][] board, int x, int y) {
        ArrayList<char[][]> movesList = new ArrayList();
        board = Available_Moves(board, movesList);
        return board;
    }//x and y describes location of piece

    public char[][] Available_Moves(char[][] board, ArrayList<char[][]> movesList) {

        ArrayList<char[][]> moveList = new ArrayList();
        int vertMove=2,horiMove=1;
        
        System.out.println("Selct an available move(s): ");
        if (board[x + vertMove * direction][y + horiMove] == ' ') {
            System.out.println("(" + (moveList.size() + 1) + ") " + (x + (1 * direction)) + ":" + (y)); //show user the available moves
            char[][] aBoard = new char[board.length][board.length];
            aBoard = copyBoard(board);
            aBoard[x + vertMove * direction][y+horiMove] = 'N';
            aBoard[x][y] = ' ';
            moveList.add(aBoard);
        }//checks Up-right Move
        
        if (board[x + vertMove * direction][y - horiMove] == ' ') {
            System.out.println("(" + (moveList.size() + 1) + ") " + x + ":" + (y+ (1 * direction))); //show user the available moves
            char[][] aBoard = new char[board.length][board.length];
            aBoard = copyBoard(board);
            aBoard[x + vertMove * direction][y-horiMove] = 'N';
            aBoard[x][y] = ' ';
            moveList.add(aBoard);
        }//checks Up-Left Move
        
        /*if (board[x + vertMove * -direction][y - horiMove] == ' ') {
            System.out.println("(" + (moveList.size() + 1) + ") " + x  + ":" + (y+ (1 * -direction))); //show user the available moves
            char[][] aBoard = new char[board.length][board.length];
            aBoard = copyBoard(board);
            aBoard[x + vertMove * -direction][y-horiMove] = 'N';
            aBoard[x][y] = ' ';
            moveList.add(aBoard);
        }//checks Down-Left Move
        
        if (board[x + vertMove * -direction][y + horiMove] == ' ') {
            System.out.println("(" + (moveList.size() + 1) + ") " + (x + (1 * -direction)) + ":" + (y)); //show user the available moves
            char[][] aBoard = new char[board.length][board.length];
            aBoard = copyBoard(board);
            aBoard[x + vertMove * -direction][y+horiMove] = 'N';
            aBoard[x][y] = ' ';
            moveList.add(aBoard);
        }//checks Down-Right Move
        
        *///TODO:CHECK FOR OUT OF BOUNDS

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
