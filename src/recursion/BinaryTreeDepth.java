package recursion;

import util.TreeNode;

/**
 * @author Kiven Ling
 * 2018/4/9 20:31
 * ID: 104 111
 */
public class BinaryTreeDepth {
    /**
     * ID: 104
     * @param root the root of a binary tree
     * @return the max depth of the tree
     *
     * Given a binary tree, find its maximum depth.
     * The maximum depth is the number of nodes along the longest path
     * from the root node down to the farthest leaf node.
     *
     * For example:
     * Given binary tree [3,9,20,null,null,15,7],
     *
     *      3
     *    /  \
     *   9   20
     *       / \
     *      15 7
     * return its depth = 3.
     */
    public static int maxDepth(TreeNode root) {
        if (root == null)
            return 0;
        int leftMaxDepth = maxDepth(root.left);
        int rightMaxDepth = maxDepth(root.right);
        return Integer.max(leftMaxDepth, rightMaxDepth) + 1;
    }

    public static int minDepth(TreeNode root){
        if(root == null)
            return 0;
        if(root.left == null && root.right != null)
            return minDepth(root.right) + 1;
        if(root.left != null && root.right == null)
            return minDepth(root.left) + 1;
        int leftMinDepth = minDepth(root.left);
        int rightMinDepth = minDepth(root.right);
        return Integer.min(leftMinDepth, rightMinDepth) + 1;
    }
}
