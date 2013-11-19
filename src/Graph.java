import java.util.ArrayList;

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
        Queue queue= new Queue();
        V.add(node);
        queue.enqueue(node);

        while (!queue.isEmpty()){
            Node r= queue.dequeue();
            for (int i= 0; i < r.childen.size(); i++){
                Node s= (Node)r.childen.get(i); //Why do I need to do the cast? Because of generics?
                if (V.indexOf(s) < 0){
                    V.add(s);
                    queue.enqueue(s);
                }
            }
        }
    }

    public static Tree BFS(Graph G, Node node){
        Queue queue= new Queue();
        Queue treeQueue= new Queue();
        ArrayList<Node> tempV= new ArrayList<Node>();
        tempV.add(node);
        queue.enqueue(node);
        Node root= new Node(node.data);
        treeQueue.enqueue(root);

        while (!queue.isEmpty()){
            Node r= queue.dequeue();
            Node t= treeQueue.dequeue();
            for (int i= 0; i < r.childen.size(); i++){
                Node s= (Node)r.childen.get(i); //Why do I need to do the cast? Because of generics?
                if (tempV.indexOf(s) < 0){
                    tempV.add(s);
                    Node child= new Node(s.data);
                    t.childen.add(child);
                    queue.enqueue(s);
                    treeQueue.enqueue(child);
                }
            }
        }
        Tree tree= new Tree(root);
        return tree;
    }
}
