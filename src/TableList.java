/**
 * User: ijk
 * Date: 1/4/14
 */
public class TableList{
    Node start;
    int length;
    TableList next;

    public TableList(){
        start = new Node();
        length= 0;
        next= null;
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

/*    private class Node<T> {
        T data;
        Node next= null;

        public Node(){
            this.next= null;
        }

        public Node(T data){
            this.data= data;
        }
    }*/

}
