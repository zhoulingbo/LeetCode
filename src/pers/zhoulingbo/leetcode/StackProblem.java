package pers.zhoulingbo.leetcode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 
 * 栈问题
 * 
 * @author zhoulingbo 2019-2-15 新建与整理
 */
public class StackProblem
{

    /**
     * 144. 二叉树的前序遍历(binary-tree-preorder-traversal)
     */
    public List<Integer> preorderTraversal(TreeNode root)
    {
        List<Integer> list = new ArrayList<>();
        if (root == null)
            return list;

        Stack<TreeNode> stack = new Stack<>();
        stack.add(root);

        while (!stack.isEmpty())
        {
            TreeNode node = stack.pop();
            list.add(node.val);
            if (node.right != null)
                stack.add(node.right);
            if (node.left != null)
                stack.add(node.left);
        }

        return list;
    }
}
