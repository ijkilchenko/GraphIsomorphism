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
            int k= table.getLevel(r);
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
            System.out.println("False. Graphs are of different size! ");
            return map;
        }
        Table[] tables1= new Table[n];
        Table[] tables2= new Table[n];

        for (int i= 0; i < n; i++){
            tables1[i]= Graph.makeTable(G1, i);
            tables2[i]= Graph.makeTable(G2, i);
        }

        boolean[] matched= new boolean[n];
        int mismatched= -1;
        for (int i= 0; i < n; i++){
            int currentLength= map.length;
            for (int j= 0; j < n; j++){
                if (matched[j] != true && j > mismatched){
                    boolean match= true;
                    match = checkConditions(map, tables1[i], tables2[j], match);
                    if (match == true){
                        map.add(currentLength, i);
                        map.add(currentLength, j);
                        matched[j]= true;
                        mismatched= -1;
                        break;
                    }
                }
            }
            if (map.length == currentLength){
                if (i-1 < 0){
                    System.out.println("False. Graphs are non-isomorphic! ");
                    return new Table();
                }
                mismatched= map.get((i-1)*2+1);
                matched[mismatched]= false;
                map.pop();
                i= map.length-1;
            }
        }

        //System.out.println("True. Graphs are isomorphic! ");
        return map;
    }

    public static boolean checkConditions(Table map, Table table, Table table1, boolean match) {
        if (table.length != table1.length){
            match= false;
        }
        else{
            for (int k= 0; k < table.length; k++){
                if (table.getWidth(k) != table1.getWidth(k)){
                    match= false;
                    break;
                }
            }
            if (match == true){
                for (int k= 0; k < map.length; k++){
                    int key= map.get(k*2);
                    int mapped= map.get(k*2+1);
                    int keyLevel= table.getLevel(key);
                    int mapLevel= table1.getLevel(mapped);
                    if (keyLevel != mapLevel){
                        match= false;
                        break;
                    }
                }
            }
        }
        return match;
    }

    public static boolean checkEdges(Graph G1, Graph G2, Table map){
        //Forward direction.
        int n= map.length;
        for (int i= 0; i < n; i++){
            int key= map.get(2*i);
            int value= map.get(2*i+1);
            if (G1.V[key].children.length != G2.V[value].children.length){
                System.out.println("Error. Map sets correspondence between nodes with different number of children! ");
                return false;
            }
            for (int j= 0; j < G1.V[key].children.length; j++){
                int key1= G1.V[key].children[j].data;
                int value1= map.get(2*key1+1);
                boolean flag= false;
                for (int k= 0; k < G2.V[value].children.length; k++){
                    if (G2.V[value].children[k].data == value1){
                        flag= true;
                    }
                }
                if (flag == false){
                    System.out.println("Error. An edge was not mapped! ");
                    return false;
                }

            }
        }
        //Backward direction.
        Table map2= new Table(); //Backward map.
        for (int i= 0; i < n; i++){
            map2.add(i, i);
        }
        for (int i= 0; i < n; i++){
            map2.add(map.get(2*i+1),i);
        }

        for (int i= 0; i < n; i++){
            int key= map2.get(2*i);
            int value= map2.get(2*i+1);
            if (G2.V[key].children.length != G1.V[value].children.length){
                System.out.println("Error. Backward map sets correspondence between nodes with different number of children! ");
                return false;
            }
            for (int j= 0; j < G2.V[key].children.length; j++){
                int key1= G2.V[key].children[j].data;
                int value1= map2.get(2*key1+1);
                boolean flag= false;
                for (int k= 0; k < G1.V[value].children.length; k++){
                    if (G1.V[value].children[k].data == value1){
                        flag= true;
                    }
                }
                if (flag == false){
                    System.out.println("Error. An edge was not backward mapped! ");
                    return false;
                }

            }
        }



        //System.out.println("Success! Isomorphism confirmed.");
        return true;
    }


}
