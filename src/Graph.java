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
        int m= 0;
        visited[i]= true;
        table.add(0, i);

        while (table.get(m) != -1 ){
            int r= table.get(m);
            int k= table.getLevel(m);
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

    public static Table areIsomorphic(Graph G1, Graph G2){
        int n= G1.V.length;
        Table map= new Table();
        if (n != G2.V.length){
            System.out.println("Graphs are of different size!");
            return map;
        }
        Table[] tables1= new Table[n];
        Table[] tables2= new Table[n];

        for (int i= 0; i < n; i++){
            tables1[i]= Graph.makeTable(G1, i);
            tables2[i]= Graph.makeTable(G2, i);
        }

        boolean[] matched= new boolean[n];
        int noMatch= -1;
        for (int i= 0; i < n; i++){
            int mapSize= map.length;
            for (int j= 0; j < n; j++){
                if (matched[j] != true && j > noMatch){
                    boolean match= true;
                    if (tables1[i].length != tables2[j].length){
                        match= false;
                    }
                    else{
                        for (int k= 0; k < tables1[i].length; k++){
                            if (tables1[i].getWidth(k) != tables2[j].getWidth(k)){
                                match= false;
                                break;
                            }
                        }
                        if (match == false){
                            for (int k= 0; k < map.length; k++){
                                int key= map.get(k*2);
                                int mapped= map.get(k*2+1);
                                int keyLevel= 0;
                                int mapLevel= 0;
                                keyLevel= tables1[i].getLevel(key);
                                mapLevel= tables2[j].getLevel(mapped);
                                if (keyLevel != mapLevel){
                                    match= false;
                                    break;
                                }
                            }
                        }
                    }
                    if (match == true){
                        map.add(mapSize, i);
                        map.add(mapSize, j);
                        matched[j]= true;
                        noMatch= -1;
                        break;
                    }
                }
            }
            if (map.length == mapSize){
                if (i-1 < 0){
                    System.out.println("Can't find match for the very first node...");
                }
                noMatch= map.get((i-1)*2+1);
                matched[noMatch]= false;
                map.pop();
                i= map.length-1;
            }
        }

        System.out.println("Breakpoint!");

        return map;
    }
}
