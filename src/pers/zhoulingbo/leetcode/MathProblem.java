package pers.zhoulingbo.leetcode;

import java.util.HashMap;
import java.util.Map;

public class MathProblem
{

    public static void main(String[] args)
    {
        System.out.println(isHappy(2));
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
                val += m*m;
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
