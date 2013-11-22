import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

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

        boolean isomorphic= Graph.areIsomorphic(G, G);

        System.out.println("Breakpoint");

    }
}
