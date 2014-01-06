/**
 * User: ijk
 * Date: 1/4/14
 */
public class LinkedList{
    Node start;
    int length;

    public LinkedList(){
        start = new Node();
        length= 0;
    }

    public void addNode(Node node){
        Node s= start;
        while (s.next != null){
            s= s.next;
        }
        s.next= node;
        length++;
    }

    public int getLength(){
        return length;
    }

    public boolean findNode(Node node){
        Node s= start;
        while(s.next != null){
            if (s == node){
                return true;
            }
            s.next= node;
        }
        return false;
    }

}
