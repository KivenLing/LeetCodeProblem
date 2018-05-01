package recursion;

import util.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Kiven Ling
 * 2018/4/18 10:49
 * ID: 112 404 113 129
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

    /**
     * ID：113
     * Given a binary tree and a sum, find all root-to-leaf paths
     * where each path's sum equals the given sum.
     * Note: A leaf is a node with no children.
     *
     * Example:
     * Given the below binary tree and sum = 22,
     *       5
     *      / \
     *     4   8
     *    /   / \
     *   11  13  4
     *  /  \    / \
     * 7    2  5   1
     *
     * @return
     * [
     * [5,4,11,2],
     * [5,8,4,5]
     * ]
     */
    public static List<List<Integer>> pathSum(TreeNode<Integer> root, int sum) {
        List<List<Integer>> paths = new ArrayList<>();
        List<Integer> path = new ArrayList<>();
        pathSumHelper(root, sum, 0, path, paths);
        return paths;
    }

    private static void pathSumHelper(TreeNode<Integer> root, int sum, int lastSum, List<Integer> path, List<List<Integer>> ans){
        if (root == null)
            return;
        int sumBefore = lastSum + root.val;
        path.add(root.val);
        //路径之和为sum，并且路径是到叶子节点
        if (sumBefore == sum && root.left == null && root.right == null) {
            ans.add(new ArrayList<Integer>(path));
            path.remove(path.size() - 1);
            return;
        }
        if (root.left != null){
            List<Integer> leftPath = new ArrayList<>(path);
            pathSumHelper(root.left, sum, sumBefore, leftPath, ans);
        }
        if (root.right != null){
            List<Integer> rightPath = new ArrayList<>(path);
            pathSumHelper(root.right, sum, sumBefore, rightPath, ans);
        }
        path.remove(path.size() - 1);
    }

    /**
     * ID:129
     * Given a binary tree containing digits from 0-9 only,
     * each root-to-leaf path could represent a number.
     * An example is the root-to-leaf path 1->2->3 which
     * represents the number 123.
     * Find the total sum of all root-to-leaf numbers.
     * Note: A leaf is a node with no children.
     * Example:
     *
     * Input: [1,2,3]
     *   1
     *  / \
     * 2   3
     * Output: 25
     * Explanation:
     * The root-to-leaf path 1->2 represents the number 12.
     * The root-to-leaf path 1->3 represents the number 13.
     * Therefore, sum = 12 + 13 = 25.
     */
    public static int sumNumbers(TreeNode root) {
        int sums = 0;
        if (root == null)
            return sums;
        List<Integer> paths = new ArrayList<>();
        sumNumHelper(root, 0, paths);
        for (Integer sum : paths) {
            sums += sum;
        }
        return sums;
    }

    private static void sumNumHelper(TreeNode<Integer> root, int beforeSum, List<Integer> paths){
        beforeSum = beforeSum * 10 + root.val;
        if (root.left == null && root.right == null){
            paths.add(beforeSum);
            return;
        }
        if (root.left != null){
            sumNumHelper(root.left, beforeSum, paths);
        }
        if (root.right != null){
            sumNumHelper(root.right, beforeSum, paths);
        }
    }

    //ID:124 todo
}
