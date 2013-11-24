import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * User: ijk
 * Date: 11/17/13
 */
public class Graph {
    Node mainNode;
    ArrayList<Node> V= new ArrayList<Node>();

    public Graph(Node node){
        mainNode= node;
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

        Map<Node, Node> map= new HashMap<Node, Node>();

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
                        if (match == false){
                            //break;
                        }
                        else{
                            //if match== true, we need to check the all the mappings up to this point hold true.
                            //by calculating distances between nodes or something.
                        }

                    }
                    if (match == true){
                        map.put(G1.V.get(i), G2.V.get(j));
                        matched[j]= 1;
                        break;//Break at first possible match <- this may not be right.
                    }
                }
            }
            if (map.size() == mapSize){
                return false; //Couldn't find a match for a node.
            }
        }

        return true;
    }
}
