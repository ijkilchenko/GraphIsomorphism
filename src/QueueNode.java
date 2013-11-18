/**
 * User: ijk
 * Date: 11/17/13
 */
public class QueueNode {
    Node data;
    Node next;

    public QueueNode(Node node){
        data= node;
    }

    public void addNext(Node node){
        next= node;
    }
}
