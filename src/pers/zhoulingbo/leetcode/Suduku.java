package pers.zhoulingbo.leetcode;

/**
 * 
 * 数独游戏
 *
 * @version v1.0.0 @author zhoulingbo 2019-1-24 新建与整理
 */
public class Suduku
{

    /**
     * 验证有效性
     * @param board
     * @return
     */
    public boolean isValidSudoku(char[][] board)
    {
        boolean[][] row = new boolean[9][9];
        boolean[][] col = new boolean[9][9];
        boolean[][] block = new boolean[9][9];
        for (int i = 0; i < 9; i++)
        {
            for (int j = 0; j < 9; j++)
            {
                if (board[i][j] != '.')
                {
                    int num = board[i][j] - '1';
                    if (row[i][num] || col[j][num] || block[i / 3 * 3 + j / 3][num])
                    {
                        return false;
                    }
                    else
                    {
                        row[i][num] = true;
                        col[j][num] = true;
                        block[i / 3 * 3 + j / 3][num] = true;
                    }
                }
            }
        }
        return true;
    }
}
