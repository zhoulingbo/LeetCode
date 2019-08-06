package pers.zhoulingbo.leetcode;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Stack;

/**
 * 
 * 贪婪算法
 * 
 * @author zhoulingbo 2019-2-14 新建与整理
 */
public class Greedy
{

    /**
     * 1046. 最后一块石头的重量
     * @param stones
     * @return
     */
    public int lastStoneWeight(int[] stones) {
        Queue<Integer> queue = new PriorityQueue<>(30, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2)
            {
                return o2 - o1;
            }});
        for (int val : stones)
            queue.add(val);

        while (!queue.isEmpty())
        {
            int max = queue.poll();
            if (queue.isEmpty())
                return max;
            int next = queue.peek();
            queue.poll();
            if (next < max)
                queue.add(max - next);
        }

        return 0;
    }

    /**
     * 944. 删列造序
     * @param A
     * @return
     */
    public int minDeletionSize(String[] A)
    {
        int size = 0;
        for (int i = 0; i < A[0].length(); i++)
        {
            char ch = A[0].charAt(i);
            for (String s : A)
            {
                char c = s.charAt(i);
                if (c >= ch)
                {
                    ch = c;
                    continue;
                }
                else
                {
                    size++;
                    break;
                }
            }
        }
        return size;
    }

    /**
     * 860. 柠檬水找零
     * @param bills
     * @return
     */
    public boolean lemonadeChange(int[] bills)
    {
        int five = 0, ten = 0;
        for (int bill : bills)
        {
            if (bill == 5)
                five++;
            else if (bill == 10)
            {
                if (five == 0)
                    return false;
                five--;
                ten++;
            }
            else
            {
                if (five > 0 && ten > 0)
                {
                    five--;
                    ten--;
                }
                else if (five >= 3)
                {
                    five -= 3;
                }
                else
                {
                    return false;
                }
            }
        }

        return true;
    }

    /**
     * 122. 买卖股票的最佳时机 II
     * @param prices
     * @return
     */
    public int maxProfit(int[] prices)
    {
        int profit = 0;
        boolean isBuyer = false;
        int buyer = 0;
        int seller = 0;
        for (int price : prices)
        {
            if (!isBuyer)
            {// 设置买入
                buyer = price;
                isBuyer = true;
                continue;
            }

            if (seller == 0)
            {
                if (price <= buyer)
                {// 重置买入
                    buyer = price;
                }
                else
                {// 设置卖出
                    seller = price;
                }
                continue;
            }
            else
            {
                if (price < seller)
                {// 卖出
                    profit = profit + seller - buyer;
                    buyer = price;
                    seller = 0;
                    continue;
                }
                else
                {
                    seller = price;
                    continue;
                }
            }
        }

        if (seller - buyer > 0)
            profit = profit + seller - buyer;
        return profit;
    }

    /**
     * 455. 分发饼干
     * @param g
     * @param s
     * @return
     */
    public int findContentChildren(int[] g, int[] s)
    {
        Arrays.sort(g);
        Arrays.sort(s);
        int count = 0;
        int n = g.length;
        int m = s.length;
        while (n > 0 && m > 0)
        {
            if (s[m - 1] < g[n - 1])
            {
                n--;
                continue;
            }
            else
            {
                count++;
                m--;
                n--;
                continue;
            }
        }
        return count;
    }

    /**
     * 921. 使括号有效的最少添加
     * @param S
     * @return
     */
    public int minAddToMakeValid(String S)
    {
        Stack<Character> stack = new Stack<>();
        char[] a = S.toCharArray();
        for (int i = 0; i < a.length; i++)
        {
            char c = a[i];
            if (stack.isEmpty())
            {
                stack.push(c);
                continue;
            }

            if (c == '(')
            {
                stack.push(c);
            }
            else
            {
                if ('(' == stack.peek())
                {
                    stack.pop();
                }
                else
                {
                    stack.push(c);
                }
            }
        }

        return stack.size();
    }
}
