import java.util.Scanner;

public class Main {
    public static int[] parent;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int m = sc.nextInt();
        parent = new int[n];

        // 아직 아무도 연결하지 않은 상태
        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }

        // 연결 시작
        for (int i = 1; i <= m; i++) {
            int dot1 = sc.nextInt();
            int dot2 = sc.nextInt();

            boolean isCycle = union(dot1, dot2);

            if (!isCycle) {
                System.out.println(i);
                return;
            }
        }
        sc.close();
        System.out.println(0);
    }

    public static boolean union(int x, int y) {
        int rootX = find(x);
        int rootY = find(y);

        if (rootX == rootY) return false;
        else parent[rootY] = rootX;
        return true;
    }

    // x가 속한 그래프 번호(root) 찾기
    // 경로 압축을 통해 부모 노드들도 다 root로 업데이트 시킴
    public static int find(int x) {
        if (parent[x] == x) return x;
        else return parent[x] = find(parent[x]);
    }
}
