/**
 * User: ijk
 * Date: 11/17/13
 */
public class Node<T> {
    T data;
    LinkedList children;
    Node next;

    public Node(){

    }

    public Node(T data){
        this.children= new LinkedList();
        this.data= data;
    }

    public void addChild(Node node){
        children.add(node);
    }
}
