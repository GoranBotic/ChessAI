package chessai;


public class king extends pieces {
    int value = 100;
    public king(int x,int y,int direction,String name) {
        super(x,y,direction,name);
    }
    
    public char[][] move(char[][] board,int x,int y) {
        return board;
    }
    
    public String getName(){
        return name;
    }
}
