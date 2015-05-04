/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package tictactoej;

import java.util.Arrays;

/**
 *
 * @author carlos
 */
public class Node {
    //variable: array of nodes, number of nodes.
    public int[] gameArray; // the array that contains our moves
    public int[] validMoves; // checks if the position is open for a move
    public Node[] children; // all possible children
    public int mode; // x or o
    public int xWin;
    public int oWin;
    public int tie;
    public int size; // size of children array

    public Node(){}

    public Node(int mode, int[] array){
        //initialize our gameArray and valid moves
        gameArray = Arrays.copyOf(array,9);
        validMoves = Arrays.copyOf(array,9);
        for (int i = 0; i<validMoves.length;i++){
            if (validMoves[i]==2)
                validMoves[i] = 1;
        }

        // setting the current mode.
        if (mode == 1)
            this.mode = 1;
        else if (mode == 2)
            this.mode = 2;


        if (checkWin(gameArray) == 1){ // if we win we increment wins, and spawn no children
            xWin++;
        } else if (checkWin(gameArray) == 2){ // if we win we increment wins and spawn no children
            oWin++;
        } else if (checkWin(gameArray) == 0 && countEmpty(gameArray) != 0){
            // if we havent won and there are empty spaces, we dont increment wins and we make children
            size = countEmpty(gameArray);
            children = new Node[size];
            for (int i =0; i<size; i++){
                // find a valid position
                int validPosition = 0;
                for (int j = 0; j<validMoves.length; j++){
                    if (validMoves[j] == 0){
                        validPosition = j;
                        validMoves[j] = 1;
                        break;
                    }
                }
                //create child
                children[i] = new Node(this.mode, gameArray,validPosition);
            }
        } else // if we havent won and there are no empty spaces we have a tie spawn no children
            tie++;

    }

    public Node(int mode, int[] array, int position){
        //initialize our gameArray and valid moves
        gameArray = Arrays.copyOf(array,9);
        validMoves = Arrays.copyOf(array,9);
        for (int i = 0; i<validMoves.length;i++){
            if (validMoves[i]==2)
                validMoves[i] = 1;
        }

        // setting the current mode.
        if (mode == 1)
            this.mode = 2;
        else if (mode == 2)
            this.mode = 1;

        //playing our mode at the given position delegated by the parent.
        gameArray[position] = this.mode;
        validMoves[position] = 1;

        
        if (checkWin(gameArray) == 1){ // if we win we increment wins, and spawn no children
            xWin++;
            Tree.xWins++;
        } else if (checkWin(gameArray) == 2){ // if we win we increment wins and spawn no children
            oWin++;
            Tree.oWins++;
        } else if (checkWin(gameArray) == 0 && countEmpty(gameArray) != 0){
            // if we havent won and there are empty spaces, we dont increment wins and we make children
            size = countEmpty(gameArray);
            children = new Node[size];
            for (int i =0; i<size; i++){
                // find a valid position
                int validPosition = 0;
                for (int j = 0; j<validMoves.length; j++){
                    if (validMoves[j] == 0){
                        validPosition = j;
                        validMoves[j] = 1;
                        break;
                    }
                }
                //create child
                children[i] = new Node(this.mode, gameArray,validPosition);
            }
        } else // if we havent won and there are no empty spaces we have a tie spawn no children
        {
            tie++;
            Tree.ties++;
        }

    }

    // this function counts our empty spaces in the gameArray so that we can know
    // how many children to create for the game tree
    public int countEmpty(int[] gameArray){
        int temp=0;
        for (int i = 0; i < 9; i++){
            if (gameArray[i] == 0)
                temp++;
        }
        return temp;
    }

    //this checks our current gameArray to see if win condition has been met.
    public int checkWin(int[] gameArray){
        //check if x won return 1
        if (gameArray[0] == 1 && gameArray[1]==1 && gameArray[2]==1)
            return 1;
        else if (gameArray[3] == 1 && gameArray[4]==1 && gameArray[5]==1)
            return 1;
        else if (gameArray[6] == 1 && gameArray[7]==1 && gameArray[8]==1)
            return 1;
        else if (gameArray[0] == 1 && gameArray[3]==1 && gameArray[6]==1)
            return 1;
        else if (gameArray[1] == 1 && gameArray[4]==1 && gameArray[7]==1)
            return 1;
        else if (gameArray[2] == 1 && gameArray[5]==1 && gameArray[8]==1)
            return 1;
        else if (gameArray[0] == 1 && gameArray[4]==1 && gameArray[8]==1)
            return 1;
        if (gameArray[2] == 1 && gameArray[4]==1 && gameArray[6]==1)
            return 1;
        //checks if O won returns 2
        else if (gameArray[0] == 2 && gameArray[1]==2 && gameArray[2]==2)
            return 2;
        else if (gameArray[3] == 2 && gameArray[4]==2 && gameArray[5]==2)
            return 2;
        else if (gameArray[6] == 2 && gameArray[7]==2 && gameArray[8]==2)
            return 2;
        else if (gameArray[0] == 2 && gameArray[3]==2 && gameArray[6]==2)
            return 2;
        else if (gameArray[1] == 2 && gameArray[4]==2 && gameArray[7]==2)
            return 2;
        else if (gameArray[2] == 2 && gameArray[5]==2 && gameArray[8]==2)
            return 2;
        else if (gameArray[0] == 2 && gameArray[4]==2 && gameArray[8]==2)
            return 2;
        if (gameArray[2] == 2 && gameArray[4]==2 && gameArray[6]==2)
            return 2;
        //if there are no wins, then it is either a tie or not complete.
        else
            return 0;
    }

    public void print(){
        for (int i =0; i<3;i++){
            if (i == 0){
                System.out.println(gameArray[0]+"_|_"+gameArray[1]+"_|_"+gameArray[2] + winType());

            } else if (i ==1){
                System.out.println(gameArray[3]+"_|_"+gameArray[4]+"_|_"+gameArray[5]);

            } else if (i ==2){
                System.out.println(gameArray[6]+" | "+gameArray[7]+" | "+gameArray[8]+"\n");

            }
        }
    }
    public String winType(){
        if (this.xWin == 1)
            return " X Wins";
        if (this.oWin == 1)
            return " O Wins";
        if (this.tie == 1)
            return " Tie";
        else
            return " Not a final State";
    }
}
