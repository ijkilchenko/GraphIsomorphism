/**
 * User: ijk
 * Date: 11/17/13
 */
public class Node {
    int data;
    Node[] children;

    public Node(){

    }

    public Node(int data){
        this.data= data;
    }

    public void addChildren(Node[] C){
        this.children= C;
    }
}
