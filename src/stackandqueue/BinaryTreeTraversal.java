package stackandqueue;

import util.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 * ID:102 107 103 199
 */
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
            Command<Integer> c = commands.pop();
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
            Command<Integer> c = commands.pop();
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
            Command<Integer> c = commands.pop();
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

    /*
    ID:102
    Given a binary tree, return the level order traversal of its nodes'
    values. (ie, from left to right, level by level).

    For example:
    Given binary tree [3,9,20,null,null,15,7],
     */
    public static List<List<Integer>> levelOrder(TreeNode<Integer> root) {
        List<List<Integer>> ans = new ArrayList<>();
        if (root == null)
            return ans;
        List<TreeNode> queue = new ArrayList<>();
        int index = 0;
        queue.add(root);
        while (index < queue.size()){
            List<Integer> leverNodes = new ArrayList<>();
            int size = queue.size();
            while (index < size){
                TreeNode<Integer> curNode = queue.get(index);
                leverNodes.add(curNode.val);
                if (curNode.left != null)
                    queue.add(curNode.left);
                if (curNode.right != null)
                    queue.add(curNode.right);
                index++;
            }
            ans.add(leverNodes);
        }
        return ans;
    }

    /*
    ID:107
    Given a binary tree, return the bottom-up level order traversal
    of its nodes' values. (ie, from left to right, level by level
    from leaf to root).
     */
    public static List<List<Integer>> levelOrderBottom(TreeNode<Integer> root) {
        List<List<Integer>> ans = new LinkedList<>();
        if (root == null)
            return ans;
        List<TreeNode> queue = new ArrayList<>();
        queue.add(root);
        int index = 0;
        while (index < queue.size()){
            List<Integer> level = new ArrayList<>();
            int size = queue.size();
            while (index < size){
                TreeNode<Integer> curNode = queue.get(index);
                level.add(curNode.val);
                if (curNode.left != null)
                    queue.add(curNode.left);
                if (curNode.right != null)
                    queue.add(curNode.right);
                index++;
            }
            ans.add(0, level);
        }
        return ans;
    }

    //ID:103
    /*
    Given a binary tree, return the zigzag level order traversal
    of its nodes' values. (ie, from left to right, then right to
    left for the next level and alternate between).
     */
    public static List<List<Integer>> zigzagLevelOrder(TreeNode<Integer> root) {
        List<List<Integer>> ans = new ArrayList<>();
        if (root == null)
            return ans;
        List<TreeNode> queue = new ArrayList<>();
        int index = 0;
        int count = 0;
        queue.add(root);
        while (index < queue.size()){
            List<Integer> leverNodes = new LinkedList<>();
            int size = queue.size();
            while (index < size){
                TreeNode<Integer> curNode = queue.get(index);
                if (count % 2 == 0)
                    leverNodes.add(curNode.val);
                else
                    leverNodes.add(0, curNode.val);
                if (curNode.left != null)
                        queue.add(curNode.left);
                if (curNode.right != null)
                        queue.add(curNode.right);
                index++;
            }
            ans.add(leverNodes);
            count++;
        }
        return ans;
    }

    /*
    ID:199
    Given a binary tree, imagine yourself standing on the right side of it,
    return the values of the nodes you can see ordered from top to bottom.

    For example:
    Given the following binary tree,
       1          <---
     /   \
    2     3       <---
    \     \
    5     4       <---
    You should return [1, 3, 4].
     */
    public static List<Integer> rightSideView(TreeNode<Integer> root) {
        List<Integer> res = new ArrayList<>();
        if (root == null)
            return res;
        List<TreeNode<Integer>> queue = new ArrayList<>();
        int index = 0;
        queue.add(root);
        while (index < queue.size()){
            int size = queue.size();
            res.add(queue.get(size - 1).val);
            while (index < size){
                TreeNode curTreeNode = queue.get(index);
                if (curTreeNode.left != null)
                    queue.add(curTreeNode.left);
                if (curTreeNode.right != null)
                    queue.add(curTreeNode.right);
                index++;
            }
        }
        return res;
    }
    public static void main(String[] args){
        TreeNode<Integer> root = new TreeNode(1);
        TreeNode<Integer> left = new TreeNode(2);
        TreeNode<Integer> right = new TreeNode(3);
        root.left = left;
        root.right = right;
        List<List<Integer>> ans = levelOrderBottom(root);
    }
}

class Command<T>{
    String order;
    TreeNode<T> treeNode;
    public Command(String order, TreeNode treeNode){
        this.order = order;
        this.treeNode = treeNode;
    }
}
