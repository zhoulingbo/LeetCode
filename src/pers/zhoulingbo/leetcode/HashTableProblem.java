package pers.zhoulingbo.leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class HashTableProblem
{

    public static void main(String[] args)
    {
        String[] A = new String[]{"bella","label","roller"};
        commonChars(A);
    }

    /**
     * 36. 有效的数独(valid-sudoku)
     * @param board
     * @return
     */
    public boolean isValidSudoku(char[][] board)
    {
        int count = 0;
        for (int i = 0; i < 9; i++)
        {
            Set<Character> set = new HashSet<>(16);
            Set<Character> set1 = new HashSet<>(16);
            Set<Character> set2 = new HashSet<>(16);
            for (int j = 0; j < 9; j++)
            {
                char ch = board[i][j];
                char ch1 = board[j][i];
                char ch2 = board[i / 3 * 3 + j / 3][count + j % 3];

                if (ch != '.')
                {
                    if (set.contains(ch))
                        return false;
                    set.add(ch);
                }

                if (ch1 != '.')
                {
                    if (set1.contains(ch1))
                        return false;
                    set1.add(ch1);
                }

                if (ch2 != '.')
                {
                    if (set2.contains(ch2))
                        return false;
                    set2.add(ch2);
                }
            }
            count += 3;
            if (count > 8)
                count = 0;
        }

        return true;
    }

    /**
     * 575. 分糖果(distribute-candies)
     * @param candies
     * @return
     */
    public int distributeCandies(int[] candies)
    {
        Set<Integer> set = new HashSet<>();
        for (int i : candies)
            set.add(i);

        int size = set.size();
        if (size >= candies.length / 2)
            return candies.length / 2;

        return size;
    }

    /**
     * 1002. 查找常用字符
     * @param A
     * @return
     */
    public static List<String> commonChars(String[] A)
    {
        int[] a = new int[26];
        boolean first = true;
        Map<Character, Integer> map = new HashMap<>();
        for (String s : A)
        {
            for (Character ch : s.toCharArray())
            {
                if (map.containsKey(ch))
                    map.put(ch, map.get(ch) + 1);
                else
                    map.put(ch, 1);
            }

            for (Character key : map.keySet())
            {
                int index = key - 'a';
                if (first)
                    a[index] = map.get(key);
                else
                    a[index] = Math.min(a[index], map.get(key));
                map.put(key, 0);
            }

            if (first)
                first = false;
        }
        
        List<String> list = new ArrayList<>();
        for (int i=0; i<a.length; i++)
        {
            for (int j=0; j<a[i]; j++)
            {
                char ch = (char) ('a' + i);
                list.add(String.valueOf(ch));
            }
        }
        return list;
    }
}
