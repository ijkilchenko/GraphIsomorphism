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
        Tree tree= G.BFS(G, G.mainNode);



    }
}
