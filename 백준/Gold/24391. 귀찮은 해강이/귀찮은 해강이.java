import java.util.Scanner;

public class Main {
    public static int[] parent;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();   // 강의 개수
        int M = sc.nextInt();   // 연결되어 있는 쌍의 개수

        parent = new int[N+1];
        int[] lectures = new int[N];
        int answer = 0;

        for (int i = 0; i <= N; i++) {
            parent[i] = i;
        }

        // 연결된 건물 쌍 입력받기
        for (int k = 0; k < M; k++) {
            int i = sc.nextInt();
            int j = sc.nextInt();

            union(i, j);
        }

        // 마지막 줄 강의코드 입력 받기
        for (int i = 0; i < N; i++) {
            lectures[i] = sc.nextInt();
        }
        sc.close();

        for (int i = 0; i < N-1; i++) {
            int prevLecture = lectures[i];
            int nextLecture = lectures[i+1];

            // 중요!!!!!!!!! 이거 때문에 계속 틀림..ㅠㅠ
            // if (parent[prevLecture] != parent[nextLecture]) answer++;
            if (find(prevLecture) != find(nextLecture)) answer++;
        }

        System.out.println(answer);
    }

    // 너 부모가 누구양? 어디 그래프출신?
    public static int find(int x) {
        if (parent[x] == x) return x;
        else return parent[x] = find(parent[x]);
    }

    // 너희 둘은 같은 그래프다!
    public static void union(int x, int y) {
        // x, y 출신 알아내기
        int rootX = find(x);
        int rootY = find(y);

        // 달랐으면 같게 만들어주기!
        if (rootX != rootY) {
            parent[rootY] = rootX;
        }
    }
}
