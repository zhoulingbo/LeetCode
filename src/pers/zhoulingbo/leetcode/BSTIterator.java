package pers.zhoulingbo.leetcode;

import java.util.Stack;

/**
 * 
 * 173. 二叉搜索树迭代器
 * 
 * @author zhoulingbo 2019-2-28 新建与整理
 */
public class BSTIterator
{
    Stack<TreeNode> stack;

    public BSTIterator(TreeNode root)
    {
        stack = new Stack<>();
        inOrder(root);
    }
    
    protected void inOrder(TreeNode node)
    {
        if (node == null)
            return;

        stack.push(node);
        inOrder(node.left);
    }

    /** @return the next smallest number */
    public int next()
    {
        TreeNode node = stack.pop();
        int val = node.val;
        if (node.right != null)
            inOrder(node.right);
        return val;
    }

    /** @return whether we have a next smallest number */
    public boolean hasNext()
    {
        return !stack.isEmpty();
    }
}
