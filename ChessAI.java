/*
 * Goran Botic and Steven Thai
 * COSC 3P71
 * Chess AI Final Project
 * 
 */
package chessai;

import java.util.ArrayList;
import java.util.Scanner;

public class ChessAI {

    char[][] board, newBoard;
    int depth = 2;
    boolean playerTurn = true; //determines who's turn it is. 0: AI 1: Human
    boolean play = true; //determines when the game is done
    ArrayList<pieces> HumanPieces = new ArrayList();
    ArrayList<pieces> AiPieces = new ArrayList();
    Scanner kbd = new Scanner(System.in);

    public ChessAI() {
        board = new char[8][8];
        newBoard = new char[8][8];

        initBoard();
        while (play) {
            while (playerTurn) {
                System.out.println("Select a piece");
                for (int i = 0; i < HumanPieces.size(); i++) {
                    pieces aPiece = HumanPieces.get(i);
                    System.out.println("(" + i + ") " + aPiece.getName() + " (" + aPiece.x + "," + aPiece.y + ") ");
                }//this loop will print all the pieces available for player

                int choice = kbd.nextInt(); //player picks the piece to move
                pieces p = HumanPieces.get(choice);//player chooses where the piece wants to move
                ArrayList<char[][]> movesList = new ArrayList();
                movesList = p.move(board, p.x, p.y, HumanPieces, AiPieces); //board is updated with newest state
                newBoard = chooseBoard(movesList, p, HumanPieces, AiPieces, board);

                if (!newBoard.equals(board)) {
                    playerTurn = false;
                    board = newBoard;
                    System.out.println();
                    updateBoard(board); //prints new board 
                }
            }//the player's turn

            while (playerTurn == false) {
                for (int i = 0; i < AiPieces.size(); i++) {
                    pieces p = AiPieces.get(i);
                    ArrayList<char[][]> moveList = new ArrayList();
                    moveList = p.move(board, p.x, p.y, HumanPieces, AiPieces); //get all available moves for the Ai
                    if (!moveList.isEmpty()) {
                        newBoard = mini(board, depth, p, Integer.MIN_VALUE, Integer.MAX_VALUE, moveList);//alpha = best local max, beta = best local min
                    }
                }
            }//While Ai's turn 
        }
    }

    public char[][] chooseBoard(ArrayList<char[][]> moveList, pieces p, ArrayList<pieces> HumanList, ArrayList<pieces> AiList, char[][] board) {
        if (!moveList.isEmpty()) {

            boolean validChoice = true; //this will flag if user picked an integer available from the list
            while (validChoice) {
                int choice = kbd.nextInt();
                if (choice == 0) {
                    System.out.println("You have deselected this piece");
                    validChoice = false; //this breaks out of loop and nothing happens to board
                } else {
                    if (choice > 0 && choice <= moveList.size()) {
                        int[] XY = p.updatedXY.get(choice - 1);//get piece's new location
                        validChoice = false;
                        pieces aPiece = p.checkPiece(XY[0], XY[1], HumanList, AiList);
                        if (aPiece != null) {
                            p.removePiece(aPiece, HumanList, AiList);
                        }

                        p.x = XY[0];
                        p.y = XY[1]; //updates pieces new positions
                        p.updatedXY.clear();//clears available moves

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

    /*
    The Ai will choose a move that minimizes the player's maximum score,
    we will be assuming the player will always make the best move.
     */
    public char[][] mini(char[][] theBoard, int depth, pieces thePiece, int alpha, int beta, ArrayList<char[][]> moveList) {
        ArrayList<char[][]> nextMoves = new ArrayList();

        if (depth == 0) {
            int value = EvaluateBoard(theBoard); //Find values of each different board states
            System.out.println();
            updateBoard(theBoard);
            if (value > alpha) {
                alpha = value;
            }
        } else {
            depth--;
            theBoard = moveList.get(0); //Ai chooses the first available move
            int prevX = thePiece.x;
            int prevY = thePiece.y; //save the piece's last location
            int[] XY = thePiece.updatedXY.get(0); //get new piece location
            thePiece.x = XY[0];
            thePiece.y = XY[1]; //set new location
            moveList.remove(0); //take out the move  
            nextMoves = thePiece.move(theBoard, thePiece.x, thePiece.y, HumanPieces, AiPieces);  //get next moves
//            System.out.println();
//            updateBoard(theBoard);
            theBoard = max(theBoard, depth, thePiece, alpha, beta, nextMoves);

        }//recursive method

        /*if (alpha < beta) {
            beta = alpha;
        }//if local max is greater than local min */
        return theBoard;
    }

    public char[][] max(char[][] theBoard, int depth, pieces thePiece, int alpha, int beta, ArrayList<char[][]> moveList) {

        ArrayList<char[][]> nextMoves = new ArrayList(); //create a branch for every move available for player

        if (depth == 0) {
            int value = EvaluateBoard(theBoard); //Find values of each different board states
            if (value <= beta) {
                beta = value;
            }
        } else {
            depth--;
            for (int i = 0; i < HumanPieces.size(); i++) {
                pieces p = HumanPieces.get(i);
                p.AiControl = 1; //prevents the prompts from showing in console
                nextMoves = p.move(theBoard, p.x, p.y, HumanPieces, AiPieces);
                p.AiControl = 0;
                if (!nextMoves.isEmpty()) {
                    int prevX = p.x;
                    int prevY = p.y; //save the piece's last location
                    while (!nextMoves.isEmpty()) {
                        theBoard = nextMoves.get(0); //update board with player's move only through ASCII
                        int[] XY = p.updatedXY.get(0); //get new piece location
                        p.x = XY[0];
                        p.y = XY[1]; //set new location
                        nextMoves.remove(0); //remove possible player move
                        theBoard = mini(theBoard, depth, thePiece, alpha, beta, moveList);
                        p.x = prevX;
                        p.y = prevY; //undo the move and loop for next move
                    }
                }//check if the piece can make a move

            }//looks through all pieces player can move

        }

        return theBoard;
    }

    public int EvaluateBoard(char[][] board) {
        int totalValue, HumanVal = 0, AiVal = 0;
        for (int i = 0; i < HumanPieces.size(); i++) {
            HumanVal += HumanPieces.get(i).value;
        }
        for (int i = 0; i < HumanPieces.size(); i++) {
            AiVal += AiPieces.get(i).value;
        }
        totalValue = HumanVal - AiVal;

        return totalValue;
    }

    public void initBoard() {

        board[0][0] = 'r';
        pieces r0 = new rook(0, 0, 1, 'r', 1, 5, 1);
        AiPieces.add(r0);
        board[0][1] = 'n';
        pieces n0 = new knight(0, 1, 1, 'n', 1, 3, 1);
        AiPieces.add(n0);
        board[0][2] = 'b';
        pieces b0 = new bishop(0, 2, 1, 'b', 1, 3, 1);
        AiPieces.add(b0);
        board[0][3] = 'k';
        pieces k0 = new king(0, 3, 1, 'k', 1, 100, 1);
        AiPieces.add(k0);
        board[0][4] = 'q';
        pieces q0 = new queen(0, 4, 1, 'q', 1, 9, 1);
        AiPieces.add(q0);
        board[0][5] = 'b';
        pieces b3 = new bishop(0, 5, 1, 'b', 1, 3, 1);
        AiPieces.add(b3);
        board[0][6] = 'n';
        pieces n3 = new knight(0, 6, 1, 'n', 1, 3, 1);
        AiPieces.add(n3);
        board[0][7] = 'r';
        pieces r3 = new rook(0, 7, 1, 'r', 1, 5, 1);
        AiPieces.add(r3);
        for (int i = 0; i < board.length; i++) {
            board[1][i] = 'p';
            pieces p = new pawn(1, i, 1, 'p', 1, 1, 1);
            AiPieces.add(p);
        }
        for (int j = 0; j < board.length; j++) {
            board[2][j] = ' ';
            board[3][j] = ' ';
            board[4][j] = ' ';
            board[5][j] = ' ';
        }
        board[7][0] = 'R';
        pieces r1 = new rook(7, 0, -1, 'R', 0, 5, 0);
        HumanPieces.add(r1);
        board[7][1] = 'N';
        pieces n1 = new knight(7, 1, -1, 'N', 0, 3, 0);
        HumanPieces.add(n1);
        board[7][2] = 'B';
        pieces b1 = new bishop(7, 2, -1, 'B', 0, 3, 0);
        HumanPieces.add(b1);
        board[7][3] = 'K';
        pieces k = new king(7, 3, -1, 'K', 0, 100, 0);
        HumanPieces.add(k);

        board[7][4] = 'Q';
        pieces q = new queen(7, 4, -1, 'Q', 0, 9, 0);
        HumanPieces.add(q);
        board[7][5] = 'B';
        pieces b2 = new bishop(7, 5, -1, 'B', 0, 3, 0);
        HumanPieces.add(b2);
        board[7][6] = 'N';
        pieces n2 = new knight(7, 6, -1, 'N', 0, 3, 0);
        HumanPieces.add(n2);
        board[7][7] = 'R';
        pieces r2 = new rook(7, 7, -1, 'R', 0, 5, 0);
        HumanPieces.add(r2);

        for (int i = 0; i < board.length; i++) {
            board[6][i] = 'P';
            pieces p = new pawn(6, i, -1, 'P', 0, 1, 0);
            HumanPieces.add(p);
        }

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                System.out.print(" |" + board[i][j] + "| ");
            }
            System.out.print("\n");
        }

    }

    public char[][] updateBoard(char[][] board) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                System.out.print(" |" + board[i][j] + "| ");
            }
            System.out.print("\n");
        }

        return board;
    }

    public static void main(String[] args) {
        ChessAI c = new ChessAI();
    }
;

}
