import java.util.LinkedList;
import java.util.Queue;

public class Solution {

    private static class Dinic {
        private int n;
        private int[][] graph;
        private int[] level;
        private int[] ptr;
        private Queue<Integer> queue;

        public Dinic(int n, int[][] graph) {
            this.n = n;
            this.graph = graph;
            this.level = new int[n];
            this.ptr = new int[n];
            this.queue = new LinkedList<>();
        }

        private boolean bfs(int s, int t) {
            for (int i = 0; i < n; i++) {
                level[i] = -1;
            }
            queue.add(s);
            level[s] = 0;
            while (!queue.isEmpty()) {
                int u = queue.poll();
                for (int v = 0; v < n; v++) {
                    if (level[v] < 0 && graph[u][v] > 0) {
                        level[v] = level[u] + 1;
                        queue.add(v);
                    }
                }
            }
            return level[t] >= 0;
        }

        private int dfs(int u, int t, int flow) {
            if (u == t) {
                return flow;
            }
            for (int v = ptr[u]; v < n; v++) {
                if (level[v] == level[u] + 1 && graph[u][v] > 0) {
                    int f = dfs(v, t, Math.min(flow, graph[u][v]));
                    if (f > 0) {
                        graph[u][v] -= f;
                        graph[v][u] += f;
                        return f;
                    }
                }
                ptr[u]++;
            }
            return 0;
        }

        public int maxFlow(int s, int t) {
            int flow = 0;
            while (bfs(s, t)) {
                for (int i = 0; i < n; i++) {
                    ptr[i] = 0;
                }
                while (true) {
                    int f = dfs(s, t, Integer.MAX_VALUE);
                    if (f == 0) {
                        break;
                    }
                    flow += f;
                }
            }
            return flow;
        }
    }

    public static int solution(int[] entrances, int[] exits, int[][] path) {
        
        int n = path.length + 2;
        int[][] graph = new int[n][n];

        for (int i = 0; i < path.length; i++) {
            for (int j = 0; j < path[i].length; j++) {
                graph[i + 1][j + 1] = path[i][j];
            }
        }

        for (int e: entrances) {
            graph[0][e + 1] = Integer.MAX_VALUE;
        }

        for (int e: exits) {
            graph[e + 1][n - 1] = Integer.MAX_VALUE;
        }

        return new Dinic(n, graph).maxFlow(0, n - 1);

    }

}

