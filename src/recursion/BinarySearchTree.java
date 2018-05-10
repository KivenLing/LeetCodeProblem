package recursion;

import util.ListNode;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Kiven Ling
 * 2018/5/8 0:31
 * ID:98 450 108 109 230
 */
public class BinarySearchTree {
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

    /**
     * ID:450
     * @param root Given a root node reference of a BST and a key,
     * @param key delete the node with the given key in the BST.
     * @return Return the root node reference (possibly updated) of the BST.
     * Basically, the deletion can be divided into two stages:
     * 1.Search for a node to remove.
     * 2.If the node is found, delete the node.
     *
     * Note: Time complexity should be O(height of tree).
     */
    public static TreeNode deleteNode(TreeNode root, int key) {
        if (root == null)
            return null;
        if (root.val == key) {//如何删除？
            if (root.left == null){
                TreeNode rightNode = root.right;
                root.right = null;
                return rightNode;
            } else if (root.right == null){
                TreeNode leftNode = root.left;
                root.left = null;
                return leftNode;
            }else {
                //当左右子树都存在时
                //一种将左子树最大值作为根 (*)
                //另外一种将右子树最小值作为根
                //这里是迭代的方法，也可以变成递归
                TreeNode pre = root;
                TreeNode maxNode = root.left;
                while (maxNode.right != null){
                    pre = maxNode;
                    maxNode = maxNode.right;
                }
                if (maxNode != root.left) {
                    pre.right = maxNode.left;
                    maxNode.left = root.left;//这一步当root的左子树只有一个节点时会出现bug
                }
                maxNode.right = root.right;
                return maxNode;
            }
        }
        if (root.val > key)
            root.left = deleteNode(root.left, key);
        else //root.val < key
            root.right = deleteNode(root.right, key);
        return root;
    }

    /**
     * ID：108
     * @param nums Given an array where elements are sorted in ascending order,
     * @return convert it to a height balanced BST.
     * For this problem, a height-balanced binary tree is defined as
     * a binary tree in which the depth of the two subtrees of every
     * node never differ by more than 1.
     */
    public static TreeNode sortedArrayToBST(int[] nums) {
        if (nums.length == 0)
            return null;
        return sortedArrayToBST(nums, 0, nums.length - 1);
    }

    private static TreeNode sortedArrayToBST(int[] nums, int l, int r){
        int mid = l + (r - l) / 2;
        TreeNode root = new TreeNode(nums[mid]);
        if (l < mid)
            root.left = sortedArrayToBST(nums, l, mid - 1);
        if (r > mid)
            root.right = sortedArrayToBST(nums, mid + 1, r);
        return root;
    }

    /**
     * ID: 109
     * 和108相同，参数不同
     */
    public static TreeNode sortedListToBST(ListNode head) {
        ListNode mid = findMidNode(head);
        if (mid == null)
            return null;
        TreeNode node =new TreeNode(mid.val);
        if (mid != head)
            node.left = sortedListToBST(head);
        node.right = sortedListToBST(mid.next);
        return node;
    }

    /**
     * 寻找链表的中间节点，并且将中间节点与前一节点分开
     * @param head 链表头节点
     * @return 链表的中部节点
     */
    private static ListNode findMidNode(ListNode head){
        if (head == null || head.next == null)
            return head;
        ListNode slow = head;
        ListNode quick = head;
        ListNode pre = new ListNode();
        while (quick.next != null && quick.next.next != null){
            pre = slow;
            slow = slow.next;
            quick = quick.next.next;
        }
        pre.next = null;
        return slow;
    }

    /**
     * ID: 230
     * Given a binary search tree, write a function
     * kthSmallest to find the kth smallest element in it.
     *
     * Note:
     * You may assume k is always valid, 1 ≤ k ≤ BST's total elements.
     *
     * Follow up:
     * What if the BST is modified (insert/delete operations) often
     * and you need to find the kth smallest frequently?
     * How would you optimize the kthSmallest routine?
     */
    //时间性能差，首先浪费了时间遍历全部的树，这是不必要的，其次map的搜索也比较耗时
    public static int kthSmallest(TreeNode root, int k) {
        if (k == 1)
            return minNode(root).val;
        Map<TreeNode, Integer> record = new HashMap<>();
        record.put(null, 0);
        int count = size(root, record);
        if (k == count)
            return maxNode(root).val;
        return kthSmallestHelper(root, k, record).val;
    }

    private static TreeNode kthSmallestHelper(TreeNode root, int k, Map<TreeNode, Integer> recordSize){
        if (k == 1){
            return minNode(root);
        }
        int count = recordSize.get(root);
        if (k == count){
            return maxNode(root);
        }
        int rank = recordSize.get(root.left) + 1;
        if (rank > k){
            return kthSmallestHelper(root.left, k, recordSize);
        }else if (rank == k){
            return root;
        }else {//rank < k
            return kthSmallestHelper(root.right, k - rank, recordSize);
        }
    }
    //230 优化
//    int val = 0;
//    int rank = 0;
//    public int kthSmallest(TreeNode root, int k) {
//        travel(root, k);
//        return val;
//    }
//
//    private void travel(TreeNode root, int k){
//        if(root == null)
//            return ;
//        travel(root.left, k);
//        rank++;
//        if(rank == k){
//            val = root.val;
//            return;
//        }
//        travel(root.right, k);
//    }

    /**
     * 求bs的节点个数，并保存结果
     * @param recordSize 保存树节点个数的Map
     */
    private static int size(TreeNode root, Map<TreeNode, Integer> recordSize){
        if (root == null)
            return 0;
        int count = 1 + size(root.left, recordSize) + size(root.right, recordSize);
        recordSize.put(root, count);
        return count;
    }
    /**
     * 求一棵BST树的最小节点
     * @param root BST 根节点
     * @return 返回最小节点
     */
    private static TreeNode minNode(TreeNode root){
        if (root == null)
            return null;
        TreeNode tempRoot = root;
        while (tempRoot.left != null)
            tempRoot = tempRoot.left;
        return tempRoot;
    }

    /**
     * 求一棵BST树的最大节点
     * @param root BST 根节点
     * @return 返回最大节点
     */
    private static TreeNode maxNode(TreeNode root){
        if (root == null)
            return null;
        TreeNode tempRoot = root;
        while (tempRoot.right != null)
            tempRoot = tempRoot.right;
        return tempRoot;
    }

    /**
     * 删除最大节点root，维护bst
     * @param root BST中的节点
     * @return 返回最大结点左子树
     */
    private static TreeNode removeMax(TreeNode root){
        if (root.right == null) {
            TreeNode leftNode = root.left;
            root.left = null;
            return leftNode;
        }
        root.right = removeMax(root.right);
        return root;
    }
}
