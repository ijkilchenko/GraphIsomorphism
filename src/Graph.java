/**
 * User: ijk
 * Date: 1/4/14
 */
public class Graph{
    Node[] V;

    public Graph(BitMatrix matrix){
        int n= matrix.getSize();
        this.V= new Node[n];
        for (int i= 0; i < n; i++){
            V[i]= new Node(String.valueOf(i));
        }
        for (int i= 0; i < n; i++){
            for (int j= 0; j < n; j++){
                if (matrix.getBit(i,j) == true){
                    V[i].addChild(V[j]);
                }
            }
        }
    }

    public static Table makeTable(Graph G, int i){
        int n= G.V.length;

        Table table= new Table();
        LinkedList temp= new LinkedList();
        int k= 0;
        temp.add(G.V[i]);
        table.add(k, G.V[i]);

        while (i < n){
            Node r= table.get(i);
            for (int j= 0; j < r.children.getLength(); j++){
                Node s= r.children.get(j);
                if (temp.findNode(s) == false){
                    temp.add(s);
                    table.add(k + 1, s);
                    i++;
                }
            }
        }
        return table;
    }

    public static void areIsomorphic(Graph G1, Graph G2){
        Table table= makeTable(G1, 0);

        System.out.println("Breakpoint!");

    }
}
