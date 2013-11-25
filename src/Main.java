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

        int[][] perm= Checker.makePermutation(6);
        int[][] permTranspose= Checker.makeTranspose(perm);

        Graph G2= new Graph("graph");
        Graph kite= new Graph("kite");

        Graph randomGraph5= new Graph(6);

        Graph N1= new Graph("star_in_pentagon");
        Graph N2= new Graph("star_in_pentagon_isomess");

        boolean isomorphic= Graph.areIsomorphic(N2, N1);

        System.out.println(isomorphic);
    }
}
