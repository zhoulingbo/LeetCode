package pers.zhoulingbo.leetcode;

/**
 * 
 * 分治法
 * 
 * @version v1.0.0 @author zhoulingbo 2019-1-17 新建与整理
 */
public class DivideAndConquer
{

    public static void main(String[] args)
    {
        int[][] points = new int[][] { { 6, 10 }, { -3, 3 }, { -2, 5 }, { 0, 2 } };
        kClosest(points, 3);
    }

    /**
     * 973. 最接近原点的 K 个点(k-closest-points-to-origin)
     * @param points
     * @param K
     * @return
     */
    public static int[][] kClosest(int[][] points, int K)
    {
        sort(points);
        int[][] result = new int[K][2];
        System.arraycopy(points, 0, result, 0, K);
        return result;
    }

    public static void sort(int[][] a)
    {
        int[][] b = new int[a.length][2];
        sort(a, 0, a.length - 1, b);
    }

    public static void sort(int[][] a, int start, int end, int[][] b)
    {
        if (start >= end)
            return;
        int middle = (start + end) / 2;
        sort(a, start, middle, b);
        sort(a, middle + 1, end, b);
        merge(a, start, middle, end, b);
    }

    public static void merge(int[][] a, int start, int middle, int end, int[][] b)
    {
        int i = start;
        int j = middle + 1;
        int k = 0;
        while (i <= middle && j <= end)
        {
            if (a[i][0] * a[i][0] + a[i][1] * a[i][1] < a[j][0] * a[j][0] + a[j][1] * a[j][1])
                b[k++] = a[i++];
            else
                b[k++] = a[j++];
        }

        while (i <= middle)
            b[k++] = a[i++];
        while (j <= end)
            b[k++] = a[j++];

        k = 0;
        while (start <= end)
            a[start++] = b[k++];
    }

    /**
     * 215. 数组中的第K个最大元素
     * @param nums
     * @param k
     * @return
     */
    public static int findKthLargest(int[] nums, int k)
    {
        sort(nums);
        return nums[k - 1];
    }

    public static void sort(int[] a)
    {
        int[] t = new int[a.length];
        sort(a, 0, a.length - 1, t);
    }

    public static void sort(int[] a, int left, int right, int[] t)
    {
        if (left < right)
        {
            int middle = (right + left) / 2;
            sort(a, left, middle, t);
            sort(a, middle + 1, right, t);

            merge(a, left, middle, right, t);
        }
    }

    public static void merge(int[] a, int left, int middle, int right, int[] t)
    {
        int i = 0, l = left, r = middle + 1;
        while (l <= middle && r <= right)
        {
            if (a[l] < a[r])
                t[i++] = a[l++];
            else
                t[i++] = a[r++];
        }

        while (l <= middle)
            t[i++] = a[l++];
        while (r <= right)
            t[i++] = a[r++];

        i = 0;
        while (left <= right)
            a[left++] = t[i++];
    }
}
