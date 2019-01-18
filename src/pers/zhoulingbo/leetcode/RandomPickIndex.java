package pers.zhoulingbo.leetcode;

import java.util.HashMap;
import java.util.Map;

public class RandomPickIndex
{
    private Map<Integer, Integer> map;
    private int[] a;

    public RandomPickIndex(int[] nums)
    {
        map = new HashMap<>();
        a = nums;
    }

    public int pick(int target)
    {
        int last = 0;
        if (map.containsKey(target))
            last = map.get(target) + 1;

        int next = -1;
        for (int i = last; i < a.length; i++)
        {
            if (target == a[i])
            {
                next = i;
                break;
            }
        }

        if (next == -1)
        {
            for (int i = 0; i < a.length; i++)
            {
                if (target == a[i])
                {
                    next = i;
                    break;
                }
            }
        }

        map.put(target, next);
        return next;
    }
}
