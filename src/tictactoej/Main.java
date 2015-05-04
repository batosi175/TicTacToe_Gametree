/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package tictactoej;

import java.util.Scanner;

/**
 *
 * @author carlos
 */
public class Main {

    /**
     * @param args the command line arguments
     */


    public static void main(String[] args) {
/*
        Scanner input = new Scanner (System.in);
        String id;

        System.out.println("please input your id");
        id = input.nextLine();
*/
        String id = "1010824";
        int idsize = id.length();
        int upper=0;
        int middle=0;
        int lower=0;
        int odds;
        for (int i =idsize-6;i<=idsize;i++){
            if (i == idsize-6)
                upper = (id.charAt(i)-48)*10;
            else if (i == idsize -5)
                upper += (id.charAt(i)-48);
            else if (i == idsize-4)
                middle = (id.charAt(i)-48)*10;
            else if (i == idsize-3)
                middle += id.charAt(i)-48;
            else if (i == idsize-2)
                lower = (id.charAt(i)-48)*10;
            else if (i == idsize-1)
                lower += id.charAt(i)-48;

        }

        //create intial state array seed for your gametree. 
        
        int[] initialState = new int[9];
        
        for (int i =0; i<9; i++){
            initialState[i] = 0;
            if (i == upper%9)
                initialState[i]= 1;
            else if (i == middle %9)
                initialState[i]=2;
            else if (i == lower %9)
                initialState[i]=1;
        }

        //Tree testTree = new Tree(testArray);
        Tree testTree = new Tree(initialState);

        //print out wins losses and ties. 
        System.out.println("x wins:"+Tree.xWins+"\no wins: "+Tree.oWins+"\nties: "+Tree.ties);

        //print out the odds for wins, losses, and ties
        float xWinPercentage = (float)Tree.xWins/((float)Tree.xWins+(float)Tree.oWins+(float)Tree.ties);
        float oWinPercentage = (float)Tree.oWins/((float)Tree.xWins+(float)Tree.oWins+(float)Tree.ties);
        float tiesPercentage = (float)Tree.ties/((float)Tree.xWins+(float)Tree.oWins+(float)Tree.ties);
        System.out.println("\nx win%: "+xWinPercentage*100+"%\no Win%: "+oWinPercentage*100+"%\ntie%: "+tiesPercentage*100+"%");
        
        odds = winOdds(initialState);
        if (odds == 1 )
            System.out.println("we are rooting for X\n");
        else if (odds == 2)
            System.out.println("we are rooting for O\n");
        

        //breathfirst is working perfectly
        testTree.breadthFirst();


        // depthfirst working perfectly
        testTree.depthFirst();

        testTree.printTree();



    }

    public static  int winOdds(int[] a){

        int xOdds=0;
        int oOdds=0;
        for (int i = 0; i < a.length; i++){
            if (a[i] == 1)
                xOdds += winChecker(a,i,1);
            if (a[i] == 2)
                oOdds += winChecker(a,i,2);

        }
        if (xOdds <oOdds)
            return 1;
        else
            return 1;
    }

    public static int winChecker(int [] a, int position, int type){
        
                //checking the horizontal.
        //horizontal
        // modules 0,1,2
        // modules 3,4,5
        // modules 6,7,8
                //cheking the vertical
        //vertical
        // modules 0,3,6
        // modules 1,4,7
        // modules 2,5,8
                //checking the diagonals
        //diagonal
        // modules 0,4,8
        // modules 2,4,6
        int temp = 0;
        if (position == 0){
            if ((a[1] == type || a[1] == 0)&& (a[2] == type || a[2] == 0))
                temp++;
            if ((a[3] == type || a[3] == 0)&& (a[6] == type || a[6] == 0))
                temp++;
            if ((a[4] == type || a[4] == 0)&& (a[8] == type || a[8] == 0))
                temp++;

        } else if ( position == 1){
            if ((a[0] == type || a[0] == 0)&& (a[2] == type || a[2] == 0))
                temp++;
            if ((a[4] == type || a[4] == 0)&& (a[7] == type || a[7] == 0))
                temp++;


        }else if ( position == 2){
            if ((a[0] == type || a[0] == 0)&& (a[1] == type || a[1] == 0))
                temp++;
            if ((a[5] == type || a[5] == 0)&& (a[8] == type || a[8] == 0))
                temp++;
            if ((a[4] == type || a[4] == 0)&& (a[6] == type || a[6] == 0))
                temp++;

        }else if ( position == 3){
            if ((a[4] == type || a[4] == 0)&& (a[5] == type || a[5] == 0))
                temp++;
            if ((a[0] == type || a[0] == 0)&& (a[6] == type || a[6] == 0))
                temp++;

        }else if ( position == 4){
            if ((a[3] == type || a[3] == 0)&& (a[5] == type || a[5] == 0))
                temp++;
            if ((a[1] == type || a[1] == 0)&& (a[7] == type || a[7] == 0))
                temp++;
            if ((a[0] == type || a[0] == 0)&& (a[8] == type || a[8] == 0))
                temp++;
            if ((a[2] == type || a[2] == 0)&& (a[6] == type || a[6] == 0))
                temp++;

        }else if ( position == 5){
            if ((a[3] == type || a[3] == 0)&& (a[4] == type || a[4] == 0))
                temp++;
            if ((a[2] == type || a[2] == 0)&& (a[8] == type || a[8] == 0))
                temp++;

        }else if ( position == 6){
            if ((a[0] == type || a[0] == 0)&& (a[3] == type || a[3] == 0))
                temp++;
            if ((a[7] == type || a[7] == 0)&& (a[8] == type || a[8] == 0))
                temp++;
            if ((a[4] == type || a[4] == 0)&& (a[2] == type || a[2] == 0))
                temp++;

        }else if ( position == 7){
            if ((a[6] == type || a[6] == 0)&& (a[8] == type || a[8] == 0))
                temp++;
            if ((a[1] == type || a[1] == 0)&& (a[4] == type || a[4] == 0))
                temp++;

        } else if (position == 8){
            if ((a[6] == type || a[6] == 0)&& (a[7] == type || a[7] == 0))
                temp++;
            if ((a[2] == type || a[2] == 0)&& (a[5] == type || a[5] == 0))
                temp++;
            if ((a[0] == type || a[0] == 0)&& (a[4] == type || a[4] == 0))
                temp++;

        }
        return temp;

    }
}
