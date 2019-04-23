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
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
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
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.right = new TreeNode(4);
        System.out.println(tree2str(root));
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

    /**
     * 606. 根据二叉树创建字符串
     * @param t
     * @return
     */
    public static String tree2str(TreeNode t)
    {
        if (t == null)
            return "";
        StringBuffer strb = new StringBuffer();
        strb = tree2str(t, strb);
        return strb.toString();
    }

    public static StringBuffer tree2str(TreeNode t, StringBuffer strb)
    {
        if (t == null)
            return strb.append('(').append(')');
        strb.append(t.val);
        if (t.left != null)
        {
            strb.append('(');
            tree2str(t.left, strb);
            strb.append(')');
        }
        if (t.right != null)
        {
            if (t.left == null)
                strb.append('(').append(')');
            strb.append('(');
            tree2str(t.right, strb);
            strb.append(')');
        }
        return strb;
    }

    /**
     * 563. 二叉树的坡度
     * @param root
     * @return
     */
    public static int findTilt(TreeNode root)
    {
        Map<Integer, Integer> map = sumMap(root, null);
        return findTilt(root, map);
    }

    public static int findTilt(TreeNode root, Map<Integer, Integer> map)
    {
        if (root == null)
            return 0;
        int left = 0, right = 0;
        if (root.left != null)
            left = map.get(root.left.val);
        if (root.right != null)
            right = map.get(root.right.val);
        int tilt = Math.abs(left - right);
        tilt += findTilt(root.left);
        tilt += findTilt(root.right);
        return tilt;
    }

    public static int sum(TreeNode root)
    {
        if (root == null)
            return 0;
        int sum = 0;
        sum += root.val;
        sum += sum(root.left);
        sum += sum(root.right);
        return sum;
    }

    public static Map<Integer, Integer> sumMap(TreeNode root, Map<Integer, Integer> map)
    {
        if (map == null)
            map = new HashMap<>();
        if (root == null)
            return map;
        int sum = 0;
        sum += root.val;
        if (root.left != null)
        {
            if (!map.containsKey(root.left))
                map = sumMap(root.left, map);
            sum += map.get(root.left.val);
        }
        if (root.right != null)
        {
            if (!map.containsKey(root.right))
                map = sumMap(root.right, map);
            sum += map.get(root.right.val);
        }
        map.put(root.val, sum);
        return map;
    }

    /**
     * 671. 二叉树中第二小的节点
     * @param root
     * @return
     */
    public int findSecondMinimumValue(TreeNode root)
    {
        if (root == null)
            return -1;
        int val = findSecondMinimumValue(root, root.val);
        return val;
    }
    
    public int findSecondMinimumValue(TreeNode root, int min)
    {
        if (root.val > min)
            return root.val;
        int val = -1;
        if (root.left != null)
        {
            int a = findSecondMinimumValue(root.left, min);
            int b = findSecondMinimumValue(root.right, min);
            if (a != -1 && b != -1)
            {
                if (a > b)
                    return b;
                else
                    return a;
            }
            if (a != -1)
                return a;
            if (b != -1)
                return b;
        }
        return val;
    }

    /**
     * 993. 二叉树的堂兄弟节点
     * @param root
     * @param x
     * @param y
     * @return
     */
    public boolean isCousins(TreeNode root, int x, int y)
    {
        int px = -1, py = -1;
        int hx = -1, hy = -1;
        int h = 0;
        List<TreeNode> nodeList = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty())
        {
            TreeNode node = queue.poll();
            if (node.left != null)
            {
                nodeList.add(node.left);
                if (node.left.val == x)
                {
                    hx = h;
                    px = node.val;
                }
                if (node.left.val == y)
                {
                    hy = h;
                    py = node.val;
                }
            }
            if (node.right != null)
            {
                nodeList.add(node.right);
                if (node.right.val == x)
                {
                    hx = h;
                    px = node.val;
                }
                if (node.right.val == y)
                {
                    hy = h;
                    py = node.val;
                }
            }

            if (queue.isEmpty())
            {
                h++;
                if (nodeList.size() > 0)
                {
                    queue.addAll(nodeList);
                    nodeList.clear();
                }
            }
        }

        if (px != py && hx == hy)
            return true;
        return false;
    }

}
