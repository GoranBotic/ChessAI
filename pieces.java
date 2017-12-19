package chessai;

import java.util.ArrayList;

public class pieces {

    int x, y, direction, team;
    String name;

    public pieces(int x, int y, int direction, String name, int team) {
        this.x = x;
        this.y = y;
        this.direction = direction; //correctly orientates movement of pieces
        this.name = name;
        this.team = team;
    }

    public char[][] move(char[][] board, int x, int y,ArrayList<pieces> Human, ArrayList<pieces> AiList) {

        return board;
    }

    public boolean inBounds(int x, int y) {

        if (x < 0 || x > 7) {
            return false;
        }
        if (y < 0 || y > 7) {
            return false;
        }
        return true;
    }

    public String getName() {
        return name;
    }

    public pieces checkPiece(int x, int y, ArrayList<pieces> HumanList, ArrayList<pieces> AiList) {

       

        for (int i = 0; i < AiList.size(); i++) {
            if (AiList.get(i).x == x && AiList.get(i).y == y) {
                return AiList.get(i);
            }
        }

        for (int i = 0; i < HumanList.size(); i++) {
            if (HumanList.get(i).x == x && HumanList.get(i).y == y) {
                return HumanList.get(i);
            }
        }
        
        
        return null;
    }

    public void removePiece(pieces p, ArrayList<pieces> HumanList, ArrayList<pieces> AiList) {

        if (AiList.contains(p)) {
            AiList.remove(p);
            return;
        }
        
        if (HumanList.contains(p)) {
            HumanList.remove(p);
            return;
        }
        
        
        }
    }
