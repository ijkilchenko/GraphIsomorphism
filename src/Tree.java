/**
 * User: ijk
 * Date: 1/6/14
 */
public class Tree {
    int n;
    int height;
    int[] width;
    int[] level;

    public Tree(){

    }

    public Tree(int n){
        this.n= n;
        level= new int[n];
    }

    public void setLevel(int node, int l){
        level[node]= l;
    }

    public int getLevel(int i){
        return level[i];
    }

    public void setHeight(){
        int largest= 0;
        for (int i= 0; i < n; i++){
            if (level[i] > largest){
                largest= level[i];
            }
        }
        height= largest;
    }

    public void setWidth(){
        width= new int[height];
        for (int i= 0; i < n; i++){
            width[level[i]]++;
        }
    }

    /*public void add(int k, int data){
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

    public void pop(){
        if (length == 0){
            return;
        }
        TableList s= first;
        TableList prev= first;
        int i= 0;
        while (s.next != null){
            prev= s;
            s= s.next;
            i++;
        }
        prev.next= null;
        length--;
    }

    public int get(int i){
        TableList s= first;
        while (true){
            if (s == null){
                return -1;
            }
            if (s.length > i){
                return s.get(i);
            }
            else{
                i= i - s.length;
                s= s.next;
            }
        }
    }

    public int getWidth(int i){
        TableList s= first;
        if (this.length < i){
            return -1;
        }
        else{
            int j= 0;
            while (j < i){
                s= s.next;
                j++;
            }
            return s.length;
        }
    }

    public int getLevel(int i){
        TableList s= first;
        int level= 0;
        while (true){
            if (s == null){
                return -1;
            }
            for (int j= 0; j < s.length; j++){
                if (s.get(j) == i){
                    return level;
                }
            }
            s= s.next;
            level++;
        }
    }*/

}
