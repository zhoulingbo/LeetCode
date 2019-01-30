package pers.zhoulingbo.leetcode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 
 * 深度优先搜索
 * 
 * @version v1.0.0 @author zhoulingbo 2019-1-18 新建与整理
 */
public class DepthFirstSearch
{

    public static void main(String[] args)
    {
        TreeNode n = new TreeNode(1);
        n.left = new TreeNode(2);
        n.right = new TreeNode(3);
        n.left.left = new TreeNode(4);
        n.left.right = new TreeNode(5);
        n.right.left = new TreeNode(6);
        n.right.right = new TreeNode(7);

        Queue<Integer> queue = new LinkedList<>();
        queue = inorderTraversal(n, queue);
        while (!queue.isEmpty())
        {
            System.out.println(queue.poll());
        }
    }

    /**
     * 108. 将有序数组转换为二叉搜索树(convert-sorted-array-to-binary-search-tree)，
     * 将一个按照升序排列的有序数组，转换为一棵高度平衡二叉搜索树。
     * @param nums
     * @return
     */
    public static TreeNode sortedArrayToBST(int[] nums)
    {
        if (nums == null || nums.length == 0)
            return null;
        if (nums.length == 1)
            return new TreeNode(nums[0]);

        int index = nums.length / 2;
        TreeNode node = new TreeNode(nums[index]);
        node.left = sortedArrayToBST(nums, 0, index - 1);
        node.right = sortedArrayToBST(nums, index + 1, nums.length - 1);
        return node;
    }

    public static TreeNode sortedArrayToBST(int[] nums, int start, int end)
    {
        if (end < start)
            return null;
        if (end == start)
            return new TreeNode(nums[start]);

        int index = (end + start) / 2;
        TreeNode node = new TreeNode(nums[index]);
        node.left = sortedArrayToBST(nums, start, index - 1);
        node.right = sortedArrayToBST(nums, index + 1, end);
        return node;
    }

    /**
     * 104. 二叉树的最大深度(maximum-depth-of-binary-tree)
     * @param root
     * @return
     */
    public static int maxDepth(TreeNode root)
    {
        if (root == null)
            return 0;

        int depth = 1 + Math.max(maxDepth(root.left), maxDepth(root.right));
        return depth;
    }

    /**
     * 559. N叉树的最大深度(maximum-depth-of-n-ary-tree)
     * @param root
     * @return
     */
    public int maxDepth(Node root)
    {
        if (root == null)
            return 0;

        int subDepth = 0;
        for (Node node : root.children)
        {
            subDepth = Math.max(subDepth, maxDepth(node));
        }
        int depth = 1 + subDepth;
        return depth;
    }

    /**
     * 679. 24点游戏(24-game)
     * @param nums
     * @return
     */
    public static boolean judgePoint24(int[] nums)
    {
        if (nums[0] + nums[1] + nums[2] + nums[3] == 24)
            return true;
        return true;
    }

    /**
     * 733. 图像渲染(flood-fill)
     * @param image
     * @param sr
     * @param sc
     * @param newColor
     * @return
     */
    public static int[][] floodFill(int[][] image, int sr, int sc, int newColor)
    {
        if (image[sr][sc] == newColor)
            return image;

        int row = image.length;
        int col = image[0].length;
        boolean[] flag = new boolean[row * col];

        int oldColor = image[sr][sc];
        image[sr][sc] = newColor;
        flag[sr * col + sc] = true;

        if (sc - 1 >= 0 && image[sr][sc - 1] == oldColor && !flag[sr * col + sc - 1])
            image = floodFill(image, sr, sc - 1, newColor);
        if (sc + 1 < col && image[sr][sc + 1] == oldColor && !flag[sr * col + sc + 1])
            image = floodFill(image, sr, sc + 1, newColor);
        if (sr - 1 >= 0 && image[sr - 1][sc] == oldColor && !flag[(sr - 1) * col + sc])
            image = floodFill(image, sr - 1, sc, newColor);
        if (sr + 1 < row && image[sr + 1][sc] == oldColor && !flag[(sr + 1) * col + sc])
            image = floodFill(image, sr + 1, sc, newColor);

        return image;
    }

    /**
     * 897. 递增顺序查找树(increasing-order-search-tree)
     * 给定一个树，按中序遍历重新排列树，使树中最左边的结点现在是树的根，并且每个结点没有左子结点，只有一个右子结点.
     * @param root
     * @return
     */
    public static TreeNode increasingBST(TreeNode root)
    {
        if (root == null)
            return null;

        Queue<Integer> queue = new LinkedList<>();
        queue = inorderTraversal(root, queue);

        TreeNode tree = new TreeNode(queue.poll());
        TreeNode temp = tree;
        while (!queue.isEmpty())
        {
            TreeNode node = new TreeNode(queue.poll());
            temp.right = node;
            temp = node;
        }
        return tree;
    }

    public static Queue<Integer> inorderTraversal(TreeNode root, Queue<Integer> queue)
    {
        if (root == null)
            return queue;

        inorderTraversal(root.left, queue);
        queue.add(root.val);
        inorderTraversal(root.right, queue);

        return queue;
    }
}
