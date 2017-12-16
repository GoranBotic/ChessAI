package chessai;

public class pieces {
    int x,y,direction;
    String name;
    public pieces(int x,int y,int direction,String name){
        this.x = x;
        this.y = y;
        this.direction = direction; //correctly orientates movement of pieces
        this.name = name;
    }
    
    public char[][] move(char[][] board,int x, int y){
    
        return board;
    }
    public boolean inBounds(int x,int y){
    
        if(x < 0 || x > 7 ){
            return false;
        }
        if(y < 0 || y > 7){
            return false;
        }
        return true;
    }
    
    public String getName(){
        return name;
    }
    
}
