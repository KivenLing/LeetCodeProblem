package recursion;


/**
 * @author Kiven Ling
 * 2018/5/8 0:13
 * ID：235 236
 */
public class LowestCommonAncestor {
    /**
     * ID：235
     * Given a binary search tree (BST), find the lowest common ancestor (LCA)
     * of two given nodes in the BST.
     * According to the definition of LCA on Wikipedia:
     * “The lowest common ancestor is defined between two nodes v and w as
     * the lowest node in T that has both v and w as descendants
     * (where we allow a node to be a descendant of itself).”
     */
    //递归
    public static TreeNode lowestCommonAncestor(TreeNode root, TreeNode p,
                                                TreeNode q){
        if (root == null)
            return null;
        if (p.val > root.val && q.val > root.val){//p q 在root的右子树中
            return lowestCommonAncestor(root.right, p, q);
        }
        if (p.val < root.val && q.val < root.val){//p q 在root的左子树中
            return lowestCommonAncestor(root.left, p, q);
        }
        return root;
    }

    //迭代
    public static TreeNode lowestCommonAncestorII(TreeNode root, TreeNode p, TreeNode q){
        TreeNode temp = root;
        while (root != null){
            if (temp == p || temp == q)
                return temp;
            if (p.val > temp.val && q.val > temp.val){//p q 在root的右子树中
                temp = temp.right;
            }else if (p.val < temp.val && q.val < temp.val){//p q 在root的左子树中
                temp = temp.left;
            }else {
                return temp;
            }
        }
        return temp;
    }

    /**
     * ID: 236
     * 和上一题类似，二叉树仅仅是二叉树而不是BST
     */
    //803ms 重复遍历树导致性能差
    public static TreeNode lowestCommonAncestorInBT(TreeNode root, TreeNode p,
                                                    TreeNode q) {
        if (root == null || root == p || root == q){
            return root;
        }
        boolean leftContainP = contain(root.left, p);
        boolean leftContainQ = contain(root.left, q);
        if (leftContainP && leftContainQ){//p, q节点都在左子树
            return lowestCommonAncestorInBT(root.left, p, q);
        }else if (leftContainP || leftContainQ){//p, q分别在左右子树
            return root;
        }else {//leftContainP == false leftContainQ == false
            return lowestCommonAncestorInBT(root.right, p, q);
        }
    }

    //ID: 236 改进
    public static TreeNode lowestCommonAncestorInBTImprove(TreeNode root, TreeNode p,
                                                           TreeNode q) {
        if (root == null || root == p || root == q){
            return root;
        }
        TreeNode left = lowestCommonAncestorInBTImprove(root.left, p, q);
        TreeNode right = lowestCommonAncestorInBTImprove(root.right, p, q);
        if (left != null && right != null)
            return root;
        else {
            return left != null ? left : right;
        }
    }
    /**
     * 查看root二叉树是否包含node节点
     */
    private static boolean contain(TreeNode root, TreeNode node){
        if (root == null)
            return false;
        if (root == node)
            return true;
        return contain(root.left, node) || contain(root.right, node);
    }
}
class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode(int x) { val = x; }
}
