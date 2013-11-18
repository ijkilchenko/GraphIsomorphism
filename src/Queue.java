/**
 * User: ijk
 * Date: 11/17/13
 */
public class Queue {
    QueueNode front;
    QueueNode end;

    public void enqueue(Node node){
        if (front == null){
            end= new QueueNode(node);
            front= end;
        }
        else{
            end.next= new QueueNode(node);
            end= end.next;
        }
    }

    public Node dequeue(){
        if (front != null){
            Node node= front.data;
            front= front.next;
            return node;
        }
        else{
            return null;
        }
    }
}
