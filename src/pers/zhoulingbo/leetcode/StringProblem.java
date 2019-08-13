/*
 * 版权所有 (C) 2015 知启蒙(ZHIQIM) 保留所有权利。
 * 
 * 指定登记&发行网站： https://www.zhiqim.com/ 欢迎加盟知启蒙，[编程有你，知启蒙一路随行]。
 *
 * 本文采用《知启蒙许可证》，除非符合许可证，否则不可使用该文件！
 * 1、您可以免费使用、修改、合并、出版发行和分发，再授权软件、软件副本及衍生软件；
 * 2、您用于商业用途时，必须在原作者指定的登记网站，按原作者要求进行登记；
 * 3、您在使用、修改、合并、出版发行和分发时，必须包含版权声明、许可声明，及保留原作者的著作权、商标和专利等知识产权；
 * 4、您在互联网、移动互联网等大众网络下发行和分发再授权软件、软件副本及衍生软件时，必须在原作者指定的发行网站进行发行和分发；
 * 5、您可以在以下链接获取一个完整的许可证副本。
 * 
 * 许可证链接：http://zhiqim.org/licenses/zhiqim_register_publish_license.htm
 * 
 * 除非法律需要或书面同意，软件由原始码方式提供，无任何明示或暗示的保证和条件。详见完整许可证的权限和限制。
 */
package pers.zhoulingbo.leetcode;

import java.util.HashMap;
import java.util.Map;

public class StringProblem
{

    public static void main(String[] args)
    {
        System.out.println("LEETCODEISHIRING");
        System.out.println(convert("AB", 1));
    }

    /**
     * 6. Z 字形变换
     * @param s
     * @param numRows
     * @return
     */
    public static String convert(String s, int numRows)
    {
        if (s == null || s.length() <= 1 || numRows == 1)
            return s;
        StringBuffer strb = new StringBuffer();
        for (int i = 1; i <= numRows; i++)
        {
            int flag = 0;
            for (int j = i - 1; j <= s.length() - 1;)
            {
                strb.append(s.charAt(j));
                if (i == 1 || i == numRows)
                    j += 2 * numRows - 2;
                else
                {
                    if (flag == 0)
                    {
                        j += numRows - i + numRows - i;
                        flag = 1;
                    }
                    else
                    {
                        j += i + i - 2;
                        flag = 0;
                    }
                }
            }
        }
        return strb.toString();
    }

    /**
     * 5. 最长回文子串
     * @param s
     * @return
     */
    public static String longestPalindrome(String s)
    {
        if (s == null || s.length() <= 1)
            return s;

        int max = 1;
        String longestPalindrome = s.substring(0, 1);
        char[] cs = s.toCharArray();
        for (int i = 1; i <= s.length() - 1; i++)
        {
            int left = i - 1;
            int right = i;
            while (left >= 0 && right <= s.length() - 1)
            {
                if (cs[left] != cs[right])
                    break;

                if (right - left + 1 > max)
                {
                    max = right - left;
                    if (right == s.length() - 1)
                        longestPalindrome = s.substring(left);
                    else
                        longestPalindrome = s.substring(left, right + 1);
                }
                left--;
                right++;
            }

            left = i - 1;
            right = i + 1;
            while (left >= 0 && right <= s.length() - 1)
            {
                if (cs[left] != cs[right])
                    break;

                if (right - left + 1 > max)
                {
                    max = right - left;
                    if (right == s.length() - 1)
                        longestPalindrome = s.substring(left);
                    else
                        longestPalindrome = s.substring(left, right + 1);
                }
                left--;
                right++;
            }

        }
        return longestPalindrome;
    }

    public static String[] reorderLogFiles(String[] logs)
    {
        int high = logs.length - 1;
        int low = logs.length - 1;
        while (high > 0 && low > 0)
        {
            String s = logs[high];
            int index = s.indexOf(" ") + 1;
            char ch = s.charAt(index);
            if (ch >= '0' && ch <= '9')
            {
                high--;
                low--;
                continue;
            }

        }

        return logs;
    }

    /**
     * 3. 无重复字符的最长子串
     * @param s
     * @return
     */
    public static int lengthOfLongestSubstring(String s)
    {
        char[] a = s.toCharArray();
        int longest = 0;
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < a.length; i++)
        {
            if (map.containsKey(a[i]))
            {
                longest = Math.max(longest, map.size());
                int index = map.get(a[i]);
                map.clear();
                i = index;
                continue;
            }
            map.put(a[i], i);
        }
        longest = Math.max(longest, map.size());
        return longest;
    }

    /**
     * 1108. IP 地址无效化
     * @param address
     * @return
     */
    public String defangIPaddr(String address)
    {
        char[] chars = address.toCharArray();
        StringBuffer strb = new StringBuffer();
        for (char ch : chars)
        {
            if (ch == '.')
                strb.append("[.]");
            else
                strb.append(ch);
        }
        return strb.toString();
    }

    /**
     * 893. 特殊等价字符串组
     * @param A
     * @return
     */
    public int numSpecialEquivGroups(String[] A)
    {
        return 0;
    }
}
