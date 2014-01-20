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

/*    public static Table makeTable(Graph G, int i){
        int n= G.V.length;

        Table table= new Table();
        boolean[] visited= new boolean[n];
        int k= 0;
        visited[i]= true;
        table.add(k, i);

        while (i < n){
            int r= table.get(i);
            for (int j= 0; j < G.V[r].children.getLength(); j++){
                Node s= G.V[r].children.get(j);
                if (visited[s] == false){
                    visited[s]= true;
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

    }*/
}
