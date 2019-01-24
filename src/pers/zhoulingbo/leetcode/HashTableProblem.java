package pers.zhoulingbo.leetcode;

import java.util.HashSet;
import java.util.Set;

public class HashTableProblem
{

    public static void main(String[] args)
    {
        int count = 0;
        for (int i = 0; i < 9; i++)
        {
            for (int j = 0; j < 9; j++)
            {
                System.out.print((i / 3 * 3 + j / 3) + "-" + (count + j % 3) + "  ");
            }
            System.out.println();
            count += 3;
            if (count > 8)
                count = 0;
        }
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
}
