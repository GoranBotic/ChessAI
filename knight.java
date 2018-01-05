package chessai;

import java.util.ArrayList;
import java.util.Scanner;

public class knight extends pieces {

    int value = 3;
    Scanner kbd = new Scanner(System.in);

    public knight(int x, int y, int direction, char name, int team, int value, int AiControl) {
        super(x, y, direction, name, team, value, AiControl);
    }

    public ArrayList<char[][]> move(char[][] board, int x, int y, ArrayList<pieces> HumanList, ArrayList<pieces> AiList) {

        return Available_Moves(board, HumanList, AiList);

    }//x and y describes location of piece

    public ArrayList<char[][]> Available_Moves(char[][] board, ArrayList<pieces> HumanList, ArrayList<pieces> AiList) {

        ArrayList<char[][]> moveList = new ArrayList();

        ArrayList<int[]> updatedXY = new ArrayList(); //stores changed x and y in respect with board states
        int vertMove = 2, horiMove = 1; //L shape

        if (AiControl == 0) {
            System.out.println("Selct an available move(s): ");
        }
        char[][] aBoard = new char[board.length][board.length];
        aBoard = copyBoard(board);

        if (inBounds(x + vertMove * direction, y + horiMove)) {
            if (board[x + vertMove * direction][y + horiMove] == ' ') {
                if (AiControl == 0) {
                    System.out.println("(" + (moveList.size() + 1) + ") " + (x + vertMove * direction) + ":" + (y + horiMove)); //show user the available moves
                }

                aBoard[x + vertMove * direction][y + horiMove] = this.name;

                aBoard[x][y] = ' ';
                int[] XY = {x + vertMove * direction, y + horiMove};
                this.updatedXY.add(XY);
                moveList.add(aBoard);
            } else {
                if (board[x + vertMove * direction][y + horiMove] != ' ') {
                    pieces p = checkPiece(x + vertMove * direction, y + horiMove, AiList, HumanList);
                    if (p != null) {
                        if (this.team != p.team) {
                            if (AiControl == 0) {
                                System.out.println("(" + (moveList.size() + 1) + ") " + (x + vertMove * direction) + ":" + (y + horiMove));
                            }
                            aBoard[x + vertMove * direction][y + horiMove] = this.name;
                            aBoard[x][y] = ' ';
                            int[] XY = {x + vertMove * direction, y + horiMove};
                            this.updatedXY.add(XY);
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
                if (AiControl == 0) {
                    System.out.println("(" + (moveList.size() + 1) + ") " + (x + horiMove * direction) + ":" + (y + vertMove)); //show user the available moves
                }
                aBoard[x + horiMove * direction][y + vertMove] = this.name;
                aBoard[x][y] = ' ';
                int[] XY = {x + horiMove * direction, y + vertMove};
                this.updatedXY.add(XY);
                moveList.add(aBoard);
            } else {
                if (board[x + horiMove * direction][y + vertMove] != ' ') {
                    pieces p = checkPiece(x + horiMove * direction, y + vertMove, AiList, HumanList);
                    if (p != null) {
                        if (this.team != p.team) {
                            if (AiControl == 0) {
                                System.out.println("(" + (moveList.size() + 1) + ") " + (x + horiMove * direction) + ":" + (y + vertMove));
                            }
                            aBoard[x + horiMove * direction][y + vertMove] = this.name;
                            aBoard[x][y] = ' ';
                            int[] XY = {x + horiMove * direction, y + vertMove};
                            this.updatedXY.add(XY);
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
                if (AiControl == 0) {
                    System.out.println("(" + (moveList.size() + 1) + ") " + (x + vertMove * direction) + ":" + (y - horiMove)); //show user the available moves
                }
                aBoard[x + vertMove * direction][y - horiMove] = this.name;
                aBoard[x][y] = ' ';
                int[] XY = {x + vertMove * direction, y - horiMove};
                this.updatedXY.add(XY);
                moveList.add(aBoard);

            } else {
                if (board[x + vertMove * direction][y - horiMove] != ' ') {
                    pieces p = checkPiece(x + vertMove * direction, y - horiMove, AiList, HumanList);
                    if (p != null) {
                        if (this.team != p.team) {
                            if (AiControl == 0) {
                                System.out.println("(" + (moveList.size() + 1) + ") " + (x + vertMove * direction) + ":" + (y - horiMove));
                            }
                            aBoard[x + vertMove * direction][y - horiMove] = this.name;
                            aBoard[x][y] = ' ';
                            int[] XY = {x + vertMove * direction, y - horiMove};
                            this.updatedXY.add(XY);
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
                if (AiControl == 0) {
                    System.out.println("(" + (moveList.size() + 1) + ") " + (x + horiMove * direction) + ":" + (y - vertMove)); //show user the available moves
                }
                aBoard[x + horiMove * direction][y - vertMove] = this.name;
                aBoard[x][y] = ' ';
                int[] XY = {x + horiMove * direction, y - vertMove};
                this.updatedXY.add(XY);
                moveList.add(aBoard);
            } else {
                if (board[x + horiMove * direction][y - vertMove] != ' ') {
                    pieces p = checkPiece(x + horiMove * direction, y - vertMove, AiList, HumanList);
                    if (p != null) {
                        if (this.team != p.team) {
                            if (AiControl == 0) {
                                System.out.println("(" + (moveList.size() + 1) + ") " + (x + horiMove * direction) + ":" + (y - vertMove));
                            }
                            aBoard[x + horiMove * direction][y - vertMove] = this.name;
                            aBoard[x][y] = ' ';
                            int[] XY = {x + horiMove * direction, y - vertMove};
                            this.updatedXY.add(XY);
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
                if (AiControl == 0) {
                    System.out.println("(" + (moveList.size() + 1) + ") " + (x + vertMove * -direction) + ":" + (y - horiMove)); //show user the available moves
                }
                aBoard[x + vertMove * -direction][y - horiMove] = this.name;
                aBoard[x][y] = ' ';
                int[] XY = {x + vertMove * -direction, y - horiMove};
                this.updatedXY.add(XY);
                moveList.add(aBoard);

            } else {
                if (board[x + vertMove * -direction][y - horiMove] != ' ') {
                    pieces p = checkPiece(x + vertMove * -direction, y - horiMove, AiList, HumanList);
                    if (p != null) {
                        if (this.team != p.team) {
                            if (AiControl == 0) {
                                System.out.println("(" + (moveList.size() + 1) + ") " + (x + vertMove * -direction) + ":" + (y - horiMove));
                            }
                            
                            aBoard[x + vertMove * -direction][y - horiMove] = this.name;
                            aBoard[x][y] = ' ';
                            int[] XY = {x + vertMove * -direction, y - horiMove};
                            this.updatedXY.add(XY);
                            moveList.add(aBoard);
                        }
                    }
                }//checks to attack Down-Left

            }//checks Down-Left Move
        }

        aBoard = new char[board.length][board.length];
        aBoard = copyBoard(board);

        if (inBounds(x + horiMove * -direction, y - vertMove)) {
            if (board[x + horiMove * -direction][y - vertMove] == ' ') {
                if (AiControl == 0) {
                    System.out.println("(" + (moveList.size() + 1) + ") " + (x + horiMove * -direction) + ":" + (y - vertMove)); //show user the available moves
                }
                aBoard[x + horiMove * -direction][y - vertMove] = this.name;
                aBoard[x][y] = ' ';
                int[] XY = {x + horiMove * -direction, y - vertMove};
                this.updatedXY.add(XY);
                moveList.add(aBoard);
            } else {
                if (board[x + horiMove * -direction][y - vertMove] != ' ') {
                    pieces p = checkPiece(x + horiMove * -direction, y - vertMove, AiList, HumanList);
                    if (p != null) {
                        if (this.team != p.team) {
                            if (AiControl == 0) {
                                System.out.println("(" + (moveList.size() + 1) + ") " + (x + horiMove * -direction) + ":" + (y - vertMove));
                            }
                            aBoard[x + horiMove * -direction][y - vertMove] = this.name;
                            aBoard[x][y] = ' ';
                            int[] XY = {x + horiMove * -direction, y - vertMove};
                            this.updatedXY.add(XY);
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
                if (AiControl == 0) {
                    System.out.println("(" + (moveList.size() + 1) + ") " + (x + vertMove * -direction) + ":" + (y + horiMove)); //show user the available moves
                }
                aBoard[x + vertMove * -direction][y + horiMove] = this.name;
                aBoard[x][y] = ' ';
                int[] XY = {x + vertMove * -direction, y + horiMove};
                this.updatedXY.add(XY);
                moveList.add(aBoard);

            } else {
                if (board[x + vertMove * -direction][y + horiMove] != ' ') {
                    pieces p = checkPiece(x + vertMove * -direction, y + horiMove, AiList, HumanList);
                    if (p != null) {
                        if (this.team != p.team) {
                            if (AiControl == 0) {
                                System.out.println("(" + (moveList.size() + 1) + ") " + (x + vertMove * -direction) + ":" + (y + horiMove));
                            }
                            aBoard[x + vertMove * -direction][y + horiMove] = this.name;
                            aBoard[x][y] = ' ';
                            int[] XY = {x + vertMove * -direction, y + horiMove};
                            this.updatedXY.add(XY);
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
                if (AiControl == 0) {
                    System.out.println("(" + (moveList.size() + 1) + ") " + (x + horiMove * -direction) + ":" + (y + vertMove)); //show user the available moves
                }
                aBoard[x + horiMove * -direction][y + vertMove] = this.name;
                aBoard[x][y] = ' ';
                int[] XY = {x + horiMove * -direction, y + vertMove};
                this.updatedXY.add(XY);
                moveList.add(aBoard);
            } else {
                if (board[x + horiMove * -direction][y + vertMove] != ' ') {
                    pieces p = checkPiece(x + horiMove * -direction, y + vertMove, AiList, HumanList);
                    if (p != null) {
                        if (this.team != p.team) {
                            if (AiControl == 0) {
                                System.out.println("(" + (moveList.size() + 1) + ") " + (x + horiMove * -direction) + ":" + (y + vertMove));
                            }
                            aBoard[x + horiMove * -direction][y + vertMove] = this.name;
                            aBoard[x][y] = ' ';
                            int[] XY = {x + horiMove * -direction, y + vertMove};
                            this.updatedXY.add(XY);
                            moveList.add(aBoard);
                        }
                    }
                }//checks to attack Right-up
            }//checks Right-Down Move
        }

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
