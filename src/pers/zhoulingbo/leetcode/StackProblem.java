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
        System.out.println(decodeString("sd2[f2[e]g]i"));
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
     * 150. 逆波兰表达式求值
     * @param tokens
     * @return
     */
    public static int evalRPN(String[] tokens)
    {
        Stack<Integer> stack = new Stack<>();
        for (String s : tokens)
        {
            if (!"+".equals(s) && !"-".equals(s) && !"*".equals(s) && !"/".equals(s))
            {
                stack.push(Integer.parseInt(s));
                continue;
            }

            int b = stack.pop();
            int a = stack.pop();
            int c = 0;
            if ("+".equals(s))
                c = a + b;
            else if ("-".equals(s))
                c = a - b;
            else if ("*".equals(s))
                c = a * b;
            else
                c = a / b;
            stack.push(c);
        }
        return stack.peek();
    }

    /**
     * 331. 验证二叉树的前序序列化
     * @param preorder
     * @return
     */
    public static boolean isValidSerialization(String preorder)
    {
        if ("#".equals(preorder))
            return true;
        if (!preorder.endsWith("#"))
            return false;

        String[] strs = preorder.split(",");
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < strs.length; i++)
        {
            if (!"#".equals(strs[i]))
            {
                stack.push('d');
                continue;
            }

            if (stack.isEmpty())
                return false;

            // # 进入
            while (!stack.isEmpty())
            {
                char ch = stack.peek();
                if (ch == '#')
                {// 二个#说明遇到页节点，则消除页节点
                    stack.pop(); // 弹出#
                    stack.pop(); // 弹出d
                    if (stack.isEmpty() && i != strs.length - 1)
                        return false;
                }
                else
                    break;
            }
            stack.push('#');
        }

        if ('#' == stack.pop() && stack.isEmpty())
            return true;

        return false;
    }

    /**
     * 394. 字符串解码，s = "3[a2[c]]d", 返回 "accaccaccd".
     * @param s
     * @return
     */
    public static String decodeString(String s)
    {
        StringBuffer strb = new StringBuffer();
        Stack<Integer> stack = new Stack<>();   // 放入重复次数
        Stack<String> stack_s = new Stack<>();   // 放入字符串
        
        char[] a = s.toCharArray();
        int start = -1, end = 0;     // 数字开始和结尾
        int start_s = -1, end_s = 0; // 字符串开始和结尾
        for (int i=0; i<a.length; i++)
        {
            if ('0' <= a[i] && '9' >= a[i])
            {// 数字
                if (end > start)
                    start = i;
                if (start_s > end_s)
                {
                    end_s = i;
                    String str = s.substring(start_s, end_s);
                    stack_s.push(str);
                }
            }
            else if ('[' == a[i])
            {
                end = i;
                end_s = i;
                if (end > start)
                {
                    int m = Integer.parseInt(s.substring(start, end));
                    stack.push(m);
                }
            }
            else if (']' == a[i])
            {
                end_s = i;
                String str = s.substring(start_s, end_s);
                while (!stack.isEmpty())
                {
                    StringBuffer strbuff = new StringBuffer();
                    int z = stack.pop();
                    for (int k=0; k<z; k++)
                    {
                        strbuff.append(str);
                    }
                    
                    if (stack.isEmpty())
                    {
                        strb.append(strbuff);
                        break;
                    }
                    
                    if (!stack_s.isEmpty())
                    {
                        String v = stack_s.pop();
                        strbuff.insert(0, v);
                    }
                    str = strbuff.toString();
                }
            }
            else
            {
                if (stack.isEmpty())
                {
                    strb.append(a[i]);
                    end_s = i;
                }
                
                if (end_s > start_s)
                    start_s = i;
            }
        }
        return strb.toString();
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
