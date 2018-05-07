package util;


import java.util.LinkedList;
import java.util.List;

/**
 * 二叉树工具类
 * @author Kiven Ling
 * 2018/5/4 10:32
 */
public class TreeHelper {
    private TreeHelper(){}

    private static final String DIS_CHAR = ",";
    private static final String NULL_NODE = "null";
    private static int index = 0;
    /**
     * @param nodes 先序遍历的二叉树树节点，节点间以逗号隔开，要求格式为
     *              10,5,-3,3,2,null,11,3,-2,null,1
     * @return 一棵二叉树
     */
    public static TreeNode<Integer> createBinaryTreeBypre(String nodes){
       String[] treeNodes = nodes.split(DIS_CHAR);
       clearIndex();
       return createBinaryTree(treeNodes);
    }

    private static TreeNode<Integer> createBinaryTree(String[] nodes){
        if (index >= nodes.length || nodes[index].equals(NULL_NODE))
            return null;
        int val = Integer.parseInt(nodes[index]);
        TreeNode<Integer> binaryTree = new TreeNode<>(val);
        index++;
        binaryTree.left = createBinaryTree(nodes);
        index++;
        binaryTree.right = createBinaryTree(nodes);
        return binaryTree;
    }

    private static void clearIndex(){
        index = 0;
    }

    /**
     * 层序遍历产生二叉树
     * @param treeNodes 格式为 10,5,-3,3,2,null,11,3,-2,null,1
     * @return 二叉树
     */
    public static TreeNode<Integer> createTreeByLevel(String treeNodes){
        if (treeNodes == null || NULL_NODE.equals(treeNodes))
            return null;
        String[] nodes = treeNodes.split(DIS_CHAR);
        int i = 0;
        String node = nodes[i++];
        TreeNode<Integer> treeNode = new TreeNode<Integer>(Integer.parseInt(node));
        List<TreeNode<Integer>> queue = new LinkedList<>();
        queue.add(treeNode);
        while (!queue.isEmpty() && i < nodes.length){
            TreeNode<Integer> tree = queue.remove(0);
            node = nodes[i++];
            if (node.equals(NULL_NODE)) {
                tree.left = null;
            } else {
                tree.left = new TreeNode<Integer>(Integer.parseInt(node));
                queue.add(tree.left);
            }
            node = nodes[i++];
            if (node.equals(NULL_NODE)) {
                tree.right = null;
            } else {
                tree.right = new TreeNode<Integer>(Integer.parseInt(node));
                queue.add(tree.right);
            }
        }
        return treeNode;
    }

    private static void preOrder(TreeNode<Integer> tree){
        if (tree == null) {
            System.out.print("null ");
            return;
        }
        System.out.print(tree.val + " ");
        preOrder(tree.left);
        preOrder(tree.right);
    }
}
