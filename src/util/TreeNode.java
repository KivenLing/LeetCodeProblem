package util;

public class TreeNode<T> {
    public T val;
    public TreeNode<T> left;
    public TreeNode<T> right;

    public TreeNode() {

    }

    public TreeNode(T val) {
        this.val = val;
    }
}