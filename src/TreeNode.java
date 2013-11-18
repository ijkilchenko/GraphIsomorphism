import java.util.ArrayList;

/**
 * User: ijk
 * Date: 11/17/13
 */
public class TreeNode<T> {
    T data;
    ArrayList<TreeNode> childen= new ArrayList<TreeNode>();

    public TreeNode(T data){
        this.data= data;
    }

    public void addChild(T data){
        TreeNode<T> child= new TreeNode(data);
        childen.add(child);
    }
}
