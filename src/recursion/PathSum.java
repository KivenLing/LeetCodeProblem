package recursion;

import util.TreeNode;

/**
 * @author Kiven Ling
 * 2018/4/18 10:49
 * ID: 112 404
 */
public class PathSum {
    /**
     * ID: 112
     * Given a binary tree and a sum, determine if the tree has a
     * root-to-leaf path such that adding up all the values along
     * the path equals the given sum.
     *
     * Note: A leaf is a node with no children.
     */
    public static boolean hasPathSum(TreeNode<Integer> root, int sum) {
        if (root == null || root.val > sum)
            return false;
        return hasPath(root, sum, 0);
    }

    //辅助函数，加了记录前面路径和的参数before
    private static boolean hasPath(TreeNode<Integer> root, int sum, int before){
        //这个判断条件是为了防止错误调用，其实可以不加
        if (root == null)
            return sum == before;
        before += root.val;
        if (root.left == null && root.right == null)
            return sum == before;
        boolean leftHas = false;
        boolean rightHas = false;
        if (root.left != null) {
            leftHas = hasPath(root.left, sum, before);
        }
        if (root.right != null){
            rightHas = hasPath(root.right, sum, before);
        }
        return leftHas || rightHas;
    }

    /**
     * ID: 404
     * Find the sum of all left leaves in a given binary tree.
     *
     */
    public static int sumOfLeftLeaves(TreeNode<Integer> root) {
        if (root == null || (root.left == null && root.right == null))
            return 0;
        int leftSum = 0;
        int rightSum = 0;
        if (root.left != null){
            if (root.left.left == null && root.left.right == null)
                leftSum = root.left.val;
            else
                leftSum = sumOfLeftLeaves(root.left);
        }
        if (root.right != null)
            rightSum = sumOfLeftLeaves(root.right);
        return leftSum + rightSum;
    }
}
