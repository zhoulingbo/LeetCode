package pers.zhoulingbo.leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Sort
{
    public static void main(String[] args)
    {
        int[] A = new int[] { 3, 2, 4, 1 };
        System.out.println(pancakeSort(A));
    }

    /**
     * 350. 两个数组的交集 II
     */
    public static int[] intersect(int[] nums1, int[] nums2)
    {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums2.length; i++)
        {
            if (map.containsKey(nums2[i]))
            {
                int count = map.get(nums2[i]) + 1;
                map.put(nums2[i], count);
            }
            else
            {
                map.put(nums2[i], 1);
            }
        }

        List<Integer> list = new ArrayList<>();
        for (int j = 0; j < nums1.length; j++)
        {
            if (map.containsKey(nums1[j]))
            {
                list.add(nums1[j]);
                int count = map.get(nums1[j]) - 1;
                if (count <= 0)
                    map.remove(nums1[j]);
                else
                    map.put(nums1[j], count);
            }
        }

        int[] a = new int[list.size()];
        int i = 0;
        for (Integer item : list)
        {
            a[i++] = item;
        }
        return a;
    }

    /**
     * 969. 煎饼排序
     * @param A
     * @return
     */
    public static List<Integer> pancakeSort(int[] A)
    {
        List<Integer> list = new ArrayList<>();
        int last = A.length - 1;
        while (last >= 0)
        {
            int max = A[0];
            int index = 0;
            for (int i = 0; i <= last; i++)
            {
                if (A[i] > max)
                {
                    max = A[i];
                    index = i;
                }
            }

            if (index != last)
            {
                list.add(index + 1);
                pancakeFlip(A, index + 1);
                list.add(last + 1);
                pancakeFlip(A, last + 1);
            }
            last--;
        }

        return list;
    }

    /**
     * 翻转前k个煎饼
     * @param A
     * @param k
     */
    public static void pancakeFlip(int[] A, int k)
    {
        int low = 0;
        int high = k - 1;
        while (high > low)
        {
            int temp = A[low];
            A[low] = A[high];
            A[high] = temp;
            
            low ++;
            high --;
        }
    }

    /**
     * 977. 有序数组的平方(squares-of-a-sorted-array)
     * @param A
     * @return
     */
    public static int[] sortedSquares(int[] A)
    {
        int[] a = new int[A.length];
        int low = 0;
        int high = A.length - 1;
        int index = A.length - 1;
        while (low <= high)
        {
            if (low == high)
            {
                a[index--] = A[low] * A[low];
                break;
            }

            if (Math.abs(A[low]) > Math.abs(A[high]))
            {
                a[index--] = A[low] * A[low];
                low++;
            }
            else
            {
                a[index--] = A[high] * A[high];
                high--;
            }
        }
        return a;
    }
}
