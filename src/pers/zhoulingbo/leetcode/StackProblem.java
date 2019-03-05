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

    public static void main(String[] args)
    {
        int v = scoreOfParentheses("(()((()((())))(())()(()()()())()()())((())))");
        System.out.println(v);
    }

    /**
     * 103. 二叉树的锯齿形层次遍历
     * @param root
     * @return
     */
    public List<List<Integer>> zigzagLevelOrder(TreeNode root)
    {
        List<List<Integer>> list = new ArrayList<>();
        if (root == null)
            return list;

        Stack<TreeNode> stack = new Stack<>();
        stack.add(root);
        boolean isLeftStart = true;
        while (true)
        {
            List<Integer> items = new ArrayList<>();
            Stack<TreeNode> nextStack = new Stack<>();
            while (!stack.isEmpty())
            {
                TreeNode node = stack.pop();
                items.add(node.val);

                if (isLeftStart)
                {
                    if (node.left != null)
                        nextStack.push(node.left);
                    if (node.right != null)
                        nextStack.push(node.right);
                }
                else
                {
                    if (node.right != null)
                        nextStack.push(node.right);
                    if (node.left != null)
                        nextStack.push(node.left);
                }
            }
            list.add(items);

            if (nextStack.isEmpty())
                break;
            stack = nextStack;
            isLeftStart = !isLeftStart;
        }

        return list;
    }

    /**
     * 144. 二叉树的前序遍历(binary-tree-preorder-traversal)
     */
    public static List<Integer> preorderTraversal(TreeNode root)
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

    /**
     * 503. 下一个更大元素 II
     * @param nums
     * @return
     */
    public int[] nextGreaterElements(int[] nums)
    {
        return null;
    }

    /**
     * 856. 括号的分数
     * @param S
     * @return
     */
    public static int scoreOfParentheses(String S)
    {
        char digit = 'd';
        Stack<Character> stack = new Stack<>();
        Stack<Integer> values = new Stack<>();
        char[] chars = S.toCharArray();
        for (char ch : chars)
        {
            if (stack.isEmpty() || '(' == ch)
            {
                stack.push(ch);
                continue;
            }

            if (')' == ch)
            {
                char c = stack.peek();
                if ('(' == c)
                {
                    stack.pop();
                    if (!stack.isEmpty() && stack.peek() == digit)
                    {// 如果上一个为数字，则+
                        int a = values.pop();
                        a += 1;
                        values.push(a);
                    }
                    else
                    {// 如果为非数字，则入栈
                        stack.push(digit);
                        values.push(1);
                    }
                }
                else
                {
                    int a = values.pop();
                    a *= 2;
                    stack.pop(); // 弹出'd'
                    stack.pop(); // 弹出'('
                    if (!stack.isEmpty() && stack.peek() == digit)
                    {// 如果上一个为数字，则+
                        int b = values.pop();
                        b += a;
                        values.push(b);
                        stack.pop();
                        stack.push(digit);
                    }
                    else
                    {
                        values.push(a);
                        stack.push(digit);
                    }
                }
            }
        }
        return values.pop();
    }

    /**
     * 946. 验证栈序列
     * @param pushed
     * @param popped
     * @return
     */
    public boolean validateStackSequences(int[] pushed, int[] popped)
    {
        int i = 0, j = 0;
        Stack<Integer> stack = new Stack<>();
        while (i < pushed.length || j < popped.length)
        {
            if (stack.isEmpty())
            {
                if (i < pushed.length)
                {
                    stack.push(pushed[i]);
                    i++;
                    continue;
                }

                break;
            }
            else
            {
                if (stack.peek() == popped[j])
                {
                    stack.pop();
                    j++;
                    continue;
                }
                else
                {
                    if (i < pushed.length)
                    {
                        stack.push(pushed[i]);
                        i++;
                        continue;
                    }

                    break;
                }
            }
        }

        if (stack.isEmpty())
            return true;
        return false;
    }
}
