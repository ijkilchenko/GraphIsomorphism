/**
 * User: ijk
 * Date: 1/6/14
 */
public class Table {
    TableList first;
    int length;

    public Table(){
        first= new TableList();
        length= 0;
    }

    public void add(int k, int index){
        TableList s= first;
        int i= 0;
        while (s.next != null && i < k){
            s= s.next;
        }
        if (s.next == null){
            TableList t= new TableList();
            t.add(index);
            s.next= t;
            length++;
        }
        else{
            s.add(index);
        }
    }

/*    public int get(int i){
        TableList s= first;
        int j= 0;
        while (s.next != null && j < i){
            s= s.next;
        }
        if (s.next == null){
            TableList t= new TableList();
            t.add(index);
            s.next= t;
            length++;
        }
        else{
            s.add(index);
        }
    }*/

    public int getLength(){
        return this.length;
    }

}