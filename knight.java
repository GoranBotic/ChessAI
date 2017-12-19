package chessai;

import java.util.ArrayList;
import java.util.Scanner;

public class knight extends pieces {

    int value = 3;
    Scanner kbd = new Scanner(System.in);
    int pX, pY;

    public knight(int x, int y, int direction, String name,int team) {
        super(x, y, direction, name,team);
        pX = x;
        pY = y;
    }

    public char[][] move(char[][] board, int x, int y) {
        ArrayList<char[][]> movesList = new ArrayList();
        board = Available_Moves(board, movesList);
        return board;
    }//x and y describes location of piece

    public char[][] Available_Moves(char[][] board, ArrayList<char[][]> movesList) {

        ArrayList<char[][]> moveList = new ArrayList(); //stores all available board states
        ArrayList<int[]> updatedXY = new ArrayList(); //stores changed x and y in respect with board states
        int vertMove = 2, horiMove = 1; //L shape

        System.out.println("Selct an available move(s): ");

        if (inBounds(x + vertMove * direction, y + horiMove)) {
            if (board[x + vertMove * direction][y + horiMove] == ' ') {
                System.out.println("(" + (moveList.size() + 1) + ") " + (x + vertMove * direction) + ":" + (y + horiMove)); //show user the available moves
                char[][] aBoard = new char[board.length][board.length];
                aBoard = copyBoard(board);
                aBoard[x + vertMove * direction][y + horiMove] = 'N';
                aBoard[x][y] = ' ';
                int[] XY = {x + vertMove * direction, y + horiMove};
                updatedXY.add(XY);
                moveList.add(aBoard);
            }//checks Up-right Move
        }

        if (inBounds(x + horiMove * direction, y + vertMove)) {
            if (board[x + horiMove * direction][y + vertMove] == ' ') {
                System.out.println("(" + (moveList.size() + 1) + ") " + (x + horiMove * direction) + ":" + (y + vertMove)); //show user the available moves
                char[][] aBoard = new char[board.length][board.length];
                aBoard = copyBoard(board);
                aBoard[x + horiMove * direction][y + vertMove] = 'N';
                aBoard[x][y] = ' ';
                int[] XY = {x + horiMove * direction, y + vertMove};
                updatedXY.add(XY);
                moveList.add(aBoard);
            }//checks Right-up Move
        }

        if (inBounds(x + vertMove * direction, y - horiMove)) {
            if (board[x + vertMove * direction][y - horiMove] == ' ') {
                System.out.println("(" + (moveList.size() + 1) + ") " + (x + vertMove * direction) + ":" + (y - horiMove)); //show user the available moves
                char[][] aBoard = new char[board.length][board.length];
                aBoard = copyBoard(board);
                aBoard[x + vertMove * direction][y - horiMove] = 'N';
                aBoard[x][y] = ' ';
                int[] XY = {x + vertMove * direction, y - horiMove};
                updatedXY.add(XY);
                moveList.add(aBoard);

            }//checks Up-Left Move
        }
        
        if (inBounds(x + horiMove * direction, y - vertMove)) {
            if (board[x + horiMove * direction][y - vertMove] == ' ') {
                System.out.println("(" + (moveList.size() + 1) + ") " + (x + horiMove * direction) + ":" + (y - vertMove)); //show user the available moves
                char[][] aBoard = new char[board.length][board.length];
                aBoard = copyBoard(board);
                aBoard[x + horiMove * direction][y + vertMove] = 'N';
                aBoard[x][y] = ' ';
                int[] XY = {x + horiMove * direction, y - vertMove};
                updatedXY.add(XY);
                moveList.add(aBoard);
            }//checks Left-Up Move
        }

        if (inBounds(x + vertMove * -direction, y - horiMove)) {
            if (board[x + vertMove * -direction][y - horiMove] == ' ') {
                System.out.println("(" + (moveList.size() + 1) + ") " + (x + vertMove * -direction) + ":" + (y - horiMove)); //show user the available moves
                char[][] aBoard = new char[board.length][board.length];
                aBoard = copyBoard(board);
                aBoard[x + vertMove * -direction][y - horiMove] = 'N';
                aBoard[x][y] = ' ';
                int[] XY = {x + vertMove * -direction, y - horiMove};
                updatedXY.add(XY);
                moveList.add(aBoard);

            }//checks Down-Left Move
        }
        
        if (inBounds(x + horiMove * -direction, y - vertMove)) {
            if (board[x + horiMove * -direction][y + vertMove] == ' ') {
                System.out.println("(" + (moveList.size() + 1) + ") " + (x + horiMove * -direction) + ":" + (y - vertMove)); //show user the available moves
                char[][] aBoard = new char[board.length][board.length];
                aBoard = copyBoard(board);
                aBoard[x + horiMove * -direction][y - vertMove] = 'N';
                aBoard[x][y] = ' ';
                int[] XY = {x + horiMove * -direction, y - vertMove};
                updatedXY.add(XY);
                moveList.add(aBoard);
            }//checks Left-Down Move
        }
        
        if (inBounds(x + vertMove * -direction, y + horiMove)) {
            if (board[x + vertMove * -direction][y + horiMove] == ' ') {
                System.out.println("(" + (moveList.size() + 1) + ") " + (x + vertMove * -direction) + ":" + (y + horiMove)); //show user the available moves
                char[][] aBoard = new char[board.length][board.length];
                aBoard = copyBoard(board);
                aBoard[x + vertMove * -direction][y + horiMove] = 'N';
                aBoard[x][y] = ' ';
                int[] XY = {x + vertMove * -direction, y + horiMove};
                updatedXY.add(XY);
                moveList.add(aBoard);

            }//checks Down-Right Move
        }
        
        if (inBounds(x + horiMove * -direction, y + vertMove)) {
            if (board[x + horiMove * -direction][y + vertMove] == ' ') {
                System.out.println("(" + (moveList.size() + 1) + ") " + (x + horiMove * -direction) + ":" + (y + vertMove)); //show user the available moves
                char[][] aBoard = new char[board.length][board.length];
                aBoard = copyBoard(board);
                aBoard[x + horiMove * -direction][y + vertMove] = 'N';
                aBoard[x][y] = ' ';
                int[] XY = {x + horiMove * -direction, y + vertMove};
                updatedXY.add(XY);
                moveList.add(aBoard);
            }//checks Right-Down Move
        }

        if (moveList.size() != 0) {

            boolean validChoice = true;
            while (validChoice) {
                System.out.println("Select 0 if you want to deselect piece");
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
