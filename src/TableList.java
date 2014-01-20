/**
 * User: ijk
 * Date: 1/4/14
 */
public class TableList{
    TableNode start;
    int length;
    TableList next;

    public TableList(){
        start = new TableNode();
        length= 0;
        next= null;
    }

    public void add(int index){
        TableNode s= start;
        while (s.next != null){
            s= s.next;
        }
        s.next= new TableNode(index);
        length++;
    }

    public int getLength(){
        return this.length;
    }

    public boolean findNode(TableNode node){
        TableNode s= start;
        while(s.next != null){
            if (s == node){
                return true;
            }
            s.next= node;
        }
        return false;
    }

    public int get(int i){
        TableNode s= start;
        int j= 0;
        while (j < i){
            s.next= s;
        }
        return s.data;
    }

}
