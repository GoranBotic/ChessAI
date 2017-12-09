package chessai;

import java.util.ArrayList;

public class pawn extends pieces {

    int value = 1;

    public pawn(int x, int y) {
        super(x, y);
    }

    public void move(char[][] board, int x, int y) {
        ArrayList<char[][]> movesList = new ArrayList();
        Available_Moves(board,movesList,x,y);
        
    }//x and y describes location of piece
    public void Available_Moves(char[][] board,ArrayList<char[][]> movesList,int x,int y){
        if (board[x - 2][y] == ' ') {
            System.out.println(x-2 +":"+y);
        }
        
    }//returns array of move available

}
