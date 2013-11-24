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
        Graph G1= new Graph(a);

        //The following makes an isomorphic "kite" graph G (with "1" the main node).
        /*     1-2
               |/|
               3-4
        */
        Node<String> a1= new Node("1");
        Node<String> b2= new Node("2");
        Node<String> c3= new Node("3");
        Node<String> d4= new Node("4");
        a1.addChild(b2);
        a1.addChild(c3);
        b2.addChild(a1);
        b2.addChild(c3);
        b2.addChild(d4);
        c3.addChild(a1);
        c3.addChild(b2);
        c3.addChild(d4);
        d4.addChild(c3);
        d4.addChild(b2);
        Graph G2= new Graph(a1);
        //So, the above is a typical input.

        boolean isomorphic= Graph.areIsomorphic(G1, G2);

        System.out.println("Breakpoint");

    }
}
