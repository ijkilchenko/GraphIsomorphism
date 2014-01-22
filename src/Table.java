/**
 * User: ijk
 * Date: 1/6/14
 */
public class Table {
    TableList first;
    int length;

    public Table(){
        length= 0;
    }

    public void add(int k, int data){
        if (length == 0){
            first= new TableList();
            length++;
            first.add(data);
            return;
        }
        TableList s= first;
        TableList prev= first;
        int i= 0;
        while (i < k){
            prev= s;
            s= s.next;
            i++;
        }
        if (s == null){
            s= new TableList();
            s.add(data);
            prev.next= s;
            length++;
        }
        else{
            s.add(data);
        }
    }

    public int get(int i){
        TableList s= first;
        while (true){
            if (s.length > i){
                return s.get(i);
            }
            else{
                i= i - s.length;
                s= s.next;
            }
        }
    }

}