import java.io.IOException;

/**
 * User: ijk
 * Date: 11/17/13
 */
public class Main {
    public static void main(String[] args) throws IOException{
        System.out.println("Welcome to Graph Isomorphism!");

        //The following makes a "kite" graph G (with "a" as the main node).
        /*     a-b
               |/|
               c-d
        */
        Node<String> a= new Node("a");
        Node<String> b= new Node("b");
        Node<String> c= new Node("c");
        Node<String> d= new Node("d");
        a.addChild(b);
        a.addChild(c);
        b.addChild(a);
        b.addChild(c);
        b.addChild(d);
        c.addChild(a);
        c.addChild(b);
        c.addChild(d);
        d.addChild(c);
        d.addChild(b);
        Graph G1= new Graph(a);

/*        Graph G2= new Graph("graph");
        Graph kite= new Graph("kite");*/

        int n= 5;

        for (int i= 0; i < 100; i++){
            Graph randomGraph5= new Graph(n);
            int[][] adj5= randomGraph5.adjacency;
            int[][] perm= Checker.makePermutation(n);
            int[][] permTranspose= Checker.makeTranspose(perm);
            int[][] adj5New= Checker.matrixMultiply(perm,adj5);
            adj5New= Checker.matrixMultiply(adj5New,permTranspose);
            //Note: adj5 and adj5New must represent isomorphic graphs.


            Graph A1= new Graph();
            A1.traverseMatrix(adj5);
            Graph A2= new Graph();
            A2.traverseMatrix(adj5New);

    /*        Graph N1= new Graph("star_in_pentagon");
            Graph N2= new Graph("star_in_pentagon_isomess");*/

            boolean isomorphic= Graph.areIsomorphic(A1, A2);

            System.out.println(" " + (A1.V.size() == A2.V.size()) + " " + isomorphic);
        }
    }
}
