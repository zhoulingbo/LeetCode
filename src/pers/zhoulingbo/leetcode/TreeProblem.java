/*
 * 版权所有 (C) 2015 知启蒙(ZHIQIM) 保留所有权利。
 * 
 * 指定登记&发行网站： https://www.zhiqim.com/ 欢迎加盟知启蒙，[编程有你，知启蒙一路随行]。
 *
 * 本文采用《知启蒙许可证》，除非符合许可证，否则不可使用该文件！
 * 1、您可以免费使用、修改、合并、出版发行和分发，再授权软件、软件副本及衍生软件；
 * 2、您用于商业用途时，必须在原作者指定的登记网站，按原作者要求进行登记；
 * 3、您在使用、修改、合并、出版发行和分发时，必须包含版权声明、许可声明，及保留原作者的著作权、商标和专利等知识产权；
 * 4、您在互联网、移动互联网等大众网络下发行和分发再授权软件、软件副本及衍生软件时，必须在原作者指定的发行网站进行发行和分发；
 * 5、您可以在以下链接获取一个完整的许可证副本。
 * 
 * 许可证链接：http://zhiqim.org/licenses/zhiqim_register_publish_license.htm
 * 
 * 除非法律需要或书面同意，软件由原始码方式提供，无任何明示或暗示的保证和条件。详见完整许可证的权限和限制。
 */
package pers.zhoulingbo.leetcode;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

/**
 * 
 * 树问题
 * 
 */
public class TreeProblem
{

    public static void main(String[] args)
    {
        
    }

    /**
     * 235. 二叉搜索树的最近公共祖先
     * @param root
     * @param p
     * @param q
     * @return
     */
    public static TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q)
    {
        if (root.val == p.val)
            return p;
        if (root.val == q.val)
            return q;
        if (root.val > p.val && root.val < q.val)
            return root;
        if (root.val > q.val && root.val < p.val)
            return root;
        if (root.val > p.val && root.val > q.val)
            return lowestCommonAncestor(root.left, p, q);
        if (root.val < p.val && root.val < q.val)
            return lowestCommonAncestor(root.right, p, q);
        return root;
    }

    /**
     * 538. 把二叉搜索树转换为累加树
     * @param root
     * @return
     */
    public static TreeNode convertBST(TreeNode root)
    {
        if (root == null)
            return root;
        inorderTraversalSum(root, 0);
        return root;
    }

    private static int inorderTraversalSum(TreeNode root, int sum)
    {
        if (root.right != null)
            sum = inorderTraversalSum(root.right, sum);
        if (sum == 0)
            sum += root.val;
        else
        {
            root.val += sum;
            sum = root.val;
        }
        if (root.left != null)
            sum = inorderTraversalSum(root.left, sum);
        return sum;
    }

    /**
     * 669. 修剪二叉搜索树
     * @param root
     * @param L
     * @param R
     * @return
     */
    public static TreeNode trimBST(TreeNode root, int L, int R)
    {
        if (L == R)
            return new TreeNode(L);
        if (root == null)
            return null;
        if (root.val < L)
            return trimBST(root.right, L, R);
        if (root.val > R)
            return trimBST(root.left, L, R);

        root.left = trimBST(root.left, L, R);
        root.right = trimBST(root.right, L, R);
        return root;
    }

    /**
     * 637. 二叉树的层平均值
     * @param root
     * @return
     */
    public static List<Double> averageOfLevels(TreeNode root)
    {
        List<Double> list = new ArrayList<>();
        List<TreeNode> nodeList = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        double val = 0;
        int size = 0;
        while (!queue.isEmpty())
        {
            TreeNode node = queue.poll();
            val += node.val;
            size++;

            if (node.left != null)
                nodeList.add(node.left);
            if (node.right != null)
                nodeList.add(node.right);

            if (queue.isEmpty())
            {
                list.add(val / size);
                val = 0;
                size = 0;
                if (nodeList.size() > 0)
                {
                    queue.addAll(nodeList);
                    nodeList.clear();
                }
            }
        }

        return list;
    }

    /**
     * 653. 两数之和 IV - 输入 BST
     * @param root
     * @param k
     * @return
     */
    public boolean findTarget(TreeNode root, int k)
    {
        Set<Integer> set = new HashSet<>();
        set = preorderTraversal(root, set);
        for (Integer val : set)
        {
            int a = k - val;
            if (a != val && set.contains(a))
                return true;
        }
        return false;
    }

    private Set<Integer> preorderTraversal(TreeNode root, Set<Integer> set)
    {
        if (root == null)
            return set;
        set.add(root.val);
        if (root.left != null)
            set = preorderTraversal(root.left, set);
        if (root.right != null)
            set = preorderTraversal(root.right, set);
        return set;
    }

    /**
     * 404. 左叶子之和
     * @param root
     * @return
     */
    public static int sumOfLeftLeaves(TreeNode root)
    {
        return preorderTraversalSum(root, 0, false);
    }

    private static int preorderTraversalSum(TreeNode root, int sum, boolean isLeft)
    {
        if (root == null)
            return 0;
        if (root.left != null)
            sum = preorderTraversalSum(root.left, sum, true);
        if (root.right != null)
            sum = preorderTraversalSum(root.right, sum, false);
        if (isLeft && root.left == null && root.right == null)
            sum += root.val;
        return sum;
    }

}
