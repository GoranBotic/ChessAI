package chessai;

import java.util.ArrayList;


public class rook extends pieces {
    int value = 5;
    public rook(int x,int y,int direction) {
        super(x,y,direction);
    }
    
    public char[][] move(char[][] board,int x,int y) {
        ArrayList<char[][]> movesList = new ArrayList();
        Available_Moves(board,movesList,x,y);
        return board;
    }
    
        public void Available_Moves(char[][] board,ArrayList<char[][]> movesList,int x,int y){
        
        
    }//returns array of move available
    
}
