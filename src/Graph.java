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
        int i= 0;
        visited[m]= true;
        queue[i]= m;
        int l= 1;
        tree.setLevel(m, 0);

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

    public static Tree areIsomorphic(Graph G1, Graph G2){
        int n= G1.V.length;
        Tree map= new Tree();
        if (n != G2.V.length){
            System.out.println("False. Graphs are of different size! ");
            return map;
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
                    return new Tree();
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

    public static boolean checkConditions(int[] map, Tree tree1, Tree tree2, boolean match) {
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
                    int mapped= map[k];
                    int keyLevel= tree1.getLevel(k);
                    int mapLevel= tree2.getLevel(mapped);
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

    public static boolean checkEdges(Graph G1, Graph G2, int[] map){
        //Forward direction.
        int n= map.length;
        for (int i= 0; i < n; i++){
            int key= i;
            int value= map[i];
            if (G1.V[key].children.length != G2.V[value].children.length){
                System.out.println("Error. Map sets correspondence between nodes with different number of children! ");
                return false;
            }
            for (int j= 0; j < G1.V[key].children.length; j++){
                int key1= G1.V[key].children[j].data;
                int value1= map[key1];
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
        int[] map2= new int[n]; //Backward map.
        for (int i= 0; i < n; i++){
            map2[i]= i;
        }
        for (int i= 0; i < n; i++){
            map2[map[i]]= i;
        }

        for (int i= 0; i < n; i++){
            int key= i;
            int value= map2[i];
            if (G2.V[key].children.length != G1.V[value].children.length){
                System.out.println("Error. Backward map sets correspondence between nodes with different number of children! ");
                return false;
            }
            for (int j= 0; j < G2.V[key].children.length; j++){
                int key1= G2.V[key].children[j].data;
                int value1= map2[key1];
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
