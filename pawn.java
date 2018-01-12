package chessai;

import java.util.ArrayList;
import java.util.Scanner;

public class pawn extends pieces {

    int value = 1;
    Scanner kbd = new Scanner(System.in);
    boolean initial = true; //checks if the pawn has moved from initial position
    boolean promoted = false;
    boolean beEnPassant = false;
    boolean killEnPassant = false;

    public pawn(int x, int y, int direction, char name, int team, int value, int AiControl) {
        super(x, y, direction, name, team, value, AiControl);
    }

    public ArrayList<char[][]> move(char[][] board, int x, int y, ArrayList<pieces> HumanList, ArrayList<pieces> AiList) {
        return Available_Moves(board, HumanList, AiList);
    }//x and y describes location of piece

    public ArrayList<char[][]> Available_Moves(char[][] board, ArrayList<pieces> HumanList, ArrayList<pieces> AiList) {

        ArrayList<char[][]> moveList = new ArrayList();

        if (AiControl == 0) {
            System.out.println("Selct an available move(s): ");
        }

        char[][] aBoard = new char[board.length][board.length];
        aBoard = copyBoard(board);

        if (inBounds(x + 1 * direction, y)) {
            if (board[x + 1 * direction][y] == ' ') {
                if (AiControl == 0) {
                    System.out.println("(" + (moveList.size() + 1) + ") " + (x + (1 * direction)) + ":" + (y)); //show user the available moves
                }

                aBoard[x + 1 * direction][y] = this.name; //move the pawn to new location

                aBoard[x][y] = ' '; //remove old location
                int[] XY = {x + 1 * direction, y};
                this.updatedXY.add(XY);
                moveList.add(aBoard);
                beEnPassant = false;
            }//move one space 

        }//in bounds check

        aBoard = new char[board.length][board.length];
        aBoard = copyBoard(board);

        if (initial == true) {

            if (inBounds(x + 2 * direction, y)) {
                if (board[x + 1 * direction][y] == ' ') {
                    if (board[x + 2 * direction][y] == ' ') {
                        if (AiControl == 0) {
                            System.out.println("(" + (moveList.size() + 1) + ") " + (x + (2 * direction)) + ":" + (y));
                        }

                        aBoard[x + 2 * direction][y] = this.name;

                        aBoard[x][y] = ' ';
                        int[] XY = {x + 2 * direction, y};
                        this.updatedXY.add(XY);
                        moveList.add(aBoard);

                        initial = false;

                        if (team == 0) {
                            if (inBounds(x + 2 * direction, y + 1)) {
                                if (board[x + 2 * direction][y + 1] == 'p') {
                                    beEnPassant = true;
                                }
                            }
                            if (inBounds(x + 2 * direction, y - 1)) {
                                if (board[x + 2 * direction][y - 1] == 'p') {
                                    beEnPassant = true;
                                }
                            }
                        }

                        if (team == 1) {
                            if (inBounds(x + 2 * direction, y + 1)) {
                                if (board[x + 2 * direction][y + 1] == 'P') {
                                    beEnPassant = true;
                                }
                            }
                            if (inBounds(x + 2 * direction, y - 1)) {
                                if (board[x + 2 * direction][y - 1] == 'P') {
                                    beEnPassant = true;
                                }
                            }
                        }
                    }//if moved 2 spaces and is adjacent to enemy pawn, enemy pawn can en passant
                }
            }

        }

        aBoard = new char[board.length][board.length];
        aBoard = copyBoard(board);

        if (inBounds(x + 1 * direction, y - 1)) {
            if (board[x + 1 * direction][y - 1] != ' ') {
                pieces p = checkPiece(x + 1 * direction, y - 1, AiList, HumanList);
                if (p != null) {
                    if (this.team != p.team) {
                        if (AiControl == 0) {
                            System.out.println("(" + (moveList.size() + 1) + ") " + (x + (1 * direction)) + ":" + (y - 1));
                        }

                        aBoard[x + 1 * direction][y - 1] = this.name;
                        aBoard[x][y] = ' ';
                        int[] XY = {x + 1 * direction, y - 1};
                        this.updatedXY.add(XY);
                        moveList.add(aBoard);
                        beEnPassant = false;
                    }
                }
            }//checks to attack left
        }//checks bounds

        aBoard = new char[board.length][board.length];
        aBoard = copyBoard(board);

        if (inBounds(x + 1 * direction, y + 1)) {
            if (board[x + 1 * direction][y + 1] != ' ') {
                pieces p = checkPiece(x + 1 * direction, y + 1, AiList, HumanList);
                if (p != null) {
                    if (this.team != p.team) {
                        if (AiControl == 0) {
                            System.out.println("(" + (moveList.size() + 1) + ") " + (x + (1 * direction)) + ":" + (y + 1));
                        }
                        aBoard[x + 1 * direction][y + 1] = this.name;
                        aBoard[x][y] = ' ';
                        int[] XY = {x + 1 * direction, y + 1};
                        this.updatedXY.add(XY);
                        moveList.add(aBoard);
                        beEnPassant = false;
                    }
                }
            }//checks to attack right
        }//checks bounds

        aBoard = new char[board.length][board.length];
        aBoard = copyBoard(board);

        if (team == 0) {
            if (inBounds(x, y + 1)) {
                if (aBoard[x][y + 1] == 'p') {
                    pieces p = checkPiece(x, y + 1, HumanList, AiList);
                    if (p != null) { //**ADDED
                        if (((pawn) p).beEnPassant == true) {
                            if (AiControl == 0) {
                                System.out.println("(" + (moveList.size() + 1) + ") En Passant " + (p.x - 1) + ":" + (p.y));
                            }
                            aBoard[x][y] = ' '; //remove old piece location
                            aBoard[x][y + 1] = ' '; //remove the opposing pawn
                            aBoard[p.x - 1][p.y] = name; //update new piece location
                            moveList.add(aBoard);
                            int[] XY = {p.x - 1, p.y};
                            this.updatedXY.add(XY);
                            killEnPassant = true;
                        }
                    }
                }
            }

            aBoard = new char[board.length][board.length];
            aBoard = copyBoard(board);

            if (inBounds(x, y - 1)) {
                if (aBoard[x][y - 1] == 'p') {
                    pieces p = checkPiece(x, y - 1, HumanList, AiList);
                    if (p != null) { //**ADDED
                        if (((pawn) p).beEnPassant == true) {
                            if (AiControl == 0) {
                                System.out.println("(" + (moveList.size() + 1) + ") En Passant " + (p.x - 1) + ":" + (p.y));
                            }
                            aBoard[x][y] = ' ';
                            aBoard[x][y - 1] = ' ';
                            aBoard[p.x - 1][p.y] = name;
                            moveList.add(aBoard);
                            int[] XY = {p.x - 1, p.y};
                            this.updatedXY.add(XY);
                            killEnPassant = true;
                        }
                    }
                }
            }
        }//add en passant moves for player

        if (team == 1) {
            if (inBounds(x, y + 1)) {
                if (aBoard[x][y + 1] == 'P') {
                    pieces p = checkPiece(x, y + 1, HumanList, AiList);
                    if (p != null) {
                        if (((pawn) p).beEnPassant == true) {
                            if (AiControl == 0) {
                                System.out.println("(" + (moveList.size() + 1) + ") En Passant " + (p.x + 1) + ":" + (p.y));
                            }
                            aBoard[x][y] = ' ';
                            aBoard[x][y + 1] = ' ';
                            aBoard[p.x + 1][p.y] = name;
                            moveList.add(aBoard);
                            int[] XY = {p.x + 1, p.y};
                            this.updatedXY.add(XY);
                            killEnPassant = true;
                        }
                    }
                }
            }

            aBoard = new char[board.length][board.length];
            aBoard = copyBoard(board);

            if (inBounds(x, y - 1)) {
                if (aBoard[x][y - 1] == 'P') {
                    pieces p = checkPiece(x, y - 1, HumanList, AiList);
                    if (p != null) {
                        if (p instanceof pawn) {
                            if (((pawn) p).beEnPassant == true) {
                                if (AiControl == 0) {
                                    System.out.println("(" + (moveList.size() + 1) + ") En Passant " + (p.x + 1) + ":" + (p.y));
                                }
                                aBoard[x][y] = ' ';
                                aBoard[x][y - 1] = ' ';
                                aBoard[p.x + 1][p.y] = name;
                                moveList.add(aBoard);
                                int[] XY = {p.x + 1, p.y};
                                this.updatedXY.add(XY);
                                killEnPassant = true;
                            }
                        }
                    }
                }
            }
        }//add en passant moves for Ai
        return moveList;
    }//

    public boolean checkPromotion() {

        if (team == 0) {
            if (x == 0) {
                return true;
            }
        }//if player team

        if (team == 1) {
            if (x == 7) {
                return true;
            }
        }

        return false;
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
