package pers.zhoulingbo.leetcode;

/**
 * 
 * 二分查找
 * 
 */
public class BinarySearch
{

    public static void main(String[] args)
    {
        int[] nums = new int[] { 2, 7, 11, 15 };
        System.out.println(twoSum(nums, 9));
    }

    /**
     * 704. 二分查找
     * @param nums
     * @param target
     * @return
     */
    public static int search(int[] nums, int target)
    {
        int low = 0, high = nums.length - 1;
        while (low <= high)
        {
            int middle = (high + low) / 2;
            if (nums[middle] == target)
                return middle;
            else if (nums[middle] < target)
                low = middle + 1;
            else if (nums[middle] > target)
                high = middle - 1;
        }
        return -1;
    }
    
    public static int search(int[] nums, int start, int end, int target)
    {
        int low = start, high = end;
        while (low <= high)
        {
            int middle = (high + low) / 2;
            if (nums[middle] == target)
                return middle;
            else if (nums[middle] < target)
                low = middle + 1;
            else if (nums[middle] > target)
                high = middle - 1;
        }
        return -1;
    }

    /**
     * 167. 两数之和 II - 输入有序数组
     * @param numbers
     * @param target
     * @return
     */
    public static int[] twoSum(int[] numbers, int target)
    {
        int i = 0, j = numbers.length - 1;
        while (i < j)
        {
            if (numbers[i] + numbers[j] == target)
                return new int[]{i+1, j+1};
            else if (numbers[i] + numbers[j] > target)
                j --;
            else if (numbers[i] + numbers[j] < target)
                i ++;
        }
        return new int[]{-1, -1};
    }
}
