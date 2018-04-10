package recursion;

import util.TreeNode;

/**
 * @author Kiven Ling
 * 2018/4/10 2:11
 * 通过先序遍历和中序遍历来构造树
 */
public class CreateBT {
    /**
     * 通过前序，中序来构造树
     * @param preOrder 前序遍历的字符串顺序 ABDGECF
     * @param inOrder  中序遍历的字符串顺序 GDBEAFC
     * @return 二叉树
     */
    public static TreeNode<Character> createTree(String preOrder, String inOrder){
        if (preOrder.length() <= 0 || inOrder.length() <= 0)
            return null;
        TreeNode<Character> root = new TreeNode<>();
        root.val = preOrder.charAt(0);
        int indexOfRoot = inOrder.indexOf(root.val);
        root.left = createTree(preOrder.substring(1, indexOfRoot + 1),
                inOrder.substring(0,indexOfRoot));
        root.right = createTree(preOrder.substring(indexOfRoot + 1, preOrder.length()),
                inOrder.substring(indexOfRoot + 1, inOrder.length()));
        return root;
    }

    public static void preOrder(TreeNode root){
        if (root != null) {
            System.out.print(root.val + " ");
            preOrder(root.left);
            preOrder(root.right);
        }
    }

    public static void main(String[] args) {
        String preOrder = "ABDGECF";
        String inOrder = "GDBEAFC";
        TreeNode<Character> root = createTree(preOrder, inOrder);
        preOrder(root);
    }

}
