package chessai;

import java.util.ArrayList;
import java.util.Scanner;

public class rook extends pieces {

    int value = 5;
    Scanner kbd = new Scanner(System.in);

    public rook(int x, int y, int direction, String name) {
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
        int aMove = 1;
        

        System.out.println("Selct an available move(s): ");
        while (inBounds(x + aMove * direction, y)) {
            if (board[x + aMove * direction][y] == ' ') {

                System.out.println("(" + (moveList.size() + aMove) + ") " + (x + aMove * direction) + ":" + y); //show user the available moves
                char[][] aBoard = new char[board.length][board.length];
                aBoard = copyBoard(board);
                aBoard[x + aMove * direction][y] = 'R';
                aBoard[x][y] = ' ';
                int[] XY = {x + aMove * direction, y};
                updatedXY.add(XY);
                moveList.add(aBoard);
                aMove++;

            }else{
                break;
            }//looks for moves Up
        }
        aMove = 1;
        while (inBounds(x + aMove * -direction, y)) {
            if (board[x + aMove * -direction][y] == ' ') {

                System.out.println("(" + (moveList.size() + aMove) + ") " + (x + aMove * -direction) + ":" + y); //show user the available moves
                char[][] aBoard = new char[board.length][board.length];
                aBoard = copyBoard(board);
                aBoard[x + aMove * -direction][y] = 'R';
                aBoard[x][y] = ' ';
                int[] XY = {x + aMove * -direction, y};
                updatedXY.add(XY);
                moveList.add(aBoard);
                aMove++;

            }else{
                break;
            }//looks for moves Down
        }
        aMove = 1;
        while (inBounds(x, y + aMove * direction)) {
            if (board[x][y + aMove * direction] == ' ') {

                System.out.println("(" + (moveList.size() + 1) + ") " + x + ":" + (y + aMove * direction)); //show user the available moves
                char[][] aBoard = new char[board.length][board.length];
                aBoard = copyBoard(board);
                aBoard[x][y + aMove * direction] = 'R';
                aBoard[x][y] = ' ';
                int[] XY = {x,y + aMove * direction};
                updatedXY.add(XY);
                moveList.add(aBoard);
                aMove++;
            }else{
                break;
            }//looks for moves Left
        }
        aMove = 1;
        while (inBounds(x, y + aMove * -direction)) {
            if (board[x][y + aMove * -direction] == ' ') {

                System.out.println("(" + (moveList.size() + 1) + ") " + x + ":" + (y + aMove * -direction)); //show user the available moves
                char[][] aBoard = new char[board.length][board.length];
                aBoard = copyBoard(board);
                aBoard[x][y + aMove * -direction] = 'R';
                aBoard[x][y] = ' ';
                int[] XY = {x,y + aMove * -direction};
                updatedXY.add(XY);
                moveList.add(aBoard);
                aMove++;
            }else{
                break;
            }//looks for moves Right
        }

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
