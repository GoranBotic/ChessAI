/*
 * Goran Botic and Steven Thai
 * COSC 3P71
 * Chess AI Final Project
 * 
 */


package chessai;

public class ChessAI {
    
    char[][] board;
    
    public ChessAI(){
        board = new char[8][8];
        
        initBoard();
    }
    
    public void initBoard(){
        
        board[0][0] = 'r';
        board[0][1] = 'n';
        board[0][2] = 'b';
        board[0][3] = 'k';
        board[0][4] = 'q';
        board[0][5] = 'b';
        board[0][6] = 'n';
        board[0][7] = 'r';
        
        for (int i=0 ; i<board.length ; i++) {
            board[1][i] = 'p';
        }
        
        board[7][0] = 'R';
        board[7][1] = 'N';
        board[7][2] = 'B';
        board[7][3] = 'K';
        board[7][4] = 'Q';
        board[7][5] = 'B';
        board[7][6] = 'N';
        board[7][7] = 'R';
        
        for (int i=0 ; i<board.length ; i++) {
            board[6][i] = 'P';
        }
        
        for (int i=0 ; i<board.length ; i++) {
            for (int j=0 ; j<board.length ; j++) {
                System.out.print("|"+ board[i][j] + "|");
            }
            System.out.print("\n");
        }
        
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    public static void main(String[] args) {ChessAI c = new ChessAI();};
    
}
