package pers.zhoulingbo.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 
 * 数组问题
 * 
 * @version v1.0.0 @author zhoulingbo 2019-2-15 新建与整理
 */
public class ArrayProblem
{

    public static void main(String[] args)
    {
        int[] nums = new int[] { 4, 3, 2, 7, 8, 2, 3, 1 };
        System.out.println(findDuplicates(nums));
    }

    /**
     * 4 寻找两个有序数组的中位数
     * @param nums1
     * @param nums2
     * @return
     */
    public static double findMedianSortedArrays(int[] nums1, int[] nums2)
    {
        int n = nums1.length + nums2.length;
        int[] nums = new int[n];
        int i = 0, j = 0, k = 0;
        while (i < nums1.length && j < nums2.length)
        {
            if (nums1[i] <= nums2[j])
            {
                nums[k] = nums1[i];
                i++;
            }
            else
            {
                nums[k] = nums2[j];
                j++;
            }
            k++;
        }
        while (i < nums1.length)
        {
            nums[k] = nums1[i];
            i++;
            k++;
        }
        while (j < nums2.length)
        {
            nums[k] = nums2[j];
            j++;
            k++;
        }
        if (k % 2 != 0)
            return nums[(k - 1) / 2];
        int a = k / 2;
        double v = (nums[a] + nums[a - 1]) / 2.0;
        return v;
    }

    /**
     * 15. 三数之和
     * @param nums
     * @return
     */
    public static List<List<Integer>> threeSum(int[] nums)
    {
        List<List<Integer>> list = new ArrayList<>();
        if (nums.length < 3)
            return list;

        Arrays.sort(nums);
        int i = 0, k = nums.length - 1;
        while (i <= k - 2)
        {
            if (nums[i] == nums[k])
            {
                if (nums[i] == 0)
                {
                    List<Integer> item = new ArrayList<>();
                    item.add(0);
                    item.add(0);
                    item.add(0);
                    list.add(item);
                }
                break;
            }

            if (nums[i] > 0 || nums[k] < 0)
                break;

            int val = -nums[i] - nums[k];
            if (val < nums[i] || val > nums[k])
                break;
            if (val == nums[i])
            {
                boolean found = false;
                int j = i + 1;
                while (j < k)
                {
                    if (val < nums[j])
                        break;
                    if (!found && val == nums[j])
                        found = true;
                    j++;
                }
                if (found)
                {
                    List<Integer> item = new ArrayList<>();
                    item.add(nums[i]);
                    item.add(nums[i]);
                    item.add(nums[j]);
                    list.add(item);
                }
                i = j;
                continue;
            }
            if (val == nums[k])
            {
                boolean found = false;
                int j = k - 1;
                while (j > i)
                {
                    if (val > nums[j])
                        break;
                    if (!found && val == nums[j])
                        found = true;
                    j--;
                }
                if (found)
                {
                    List<Integer> item = new ArrayList<>();
                    item.add(nums[i]);
                    item.add(nums[j]);
                    item.add(nums[j]);
                    list.add(item);
                    k = j + 1;
                }
            }
            k--;
            i = 0;
        }
        return list;
    }

    /**
     * 448. 找到所有数组中消失的数字
     * @param nums
     * @return
     */
    public static List<Integer> findDisappearedNumbers(int[] nums)
    {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < nums.length; i++)
        {
            int val = Math.abs(nums[i]);
            if (nums[val - 1] > 0)
                nums[val - 1] = -nums[val - 1];
        }

        for (int i = 0; i < nums.length; i++)
        {
            if (nums[i] > 0)
                list.add(i + 1);
        }

        return list;
    }

    /**
     * 442. 数组中重复的数据
     * @param nums
     * @return
     */
    public static List<Integer> findDuplicates(int[] nums)
    {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < nums.length; i++)
        {
            int val = Math.abs(nums[i]);
            if (nums[val - 1] > 0)
                nums[val - 1] = -nums[val - 1];
            else
                list.add(val);
        }
        return list;
    }

    /**
     * 41. 缺失的第一个正数
     * @param nums
     * @return
     */
    public int firstMissingPositive(int[] nums)
    {
        int n = nums.length;

        int contains = 0;
        for (int i = 0; i < n; i++)
        {
            if (nums[i] == 1)
            {
                contains++;
                break;
            }
        }

        if (contains == 0)
            return 1;

        if (n == 1)
            return 2;

        for (int i = 0; i < n; i++)
            if ((nums[i] <= 0) || (nums[i] > n))
                nums[i] = 1;

        for (int i = 0; i < n; i++)
        {
            int a = Math.abs(nums[i]);
            if (a == n)
                nums[0] = -Math.abs(nums[0]);
            else
                nums[a] = -Math.abs(nums[a]);
        }

        for (int i = 1; i < n; i++)
        {
            if (nums[i] > 0)
                return i;
        }

        if (nums[0] > 0)
            return n;

        return n + 1;
    }

    /**
     * 283. 移动零
     * @param nums
     */
    public static void moveZeroes(int[] nums)
    {
        int i = 0, j = 0;
        while (i < nums.length && j < nums.length)
        {
            if (nums[j] == 0)
            {
                j++;
                continue;
            }

            if (i == j)
            {
                i++;
                j++;
                continue;
            }

            nums[i] = nums[j];
            i++;
            j++;
        }

        while (i < nums.length)
        {
            nums[i++] = 0;
        }
    }

    /**
     * 566. 重塑矩阵
     * @param nums
     * @param r
     * @param c
     * @return
     */
    public static int[][] matrixReshape(int[][] nums, int r, int c)
    {
        int row = nums.length;
        int col = nums[0].length;

        if (r * c != row * col)
            return nums;

        int[][] ans = new int[r][c];
        for (int i = 0; i < r; i++)
        {
            for (int j = 0; j < c; j++)
            {
                int index = i * c + j;
                int m = index / col;
                int n = index % col;
                ans[i][j] = nums[m][n];
            }
        }

        return ans;
    }

    /**
     * 766. 托普利茨矩阵
     * @param matrix
     * @return
     */
    public static boolean isToeplitzMatrix(int[][] matrix)
    {
        int row = matrix.length;
        if (row == 0)
            return true;
        int col = matrix[0].length;
        if (col == 0)
            return true;

        for (int i = 0; i < row; i++)
        {
            int val = matrix[i][0];
            int j = i + 1;
            int k = 1;
            while (j < row && k < col)
            {
                if (matrix[j++][k++] != val)
                    return false;
            }
        }

        for (int i = 1; i < col; i++)
        {
            int val = matrix[0][i];
            int j = 1;
            int k = i + 1;
            while (j < row && k < col)
            {
                if (matrix[j++][k++] != val)
                    return false;
            }
        }

        return true;
    }

    /**
     * 985. 查询后的偶数和(sum-of-even-numbers-after-queries)
     */
    public int[] sumEvenAfterQueries(int[] A, int[][] queries)
    {
        int[] a = new int[queries.length];
        int sum = 0;
        for (int i = 0; i < A.length; i++)
        {
            if (A[i] % 2 == 0)
                sum += A[i];
        }

        for (int j = 0; j < queries.length; j++)
        {
            int val = queries[j][0];
            int ind = queries[j][1];
            if (A[ind] % 2 == 0)
            {
                if ((A[ind] + val) % 2 == 0)
                {
                    sum += val;
                }
                else
                {
                    sum -= A[ind];
                }
            }
            else
            {
                if ((A[ind] + val) % 2 == 0)
                {
                    sum += A[ind] + val;
                }
            }
            a[j] = sum;
            A[ind] = A[ind] + val;
        }
        return a;
    }

    /**
     * 999. 车的可用捕获量
     * @param board
     * @return
     */
    public static int numRookCaptures(char[][] board)
    {
        int r = 0;
        int c = 0;
        for (int i = 0; i < board.length; i++)
        {
            for (int j = 0; j < board[0].length; j++)
            {
                if (board[i][j] == 'R')
                {
                    r = i;
                    c = j;
                }
            }
        }

        int count = 0;
        int a = r;
        int b = c;
        while (r > 0)
        {
            char ch = board[r--][c];
            if (ch == 'b' || ch == 'p')
            {
                count++;
                break;
            }
            else if (ch == 'B' || ch == 'P')
            {
                break;
            }
        }
        r = a;
        while (r < board.length)
        {
            char ch = board[r++][c];
            if (ch == 'b' || ch == 'p')
            {
                count++;
                break;
            }
            else if (ch == 'B' || ch == 'P')
            {
                break;
            }
        }
        r = a;
        while (c > 0)
        {
            char ch = board[r][c--];
            if (ch == 'b' || ch == 'p')
            {
                count++;
                break;
            }
            else if (ch == 'B' || ch == 'P')
            {
                break;
            }
        }
        c = b;
        while (c < board[0].length)
        {
            char ch = board[r][c++];
            if (ch == 'b' || ch == 'p')
            {
                count++;
                break;
            }
            else if (ch == 'B' || ch == 'P')
            {
                break;
            }
        }
        return count;
    }
}
