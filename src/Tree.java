import java.util.ArrayList;

/**
 * User: ijk
 * Date: 11/17/13
 */
public class Tree {
    Node root;

    public Tree(Node node){
        root= node;
    }

    public ArrayList<ArrayList<Node>> makeTable(){
        return makeTable(new ArrayList<ArrayList<Node>>(), root, 0);
    }


    private static ArrayList<ArrayList<Node>> makeTable(ArrayList<ArrayList<Node>> table, Node node, int level){
        if (table.size() < level+1){
            table.add(level, new ArrayList<Node>());
        }
        table.get(level).add(node);

        for (int i= 0; i < node.childen.size(); i++){
            table= makeTable(table, (Node)node.childen.get(i), level+1);
        }
        return table;
    }
}
