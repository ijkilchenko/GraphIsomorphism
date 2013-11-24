/**
 * User: ijk
 * Date: 11/17/13
 */
public class Main {
    public static void main(String[] args) {
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

        //The following makes an (isomorphic) "kite" graph G (with "1" as the main node).
        /*     1-2
               |/|
               3-4
        */
        Node<String> a1= new Node("1");
        Node<String> b1= new Node("2");
        Node<String> c1= new Node("3");
        Node<String> d1= new Node("4");
        a1.addChild(b1);
        a1.addChild(c1);
        b1.addChild(a1);
        b1.addChild(c1);
        b1.addChild(d1);
        c1.addChild(a1);
        c1.addChild(b1);
        c1.addChild(d1);
        d1.addChild(c1);
        d1.addChild(b1);
        Graph G2= new Graph(b1);

        //Need to make some fancier graphs.

        Node<String> aa= new Node("a");
        Node<String> bb= new Node("b");
        Node<String> cc= new Node("c");
        Node<String> dd= new Node("d");
        Node<String> gg= new Node("g");
        Node<String> hh= new Node("h");
        Node<String> ii= new Node("i");
        Node<String> jj= new Node("j");
        aa.addChild(gg);
        aa.addChild(hh);
        aa.addChild(ii);
        bb.addChild(gg);
        bb.addChild(hh);
        bb.addChild(jj);
        cc.addChild(gg);
        cc.addChild(ii);
        cc.addChild(jj);
        dd.addChild(hh);
        dd.addChild(ii);
        dd.addChild(jj);
        gg.addChild(aa);
        gg.addChild(bb);
        gg.addChild(cc);
        hh.addChild(aa);
        hh.addChild(bb);
        hh.addChild(dd);
        ii.addChild(aa);
        ii.addChild(cc);
        ii.addChild(dd);
        jj.addChild(bb);
        jj.addChild(cc);
        jj.addChild(dd);
        Graph G3= new Graph(aa);

        Node<String> a2= new Node("1");
        Node<String> b2= new Node("6");
        Node<String> c2= new Node("8");
        Node<String> d2= new Node("3");
        Node<String> g2= new Node("5");
        Node<String> h2= new Node("2");
        Node<String> i2= new Node("4");
        Node<String> j2= new Node("7");
        a2.addChild(g2);
        a2.addChild(h2);
        a2.addChild(i2);
        b2.addChild(g2);
        b2.addChild(h2);
        b2.addChild(j2);
        c2.addChild(g2);
        c2.addChild(i2);
        c2.addChild(j2);
        d2.addChild(h2);
        d2.addChild(i2);
        d2.addChild(j2);
        g2.addChild(a2);
        g2.addChild(b2);
        g2.addChild(c2);
        h2.addChild(a2);
        h2.addChild(b2);
        h2.addChild(d2);
        i2.addChild(a2);
        i2.addChild(c2);
        i2.addChild(d2);
        j2.addChild(b2);
        j2.addChild(c2);
        j2.addChild(d2);

        Graph G4= new Graph(c2);

        boolean isomorphic= Graph.areIsomorphic(G1, G2);

        System.out.println(isomorphic);
    }
}
