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

    public void add(Node node){
        Node s= start;
        while (s.next != null){
            s= s.next;
        }
        s.next= node;
        length++;
    }

    public int getLength(){
        return this.length;
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

    public Node get(int i){
        Node s= start;
        int j= 0;
        while (j < i){
            s.next= s;
        }
        return s;
    }

}
