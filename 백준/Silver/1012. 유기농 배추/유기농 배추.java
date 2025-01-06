import java.util.*;
import java.io.*;

public class Main {
    static int dirX[] = {0, 0, -1, 1};
    static int dirY[] = {-1, 1, 0, 0};
    static int map[][];
    static boolean visit[][];

    static int M, N, K;
    static int count;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        for (int i = 0; i < T; i++) {
            st = new StringTokenizer(br.readLine());

            M = Integer.parseInt(st.nextToken());
            N = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());

            map = new int[N][M];
            visit = new boolean[N][M];

            for (int j = 0; j < K; j++) {
                st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                map[y][x] = 1;
            }

            count = 0;
            for (int j = 0; j < N; j++) {
                for (int k = 0; k < M; k++) {
                    if (map[j][k] == 1 && !visit[j][k]) {
                        count++;
                        BFS(k, j);
                    }
                }
            }
            sb.append(count).append('\n');
        }

        System.out.println(sb);
    } // End Main

    public static void BFS(int x, int y) {
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{x, y});
        visit[y][x] = true;

        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int curX = current[0];
            int curY = current[1];

            for (int i = 0; i < 4; i++) {
                int nextX = curX + dirX[i];
                int nextY = curY + dirY[i];

                if (nextY >= 0 && nextY < N && nextX >= 0 && nextX < M && !visit[nextY][nextX] && map[nextY][nextX] == 1) {
                    visit[nextY][nextX] = true;
                    queue.add(new int[]{nextX, nextY});
                }
            }
        }
    }
}
