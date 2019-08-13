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

    public static void main(String[] args)
    {

    }

    /**
     * 1005. K 次取反后最大化的数组和
     * @param A
     * @param K
     * @return
     */
    public int largestSumAfterKNegations(int[] A, int K)
    {
        Arrays.sort(A);
        int sum = 0;
        for (int i = 0; i < A.length; i++)
        {
            if (K == 0)
            {
                sum += A[i];
                continue;
            }

            if (A[i] < 0)
            {
                K--;
                A[i] = -A[i];
                sum += A[i];
                continue;
            }
            else if (A[i] == 0)
            {
                K = 0;
                continue;
            }
            else
            {
                if (K % 2 == 0)
                {
                    K = 0;
                    sum += A[i];
                    continue;
                }
                else if (i == 0)
                {
                    A[i] = -A[i];
                    sum += A[i];
                    K = 0;
                    continue;
                }
                else if (i > 0)
                {
                    if (Math.abs(A[i - 1]) > Math.abs(A[i]))
                    {
                        A[i] = -A[i];
                        sum += A[i];
                        K = 0;
                        continue;
                    }
                    else
                    {
                        sum += A[i];
                        sum -= 2 * A[i - 1];
                        K = 0;
                        continue;
                    }
                }
            }
        }
        return sum;
    }

    /**
     * 1029. 两地调度
     * @param costs
     * @return
     */
    public int twoCitySchedCost(int[][] costs)
    {
        int result = 0;
        int[] vals = new int[costs.length];
        for (int i = 0; i < costs.length; i++)
        {
            vals[i] = costs[i][0] - costs[i][1]; // 算出飞两地的费用差值
            result = result + costs[i][1]; // 将飞B地的费用加上差值作为暂时的结果
        }
        Arrays.sort(vals); // 排序的目的是为了将最小的差值放在前面
        for (int i = 0; i < costs.length / 2; i++)
        {
            result += vals[i]; // 再用之前暂存的结果加上一半较小的差值便得到了到A地的费用
        }
        return result;
    }

    /**
     * 1046. 最后一块石头的重量
     * @param stones
     * @return
     */
    public int lastStoneWeight(int[] stones)
    {
        Queue<Integer> queue = new PriorityQueue<>(30, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2)
            {
                return o2 - o1;
            }
        });
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
