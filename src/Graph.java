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

    public static Tree makeTree(Graph G, int m){
        int n= G.V.length;

        Tree tree = new Tree(n);
        boolean[] visited= new boolean[n];
        int[] queue= new int[n];

        visited[m]= true;
        queue[0]= m;
        tree.setLevel(m, 0);
        int l= 1;

        int i= 0;
        while (i < l){
            int r= queue[i];
            i++;
            int k= tree.level[r];
            for (int j= 0; j < G.V[r].children.length; j++){
                Node s= G.V[r].children[j];
                if (visited[s.data] == false){
                    visited[s.data]= true;
                    queue[l]= s.data;
                    tree.setLevel(s.data, k+1);
                    l++;
                }
            }
        }

        tree.setHeight();
        tree.setWidth();

        return tree;
    }

    public static Map areIsomorphic(Graph G1, Graph G2){
        int n= G1.V.length;
        Map map= new Map(n);
        if (n != G2.V.length){
            System.out.println("False. Graphs are of different size! ");
            return null;
        }
        Tree[] tables1= new Tree[n];
        Tree[] tables2= new Tree[n];

        for (int i= 0; i < n; i++){
            tables1[i]= Graph.makeTree(G1, i);
            tables2[i]= Graph.makeTree(G2, i);
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
                        map.add(currentLength, i, j);
                        matched[j]= true;
                        mismatched= -1;
                        break;
                    }
                }
            }
            if (map.length == currentLength){
                if (i-1 < 0){
                    System.out.println("False. Graphs are non-isomorphic! ");
                    return null;
                }
                mismatched= map.getValue(i-1);
                matched[mismatched]= false;
                map.pop();
                i= map.length-1;
            }
        }

        //System.out.println("True. Graphs are isomorphic! ");
        return map;
    }

    public static boolean checkConditions(Map map, Tree tree1, Tree tree2, boolean match) {
        if (tree1.height != tree2.height){
            match= false;
        }
        else{
            for (int k= 0; k < tree1.height; k++){
                if (tree1.width[k] != tree2.width[k]){
                    match= false;
                    break;
                }
            }
            if (match == true){
                for (int k= 0; k < map.length; k++){
                    int key= map.getKey(k);
                    int value= map.getValue(k);
                    int keyLevel= tree1.getLevel(key);
                    int mapLevel= tree2.getLevel(value);
                    if (keyLevel != mapLevel){
                        match= false;
                        break;
                    }
                }
            }
        }
        return match;
    }
    /*
    public static boolean checkHeight(Tree map, Tree tree1, Tree tree2, boolean match) {
        if (tree1.length != tree2.length){
            match= false;
        }
        return match;
    }
    */

    public static boolean checkEdges(Graph G1, Graph G2, Map map){
        //Forward direction.
        int n= map.length;
        for (int i= 0; i < n; i++){
            int key= map.getKey(i);
            int value= map.getValue(i);
            if (G1.V[key].children.length != G2.V[value].children.length){
                System.out.println("Error. Map sets correspondence between nodes with different number of children! ");
                return false;
            }
            for (int j= 0; j < G1.V[key].children.length; j++){
                int key1= G1.V[key].children[j].data;
                int value1= map.mapKey(key1);
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
        Map map2= new Map(n); //Backway map.
        for (int i= 0; i < n; i++){
            map2.add(i, map.getValue(i), map.getKey(i));
        }

        for (int i= 0; i < n; i++){
            int key= map2.getKey(i);
            int value= map2.getValue(i);
            if (G2.V[key].children.length != G1.V[value].children.length){
                System.out.println("Error. Backward map sets correspondence between nodes with different number of children! ");
                return false;
            }
            for (int j= 0; j < G2.V[key].children.length; j++){
                int key1= G2.V[key].children[j].data;
                int value1= map2.mapKey(key1);
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
