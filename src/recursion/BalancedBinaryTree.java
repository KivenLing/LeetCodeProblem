package recursion;

import util.TreeNode;

/**
 * @author Kiven Ling
 * 2018/4/18 10:41
 * ID: 110
 */
public class BalancedBinaryTree {
    /**
     *
     * 平衡二叉树: a binary tree in which the depth of the two subtrees
     * of every node never differ by more than 1.
     * @param root 二叉树根节点
     * @return 平衡二叉树返回true，不是平衡二叉树返回false
     */
    //思路：首先判断左右子树高度差，再判断左右子树是否为平衡二叉树
    //缺点：若左右子树高度差满足要求，但左右子树中其子树有不满足要求的会做大量重复运算。
    //重复在于再求树高度有左右子树的高度比较
    //优化思路：应该提前判断左子树或者右子树的是否为平衡树，但是如果先判断，
    //会有大量递归压栈，不够号，希望再求树树高度上有体现。
    public static boolean isBalanced(TreeNode root) {
        if (root == null)
            return true;
        int leftD = BinaryTreeDepth.maxDepth(root.left);
        int rightD = BinaryTreeDepth.maxDepth(root.right);
        if (Math.abs(leftD - rightD) > 1)
            return false;
        return isBalanced(root.left) && isBalanced(root.right);
    }

    public static boolean isBalancedImprove(TreeNode root){
        if (getMaxDepth(root) == -1)
            return false;
        return true;
    }

    public static int getMaxDepth(TreeNode root){
        if (root == null)
            return 0;
        int leftD = getMaxDepth(root.left);
        int rightD = getMaxDepth(root.right);
        //高度差大于1
        if (Math.abs(leftD - rightD) > 1)
            return -1;
        //左右子树一旦有不平衡的树，那么整棵树不平衡
        if (leftD == -1 || rightD == -1)
            return -1;
        return Integer.max(leftD, rightD) + 1;
    }
}
