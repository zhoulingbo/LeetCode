package pers.zhoulingbo.leetcode;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

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

    /**
     * 743. 网络延迟时间
     * @param times
     * @param N
     * @param K
     * @return
     */
    public int networkDelayTime(int[][] times, int N, int K)
    {
        boolean[] visits = new boolean[N];
        int[][] b = new int[N][3];
        for (int i = 0; i < N; i++)
        {
            b[i][0] = K - 1;
            b[i][1] = i;
            b[i][2] = (i == K - 1) ? 0 : Integer.MAX_VALUE;
        }
        PriorityQueue<int[]> queue = new PriorityQueue<>(N, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2)
            {
                return o1[2] - o2[2];
            }
        });

        queue.offer(b[K - 1]);
        while (!queue.isEmpty())
        {
            int[] a = queue.poll();
            visits[a[1]] = true;
            for (int j = 0; j < times.length; j++)
            {
                if (times[j][0] == a[1] + 1)
                {
                    int[] before = b[times[j][1] - 1];
                    int w = b[times[j][0] - 1][2] + times[j][2];
                    int oldw = b[times[j][1] - 1][2];
                    b[times[j][1] - 1][2] = (w <= oldw) ? w : oldw;
                    int[] curr = b[times[j][1] - 1];
                    if (!visits[times[j][1] - 1])
                    {
                        queue.remove(before);
                        queue.offer(curr);
                    }
                }
            }
        }

        int w = 0;
        for (int i = 0; i < N; i++)
            w = Math.max(w, b[i][2]);
        return (w == Integer.MAX_VALUE) ? -1 : w;
    }

    /**
     * 841. 钥匙和房间
     * @param rooms
     * @return
     */
    public boolean canVisitAllRooms(List<List<Integer>> rooms)
    {
        boolean[] b = new boolean[rooms.size()];
        b[0] = true;
        Queue<Integer> queue = new LinkedList<>();
        queue.add(0);
        while (!queue.isEmpty())
        {
            int index = queue.poll();
            List<Integer> keys = rooms.get(index);
            for (Integer key : keys)
            {
                if (!b[key])
                {
                    b[key] = true;
                    queue.add(key);
                }
            }
        }

        for (int i=0 ;i<b.length; i++)
            if (!b[i])
                return false;

        return true;
    }
}
