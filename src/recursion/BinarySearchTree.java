package recursion;

import javax.xml.soap.Node;

/**
 * @author Kiven Ling
 * 2018/5/8 0:31
 * ID:98 450
 */
public class BinarySearchTree {
    /**
     * ID:98
     * Given a binary tree, determine if it is a valid binary search tree (BST).
     */
    public static boolean isValidBST(TreeNode root) {
        if (root == null)
            return true;
        TreeNode tempLeft = root.left;
        if (tempLeft != null) {
            while (tempLeft.right != null) {
                tempLeft = tempLeft.right;
            }
            if (tempLeft.val > root.val)
                return false;
        }
        TreeNode tempRight = root.right;
        if (tempRight != null){
            while (tempRight.left != null) {
                tempRight = tempRight.left;
            }
            if (tempRight.val < root.val)
                return false;
        }
        return isValidBST(root.left) && isValidBST(root.right);
    }

    //另外一种思路
    public static boolean isValidBSTII(TreeNode root) {
        return isValidHelper(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    private static boolean isValidHelper(TreeNode root, long min, long max){
        if (root == null)
            return true;
        if (root.val >= max || root.val <= min)
            return false;
        return isValidHelper(root.left, min, root.val) &&
                isValidHelper(root.right, root.val, max);
    }

    /**
     * ID:450
     * @param root Given a root node reference of a BST and a key,
     * @param key delete the node with the given key in the BST.
     * @return Return the root node reference (possibly updated) of the BST.
     * Basically, the deletion can be divided into two stages:
     * 1.Search for a node to remove.
     * 2.If the node is found, delete the node.
     *
     * Note: Time complexity should be O(height of tree).
     */
    public static TreeNode deleteNode(TreeNode root, int key) {
        if (root == null)
            return null;
        if (root.val == key) {//如何删除？
            if (root.left == null){
                TreeNode rightNode = root.right;
                root.right = null;
                return rightNode;
            } else if (root.right == null){
                TreeNode leftNode = root.left;
                root.left = null;
                return leftNode;
            }else {
                //当左右子树都存在时
                //一种将左子树最大值作为根 (*)
                //另外一种将右子树最小值作为根
                //这里是迭代的方法，也可以变成递归
                TreeNode pre = root;
                TreeNode maxNode = root.left;
                while (maxNode.right != null){
                    pre = maxNode;
                    maxNode = maxNode.right;
                }
                if (maxNode != root.left) {
                    pre.right = maxNode.left;
                    maxNode.left = root.left;//这一步当root的左子树只有一个节点时会出现bug
                }
                maxNode.right = root.right;
                return maxNode;
            }
        }
        if (root.val > key)
            root.left = deleteNode(root.left, key);
        else //root.val < key
            root.right = deleteNode(root.right, key);
        return root;
    }

    /**
     * 求一棵BST树的最小节点
     * @param root BST 根节点
     * @return 返回最小节点
     */
    private static TreeNode minNode(TreeNode root){
        if (root == null)
            return null;
        TreeNode tempRoot = root;
        while (tempRoot.left != null)
            tempRoot = tempRoot.left;
        return tempRoot;
    }

    /**
     * 求一棵BST树的最大节点
     * @param root BST 根节点
     * @return 返回最大节点
     */
    private static TreeNode maxNode(TreeNode root){
        if (root == null)
            return null;
        TreeNode tempRoot = root;
        while (tempRoot.right != null)
            tempRoot = tempRoot.right;
        return tempRoot;
    }

    /**
     * 删除最大节点root，维护bst
     * @param root BST中的节点
     * @return 返回最大结点左子树
     */
    private static TreeNode removeMax(TreeNode root){
        if (root.right == null) {
            TreeNode leftNode = root.left;
            root.left = null;
            return leftNode;
        }
        root.right = removeMax(root.right);
        return root;
    }
}
