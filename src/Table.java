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

    public void add(int k, Node node){
        TableList s= first;
        int i= 0;
        while (s.next != null && i < k){
            s= s.next;
        }
        if (s.next == null){
            TableList t= new TableList();
            t.add(node);
            s.next= t;
            length++;
        }
        else{
            s.add(node);
        }
    }

    public int getLength(){
        return this.length;
    }

/*    public Node get(int i){
        int j= 0;
        TableList s= first;
        Node node= new Node();
        while (j < i ){
            node= s.start;
            while (node.next != null && j < i){
                node.next= node;
                j++;
            }
            if (j == i){
                return node;
            }
            else{
                s.next= s;
            }
        }
        return node;
    }*/

}