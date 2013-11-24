import java.util.ArrayList;

/**
 * User: ijk
 * Date: 11/17/13
 */
public class Node<T> {
    T data;
    int index;
    ArrayList<Node> children= new ArrayList<Node>();

    public Node(){
    }

    public Node(T data){
        this.data= data;
    }

    public Node(T data, int index){
        this.data= data;
        this.index= index;
    }

    public void addChild(T data){
        Node<T> child= new Node(data);
        children.add(child);
    }

    public void addChild(Node node){
        children.add(node);
    }
}
