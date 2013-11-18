import java.util.ArrayList;

/**
 * User: ijk
 * Date: 11/17/13
 */
public class Node<T> {
    T data;
    boolean visited= false;
    ArrayList<Node> childen= new ArrayList<Node>();

    public Node(){
    }

    public Node(T data){
        this.data= data;
    }

    public void addChild(T data){
        Node<T> child= new Node(data);
        childen.add(child);
    }

    public void addChild(Node node){
        childen.add(node);
    }
}
