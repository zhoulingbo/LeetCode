package pers.zhoulingbo.leetcode;

import java.util.HashMap;
import java.util.Map;

public class MathProblem
{

    public static void main(String[] args)
    {
        System.out.println(binaryGap(22));
    }

    /**
     * 868. 二进制间距
     * @param N
     * @return
     */
    public static int binaryGap(int N)
    {
        int gap = 0;
        int t = 0;
        boolean foundBefore = false;
        while (N > 0)
        {
            int a = N & 1;
            if (a == 1)
            {
                if (!foundBefore)
                {
                    foundBefore = true;
                }
                else
                {
                    gap = Math.max(gap, t + 1);
                    t = 0;
                }

            }
            else if (foundBefore)
            {
                t++;
            }

            N = N >> 1;
        }

        return gap;
    }

    /**
     * 202. 快乐数
     * @param n
     * @return
     */
    public static boolean isHappy(int n)
    {
        Map<Integer, Integer> map = new HashMap<>();

        while (true)
        {
            int val = 0;
            while (n > 0)
            {
                int m = n % 10;
                val += m * m;
                n = n / 10;
            }

            if (val == 1)
                return true;

            if (map.containsKey(val))
                return false;

            map.put(val, 1);
            n = val;
        }
    }
}
