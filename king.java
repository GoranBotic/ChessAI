package chessai;

import java.util.ArrayList;
import java.util.Scanner;

public class king extends pieces {

    Scanner kbd = new Scanner(System.in);
    boolean hasMoved = false; //used for castling 

    public king(int x, int y, int direction, char name, int team, int value, int AiControl) {
        super(x, y, direction, name, team, value, AiControl);
    }

    public ArrayList<char[][]> move(char[][] board, int x, int y, ArrayList<pieces> HumanList, ArrayList<pieces> AiList) {
        return Available_Moves(board, HumanList, AiList);

    }//x and y describes location of piece

    public ArrayList<char[][]> Available_Moves(char[][] board, ArrayList<pieces> HumanList, ArrayList<pieces> AiList) {

        ArrayList<char[][]> moveList = new ArrayList();
        ArrayList<int[]> updatedXY = new ArrayList(); //stores changed x and y in respect with board states

        if (AiControl == 0) {
            System.out.println("Selct an available move(s): ");
        }

        if (inBounds(x + 1 * direction, y)) {
            char[][] aBoard = new char[board.length][board.length];
            aBoard = copyBoard(board);
            if (board[x + 1 * direction][y] == ' ') {
                if (AiControl == 0) {
                    System.out.println("(" + (moveList.size() + 1) + ") " + (x + 1 * direction) + ":" + y); //show user the available moves
                }

                aBoard[x + 1 * direction][y] = this.name;

                aBoard[x][y] = ' ';
                int[] XY = {x + 1 * direction, y};
                this.updatedXY.add(XY);
                moveList.add(aBoard);

            } else {
                if (board[x + 1 * direction][y] != ' ') {
                    pieces p = checkPiece(x + 1 * direction, y, AiList, HumanList);
                    if (p != null) {
                        if (this.team != p.team) {
                            if (AiControl == 0) {
                                System.out.println("(" + (moveList.size() + 1) + ") " + (x + 1 * direction) + ":" + y);
                            }

                            aBoard[x + 1 * direction][y] = this.name;

                            aBoard[x][y] = ' ';
                            int[] XY = {x + 1 * direction, y};
                            this.updatedXY.add(XY);
                            moveList.add(aBoard);

                        }
                    }
                }//checks to attack Down

            }//looks for moves Down
        }

        if (inBounds(x + 1 * -direction, y)) {
            char[][] aBoard = new char[board.length][board.length];
            aBoard = copyBoard(board);
            if (board[x + 1 * -direction][y] == ' ') {
                if (AiControl == 0) {
                    System.out.println("(" + (moveList.size() + 1) + ") " + (x + 1 * -direction) + ":" + y); //show user the available moves
                }

                aBoard[x + 1 * -direction][y] = this.name;

                aBoard[x][y] = ' ';
                int[] XY = {x + 1 * -direction, y};
                this.updatedXY.add(XY);
                moveList.add(aBoard);

            } else {
                if (board[x + 1 * -direction][y] != ' ') {
                    pieces p = checkPiece(x + 1 * -direction, y, AiList, HumanList);
                    if (p != null) {
                        if (this.team != p.team) {
                            if (AiControl == 0) {
                                System.out.println("(" + (moveList.size() + 1) + ") " + (x + 1 * -direction) + ":" + y);
                            }

                            aBoard[x + 1 * -direction][y] = this.name;

                            aBoard[x][y] = ' ';
                            int[] XY = {x + 1 * -direction, y};
                            this.updatedXY.add(XY);
                            moveList.add(aBoard);

                        }
                    }
                }//checks to attack Up

            }//looks for moves Up
        }

        if (inBounds(x, y + 1 * direction)) {
            char[][] aBoard = new char[board.length][board.length];
            aBoard = copyBoard(board);
            if (board[x][y + 1 * direction] == ' ') {
                if (AiControl == 0) {
                    System.out.println("(" + (moveList.size() + 1) + ") " + x + ":" + (y + 1 * direction)); //show user the available moves
                }

                aBoard[x][y + 1 * direction] = this.name;

                aBoard[x][y] = ' ';
                int[] XY = {x, y + 1 * direction};
                this.updatedXY.add(XY);
                moveList.add(aBoard);

            } else {
                if (board[x][y + 1 * direction] != ' ') {
                    pieces p = checkPiece(x, y + 1 * direction, AiList, HumanList);
                    if (p != null) {
                        if (this.team != p.team) {
                            if (AiControl == 0) {
                                System.out.println("(" + (moveList.size() + 1) + ") " + x + ":" + (y + 1 * direction));
                            }

                            aBoard[x][y + 1 * direction] = this.name;

                            aBoard[x][y] = ' ';
                            int[] XY = {x, y + 1 * direction};
                            this.updatedXY.add(XY);
                            moveList.add(aBoard);

                        }
                    }
                }//checks to attack Left

            }//looks for moves Left
        }

        if (inBounds(x, y + 1 * -direction)) {
            char[][] aBoard = new char[board.length][board.length];
            aBoard = copyBoard(board);
            if (board[x][y + 1 * -direction] == ' ') {
                if (AiControl == 0) {
                    System.out.println("(" + (moveList.size() + 1) + ") " + x + ":" + (y + 1 * -direction)); //show user the available moves
                }

                aBoard[x][y + 1 * -direction] = this.name;

                aBoard[x][y] = ' ';
                int[] XY = {x, y + 1 * -direction};
                this.updatedXY.add(XY);
                moveList.add(aBoard);

            } else {
                if (board[x][y + 1 * -direction] != ' ') {
                    pieces p = checkPiece(x, y + 1 * -direction, AiList, HumanList);
                    if (p != null) {
                        if (this.team != p.team) {
                            if (AiControl == 0) {
                                System.out.println("(" + (moveList.size() + 1) + ") " + x + ":" + (y + 1 * -direction));
                            }

                            aBoard[x][y + 1 * -direction] = this.name;

                            aBoard[x][y] = ' ';
                            int[] XY = {x, y + 1 * -direction};
                            this.updatedXY.add(XY);
                            moveList.add(aBoard);

                        }
                    }
                }//checks to attack Right

            }//looks for moves Right
        }

        if (inBounds(x + 1 * direction, y - 1)) {
            char[][] aBoard = new char[board.length][board.length];
            aBoard = copyBoard(board); //reset the board pieces
            if (board[x + 1 * direction][y - 1] == ' ') {
                if (AiControl == 0) {
                    System.out.println("(" + (moveList.size() + 1) + ") " + (x + 1 * direction) + ":" + (y - 1)); //show user the available moves
                }

                aBoard[x + 1 * direction][y - 1] = this.name;

                aBoard[x][y] = ' ';
                int[] XY = {x + 1 * direction, y - 1};
                this.updatedXY.add(XY);
                moveList.add(aBoard);

            } else {
                if (board[x + 1 * direction][y - 1] != ' ') {
                    pieces p = checkPiece(x + 1 * direction, y - 1, HumanList, AiList);
                    if (p != null) {
                        if (this.team != p.team) {
                            if (AiControl == 0) {
                                System.out.println("(" + (moveList.size() + 1) + ") " + (x + 1 * direction) + ":" + (y - 1));
                            }

                            aBoard[x + 1 * direction][y - 1] = this.name;

                            aBoard[x][y] = ' ';
                            int[] XY = {x + 1 * direction, y - 1};
                            this.updatedXY.add(XY);
                            moveList.add(aBoard);
                        }
                    }
                }//checks to attack Upper-left

            }
        }//Checks for Upper-Left

        if (inBounds(x + 1 * direction, y + 1)) {
            char[][] aBoard = new char[board.length][board.length];
            aBoard = copyBoard(board);
            if (board[x + 1 * direction][y + 1] == ' ') {
                if (AiControl == 0) {
                    System.out.println("(" + (moveList.size() + 1) + ") " + (x + (1 * direction)) + ":" + (y + 1)); //show user the available moves
                }

                aBoard[x + 1 * direction][y + 1] = this.name;

                aBoard[x][y] = ' ';
                int[] XY = {x + 1 * direction, y + 1};
                this.updatedXY.add(XY);
                moveList.add(aBoard);

            } else {
                if (board[x + 1 * direction][y + 1] != ' ') {
                    pieces p = checkPiece(x + 1 * direction, y + 1, HumanList, AiList);
                    if (p != null) {
                        if (this.team != p.team) {
                            if (AiControl == 0) {
                                System.out.println("(" + (moveList.size() + 1) + ") " + (x + 1 * direction) + ":" + (y + 1));
                            }

                            aBoard[x + 1 * direction][y + 1] = this.name;

                            aBoard[x][y] = ' ';
                            int[] XY = {x + 1 * direction, y + 1};
                            this.updatedXY.add(XY);
                            moveList.add(aBoard);
                        }
                    }
                }//checks to attack Upper-Right

            }
        }//Checks for Upper-Right

        if (inBounds(x + 1 * -direction, y + 1)) {
            char[][] aBoard = new char[board.length][board.length];
            aBoard = copyBoard(board);
            if (board[x + 1 * -direction][y + 1] == ' ') {
                if (AiControl == 0) {
                    System.out.println("(" + (moveList.size() + 1) + ") " + (x + 1 * -direction) + ":" + (y + 1)); //show user the available moves
                }

                aBoard[x - 1 * direction][y + 1] = this.name;

                aBoard[x][y] = ' ';
                int[] XY = {x + 1 * -direction, y + 1};
                this.updatedXY.add(XY);
                moveList.add(aBoard);

            } else {
                if (board[x + 1 * -direction][y + 1] != ' ') {
                    pieces p = checkPiece(x + 1 * -direction, y + 1, HumanList, AiList);
                    if (p != null) {
                        if (this.team != p.team) {
                            if (AiControl == 0) {
                                System.out.println("(" + (moveList.size() + 1) + ") " + (x + 1 * -direction) + ":" + (y + 1));
                            }

                            aBoard[x + 1 * -direction][y + 1] = this.name;

                            aBoard[x][y] = ' ';
                            int[] XY = {x + 1 * -direction, y + 1};
                            this.updatedXY.add(XY);
                            moveList.add(aBoard);
                        }
                    }
                }//checks to attack Down-Right

            }
        }//Checks for Down-Right

        if (inBounds(x + 1 * -direction, y - 1)) {
            char[][] aBoard = new char[board.length][board.length];
            aBoard = copyBoard(board);
            if (board[x + 1 * -direction][y - 1] == ' ') {
                if (AiControl == 0) {
                    System.out.println("(" + (moveList.size() + 1) + ") " + (x + 1 * -direction) + ":" + (y - 1)); //show user the available moves
                }

                aBoard[x + 1 * -direction][y - 1] = this.name;

                aBoard[x][y] = ' ';
                int[] XY = {x + 1 * -direction, y - 1};
                this.updatedXY.add(XY);
                moveList.add(aBoard);

            } else {
                if (board[x + 1 * -direction][y - 1] != ' ') {
                    pieces p = checkPiece(x + 1 * -direction, y - 1, HumanList, AiList);
                    if (p != null) {
                        if (this.team != p.team) {
                            if (AiControl == 0) {
                                System.out.println("(" + (moveList.size() + 1) + ") " + (x + 1 * -direction) + ":" + (y - 1));
                            }

                            aBoard[x + 1 * -direction][y - 1] = this.name;

                            aBoard[x][y] = ' ';
                            int[] XY = {x + 1 * -direction, y - 1};
                            this.updatedXY.add(XY);
                            moveList.add(aBoard);
                        }
                    }
                }//checks to attack Down-Left

            }
        }//Checks for Down-Left

        if (checkRooksCastle(board, HumanList, AiList) == true) {
            char[][] aBoard = copyBoard(board);

            if (team == 0) {

                for (pieces r : HumanList) {
                    if (r instanceof rook && r.team == this.team) {
                        r = (rook) r;
                        if (r.hasMoved == false) {
                            aBoard[x][y] = ' '; //remove original king location
                            aBoard[r.x][r.y] = ' '; //remove original rook location
                            int dir = y - r.y;

                            if (dir < 0) { //king moves right
                                if (AiControl == 0) {
                                    System.out.println("(" + (moveList.size() + 1) + ") castle " + (x) + ":" + (y + 2));
                                }
                                aBoard[x][y + 2] = name;
                                aBoard[x][y + 1] = r.name;
                                moveList.add(aBoard); //add to move list
                                int[] XY = {x, y + 2};
                                this.updatedXY.add(XY); //add new location for king
                                int[] rXY = {x, y + 1};
                                r.updatedXY.add(rXY); //add new location for rook

                            } else { //else king moves left
                                if (AiControl == 0) {
                                    System.out.println("(" + (moveList.size() + 1) + ") castle " + (x) + ":" + (y - 2));
                                }
                                aBoard[x][y - 2] = name;
                                aBoard[x][y - 1] = r.name;
                                moveList.add(aBoard);
                                int[] XY = {x, y - 2};
                                this.updatedXY.add(XY);
                                int[] rXY = {x, y - 1};
                                r.updatedXY.add(rXY);

                            }//move king two spaces towards the rook and rook moves 1 space over king

                        }
                    }
                    aBoard = copyBoard(board);
                }//check which rook is available for castling
            }

            if (team == 1) {
                for (pieces r : AiList) {
                    if (r instanceof rook && r.team == this.team) {
                        r = (rook) r;
                        if (r.hasMoved == false) {
                            aBoard[x][y] = ' '; //remove original king location
                            aBoard[r.x][r.y] = ' '; //remove original rook location
                            int dir = y - r.y;

                            if (dir < 0) { //king moves right
                                aBoard[x][y + 2] = name;
                                aBoard[x][y + 1] = r.name;
                                moveList.add(aBoard); //add to move list
                                int[] XY = {x, y + 2};
                                this.updatedXY.add(XY); //add new location for king
                                int[] rXY = {x, y + 1};
                                r.updatedXY.add(rXY); //add new location for rook
                            } else { //else king moves left
                                aBoard[x][y - 2] = name;
                                aBoard[x][y - 1] = r.name;
                                moveList.add(aBoard);
                                int[] XY = {x, y - 2};
                                this.updatedXY.add(XY);
                                int[] rXY = {x, y - 1};
                                r.updatedXY.add(rXY);
                            }//move king two spaces towards the rook and rook moves 1 space over king

                        }
                    }
                    aBoard = copyBoard(board);
                }//check which rook is available for castling
            }

        }//castling function

        return moveList;
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

    public boolean checkRooksCastle(char[][] theBoard, ArrayList<pieces> HumanList, ArrayList<pieces> AiList) {
        int aMove = 1;

        if (hasMoved == false) {
            while (inBounds(x, y + direction * aMove)) {
                if (theBoard[x][y + direction * aMove] == ' ') {
                    aMove++;
                } else if (theBoard[x][y + direction * aMove] == 'R') {
                    pieces rook = checkPiece(x, y + direction * aMove, HumanList, AiList);
                    if (rook.hasMoved == false) {
                        return true;
                    }

                }
                break;
            }

        }

        aMove = 1;

        if (hasMoved == false) {
            while (inBounds(x, y + (-direction * aMove))) {
                if (theBoard[x][y + (-direction * aMove)] == ' ') {
                    aMove++;
                } else {
                    if (theBoard[x][y + -direction + aMove] == 'R') {
                        pieces rook = checkPiece(x, y + -direction * aMove, HumanList, AiList);
                        if (rook.hasMoved == false) {
                            return true;
                        }
                    }

                }
                break;
            }

        }

        return false;
    }

//    @Override
//    public void changeX(int newX) {
//        x = newX;
//    }
//
//    @Override
//    public void changeY(int newY) {
//        y = newY;
//    }
}
