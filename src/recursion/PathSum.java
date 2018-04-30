package recursion;

import util.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Kiven Ling
 * 2018/4/18 10:49
 * ID: 112 404 113
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
            ans.add(path);
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
    }
    /*leetcode 最有解，有待研究
        public List<List<Integer>> pathSum(TreeNode root, int sum) {
         List<List<Integer>> res = new ArrayList<>();
	        if(root == null) {
	        	return res;
	        }
	    List<Integer> curArray = new ArrayList<>();
	    hasPath(root, sum, 0,  curArray, res );
	    return res;
	 }
	public   void hasPath(TreeNode root, int sum, int cursum,
			List<Integer> curArray, List<List<Integer>> res) {

		if(root.left == null && root.right ==null) {
            cursum+=root.val;
            curArray.add(root.val);
			if(cursum == sum) {
				res.add(new ArrayList<Integer>(curArray));
			}
			curArray.remove(curArray.size()-1);
            return;
		}
		cursum+=root.val;
		curArray.add(root.val);
        if(root.left!=null)
        {
            hasPath(root.left, sum, cursum, curArray, res);
        }
        if(root.right!=null)
        {
            hasPath(root.right, sum, cursum, curArray, res);
        }
        curArray.remove(curArray.size()-1);
    }
     */
}
