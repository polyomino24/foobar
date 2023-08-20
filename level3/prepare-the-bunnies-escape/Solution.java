import java.util.LinkedList;
import java.util.Queue;

public class Solution {

    private static final int[] dx = new int[]{-1, 0, 0, 1};
    private static final int[] dy = new int[]{0, -1, 1, 0};

    private static final int INF = 1 << 30;

    public static int solution(int[][] map) {
        int h = map.length;
        int w = map[0].length;

        int[][][] dp = new int[h][w][2];
        for (int i = 0; i < h; i++)
            for (int j = 0; j < w; j++)
                for (int k = 0; k < 2; k++)
                    dp[i][j][k] = INF;
        dp[0][0][0] = 1;

        Queue<int[]> queue = new LinkedList<>();

        queue.add(new int[]{0, 0, 0});
        while (!queue.isEmpty()) {
            int[] cur = queue.remove();
            int x = cur[0];
            int y = cur[1];
            int used = cur[2];
            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if (nx < 0 || nx >= h || ny < 0 || ny >= w) continue;

                if (map[nx][ny] == 0) {
                    if (dp[nx][ny][used] != INF) continue;
                    dp[nx][ny][used] = dp[x][y][used] + 1;
                    queue.add(new int[]{nx, ny, used});
                } else if (used == 0) {
                    if (dp[nx][ny][1] != INF) continue;
                    dp[nx][ny][1] = dp[x][y][used] + 1;
                    queue.add(new int[]{nx, ny, 1});
                }
            }
        }

        return Math.min(dp[h - 1][w - 1][0], dp[h - 1][w - 1][1]);
    }
}

