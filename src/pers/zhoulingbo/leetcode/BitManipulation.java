package pers.zhoulingbo.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * 位运算
 * 
 * @version v1.0.0 @author zhoulingbo 2019-1-15 新建与整理
 */
public class BitManipulation
{

    public static void main(String[] args)
    {
        System.out.println(subsets(new int[] { 1, 2 }));
    }

    /**
     * 78. 子集(subsets) 给定一组不含重复元素的整数数组 nums，返回该数组所有可能的子集（幂集）。 说明：解集不能包含重复的子集。
     * @param nums
     * @return
     */
    public static List<List<Integer>> subsets(int[] nums)
    {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> empty = new ArrayList<>();
        result.add(empty);
        for (int n : nums)
        {
            int size = result.size();
            for (int i = 0; i < size; i++)
            {
                List<Integer> subset = new ArrayList<>(result.get(i));
                subset.add(n);
                result.add(subset);
            }
        }
        return result;
    }

    /**
     * 338. 比特位计数(counting-bits)
     * @param num
     * @return
     */
    public static int[] countBits(int num)
    {
        int[] a = new int[num + 1];
        a[0] = 0;
        for (int i = 1; i <= num; i++)
            a[i] = a[i >> 1] + (i & 1);

        return a;
    }

    /**
     * 405. 数字转换为十六进制数(convert-a-number-to-hexadecimal)
     * @param num
     * @return
     */
    public static String toHex(int num)
    {
        if (num == 0)
            return "0";

        char[] a = new char[] { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };
        StringBuffer strb = new StringBuffer();
        if (num > 0)
        {
            while (num > 0)
            {
                int t = num & 0xf;
                strb.append(a[t]);
                num = num >> 4;
            }
            return strb.reverse().toString();
        }
        else
        {
            char[] b = Integer.toBinaryString(num).toCharArray();
            for (int i = 0; i < b.length; i += 4)
            {
                int t = (int) (b[i] - '0') * 8 + (int) (b[i + 1] - '0') * 4 + (int) (b[i + 2] - '0') * 2 + (int) (b[i + 3] - '0');
                strb.append(a[t]);
            }
            return strb.toString();
        }
    }

    /**
     * 461.汉明距离(hamming-distance) 两个整数之间的汉明距离指的是这两个数字对应二进制位不同的位置的数目。 给出两个整数 x 和
     * y，计算它们之间的汉明距离。 注意：0 ≤ x, y < 2^31.
     * @param x
     * @param y
     * @return
     */
    public static int hammingDistance(int x, int y)
    {
        int z = x ^ y;
        int distance = 0;
        while (z != 0)
        {
            if ((z & 1) > 0)
                distance++;
            z = z >> 1;
        }
        return distance;
    }

    /**
     * 477. 汉明距离总和(total-hamming-distance) 计算一个数组中，任意两个数之间汉明距离的总和。
     * @param nums
     * @return
     */
    public static int totalHammingDistance(int[] nums)
    {
        int distance = 0;
        int count = 0;
        for (int i = 0; i < 32; i++)
        {
            count = 0;  // 计算每个位置上1或者0的个数
            for (int j = 0; j < nums.length; j++)
            {
                if ((nums[j] & 1) == 1)
                {
                    count++;
                }
                nums[j] >>= 1;
            }
            distance += count * (nums.length - count);  // 每个位置上0或者1个数之乘积就是单个位置上所有元素距离之和
        }
        return distance;
    }
    
    /**
     * 756. 金字塔转换矩阵
     * @param bottom
     * @param allowed
     * @return
     */
    public static boolean pyramidTransition(String bottom, List<String> allowed) {
        return true;
    }
}
