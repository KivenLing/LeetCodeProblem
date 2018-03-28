package stackandqueue;

import util.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class BinaryTreeTraversal {
    /*
    ID:144
     */
    public static List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null)
            return res;
        Stack<Command> commands = new Stack<>();
        commands.push(new Command("go", root));
        while (!commands.isEmpty()){
            Command c = commands.pop();
            if (c.order.equals("print")){
                res.add(c.treeNode.val);
            }else {
                if (c.treeNode.right != null)
                    commands.push(new Command("go", c.treeNode.right));
                if (c.treeNode.left != null)
                    commands.push(new Command("go", c.treeNode.left));
                commands.push(new Command("print", c.treeNode));
            }
        }
        return res;
    }

    //ID:94
    public static List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null)
            return res;
        Stack<Command> commands = new Stack<>();
        commands.push(new Command("go", root));
        while (!commands.isEmpty()){
            Command c = commands.pop();
            if (c.order.equals("print")){
                res.add(c.treeNode.val);
            }else {
                if (c.treeNode.right != null)
                    commands.push(new Command("go", c.treeNode.right));
                commands.push(new Command("print", c.treeNode));
                if (c.treeNode.left != null)
                    commands.push(new Command("go", c.treeNode.left));

            }
        }
        return res;
    }
    //ID:145
    public static List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null)
            return res;
        Stack<Command> commands = new Stack<>();
        commands.push(new Command("go", root));
        while (!commands.isEmpty()){
            Command c = commands.pop();
            if (c.order.equals("print")){
                res.add(c.treeNode.val);
            }else {
                commands.push(new Command("print", c.treeNode));

                if (c.treeNode.right != null)
                    commands.push(new Command("go", c.treeNode.right));

                if (c.treeNode.left != null)
                    commands.push(new Command("go", c.treeNode.left));
            }
        }
        return res;
    }
}

class Command{
    String order;
    TreeNode treeNode;
    public Command(String order, TreeNode treeNode){
        this.order = order;
        this.treeNode = treeNode;
    }
}
