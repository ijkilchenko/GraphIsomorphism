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

//        while (map.size() < G1.V.size()+1){
//
//
//
//        }

        return true;
    }
}
