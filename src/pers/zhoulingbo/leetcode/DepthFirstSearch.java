package pers.zhoulingbo.leetcode;

/**
 * 
 * 深度优先搜索
 * 
 * @version v1.0.0 @author zhoulingbo 2019-1-18 新建与整理
 */
public class DepthFirstSearch
{

    public static void main(String[] args)
    {

    }

    /**
     * 679. 24点游戏(24-game)
     * @param nums
     * @return
     */
    public static boolean judgePoint24(int[] nums)
    {

        return true;
    }

    /**
     * 733. 图像渲染(flood-fill)
     * @param image
     * @param sr
     * @param sc
     * @param newColor
     * @return
     */
    public static int[][] floodFill(int[][] image, int sr, int sc, int newColor)
    {
        if (image[sr][sc] == newColor)
            return image;

        int row = image.length;
        int col = image[0].length;
        boolean[] flag = new boolean[row * col];

        int oldColor = image[sr][sc];
        image[sr][sc] = newColor;
        flag[sr * col + sc] = true;

        if (sc - 1 >= 0 && image[sr][sc - 1] == oldColor && !flag[sr * col + sc - 1])
            image = floodFill(image, sr, sc - 1, newColor);
        if (sc + 1 < col && image[sr][sc + 1] == oldColor && !flag[sr * col + sc + 1])
            image = floodFill(image, sr, sc + 1, newColor);
        if (sr - 1 >= 0 && image[sr - 1][sc] == oldColor && !flag[(sr - 1) * col + sc])
            image = floodFill(image, sr - 1, sc, newColor);
        if (sr + 1 < row && image[sr + 1][sc] == oldColor && !flag[(sr + 1) * col + sc])
            image = floodFill(image, sr + 1, sc, newColor);

        return image;
    }
}
