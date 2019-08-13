package pers.zhoulingbo.leetcode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class MathProblem
{

    public static void main(String[] args)
    {
        System.out.println(arrangeCoins(16));
    }

    /**
     * 812. 最大三角形面积
     * @param points
     * @return
     */
    public double largestTriangleArea(int[][] points)
    {
        return 0;
    }

    /**
     * 441. 排列硬币
     * @param n
     * @return
     */
    public static int arrangeCoins(int n)
    {
        if (n == 0)
            return 0;
        int i = (int) Math.sqrt(2 * n);
        while (true)
        {
            if (i * (i + 1) < 2 * n)
                i++;
            else if (i * (i + 1) == 2 * n)
                return i;
            else
                return i-1;
        }
    }

    /**
     * 836. 矩形重叠
     * @param rec1
     * @param rec2
     * @return
     */
    public boolean isRectangleOverlap(int[] rec1, int[] rec2)
    {
        if (rec1[2] <= rec2[0] || rec1[0] >= rec2[2])
            return false;
        if (rec1[3] <= rec2[1] || rec1[1] >= rec2[3])
            return false;
        return true;
    }

    /**
     * 976. 三角形的最大周长
     * @param A
     * @return
     */
    public int largestPerimeter(int[] A)
    {
        Arrays.sort(A);
        int k = A.length - 1;
        int i = k - 2, j = k - 1;
        while (i >= 0 && j >= 0 && k >= 0)
        {
            if (A[i] + A[j] > A[k])
                return A[i] + A[j] + A[k];
            k--;
            j--;
            i--;
        }
        return 0;
    }

    /**
     * 453. 最小移动次数使数组元素相等
     * @param nums
     * @return
     */
    public static int minMoves(int[] nums)
    {
        int min = nums[0];
        for (int num : nums)
            if (num < min)
                min = num;
        int count = 0;
        for (int num : nums)
            count += num - min;
        return count;
    }

    /**
     * 1103. 分糖果 II
     * @param candies
     * @param num_people
     * @return
     */
    public static int[] distributeCandies(int candies, int num_people)
    {
        int[] a = new int[num_people];
        int b = (int) Math.ceil(Math.sqrt(2 * candies + 0.25) - 0.5);
        int m = b % num_people;
        if (m == 0)
            m = num_people;
        int n = (b - 1) / num_people;
        for (int i = 0; i < a.length; i++)
        {
            if (i < m - 1)
            {
                a[i] = (1 + n) * n / 2 * num_people + (i + 1) * (n + 1);
            }
            else if (i == m - 1)
            {
                a[i] = (1 + n) * n / 2 * num_people + (i + 1) * (n + 1) + candies - (1 + b) * b / 2;
            }
            else
            {
                a[i] = (n - 1) * n / 2 * num_people + (i + 1) * n;
            }
        }
        return a;
    }

    public static int[] findErrorNums(int[] nums)
    {
        int n = nums.length;
        int[] a = new int[n];
        int x = 0, sum = 0;
        for (int i : nums)
        {
            sum += i;
            if (a[i - 1] == 1)
                x = i;
            else
                a[i - 1] = 1;
        }
        int y = (n + 1) * n / 2 - sum + x;
        int[] ret = new int[] { x, y };
        return ret;
    }

    /**
     * 367. 有效的完全平方数
     * @param num
     * @return
     */
    public static boolean isPerfectSquare(int num)
    {
        if (num == 1)
            return true;
        int r = num;
        while (r * r > num)
            r = (r + num / r) / 2; // 牛顿迭代法

        if (r * r == num)
            return true;
        else
            return false;
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

        return N ^ b;
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

        for (int i = 4; i <= 1000; i++)
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
    public static int surfaceArea(int[][] grid)
    {
        int area = 0;
        int n = grid.length;
        for (int i = 0; i < n; i++)
        {
            for (int j = 0; j < n; j++)
            {
                if (grid[i][j] == 0)
                    continue;
                int a = 4 * grid[i][j] + 2;
                if (i - 1 >= 0 && grid[i - 1][j] > 0)
                    a -= Math.min(grid[i - 1][j], grid[i][j]);
                if (j - 1 >= 0 && grid[i][j - 1] > 0)
                    a -= Math.min(grid[i][j - 1], grid[i][j]);
                if (i + 1 < n && grid[i + 1][j] > 0)
                    a -= Math.min(grid[i + 1][j], grid[i][j]);
                if (j + 1 < n && grid[i][j + 1] > 0)
                    a -= Math.min(grid[i][j + 1], grid[i][j]);
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
    public double minAreaFreeRect(int[][] points)
    {
        double area = 0;

        return area;
    }

}
