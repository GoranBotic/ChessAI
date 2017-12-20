package chessai;

import java.util.ArrayList;
import java.util.Scanner;

public class bishop extends pieces {

    int value = 3;
    Scanner kbd = new Scanner(System.in);

    public bishop(int x, int y, int direction, String name, int team) {
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
        
        int diagMove = 1;
        System.out.println("Selct an available move(s): ");

        while (inBounds(x + diagMove * direction, y - diagMove)) {
            char[][] aBoard = new char[board.length][board.length];
            aBoard = copyBoard(board); //reset the board pieces
            if (board[x + diagMove * direction][y - diagMove] == ' ') {
                System.out.println("(" + (moveList.size() + 1) + ") " + (x + diagMove * direction) + ":" + (y - diagMove)); //show user the available moves
                aBoard[x + diagMove * direction][y - diagMove] = 'B';
                aBoard[x][y] = ' ';
                int[] XY = {x + diagMove * direction, y - diagMove};
                updatedXY.add(XY);
                moveList.add(aBoard);
                diagMove++; //increment diagMove
            } else {
                if (board[x + diagMove * direction][y - diagMove] != ' ') {
                    pieces p = checkPiece(x + diagMove * direction, y - diagMove, HumanList, AiList);
                    if (p != null) {
                        if (this.team != p.team) {
                            System.out.println("(" + (moveList.size() + 1) + ") " + (x + diagMove * direction) + ":" + (y - diagMove));
                            aBoard[x + diagMove * direction][y - diagMove] = 'B';
                            aBoard[x][y] = ' ';
                            int[] XY = {x + diagMove * direction, y - diagMove};
                            updatedXY.add(XY);
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
                System.out.println("(" + (moveList.size() + 1) + ") " + (x + (diagMove * direction)) + ":" + (y + diagMove)); //show user the available moves

                aBoard[x + diagMove * direction][y + diagMove] = 'B';
                aBoard[x][y] = ' ';
                int[] XY = {x + diagMove * direction, y + diagMove};
                updatedXY.add(XY);
                moveList.add(aBoard);
                diagMove++;
            } else {
                if (board[x + diagMove * direction][y + diagMove] != ' ') {
                    pieces p = checkPiece(x + diagMove * direction, y + diagMove, HumanList, AiList);
                    if (p != null) {
                        if (this.team != p.team) {
                            System.out.println("(" + (moveList.size() + 1) + ") " + (x + diagMove * direction) + ":" + (y + diagMove));
                            aBoard[x + diagMove * direction][y + diagMove] = 'B';
                            aBoard[x][y] = ' ';
                            int[] XY = {x + diagMove * direction, y + diagMove};
                            updatedXY.add(XY);
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
                System.out.println("(" + (moveList.size() + 1) + ") " + (x + diagMove * -direction) + ":" + (y + diagMove)); //show user the available moves

                aBoard[x - diagMove * direction][y + diagMove] = 'B';
                aBoard[x][y] = ' ';
                int[] XY = {x + diagMove * -direction, y + diagMove};
                updatedXY.add(XY);
                moveList.add(aBoard);
                diagMove++;
            } else {
                if (board[x + diagMove * -direction][y + diagMove] != ' ') {
                    pieces p = checkPiece(x + diagMove * -direction, y + diagMove, HumanList, AiList);
                    if (p != null) {
                        if (this.team != p.team) {
                            System.out.println("(" + (moveList.size() + 1) + ") " + (x + diagMove * -direction) + ":" + (y + diagMove));
                            aBoard[x + diagMove * -direction][y + diagMove] = 'B';
                            aBoard[x][y] = ' ';
                            int[] XY = {x + diagMove * -direction, y + diagMove};
                            updatedXY.add(XY);
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
                System.out.println("(" + (moveList.size() + 1) + ") " + (x + diagMove * -direction) + ":" + (y - diagMove)); //show user the available moves
                aBoard[x + diagMove * -direction][y - diagMove] = 'B';
                aBoard[x][y] = ' ';
                int[] XY = {x + diagMove * -direction, y - diagMove};
                updatedXY.add(XY);
                moveList.add(aBoard);
                diagMove++;
            } else {
                if (board[x + diagMove * -direction][y - diagMove] != ' ') {
                    pieces p = checkPiece(x + diagMove * -direction, y - diagMove, HumanList, AiList);
                    if (p != null) {
                        if (this.team != p.team) {
                            System.out.println("(" + (moveList.size() + 1) + ") " + (x + diagMove * -direction) + ":" + (y - diagMove));
                            aBoard[x + diagMove * -direction][y - diagMove] = 'B';
                            aBoard[x][y] = ' ';
                            int[] XY = {x + diagMove * -direction, y - diagMove};
                            updatedXY.add(XY);
                            moveList.add(aBoard);
                        }
                    }
                }//checks to attack Down-Right
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
                        int[] XY = updatedXY.get(choice - 1);//get piece's new location
                        validChoice = false;
                        pieces p = checkPiece(XY[0], XY[1], HumanList, AiList);
                        if (p != null) {
                            this.removePiece(p, HumanList, AiList);
                        }
                        
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
