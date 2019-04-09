package pers.zhoulingbo.leetcode;

import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;

/**
 * 
 * 图问题求解
 * 
 */
public class GraphProblem
{

    public static void main(String[] args)
    {
//        int[][] prerequisites = new int[][] {{ },  { 3 }, {  }, { 1 }, { } };
//        System.out.println(isBipartite(prerequisites));

    }

    /**
     * 399. 除法求值
     * @param equations
     * @param values
     * @param queries
     * @return
     */
    public static double[] calcEquation(String[][] equations, double[] values, String[][] queries)
    {
        Set<String> set = new HashSet<>();
        for (int i = 0; i < equations.length; i++)
        {
            set.add(equations[i][0]);
            set.add(equations[i][1]);
        }
        double[] a = new double[queries.length];
        for (int i = 0; i < queries.length; i++)
        {
            String first = queries[i][0];
            String second = queries[i][1];
            if (first.equals(second))
            {
                if (set.contains(first))
                    a[i] = 1.0;
                else
                    a[i] = -1.0;
                continue;
            }

            Map<String, Double> map = new HashMap<>();
            map.put(first, 1.0);
            while (true)
            {
                boolean hasNew = false;
                boolean found = false;
                for (int j = 0; j < equations.length; j++)
                {
                    String f = equations[j][0];
                    String s = equations[j][1];
                    if (map.containsKey(f))
                    {
                        double val = map.get(f) * values[j];
                        if (!map.containsKey(s))
                        {
                            map.put(s, val);
                            hasNew = true;
                        }
                        if (s.equals(second))
                        {
                            found = true;
                            break;
                        }
                    }
                    else if (map.containsKey(s))
                    {
                        double val = map.get(s) / values[j];
                        if (!map.containsKey(f))
                        {
                            map.put(f, val);
                            hasNew = true;
                        }
                        if (f.equals(second))
                        {
                            found = true;
                            break;
                        }
                    }
                }

                if (found || !hasNew)
                    break;
            }
            
            if (map.containsKey(second))
                a[i] = map.get(second);
            else
                a[i] = -1.0;
        }

        return a;
    }

    /**
     * 207. 课程表
     * @param numCourses
     * @param prerequisites
     * @return
     */
    public static boolean canFinish(int numCourses, int[][] prerequisites)
    {
        int[] a = new int[numCourses];
        for (int i = 0; i < prerequisites.length; i++)
        {
            int[] item = prerequisites[i]; // 需要先决条件
            a[item[0]] = 1; // 1表示需先决条件,0表示不需要
        }

        for (int i = 0; i < a.length; i++)
        {
            if (a[i] == 0)
                continue;

            Queue<Integer> queue = new LinkedList<>();
            for (int j = 0; j < prerequisites.length; j++)
            {
                if (prerequisites[j][0] == i)
                {
                    queue.add(prerequisites[j][1]);
                }
            }
        }

        for (int i = 0; i < a.length; i++)
        {
            if (a[i] == 1)
                return false;
        }

        return true;
    }

    /**
     * 785. 判断二分图
     * @param graph
     * @return
     */
    public static boolean isBipartite(int[][] graph)
    {
        Set<Integer> red = new HashSet<>();
        Set<Integer> blue = new HashSet<>();
        Set<Integer> disperse = new HashSet<>();

        for (int i = 0; i < graph.length; i++)
        {
            if (graph[i].length == 0)
            {
                disperse.add(i);
                continue;
            }
            
            if (!red.contains(i))
            {
                red.add(i);
                boolean b = color(graph, red, blue, i);
                if (!b)
                    return false;
            }
        }

        return true;
    }
    
    private static boolean color(int[][] graph, Set<Integer> red, Set<Integer> blue, int r)
    {
        Queue<Integer> queueRed = new LinkedList<>();
        Queue<Integer> queueBlue = new LinkedList<>();
        queueRed.add(r);
        while (!queueRed.isEmpty() && !queueBlue.isEmpty())
        {
            while (!queueRed.isEmpty())
            {
                int i = queueRed.poll();
                for (int j = 0; j < graph[i].length; j++)
                {
                    if (red.contains(graph[i][j]))
                        return false;
                    blue.add(graph[i][j]);
                    queueBlue.add(graph[i][j]);
                }
            }
            
            while (!queueBlue.isEmpty())
            {
                int i = queueBlue.poll();
                for (int j = 0; j < graph[i].length; j++)
                {
                    if (blue.contains(graph[i][j]))
                        return false;
                    red.add(graph[i][j]);
                    queueRed.add(graph[i][j]);
                }
            }
        }
        
        return true;
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

        for (int i = 0; i < b.length; i++)
            if (!b[i])
                return false;

        return true;
    }
}
