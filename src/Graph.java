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
            V[i]= new Node(i);
        }
        for (int i= 0; i < n; i++){
            int childrenSize= 0;
            for (int j= 0; j < n; j++){
                if (matrix.getBit(i,j) == true){
                    childrenSize++;
                }
            }
            Node[] C= new Node[childrenSize];
            int k= 0;
            for (int j= 0; j < n; j++){
                if (matrix.getBit(i,j) == true){
                    C[k]= V[j];
                    k++;
                }
            }
            V[i].addChildren(C);
        }
    }

    public static Table makeTable(Graph G, int i){
        int n= G.V.length;

        Table table= new Table();
        boolean[] visited= new boolean[n];
        int k= 0;
        int m= 0;
        visited[i]= true;
        table.add(k, i);

        while (m < n){
            int r= table.get(m);
            k= table.getLevel(m);
            for (int j= 0; j < G.V[r].children.length; j++){
                Node s= G.V[r].children[j];
                if (visited[s.data] == false){
                    visited[s.data]= true;
                    table.add(k + 1, s.data);
                }
            }
            m++;
        }
        return table;
    }

    public static void areIsomorphic(Graph G1, Graph G2){
        int n= G1.V.length;
        if (n != G2.V.length){
            System.out.println("Graphs are of different size!");
            return;
        }
        Table[] tables1= new Table[n];
        Table[] tables2= new Table[n];

        for (int i= 0; i < n; i++){
            tables1[i]= Graph.makeTable(G1, i);
            tables2[i]= Graph.makeTable(G2, i);
        }



        System.out.println("Breakpoint!");

    }
}
