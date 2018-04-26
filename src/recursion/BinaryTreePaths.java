package recursion;

import util.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Kiven Ling
 * 2018/4/25 23:54
 * ID: 257
 */
public class BinaryTreePaths {
    private static final String PATH = "->";
    /**
     *ID: 257
     * Given a binary tree, return all root-to-leaf paths.
     * For example, given the following binary tree:
     *     1
     *   /   \
     *  2     3
     *  \
     *  5
     * All root-to-leaf paths are:
     * ["1->2->5", "1->3"]
     *
     */
    //使用StringBuffer效果好像没有String效率高
    public static List<String> binaryTreePaths(TreeNode root) {
        List<String> paths = new ArrayList<>();
        StringBuffer path = new StringBuffer();
        initTreePath(root, paths, path);
        return paths;
    }

    /**
     * 将路径保存到paths中
     * @param root 节点
     * @param paths 所有路径
     * @param lastPath 到父节点的路径
     */
    private static void initTreePath(TreeNode root, List<String> paths, StringBuffer lastPath){
        if (root == null)
            return;
        lastPath.append(root.val);
        if (isLeaf(root)){
            paths.add(lastPath.toString());
        }
        lastPath.append(PATH);
        StringBuffer vicePath = new StringBuffer(lastPath);
        initTreePath(root.left, paths, lastPath);
        initTreePath(root.right, paths, vicePath);
    }

    /**
     * 判断节点是否为叶子节点
     * 要求root不能为空
     * @param root 树的节点
     * @return 是否为叶子节点
     */
    private static boolean isLeaf(TreeNode root){
        assert root != null;
        return root.left == null && root.right == null;
    }
}
