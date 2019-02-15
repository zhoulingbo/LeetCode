package pers.zhoulingbo.leetcode;

/**
 * 
 * 数组问题
 * 
 * @version v1.0.0 @author zhoulingbo 2019-2-15 新建与整理
 */
public class ArrayProblem
{

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
