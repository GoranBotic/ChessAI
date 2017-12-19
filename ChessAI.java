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

    char[][] board;
    boolean turnState; //determines who's turn it is. 0: Human 1: AI
    boolean play = true;
    ArrayList<pieces> HumanPieces = new ArrayList();
    ArrayList<pieces> AiPieces = new ArrayList();
    Scanner kbd = new Scanner(System.in);

    public ChessAI() {
        board = new char[8][8];

        initBoard();
        while (play) {
            System.out.println("Select a piece");
            for (int i = 0; i < HumanPieces.size(); i++) {
                pieces aPiece = HumanPieces.get(i);
                System.out.println("("+i+") "+aPiece.getName() + " (" + aPiece.x + "," + aPiece.y + ") ");
            }//this loop will print all the pieces available for player
            
            int choice = kbd.nextInt(); //player picks the piece to move
            pieces p = HumanPieces.get(choice);//player chooses where the piece wants to move
            board = p.move(board, p.x, p.y,HumanPieces,AiPieces); //board is updated with newest state
            System.out.println();
            updateBoard(board); //prints new board 
           
        }
    }

    public char[][] miniMax(char[][] theBoard) {

        //TODO: Evaluate the boardState; determine fitness
        return theBoard;
    }

    public void initBoard() {

        /*board[0][0] = 'r';
        pieces r0 = new rook(0, 0, 1, "r",1);
        AiPieces.add(r0);
        board[0][1] = 'n';
        pieces n0 = new knight(0, 1, 1, "n",1);
        AiPieces.add(n0);
        board[0][2] = 'b';
        pieces b0 = new bishop(0, 2, 1, "b",1);
        AiPieces.add(b0);
        board[0][3] = 'k';
        pieces k0 = new king(0, 3, 1, "k",1);
        AiPieces.add(k0);
        board[0][4] = 'q';
        pieces q0 = new queen(0, 4, 1, "q",1);
        AiPieces.add(q0);
        board[0][5] = 'b';
        pieces b3 = new bishop(0, 5, 1, "b",1);
        AiPieces.add(b3);
        board[0][6] = 'n';
        pieces n3 = new knight(0, 6, 1, "n",1);
        AiPieces.add(n3);
        board[0][7] = 'r';
        pieces r3 = new rook(0, 7, 1, "r",1);
        AiPieces.add(r3);*/

        for (int i = 0; i < board.length; i++) {
            board[1][i] = 'p';
            pieces p = new pawn(1, i, 1, "p",1);
            AiPieces.add(p);
        }
        for (int j = 0; j < board.length; j++) {
            board[2][j] = ' ';
            board[3][j] = ' ';
            board[4][j] = ' ';
            board[5][j] = ' ';
        }
        /*board[7][0] = 'R';
        pieces r1 = new rook(7, 0, -1, "R",0);
        HumanPieces.add(r1);
        board[7][1] = 'N';
        pieces n1 = new knight(7, 1, -1, "N",0);
        HumanPieces.add(n1);
        board[7][2] = 'B';
        pieces b1 = new bishop(7, 2, -1, "B",0);
        HumanPieces.add(b1);
        board[7][3] = 'K';
        pieces k = new king(7, 3, -1, "K",0);
        HumanPieces.add(k);
        board[7][4] = 'Q';
        pieces q = new queen(7, 4, -1, "Q",0);
        HumanPieces.add(q);
        board[7][5] = 'B';
        pieces b2 = new bishop(7, 5, -1, "B",0);
        HumanPieces.add(b2);
        board[7][6] = 'N';
        pieces n2 = new knight(7, 6, -1, "K",0);
        HumanPieces.add(n2);
        board[7][7] = 'R';
        pieces r2 = new rook(7, 7, -1, "R",0);
        HumanPieces.add(r2);*/
        
        for (int i = 0; i < board.length; i++) {
            board[6][i] = 'P';
            pieces p = new pawn(6, i, -1, "P",0);
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
