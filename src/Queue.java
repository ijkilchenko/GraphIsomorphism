/**
 * User: ijk
 * Date: 11/17/13
 */
public class Queue {
    Node front;
    Node end;

    public Queue(){
        front= null;
        end= null;
    }

    public void enqueue(Node node){
        if (front == null){
            end= new Node(node);
            front= end;
        }
        else{
            end.next= new Node(node);
            end= end.next;
        }
    }

    public Node dequeue(){
        if (front != null){
            Node node= (Node)front.data;
            front= front.next;
            return node;
        }
        else{
            return null;
        }
    }

    public boolean isEmpty(){
        if (front == null){
            return true;
        }
        else{
            return false;
        }
    }
}
