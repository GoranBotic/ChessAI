/*
 * Goran Botic and Steven Thai
 * COSC 3P71
 * Chess AI Final Project
 * 
 */
package chessai;

import java.util.ArrayList;

public class ChessAI {

    char[][] board;
    ArrayList<pieces> HumanPieces = new ArrayList();

    public ChessAI() {
        board = new char[8][8];

        initBoard();
        pieces p = HumanPieces.get(0);
        p.move(board, p.x, p.y);
        System.out.println();
        updateBoard(board);
    }

    public char[][] miniMax(char[][] theBoard) {

        //TODO: Evaluate the boardState; determine fitness
        return theBoard;
    }

    public void initBoard() {

        board[0][0] = 'r';
        board[0][1] = 'n';
        board[0][2] = 'b';
        board[0][3] = 'k';
        board[0][4] = 'q';
        board[0][5] = 'b';
        board[0][6] = 'n';
        board[0][7] = 'r';

        for (int i = 0; i < board.length; i++) {
            board[1][i] = 'p';
            pieces p = new pawn(1, i);
        }
        for (int j = 0; j < board.length; j++) {
            board[2][j] = ' ';
            board[3][j] = ' ';
            board[4][j] = ' ';
            board[5][j] = ' ';
        }
        board[7][0] = 'R';
        pieces r1 = new rook(7, 0);
        //HumanPieces.add(r1);
        board[7][1] = 'N';
        pieces n1 = new knight(7, 0);
//        HumanPieces.add(n1);
        board[7][2] = 'B';
        pieces b1 = new bishop(7, 2);
//        HumanPieces.add(b1);
        board[7][3] = 'K';
        pieces k = new king(7, 3);
//        HumanPieces.add(k);
        board[7][4] = 'Q';
        pieces q = new queen(7, 4);
//        HumanPieces.add(q);
        board[7][5] = 'B';
        pieces b2 = new bishop(7, 5);
//        HumanPieces.add(b2);
        board[7][6] = 'N';
        pieces n2 = new knight(7, 6);
//        HumanPieces.add(n2);
        board[7][7] = 'R';
        pieces r2 = new rook(7, 7);
//        HumanPieces.add(r2);

        for (int i = 0; i < board.length; i++) {
            board[6][i] = 'P';
            pieces p = new pawn(6, i);
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
