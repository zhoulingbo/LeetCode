package pers.zhoulingbo.leetcode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class MathProblem
{

    public static void main(String[] args)
    {
        int[][] grid = new int[][]{{1,0},{0,2}};
        System.out.println(surfaceArea(grid));
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

    /**
     * 67. 二进制求和
     * @param a
     * @param b
     * @return
     */
    public static String addBinary(String a, String b)
    {
        StringBuffer strb = new StringBuffer();
        char[] as = a.toCharArray();
        char[] bs = b.toCharArray();
        int i = 0;
        char carry = '0';
        while (as.length > i && bs.length > i)
        {
            if (as[as.length - 1 - i] == '1' && bs[bs.length - 1 - i] == '1')
            {
                if (carry == '0')
                    strb.append('0');
                else
                    strb.append('1');
                carry = '1';
            }
            else if (as[as.length - 1 - i] == '1' && bs[bs.length - 1 - i] == '0')
            {
                if (carry == '0')
                {
                    strb.append('1');
                    carry = '0';
                }
                else
                {
                    strb.append('0');
                    carry = '1';
                }
            }
            else if (as[as.length - 1 - i] == '0' && bs[bs.length - 1 - i] == '1')
            {
                if (carry == '0')
                {
                    strb.append('1');
                    carry = '0';
                }
                else
                {
                    strb.append('0');
                    carry = '1';
                }
            }
            else if (as[as.length - 1 - i] == '0' && bs[bs.length - 1 - i] == '0')
            {
                if (carry == '0')
                {
                    strb.append('0');
                    carry = '0';
                }
                else
                {
                    strb.append('1');
                    carry = '0';
                }
            }
            i++;
        }

        while (as.length > i)
        {
            if (as[as.length - 1 - i] == '0')
            {
                if (carry == '0')
                    strb.append('0');
                else
                    strb.append('1');
                carry = '0';
            }
            else
            {
                if (carry == '0')
                {
                    strb.append('1');
                    carry = '0';
                }
                else
                {
                    strb.append('0');
                    carry = '1';
                }
            }
            i++;
        }
        while (bs.length > i)
        {
            if (bs[bs.length - 1 - i] == '0')
            {
                if (carry == '0')
                    strb.append('0');
                else
                    strb.append('1');
                carry = '0';
            }
            else
            {
                if (carry == '0')
                {
                    strb.append('1');
                    carry = '0';
                }
                else
                {
                    strb.append('0');
                    carry = '1';
                }
            }
            i++;
        }
        if (carry == '1')
            strb.append(carry);
        return strb.reverse().toString();
    }

    /**
     * 628. 三个数的最大乘积
     * @param nums
     * @return
     */
    public static int maximumProduct(int[] nums)
    {
        if (nums.length == 3)
            return nums[0] * nums[1] * nums[2];
        int max = nums[0], secondMax = nums[1], thirdMax = nums[2];
        if (max < secondMax)
        {
            max = max + secondMax;
            secondMax = max - secondMax;
            max = max - secondMax;
        }
        if (max < thirdMax)
        {
            max = max + thirdMax;
            thirdMax = max - thirdMax;
            max = max - thirdMax;
        }
        if (secondMax < thirdMax)
        {
            secondMax = secondMax + thirdMax;
            thirdMax = secondMax - thirdMax;
            secondMax = secondMax - thirdMax;
        }

        int min = thirdMax, secondMin = secondMax;
        for (int i = 3; i < nums.length; i++)
        {
            if (nums[i] >= max)
            {
                thirdMax = secondMax;
                secondMax = max;
                max = nums[i];
            }
            else if (nums[i] >= secondMax)
            {
                thirdMax = secondMax;
                secondMax = nums[i];
            }
            else if (nums[i] >= thirdMax)
            {
                thirdMax = nums[i];
            }
            else if (nums[i] <= min)
            {
                secondMin = min;
                min = nums[i];
            }
            else if (nums[i] <= secondMin)
            {
                secondMin = nums[i];
            }
        }

        if (max == 0)
            return 0;

        int a = max * secondMin * min;
        int b = max * secondMax * thirdMax;
        if (a > b)
            return a;
        return b;
    }

    /**
     * 1009. 十进制整数的反码
     * @param N
     * @return
     */
    public static int bitwiseComplement(int N)
    {
        if (N == 0)
            return 1;
        int a = N;
        int b = 0;
        while (a > 0)
        {
            a = a >> 1;
            if (b == 0)
                b = 1;
            else
                b = b << 1 | 1;
        }

        return N^b;
    }

    /**
     * 1025. 除数博弈
     * @param N
     * @return
     */
    public static boolean divisorGame(int N) 
    {
        Set<Integer> win = new HashSet<Integer>();
        win.add(2);
        
        Set<Integer> lose = new HashSet<Integer>();
        lose.add(1);
        lose.add(3);
        
        for (int i=4; i<=1000; i++)
        {
            for (Integer a : lose)
            {
                if (i % a == 0 && lose.contains(i - a))
                {
                    win.add(i);
                    break;
                }
            }
            
            if (!win.contains(i))
                lose.add(i);
        }
        
        if (win.contains(N))
            return true;
        return false;
    }

    /**
     * 892. 三维形体的表面积
     * @param grid
     * @return
     */
    public static int surfaceArea(int[][] grid) {
        int area = 0;
        int n = grid.length;
        for (int i=0; i<n; i++)
        {
            for (int j=0; j<n; j++)
            {
                if (grid[i][j] == 0)
                    continue;
                int a = 4 * grid[i][j] + 2;
                if (i-1 >= 0 && grid[i-1][j] > 0)
                    a -= Math.min(grid[i-1][j], grid[i][j]);
                if (j-1 >= 0 && grid[i][j-1] > 0)
                    a -= Math.min(grid[i][j-1], grid[i][j]);
                if (i+1 < n && grid[i+1][j] > 0)
                    a -= Math.min(grid[i+1][j], grid[i][j]);
                if (j+1 < n && grid[i][j+1] > 0)
                    a -= Math.min(grid[i][j+1], grid[i][j]);
                area += a;
            }
        }
        return area;
    }

    /**
     * 963. 最小面积矩形 II
     * @param points
     * @return
     */
    public double minAreaFreeRect(int[][] points) {
        double area = 0;
        
        return area;
    }

}
