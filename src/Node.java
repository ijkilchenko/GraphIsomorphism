/**
 * User: ijk
 * Date: 11/17/13
 */
public class Node<T> {
    T data;
    Node[] children;

    public Node(){

    }

    public Node(T data){
        this.data= data;
    }

    public void addChildren(Node[] C){
        this.children= C;
    }
}
