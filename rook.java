package chessai;

import java.util.ArrayList;
import java.util.Scanner;


public class rook extends pieces {
    int value = 5;
    Scanner kbd = new Scanner(System.in);
    
    public rook(int x,int y,int direction,String name) {
        super(x,y,direction,name);
    }
    
    public char[][] move(char[][] board, int x, int y) {
        ArrayList<char[][]> movesList = new ArrayList();
        board = Available_Moves(board, movesList);
        return board;
    }//x and y describes location of piece

    public char[][] Available_Moves(char[][] board, ArrayList<char[][]> movesList) {

        ArrayList<char[][]> moveList = new ArrayList();
        int aMove = 1;
        int bMove = 1;
        
        System.out.println("Selct an available move(s): ");
      while(board[x+aMove*direction][y] == ' '){
        
            System.out.println("(" + (moveList.size() + aMove) + ") " + (x + (aMove * direction)) + ":" + (y)); //show user the available moves
            char[][] aBoard = new char[board.length][board.length];
            aBoard = copyBoard(board);
            aBoard[x + aMove * direction][y] = 'R';
            aBoard[x][y] = ' ';
            moveList.add(aBoard);
            aMove++;
        
      }//looks for moves vertically
      
      while(board[x][y-bMove*direction] == ' '){
        
            System.out.println("(" + (moveList.size() + 1) + ") " + x + ":" + (y + (bMove * direction))); //show user the available moves
            char[][] aBoard = new char[board.length][board.length];
            aBoard = copyBoard(board);
            aBoard[x + bMove * direction][y] = 'R';
            aBoard[x][y] = ' ';
            moveList.add(aBoard);
            bMove++;
      }//looks for moves horizontally

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
    
    public String getName(){
    
        return name;
    }
    
}
