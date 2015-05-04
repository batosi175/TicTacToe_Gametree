/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package tictactoej;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

/**
 *
 * @author carlos
 */
public class Tree {
    public int[] initialArray;
    public Node gameNode;
    public static int xWins;
    public static int oWins;
    public static int ties;
    public Tree(int[] array){
        xWins = 0;
        oWins = 0;
        ties = 0;
        // determines what was the last move made. changes for the test cases
        int move = currentMove(array);

        //creation of our first node: this one does not have a parent. has own constructor
        gameNode = new Node(move, array);

    }

    // takes in the initial array
    // returns 1 for x current move, return 2 for o current move
    public int currentMove(int[] array){

        int mode =0;
        for (int i : array ){
            if (i !=0)
                mode++;
        }
        if (mode % 2 == 0)
            return 2;
        else
            return 1;
    }

    // first we have our breadth first search funcion
    // this will be recursive and only print out the first final state
    public void breadthFirst(){
        System.out.println("First Final State Breadth First Search");
        Queue<Node> q = new LinkedList<Node>();
        List<Node> l = new LinkedList<Node>();
        q.add(gameNode);
        breadthFirstRecursive(q,l);
        l.get(0).print();
    }

    public void breadthFirstRecursive(Queue q,List l){
    if (q.isEmpty())
        return;
    Node n = (Node) q.remove();
    
    //print the node if it meets condition
    //if n.xwin||n.owin||n.tie == 1;
    if ((n.xWin == 1 || n.oWin == 1 || n.tie == 1))
        //n.print();
        l.add(n);

    for (int i = 0; i<n.size; i++){
        if (n.children[i] != null)
            q.add(n.children[i]);
    }

    breadthFirstRecursive(q,l);


    }

    // depth first search algorithm recursive.
    public void depthFirst(){
        System.out.println("First Final State Depth First Search");
        //Queue<Node> q = new LinkedList<Node>();
        Stack<Node> s = new Stack<Node>();
        int size = Tree.oWins + Tree.xWins + Tree.ties;
        List<Node> l = new LinkedList<Node>();
        s.push(gameNode);
        depthFirstRecursive(s, l);
        l.get(l.size()-1).print();
    }

    public void depthFirstRecursive(Stack s, List l){
        if (s.isEmpty())
            return;

        Node n = (Node) s.peek();

        s.pop();
    
        if ((n.xWin == 1 || n.oWin == 1 || n.tie == 1))
            //n.print();
            l.add(n);


        for (int i = 0; i<n.size; i++){
            if (n.children[i] != null)
                s.push(n.children[i]);
        }

        depthFirstRecursive(s,l);


    }

        public void printTree(){
        System.out.println("All Final States\n");
        //Queue<Node> q = new LinkedList<Node>();
        Stack<Node> s = new Stack<Node>();
        s.push(gameNode);
        depthFirstRecursive(s);
    }

    public void depthFirstRecursive(Stack s){
        if (s.isEmpty())
            return;

        Node n = (Node) s.peek();

        s.pop();

        if ((n.xWin == 1 || n.oWin == 1 || n.tie == 1))
            n.print();

        for (int i = 0; i<n.size; i++){
            if (n.children[i] != null)
                s.push(n.children[i]);
        }

        depthFirstRecursive(s);


    }

}

