package pers.zhoulingbo.leetcode;

public class GraphProblem
{

    public static void main(String[] args)
    {
        
    }

    /**
     * 997. 找到小镇的法官
     * @param N
     * @param trust
     * @return
     */
    public static int findJudge(int N, int[][] trust)
    {
        int[] t = new int[N];
        for (int i = 0; i < trust.length; i++)
        {
            int a = trust[i][0];
            int b = trust[i][1];
            t[a - 1] = -1;
            t[b - 1] += 1;
        }

        for (int i = 0; i < N; i++)
            if (t[i] == N - 1)
                return i + 1;
        return -1;
    }
}
