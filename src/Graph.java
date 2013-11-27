import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

/**
 * User: ijk
 * Date: 11/17/13
 */
public class Graph{
    Node mainNode;
    int[][] adjacency;//TODO: possibly change this to boolean[][] to save space?
    ArrayList<Node> V= new ArrayList<Node>();

    public Graph(){

    }

    public Graph(Node node){
        mainNode= node;
    }

    public Graph(String name) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("Graphs/"+name+".txt"));
        StringBuilder sb = new StringBuilder();
        String line = br.readLine();
        while (line != null) {
            sb.append(line);
            sb.append('\n');
            line = br.readLine();
        }
        br.close();
        String matrixStr = sb.toString();
        int n= (matrixStr.indexOf("\n")+1)/2; //Number of nodes in the graph.
        int[][] matrix= new int[n][n];
        for (int i= 0; i < n; i++){
            for (int j= 0; j < 2*n-1; j=j+2){
                matrix[i][j/2]= Integer.parseInt(matrixStr.substring(i*(2*n-1+1)+j,i*(2*n-1+1)+j+1));
            }
        }
        traverseMatrix(matrix);
    }

    public Graph(int n) throws IOException {
        Random random= new Random();

        //Make a random undirected (i.e. symmetric matrix) graph's adjacency matrix.
        int[][] matrix= new int[n][n];
        for (int i= 0; i < n; i++){
            for (int j= i; j < n; j++){
                if (i == j){
                    matrix[i][j]= 0;
                }
                else{
                    matrix[i][j]= random.nextInt(2);
                    matrix[j][i]= matrix[i][j];
                }
            }
        }
        //TODO: matrix might not be connected,yet.
        //I think the probability of a graph being disconnected is (1/2)^n. For example, if n=5, then p=3%.
        traverseMatrix(matrix);
    }

    public void traverseMatrix(int[][] matrix) throws IOException{
        ArrayList<Node> nodes= new ArrayList<Node>();
        for (int i= 0; i < matrix.length; i++){
            nodes.add(new Node(String.valueOf(i)));
        }

        for (int i= 0; i < matrix.length; i++){
            for (int j= 0; j < matrix.length; j++){
                if (matrix[i][j] == 1){
                    nodes.get(i).addChild(nodes.get(j));
                }
            }
        }

        mainNode= nodes.get(0);
        this.adjacency= matrix;
    }

    public void traverse(Node node){
        //Breadth First Search to make a list of all nodes in the graph.
        if (node.index != null){
            return; //Already traversed.
        }
        int index= 0;
        Queue queue= new Queue();
        node.index= 0;
        V.add(node);
        index++;
        queue.enqueue(node);

        while (!queue.isEmpty()){
            Node r= queue.dequeue();
            for (int i= 0; i < r.children.size(); i++){
                Node s= (Node)r.children.get(i);
                if (V.indexOf(s) < 0){
                    s.index= index;
                    V.add(s);
                    index++;
                    queue.enqueue(s);
                }
            }
        }
    }

    public static Tree BFS(Node node){
        //Breadth First Search to make a spanning tree of a graph rooted at 'node'.
        Queue queue= new Queue();
        Queue treeQueue= new Queue();
        ArrayList<Node> tempV= new ArrayList<Node>();
        tempV.add(node);
        queue.enqueue(node);
        Node root= new Node(node.data, node.index);
        treeQueue.enqueue(root);

        while (!queue.isEmpty()){
            Node r= queue.dequeue();
            Node t= treeQueue.dequeue();
            for (int i= 0; i < r.children.size(); i++){
                Node s= (Node)r.children.get(i);
                if (tempV.indexOf(s) < 0){
                    tempV.add(s);
                    Node child= new Node(s.data, s.index);
                    t.children.add(child);
                    queue.enqueue(s);
                    treeQueue.enqueue(child);
                }
            }
        }
        return (new Tree(root));
    }

    public static boolean areIsomorphic(Graph G1, Graph G2){
        //Some initializations...
        G1.traverse(G1.mainNode);
        G2.traverse(G2.mainNode);
        if (G1.V.size() != G2.V.size()) return false;

        ArrayList<Tree> trees1= new ArrayList<Tree>();
        for (int i= 0; i < G1.V.size(); i++){
            trees1.add(G1.BFS(G1.V.get(i)));
        }

        ArrayList<Tree> trees2= new ArrayList<Tree>();
        for (int i= 0; i < G2.V.size(); i++){
            trees2.add(G2.BFS(G2.V.get(i)));
        }

        ArrayList<ArrayList<ArrayList<Node>>> tables1= new ArrayList<ArrayList<ArrayList<Node>>>();
        for (int i= 0; i < G1.V.size(); i++){
            tables1.add(trees1.get(i).makeTable());
        }

        ArrayList<ArrayList<ArrayList<Node>>> tables2= new ArrayList<ArrayList<ArrayList<Node>>>();
        for (int i= 0; i < G2.V.size(); i++){
            tables2.add(trees2.get(i).makeTable());
        }

        ArrayList<Map<Node, Node>> map= new ArrayList<Map<Node, Node>>();

        int[] matched= new int[G2.V.size()];

        for (int i= 0; i < G1.V.size(); i++){
            int mapSize= map.size();
            for (int j= 0; j < G2.V.size(); j++){
                if (matched[j] != 1){
                    boolean match= true;
                    if (tables1.get(G1.V.get(i).index).size() != tables2.get(G2.V.get(j).index).size()){
                        //Distance to the farthest node must be the same in both spanning trees.
                        match= false;
                    }
                    else{
                        //Check that the number of nodes at each distance away is the same in both spanning trees.
                        for (int k= 0; k < tables1.get(G1.V.get(i).index).size(); k++){
                            if (tables1.get(G1.V.get(i).index).get(k).size() != tables2.get(G2.V.get(j).index).get(k).size()){
                                match= false;
                                break;
                            }
                        }
                        if (match == true){
                            //if match== true, we need to check the all the mappings up to this point hold true.
                            //by calculating distances between nodes or something.
                            for (int k= 0; k < map.size(); k++){
                                Set<Node> set= map.get(k).keySet();
                                Node node= set.iterator().next();
                                int nodeLevel= 0;
                                int mNodeLevel= 0;
                                //Search current spanning tables...
                                ArrayList<ArrayList<Node>> table1= tables1.get(G1.V.get(i).index);
                                ArrayList<ArrayList<Node>> table2= tables2.get(G2.V.get(j).index);
                                for (int l= 0; l < table1.size(); l++){
                                    for (int m= 0; m < table1.get(l).size(); m++){
                                        if (table1.get(l).get(m).index == node.index){ //We are comparing graph nodes with tree nodes.
                                            nodeLevel= l;
                                            break;
                                        }
                                    }
                                }
                                for (int l= 0; l < table2.size(); l++){
                                    for (int m= 0; m < table2.get(l).size(); m++){
                                        if (table2.get(l).get(m).index == map.get(k).get(node).index){
                                            mNodeLevel= l;
                                            break;
                                        }
                                    }
                                }
                                if (nodeLevel != mNodeLevel){
                                    //System.out.println("Map mistmatch.");
                                    match= false;
                                    break;
                                }
                            }
                        }
                    }
                    if (match == true){
                        Map<Node, Node> newMap= new HashMap<Node, Node>();
                        newMap.put(G1.V.get(i), G2.V.get(j));
                        map.add(newMap);
                        matched[j]= 1;
                        break;//Break at first possible match <- this may not be right.
                    }
                }
            }
            if (map.size() == mapSize){
                //If this is the case, all we know for sure is that the last key-value pair is not correct
                // for the proposed isomorphism.
                System.out.println("Couldn't find a match for a node. Current map size is " + map.size());
                return false; //Couldn't find a match for a node.
            }
        }

        Map<Node, Node> mapmap= new HashMap<Node, Node>();
        for (int i= 0; i < map.size(); i++){
            mapmap.putAll(map.get(i));
        }

        if (checkMap(G1, G2, mapmap)){
            return true;
        }
        else{
            System.out.println("There was an error...");
            return false;
        }
    }

    public static boolean checkMap(Graph G1, Graph G2, Map map){
        if (map.size() != G1.V.size() || map.size() != G2.V.size()){
            return false;
        }

        //TODO: we may need to check that every point in the domain of 'map' is unique, but may need not since 'map' is a map.

        //The following nested for-loop checks the forward direction of edge preservation,
        //i.e., each edge in G1 is also found in G2.
        if (missingEdge(G1, map)){
            return false; //No edge connecting 'node' and 'child' exists in the second Graph.
        }

        //We need to check the edge preservation in the backward direction: we need to construct an inverse map.
        Map<Node, Node> inverseMap= new HashMap<Node, Node>();

        Set<Node> set= map.keySet();
        for (Node node : set){
            inverseMap.put((Node)map.get(node), node);
        }
        if (missingEdge(G2, inverseMap)){
            return false; //Missing edge when using the inverse map.
        }
        return true;
    }

    private static boolean missingEdge(Graph G1, Map map) {
        for (int i= 0; i < G1.V.size(); i++){
            Node node= G1.V.get(i);
            for (int j= 0; j < G1.V.get(i).children.size(); j++){
                Node child= (Node)node.children.get(j);
                //There exists an edge between 'node' and 'child.'
                Node mNode= (Node)map.get(node);
                Node mChild= (Node)map.get(child);
                if (mNode.children.indexOf(mChild) < 0){
                    return true;
                }
            }
        }
        return false;
    }
}
