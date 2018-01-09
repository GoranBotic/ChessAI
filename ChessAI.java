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
    int depth = 4; //defines the current depth in game tree
    final int MaxDepth = 2; //defines the max depth
    boolean playerTurn = true; //determines who's turn it is. 0: AI 1: Human
    boolean play = true; //determines when the game is done

    Scanner kbd = new Scanner(System.in);

    public ChessAI() {
        ArrayList<pieces> HumanPieces = new ArrayList();
        ArrayList<pieces> AiPieces = new ArrayList();
        board = new char[8][8];
        newBoard = new char[8][8];

        initBoard(HumanPieces, AiPieces);
        while (play) {
            while (playerTurn) {
                System.out.println("Select a piece");
                for (int i = 0; i < HumanPieces.size(); i++) {
                    pieces aPiece = HumanPieces.get(i);
                    System.out.println("(" + i + ") " + aPiece.name + " (" + aPiece.x + "," + aPiece.y + ") ");
                }//this loop will print all the pieces available for player

                int choice = kbd.nextInt(); //player picks the piece to move
                pieces p = HumanPieces.get(choice);//player chooses where the piece wants to move
                ArrayList<char[][]> movesList = new ArrayList();
                movesList = p.move(board, p.x, p.y, HumanPieces, AiPieces); //board is updated with newest state
                newBoard = chooseBoard(movesList, p, HumanPieces, AiPieces, board);

                if (!newBoard.equals(board)) {
                    playerTurn = false;
                    board = copyBoard(newBoard);
                    System.out.println();
                    updateBoard(board); //prints new board 
                    isCheck(board,HumanPieces,AiPieces); //notfies player that Ai is in check
                }
            }//the player's turn

            if (checkEnd(newBoard, HumanPieces, AiPieces)) {
                break;
            }//check if player has won

            while (playerTurn == false) {

                newBoard = mini(newBoard, depth, Integer.MIN_VALUE, Integer.MAX_VALUE, HumanPieces, AiPieces);//alpha = best local max, beta = best local min
                updateListFromBoard(newBoard, board, HumanPieces, AiPieces);
                board = copyBoard(newBoard);
                playerTurn = true;
                System.out.println();
                updateBoard(board); //prints new board
                isCheck(board,HumanPieces,AiPieces); //notfies player is in check
            }//While Ai's turn 

            if (checkEnd(newBoard, HumanPieces, AiPieces)) {
                play = false;
            }//check if Ai has won

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
    public char[][] mini(char[][] theBoard, int depth, int alpha, int beta, ArrayList<pieces> HumanList, ArrayList<pieces> AiList) {
        ArrayList<char[][]> nextMoves = new ArrayList();
        char[][] tempBoard = new char[8][8];//use for getting value of next move
        tempBoard = copyBoard(theBoard);//use for getting value of next move
        ArrayList<pieces> tempAiList = new ArrayList();
        ArrayList<pieces> tempHumanList = new ArrayList();

        boolean removed = false;
        int removedX = 0, removedY = 0;

        tempAiList = copyList(AiList);
        tempHumanList = copyList(HumanList);
        pieces removedPiece = tempHumanList.get(0); //keeps track of any pieces that were removed

        int bestX = 0, bestY = 0; //get the best coordinates
        pieces bestPiece = tempAiList.get(0); //get the best piece

        if (depth == 0) { //if we are at the leaf nodes 
            return theBoard;

        } else {
            depth--;

            for (int i = 0; i < tempAiList.size(); i++) {

                pieces p = tempAiList.get(i);

                nextMoves = p.move(tempBoard, p.x, p.y, tempHumanList, tempAiList);
                int prevX = p.x;
                int prevY = p.y; //save the piece's last location

                if (beta >= alpha) {
                    while (!nextMoves.isEmpty()) {

                        int[] XY = p.updatedXY.get(0); //get new piece location 

                        if (tempBoard[XY[0]][XY[1]] != ' ') {

                            removedPiece = p.checkPiece(XY[0], XY[1], tempHumanList, tempAiList);
                            if (removedPiece.team == 0) {
                                p.removePiece(removedPiece, tempHumanList, tempAiList);
                                removedX = XY[0];
                                removedY = XY[1];
                                removed = true;
                            }
                        }

                        tempBoard = nextMoves.get(0); //Ai gets available move for that piece
                        p.x = XY[0];
                        p.y = XY[1]; //set new location

                        nextMoves.remove(0); //take out the move  
                        p.updatedXY.remove(0); //removes a from movelist
                        System.out.println();
                        updateBoard(tempBoard);
                        char[][] currentBoard = copyBoard(tempBoard);
                        int value = 0;

                        if (beta > alpha) {
                            tempBoard = max(tempBoard, depth, alpha, beta, tempHumanList, tempAiList);
                            updateListFromBoard(tempBoard, currentBoard, tempHumanList, tempAiList);
                            value = EvaluateBoard(tempBoard, tempHumanList, tempAiList);
                        }

                        System.out.println();
                        updateBoard(tempBoard);

                        if (value < beta) {
                            beta = value; //update new beta
                            bestPiece = AiList.get(i);
                            bestX = p.x;
                            bestY = p.y;
                            tempBoard = copyBoard(theBoard);
                            tempHumanList = copyList(HumanList);//undoes the player move
                            tempAiList = copyList(AiList); //undoes the Ai move
                            System.out.println();
                            updateBoard(tempBoard);

                            if (beta <= alpha) {
                                undoMove(prevX, prevY, p, tempBoard);
                                break;//prune here
                            }

                        } else {

                            tempBoard = copyBoard(theBoard);
                            tempHumanList = copyList(HumanList);//undoes the player move
                            tempAiList = copyList(AiList);
                            undoMove(prevX, prevY, p, tempBoard);

                        }
                        undoMove(prevX, prevY, p, tempBoard);
                        if (removed == true) {
                            tempBoard[removedX][removedY] = removedPiece.name;
                            removed = false;
                        }
                    }//while moves are empty
                }

            }

        }//recursive method

        if (theBoard[bestX][bestY] != ' ') {
            removedPiece = removedPiece.checkPiece(bestX, bestY, HumanList, AiList);
            if (removedPiece.team == 0) {
                removedPiece.removePiece(removedPiece, HumanList, AiList);
            }
        }

        undoMove(bestX, bestY, bestPiece, theBoard); //*does not undo* it sets the best piece in it's best location

        return theBoard;
    }//mini function

    public char[][] max(char[][] theBoard, int depth, int alpha, int beta, ArrayList<pieces> HumanList, ArrayList<pieces> AiList) {

        ArrayList<char[][]> nextMoves = new ArrayList(); //create a branch for every move available for player
        ArrayList<pieces> tempHumanList = new ArrayList();
        ArrayList<pieces> tempAiList = new ArrayList();

        tempHumanList = copyList(HumanList);
        tempAiList = copyList(AiList);
        boolean removed = false;
        pieces removedPiece = tempAiList.get(0); //keeps track of removed ai piece
        int removedX = 0, removedY = 0;
        char[][] tempBoard = new char[8][8];
        int bestX = 0, bestY = 0;
        pieces bestPiece = tempHumanList.get(0);//place holder

        tempBoard = copyBoard(theBoard);//use for getting value of next move

        if (depth == 0) {
            return theBoard;
        } else {
            depth--;

            for (int i = 0; i < tempHumanList.size(); i++) {

                pieces p = tempHumanList.get(i);
                p.AiControl = 1; //prevents the prompts from showing in console
                nextMoves = p.move(tempBoard, p.x, p.y, tempHumanList, tempAiList);
                p.AiControl = 0;
                int prevX = p.x;
                int prevY = p.y; //save the piece's last location

                if (alpha < beta) {
                    while (!nextMoves.isEmpty()) {

                        int[] XY = p.updatedXY.get(0); //get new piece location

                        if (tempBoard[XY[0]][XY[1]] != ' ') {

                            removedPiece = p.checkPiece(XY[0], XY[1], tempHumanList, tempAiList);
                            if (removedPiece.team == 1) {
                                removedX = XY[0];
                                removedY = XY[1];
                                p.removePiece(removedPiece, tempHumanList, tempAiList);
                                removed = true;
                            }
                        }
                        tempBoard = nextMoves.get(0); //update board with player's move only through ASCII

                        p.x = XY[0];
                        p.y = XY[1]; //set new location
                        nextMoves.remove(0); //remove possible player move
                        p.updatedXY.remove(0); // remove respective player move
                        char[][] currentBoard = copyBoard(tempBoard);
                        int value = 0;

                        if (alpha < beta) {
                            tempBoard = mini(tempBoard, depth, alpha, beta, tempHumanList, tempAiList);
                            updateListFromBoard(tempBoard, currentBoard, tempHumanList, tempAiList);
                            value = EvaluateBoard(tempBoard, tempHumanList, tempAiList);
                        }
                        System.out.println();
                        updateBoard(tempBoard);

                        if (value > alpha) {
                            alpha = value; //update the alpha
                            bestPiece = HumanList.get(i); //store the best piece
                            bestX = p.x;
                            bestY = p.y;//get the best piece and it's move
                            tempBoard = copyBoard(theBoard);
                            undoMove(prevX, prevY, p, tempBoard); //undo move
                            tempAiList = copyList(AiList);
                            System.out.println();
                            updateBoard(tempBoard);

                        } else {
                            undoMove(prevX, prevY, p, tempBoard); //undo move
                            tempAiList = copyList(AiList);
                            tempBoard = copyBoard(theBoard);
                        }//check if board is greater than current alpha

                        undoMove(prevX, prevY, p, tempBoard);
                        if (removed == true) {
                            tempBoard[removedX][removedY] = removedPiece.name;
                            removed = false;
                        }
                    }//while move is empty
                }//if our local max is less than our local min the evaluate else prune

            }//looks through all pieces player can move

        } //recursive method

        if (theBoard[bestX][bestY] != ' ') {
            removedPiece = removedPiece.checkPiece(bestX, bestY, HumanList, AiList);
            if (removedPiece.team == 1) {
                removedPiece.removePiece(removedPiece, HumanList, AiList);
            }
        }

        undoMove(bestX, bestY, bestPiece, theBoard);//move the best piece to it's best location

        return theBoard;
    }//max function

    public int EvaluateBoard(char[][] board, ArrayList<pieces> HumanList, ArrayList<pieces> AiList) {
        int totalValue, HumanVal = 0, AiVal = 0;

        for (int i = 0; i < HumanList.size(); i++) {
            HumanVal += HumanList.get(i).value;
        }
        for (int i = 0; i < AiList.size(); i++) {
            AiVal += AiList.get(i).value;
        }

        totalValue = HumanVal - AiVal;

        return totalValue;
    }

    public void initBoard(ArrayList<pieces> HumanPieces, ArrayList<pieces> AiPieces) {

        for (int i = 0; i < board.length; i++) {
            board[1][i] = 'p';
            pieces p = new pawn(1, i, 1, 'p', 1, 1, 1);
            AiPieces.add(p);
        }

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

    public char[][] updateBoard(ArrayList<pieces> HumanList, ArrayList<pieces> AiList) {
        char[][] resultBoard = new char[8][8];

        for (int i = 0; i < resultBoard.length; i++) {
            for (int j = 0; j < resultBoard[i].length; j++) {
                resultBoard[i][j] = ' ';
            }
        }

        for (int j = 0; j < HumanList.size(); j++) {
            pieces p = HumanList.get(j);
            resultBoard[p.x][p.y] = p.name;
        }

        for (int k = 0; k < AiList.size(); k++) {
            pieces p = AiList.get(k);
            resultBoard[p.x][p.y] = p.name;
        }

        return resultBoard;
    }

    public void updateListFromBoard(char[][] theBoard, char[][] originalBoard, ArrayList<pieces> HumanList, ArrayList<pieces> AiList) {
        char[][] tempOrigBoard = copyBoard(originalBoard);
        pieces pieceMoved;

        for (pieces p : HumanList) {
            if (theBoard[p.x][p.y] != p.name) {
                pieceMoved = p;
                pieceMoved.AiControl = 1;
                ArrayList<char[][]> possibleMoves = pieceMoved.move(tempOrigBoard, pieceMoved.x, pieceMoved.y, HumanList, AiList);
                pieceMoved.AiControl = 0;
                for (char[][] moves : possibleMoves) {
//                    System.out.println("moves");
//                    updateBoard(moves);
//                    System.out.println("tempOrig");
//                    updateBoard(theBoard);
                    if (isEqual(moves, theBoard)) {
                        int[] XY = pieceMoved.updatedXY.get(0);
                        pieceMoved.x = XY[0];
                        pieceMoved.y = XY[1];
                        pieceMoved.updatedXY.clear();
                        return;
                    }

                }

            }
        }//check if any piece from the player list has moved and get it's possible moves

        for (pieces p : AiList) {
            if (theBoard[p.x][p.y] != p.name) {
                pieceMoved = p;
                ArrayList<char[][]> possibleMoves = pieceMoved.move(tempOrigBoard, pieceMoved.x, pieceMoved.y, HumanList, AiList);

                for (char[][] moves : possibleMoves) {
//                    System.out.println("moves");
//                    updateBoard(moves);

                    if (isEqual(moves, theBoard)) {
                        int[] XY = pieceMoved.updatedXY.get(0);
                        pieceMoved.x = XY[0];
                        pieceMoved.y = XY[1];
                        pieceMoved.updatedXY.clear();
                        return;
                    }

                }//check if any piece from Ai was moved and get it's possible moves

            }

        }
    }//update list from the board

    public boolean isEqual(char[][] board1, char[][] board2) {
        for (int i = 0; i < board1.length; i++) {
            for (int j = 0; j < board1[i].length; j++) {
                if (board1[i][j] != board2[i][j]) {
                    return false;
                }
            }
        }

        return true;
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

    public ArrayList<pieces> copyList(ArrayList<pieces> someList) {
        ArrayList<pieces> resultList = new ArrayList();

        ArrayList<int[]> updatedXY = new ArrayList(); //stores changed x and y in respect with board states
        int x, y, direction, team, value, AiControl; //AiControl will turn off the messages for human users
        char name;

        for (int i = 0; i < someList.size(); i++) {
            pieces p = someList.get(i);
            x = p.x;
            y = p.y;
            direction = p.direction;
            name = p.name;
            team = p.team;
            value = p.value;
            AiControl = p.AiControl;

            if (p instanceof pawn) {
                pieces newPiece = new pawn(x, y, direction, name, team, value, AiControl);
                resultList.add(newPiece);
            }

            if (p instanceof knight) {
                pieces newPiece = new knight(x, y, direction, name, team, value, AiControl);
                resultList.add(newPiece);
            }

            if (p instanceof bishop) {
                pieces newPiece = new bishop(x, y, direction, name, team, value, AiControl);
                resultList.add(newPiece);
            }

            if (p instanceof king) {
                pieces newPiece = new king(x, y, direction, name, team, value, AiControl);
                resultList.add(newPiece);
            }

            if (p instanceof queen) {
                pieces newPiece = new queen(x, y, direction, name, team, value, AiControl);
                resultList.add(newPiece);
            }

            if (p instanceof rook) {
                pieces newPiece = new rook(x, y, direction, name, team, value, AiControl);
                resultList.add(newPiece);
            }

        }//for each piece in the original list

        return resultList;
    }//return a deep copy of list provided in parameter

    public void undoMove(int prevX, int prevY, pieces p, char[][] theBoard) {
        theBoard[p.x][p.y] = ' ';
        p.x = prevX;
        p.y = prevY;
        theBoard[p.x][p.y] = p.name;

    }//undo's the move 

    public boolean checkEnd(char[][] theBoard, ArrayList<pieces> HumanList, ArrayList<pieces> AiList) {
        int value;
        value = EvaluateBoard(theBoard, HumanList, AiList);
        if (value >= 100) {
            System.out.println("Player Wins!");
            return true;
        }
        if (value <= -100) {
            System.out.println("Ai Wins!");
            return true;
        }
        return false;
    } //checks if the game is over

    public void isCheck(char[][] theBoard,ArrayList<pieces> HumanList, ArrayList<pieces> AiList) {
        ArrayList<pieces> tempHumanList = copyList(HumanList);
        ArrayList<pieces> tempAiList = copyList(AiList);
        char[][] tempBoard = copyBoard(theBoard);
        
        for (pieces p : tempHumanList) {
            ArrayList<char[][]> movesList = new ArrayList();
            movesList = p.move(tempBoard, p.x, p.y, tempHumanList, tempAiList);
            for (int i = 0; i < movesList.size(); i++) {
                int[] XY = p.updatedXY.get(i);
                if (theBoard[XY[0]][XY[1]] == 'k') {
                    //TODO:make a boolean function that returns true if the ai king can find a safe spot
                    System.out.println("Ai king is in check");
                    return;
                }
            }

        }

        for (pieces p : tempAiList) {
            ArrayList<char[][]> movesList = new ArrayList();
            movesList = p.move(tempBoard, p.x, p.y, tempHumanList, tempAiList);
            for (int i = 0; i < movesList.size(); i++) {
                int[] XY = p.updatedXY.get(i);
                if (board[XY[0]][XY[1]] == 'K') {
                    //TODO:make a boolean function that returns true if the ai king can find a safe spot
                    System.out.println("Player king is in check");
                    return;
                }
            }

        }

    }//determines if a king is in check

    public static void main(String[] args) {
        ChessAI c = new ChessAI();
    }
;

}
