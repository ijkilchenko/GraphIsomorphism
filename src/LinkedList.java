/**
 * User: ijk
 * Date: 1/4/14
 */
public class LinkedList{
    TableNode start;
    int length;

    public LinkedList(){
        start = new TableNode();
        length= 0;
    }

    public void add(TableNode tableNode){
        TableNode s= start;
        while (s.next != null){
            s= s.next;
        }
        s.next= tableNode;
        length++;
    }

    public int getLength(){
        return this.length;
    }

    public boolean findTableNode(TableNode tableNode){
        TableNode s= start;
        while(s.next != null){
            if (s == tableNode){
                return true;
            }
            s.next= tableNode;
        }
        return false;
    }

    public TableNode get(int i){
        TableNode s= start;
        int j= 0;
        while (j < i){
            s.next= s;
        }
        return s;
    }

}
