package chessai;

import java.util.ArrayList;
import java.util.Scanner;

public class knight extends pieces {

    int value = 3;
    Scanner kbd = new Scanner(System.in);
    int pX, pY;

    public knight(int x, int y, int direction, String name, int team) {
        super(x, y, direction, name, team);
        pX = x;
        pY = y;
    }

    public char[][] move(char[][] board, int x, int y, ArrayList<pieces> HumanList, ArrayList<pieces> AiList) {
        ArrayList<char[][]> movesList = new ArrayList();
        board = Available_Moves(board, movesList, HumanList, AiList);
        return board;
    }//x and y describes location of piece

    public char[][] Available_Moves(char[][] board, ArrayList<char[][]> movesList, ArrayList<pieces> HumanList, ArrayList<pieces> AiList) {

        ArrayList<char[][]> moveList = new ArrayList(); //stores all available board states
        ArrayList<int[]> updatedXY = new ArrayList(); //stores changed x and y in respect with board states
        int vertMove = 2, horiMove = 1; //L shape

        System.out.println("Selct an available move(s): ");

        char[][] aBoard = new char[board.length][board.length];
        aBoard = copyBoard(board);

        if (inBounds(x + vertMove * direction, y + horiMove)) {
            if (board[x + vertMove * direction][y + horiMove] == ' ') {
                System.out.println("(" + (moveList.size() + 1) + ") " + (x + vertMove * direction) + ":" + (y + horiMove)); //show user the available moves
                aBoard[x + vertMove * direction][y + horiMove] = 'N';
                aBoard[x][y] = ' ';
                int[] XY = {x + vertMove * direction, y + horiMove};
                updatedXY.add(XY);
                moveList.add(aBoard);
            } else {
                if (board[x + vertMove * direction][y + horiMove] != ' ') {
                    pieces p = checkPiece(x + vertMove * direction, y + horiMove, AiList, HumanList);
                    if (p != null) {
                        if (this.team != p.team) {
                            System.out.println("(" + (moveList.size() + 1) + ") " + (x + vertMove * direction) + ":" + (y + horiMove));
                            aBoard[x + vertMove * direction][y + horiMove] = 'N';
                            aBoard[x][y] = ' ';
                            int[] XY = {x + vertMove * direction, y + horiMove};
                            updatedXY.add(XY);
                            moveList.add(aBoard);
                        }
                    }
                }//checks to attack Up-right

            }//checks Up-right Move
        }

        aBoard = new char[board.length][board.length];
        aBoard = copyBoard(board);

        if (inBounds(x + horiMove * direction, y + vertMove)) {
            if (board[x + horiMove * direction][y + vertMove] == ' ') {
                System.out.println("(" + (moveList.size() + 1) + ") " + (x + horiMove * direction) + ":" + (y + vertMove)); //show user the available moves
                aBoard[x + horiMove * direction][y + vertMove] = 'N';
                aBoard[x][y] = ' ';
                int[] XY = {x + horiMove * direction, y + vertMove};
                updatedXY.add(XY);
                moveList.add(aBoard);
            } else {
                if (board[x + horiMove * direction][y + vertMove] != ' ') {
                    pieces p = checkPiece(x + horiMove * direction, y + vertMove, AiList, HumanList);
                    if (p != null) {
                        if (this.team != p.team) {
                            System.out.println("(" + (moveList.size() + 1) + ") " + (x + horiMove * direction) + ":" + (y + vertMove));
                            aBoard[x + horiMove * direction][y + vertMove] = 'N';
                            aBoard[x][y] = ' ';
                            int[] XY = {x + horiMove * direction, y + vertMove};
                            updatedXY.add(XY);
                            moveList.add(aBoard);
                        }
                    }
                }//checks to attack Right-up

            }//checks Right-up Move
        }

        aBoard = new char[board.length][board.length];
        aBoard = copyBoard(board);

        if (inBounds(x + vertMove * direction, y - horiMove)) {
            if (board[x + vertMove * direction][y - horiMove] == ' ') {
                System.out.println("(" + (moveList.size() + 1) + ") " + (x + vertMove * direction) + ":" + (y - horiMove)); //show user the available moves

                aBoard[x + vertMove * direction][y - horiMove] = 'N';
                aBoard[x][y] = ' ';
                int[] XY = {x + vertMove * direction, y - horiMove};
                updatedXY.add(XY);
                moveList.add(aBoard);

            } else {
                if (board[x + vertMove * direction][y - horiMove] != ' ') {
                    pieces p = checkPiece(x + vertMove * direction, y - horiMove, AiList, HumanList);
                    if (p != null) {
                        if (this.team != p.team) {
                            System.out.println("(" + (moveList.size() + 1) + ") " + (x + vertMove * direction) + ":" + (y - horiMove));
                            aBoard[x + vertMove * direction][y - horiMove] = 'N';
                            aBoard[x][y] = ' ';
                            int[] XY = {x + vertMove * direction, y - horiMove};
                            updatedXY.add(XY);
                            moveList.add(aBoard);
                        }
                    }
                }//checks to attack Up-Left

            }//checks Up-Left Move
        }

        aBoard = new char[board.length][board.length];
        aBoard = copyBoard(board);

        if (inBounds(x + horiMove * direction, y - vertMove)) {
            if (board[x + horiMove * direction][y - vertMove] == ' ') {
                System.out.println("(" + (moveList.size() + 1) + ") " + (x + horiMove * direction) + ":" + (y - vertMove)); //show user the available moves

                aBoard[x + horiMove * direction][y - vertMove] = 'N';
                aBoard[x][y] = ' ';
                int[] XY = {x + horiMove * direction, y - vertMove};
                updatedXY.add(XY);
                moveList.add(aBoard);
            } else {
                if (board[x + horiMove * direction][y - vertMove] != ' ') {
                    pieces p = checkPiece(x + horiMove * direction, y - vertMove, AiList, HumanList);
                    if (p != null) {
                        if (this.team != p.team) {
                            System.out.println("(" + (moveList.size() + 1) + ") " + (x + horiMove * direction) + ":" + (y - vertMove));
                            aBoard[x + horiMove * direction][y - vertMove] = 'N';
                            aBoard[x][y] = ' ';
                            int[] XY = {x + horiMove * direction, y - vertMove};
                            updatedXY.add(XY);
                            moveList.add(aBoard);
                        }
                    }
                }//checks to attack Left-up

            }//checks Left-Up Move
        }

        aBoard = new char[board.length][board.length];
        aBoard = copyBoard(board);

        if (inBounds(x + vertMove * -direction, y - horiMove)) {
            if (board[x + vertMove * -direction][y - horiMove] == ' ') {
                System.out.println("(" + (moveList.size() + 1) + ") " + (x + vertMove * -direction) + ":" + (y - horiMove)); //show user the available moves

                aBoard[x + vertMove * -direction][y - horiMove] = 'N';
                aBoard[x][y] = ' ';
                int[] XY = {x + vertMove * -direction, y - horiMove};
                updatedXY.add(XY);
                moveList.add(aBoard);

            } else {
                if (board[x + vertMove * -direction][y - horiMove] != ' ') {
                    pieces p = checkPiece(x + vertMove * -direction, y - horiMove, AiList, HumanList);
                    if (p != null) {
                        if (this.team != p.team) {
                            System.out.println("(" + (moveList.size() + 1) + ") " + (x + vertMove * -direction) + ":" + (y - horiMove));
                            aBoard[x + vertMove * -direction][y - horiMove] = 'N';
                            aBoard[x][y] = ' ';
                            int[] XY = {x + vertMove * -direction, y - horiMove};
                            updatedXY.add(XY);
                            moveList.add(aBoard);
                        }
                    }
                }//checks to attack Down-Left

            }//checks Down-Left Move
        }

        aBoard = new char[board.length][board.length];
        aBoard = copyBoard(board);

        if (inBounds(x + horiMove * -direction, y - vertMove)) {
            if (board[x + horiMove * -direction][y + vertMove] == ' ') {
                System.out.println("(" + (moveList.size() + 1) + ") " + (x + horiMove * -direction) + ":" + (y - vertMove)); //show user the available moves

                aBoard[x + horiMove * -direction][y - vertMove] = 'N';
                aBoard[x][y] = ' ';
                int[] XY = {x + horiMove * -direction, y - vertMove};
                updatedXY.add(XY);
                moveList.add(aBoard);
            } else {
                if (board[x + horiMove * -direction][y - vertMove] != ' ') {
                    pieces p = checkPiece(x + horiMove * -direction, y - vertMove, AiList, HumanList);
                    if (p != null) {
                        if (this.team != p.team) {
                            System.out.println("(" + (moveList.size() + 1) + ") " + (x + horiMove * -direction) + ":" + (y - vertMove));
                            aBoard[x + horiMove * -direction][y - vertMove] = 'N';
                            aBoard[x][y] = ' ';
                            int[] XY = {x + horiMove * -direction, y - vertMove};
                            updatedXY.add(XY);
                            moveList.add(aBoard);
                        }
                    }
                }//checks to attack Left-Down

            }//checks Left-Down Move
        }

        aBoard = new char[board.length][board.length];
        aBoard = copyBoard(board);

        if (inBounds(x + vertMove * -direction, y + horiMove)) {
            if (board[x + vertMove * -direction][y + horiMove] == ' ') {
                System.out.println("(" + (moveList.size() + 1) + ") " + (x + vertMove * -direction) + ":" + (y + horiMove)); //show user the available moves

                aBoard[x + vertMove * -direction][y + horiMove] = 'N';
                aBoard[x][y] = ' ';
                int[] XY = {x + vertMove * -direction, y + horiMove};
                updatedXY.add(XY);
                moveList.add(aBoard);

            } else {
                if (board[x + vertMove * -direction][y + horiMove] != ' ') {
                    pieces p = checkPiece(x + vertMove * -direction, y + horiMove, AiList, HumanList);
                    if (p != null) {
                        if (this.team != p.team) {
                            System.out.println("(" + (moveList.size() + 1) + ") " + (x + vertMove * -direction) + ":" + (y + horiMove));
                            aBoard[x + vertMove * -direction][y + horiMove] = 'N';
                            aBoard[x][y] = ' ';
                            int[] XY = {x + vertMove * -direction, y + horiMove};
                            updatedXY.add(XY);
                            moveList.add(aBoard);
                        }
                    }
                }//checks to attack Down-right

            }//checks Down-Right Move
        }

        aBoard = new char[board.length][board.length];
        aBoard = copyBoard(board);

        if (inBounds(x + horiMove * -direction, y + vertMove)) {
            if (board[x + horiMove * -direction][y + vertMove] == ' ') {
                System.out.println("(" + (moveList.size() + 1) + ") " + (x + horiMove * -direction) + ":" + (y + vertMove)); //show user the available moves
                aBoard[x + horiMove * -direction][y + vertMove] = 'N';
                aBoard[x][y] = ' ';
                int[] XY = {x + horiMove * -direction, y + vertMove};
                updatedXY.add(XY);
                moveList.add(aBoard);
            } else {
                if (board[x + horiMove * -direction][y + vertMove] != ' ') {
                    pieces p = checkPiece(x + horiMove * -direction, y + vertMove, AiList, HumanList);
                    if (p != null) {
                        if (this.team != p.team) {
                            System.out.println("(" + (moveList.size() + 1) + ") " + (x + horiMove * -direction) + ":" + (y + vertMove));
                            aBoard[x + horiMove * -direction][y + vertMove] = 'N';
                            aBoard[x][y] = ' ';
                            int[] XY = {x + horiMove * -direction, y + vertMove};
                            updatedXY.add(XY);
                            moveList.add(aBoard);
                        }
                    }
                }//checks to attack Right-up
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
                       int[] XY = updatedXY.get(choice - 1);//get piece's new location
                        validChoice = false;
                        pieces p = checkPiece(XY[0], XY[1], HumanList, AiList);
                        if (p != null) {
                            this.removePiece(p, HumanList, AiList);
                        }
                        
                        x = XY[0]; 
                        y = XY[1]; //set the new x and y values for this piece

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
