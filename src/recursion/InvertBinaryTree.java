package recursion;

import util.TreeNode;

/**
 * @author Kiven Ling
 * 2018/4/15 23:17
 * ID: 226
 */
public class InvertBinaryTree {
    /**
     * ID: 226
     * Invert a binary tree.
     *      4
     *    /   \
     *   2     7
     *  / \   / \
     * 1   3 6   9
     * to
     *
     *      4
     *    /   \
     *   7     2
     *  / \   / \
     * 9   6 3   1
     */
    public static TreeNode invertTree(TreeNode root) {
        if (root == null)
            return null;
        TreeNode tempNode = root.right;
        root.right = root.left;
        root.left = tempNode;
        invertTree(root.left);
        invertTree(root.right);
        return root;
    }
}
