package recursion;

/**
 * @author Kiven Ling
 * 2018/5/8 0:31
 * ID:98
 */
public class ValidateBinarySearchTree {
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
}
