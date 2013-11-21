import java.util.ArrayList;

/**
 * User: ijk
 * Date: 11/17/13
 */
public class Main {

    public static void main(String[] args) {
        System.out.println("Hello World!");

        //The following makes a "kite" graph G (with "a" the main node).
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
        Graph G= new Graph(a);
        //So, the above is a typical input.

        //Makes a list of all nodes in the graph.
        G.traverse(G.mainNode);

        //Now we need to make spanning trees.
        Tree tree= G.BFS(G.mainNode);

        //We need to make an ArrayList of trees rooted at each node in the graph.
        ArrayList<Tree> trees= new ArrayList<Tree>();

        for (int i= 0; i < G.V.size(); i++){
            trees.add(G.BFS(G.V.get(i)));
        }

        //Now we need to make an ArrayList of ArrayLists where each ArrayList at each index will contain all nodes that
        //are at a certain level in the tree. TODO: re-phase this comment.

        ArrayList<ArrayList<Node>> table= trees.get(0).makeTable();
        System.out.println("Breakpoint");

    }
}
