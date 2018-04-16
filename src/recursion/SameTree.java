package recursion;

import util.TreeNode;

/**
 * @author Kiven Ling
 * 2018/4/16 15:11
 * ID:100 101
 */
public class SameTree {
    /**
     * ID：100
     * @param p 二叉树
     * @param q 二叉树
     * @return p q是否相同
     */
    public static boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null && q == null)
            return true;
        //根节点值相同
        if (p != null && q != null && p.val == q.val){
            boolean left = isSameTree(p.left, q.left);
            boolean right = isSameTree(p.right, q.right);
            return left && right;
        }
        //根节点值不同
        return false;
    }

    /**
     * ID: 101
     * @param root 树根节点
     * @return root 左子树 与 右子树 是否为镜像
     *
     * Given a binary tree, check whether it is a mirror of itself
     * (ie, symmetric around its center).
     * For example, this binary tree [1,2,2,3,4,4,3] is symmetric:
     *     1
     *    / \
     *   2   2
     *  / \ / \
     * 3  4 4  3
     * But the following [1,2,2,null,3,null,3] is not:
     *   1
     *  / \
     * 2   2
     * \   \
     * 3    3
     * Note:
     * Bonus points if you could solve it both recursively and iteratively.
     */
    public static boolean isSymmetric(TreeNode root) {
        if (root == null)
            return true;
        if (root.left == null && root.right == null)
            return true;
        return symmertric(root.left, root.right);
    }

    private static boolean symmertric(TreeNode left, TreeNode right){
        if (left == null && right == null)
            return true;
        if (left == null || right == null)
            return false;
        if (left.val == right.val){
            boolean isL = symmertric(left.left, right.right);
            boolean isR = symmertric(left.right, right.left);
            return isL && isR;
        }
        return false;
    }
}
