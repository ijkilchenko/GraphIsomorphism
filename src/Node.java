/**
 * User: ijk
 * Date: 11/17/13
 */
public class Node<T> {
    T data;
    LinkedList children= new LinkedList();
    Node next= null;

    public Node(){
        this.children= new LinkedList();
        this.next= null;
    }

    public Node(T data){
        this.children= new LinkedList();
        this.data= data;
    }

    public void addChild(Node node){
        children.add(node);
    }
}
