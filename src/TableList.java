/**
 * User: ijk
 * Date: 1/4/14
 */
public class TableList{
    TableNode start;
    TableList next= null;

    int length;

    public TableList(){
        length= 0;
        next= null;
    }

    public void add(int data){
        if (length == 0){
            start= new TableNode(data);
            length++;
            return;
        }
        TableNode s= start;
        while (s.next != null){
            s= s.next;
        }
        s.next= new TableNode(data);
        length++;
    }

/*    public boolean findNode(TableNode node){
        TableNode s= start;
        while(s.next != null){
            if (s == node){
                return true;
            }
            s.next= node;
        }
        return false;
    }*/

    public int get(int i){
        TableNode s= start.next;
        int j= 0;
        while (j < i){
            s= s.next;
            j++;
        }
        return s.data;
    }

}
