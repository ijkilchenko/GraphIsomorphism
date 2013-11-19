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
        node.visited= true;
        V.add(node);
        queue.enqueue(node);

        while (!queue.isEmpty()){
            Node r= queue.dequeue();
            for (int i= 0; i < r.childen.size(); i++){
                Node s= (Node)r.childen.get(i); //Why do I need to do the cast? Because of generics?
                if (s.visited == false){
                    V.add(s);
                    s.visited= true;
                    queue.enqueue(s);
                }
            }
        }

        for (int i= 0; i < V.size(); i++){
            V.get(i).visited= false;
        }
    }

    public static Tree BFS(Node node){
        //Breadth First Search to make a list of all nodes in the graph.
        Queue queue= new Queue();
        node.visited= true;
        queue.enqueue(node);

        while (!queue.isEmpty()){
            Node r= queue.dequeue();
            for (int i= 0; i < r.childen.size(); i++){
                Node s= (Node)r.childen.get(i); //Why do I need to do the cast? Because of generics?
                if (s.visited == false){
                    s.visited= true;
                    queue.enqueue(s);
                }
                else{
                    r.childen.remove(i);
                    i--;//Remove edge.
                }
            }
        }
        Tree tree= new Tree(node);
        return tree;
    }
}
