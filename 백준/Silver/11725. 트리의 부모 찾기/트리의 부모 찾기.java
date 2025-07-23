import java.io.*;
import java.util.*;

public class Main {
    static List<List<Integer>> tree = new ArrayList<>();
    static int[] parent;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        parent = new int[n + 1];
        visited = new boolean[n + 1];

        // 인접 리스트 초기화
        for (int i = 0; i <= n; i++) {
            tree.add(new ArrayList<>());
        }

        // 간선 입력
        for (int i = 0; i < n - 1; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            tree.get(u).add(v);
            tree.get(v).add(u);
        }

        // 루트 노드에서 DFS 시작
        dfs(1);

        // 2번 노드부터 부모 출력
        for (int i = 2; i <= n; i++) {
            System.out.println(parent[i]);
        }
    }

    static void dfs(int current) {
        visited[current] = true;
        for (int next : tree.get(current)) {
            if (!visited[next]) {
                parent[next] = current;
                dfs(next);
            }
        }
    }
}
