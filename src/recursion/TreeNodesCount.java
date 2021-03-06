package recursion;

import util.TreeNode;

/**
 * @author Kiven Ling
 * 2018/4/16 16:36
 * ID: 222
 */
public class TreeNodesCount {
    private static int count;
    private static int height;
    /**
     * 求完全二叉树的节点数量
     * @param root 完全二叉树的根节点
     * @return 树的节点
     */
    public static int countNodes(TreeNode root) {
        //暴力解法,遍历计数出所有节点
        //最终timeout，需要用到完全二叉树特性
        if (root == null)
            return 0;
        int left = countNodes(root.left);
        int right = countNodes(root.right);
        return 1 + left + right;
    }

    /**
     * 如果左子树和右子树深度相同返回满二叉树节点数目
     * 如果不相同，返回左子树数目+右子树节点数目 + 1
     * @param root 完全二叉树根节点
     * @return 节点数目
     */
    public static int countNodesII(TreeNode root){
        if (root == null)
            return 0;
        int leftDepth = 0;
        int rightDepth = 0;
        for(TreeNode cur = root; cur != null; cur = cur.left){
            leftDepth++;
        }
        for(TreeNode cur = root; cur != null; cur = cur.right){
            rightDepth++;
        }
        if(leftDepth == rightDepth)
            //Math.pow(2, leftDepth) - 1,利用移位操作更快
            return (1 << leftDepth) - 1;
        return countNodes(root.left) + countNodes(root.right) + 1;
    }

    /**
     * 优化思路：左右子树分别统计,迭代速度更快  48ms，leetcode最优思路
     * @param root 完全二叉树根节点
     * @return 节点数目
     */
    public static int countNodesIII(TreeNode root){
        int nodeCount = 0;
        int h = getHeight(root);//整个树高度
        TreeNode cur = root;
        while (cur != null){//记得验证边界
            if (getHeight(cur.right) == h - 1){//左右子树最高高度相同，
                // 左子树为完全二叉树，可以求左子树节点个数
                nodeCount += 1 << (h - 1);
                //求完了左子树加根节点个数
                cur = cur.right;
            }else {//此时右子树为完全二叉树，同理
                nodeCount += 1 << (h - 2);
                cur = cur.left;
            }
            h--;
        }
        return nodeCount;
    }

    //求一般完全二叉树最大高度
    private static int getHeight(TreeNode root){
        TreeNode cur = root;
        int height = 0;
        while (cur != null){
            height++;
            cur = cur.left;
        }
        return height;
    }
}
