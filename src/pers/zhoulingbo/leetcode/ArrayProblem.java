package pers.zhoulingbo.leetcode;

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
        moveZeroes(new int[]{0,1,0,3,12});
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
            i ++;
            j ++;
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
}
