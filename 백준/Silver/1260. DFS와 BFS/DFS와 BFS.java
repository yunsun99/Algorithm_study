import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	static boolean[][] map;
	static boolean[] visited;
	static int N, M, V;

	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		
		String s = bf.readLine();
		StringTokenizer st = new StringTokenizer(s);
		
		N = Integer.parseInt(st.nextToken());   // 정점(노드) 개수
		M = Integer.parseInt(st.nextToken());   // 간선 개수
		V = Integer.parseInt(st.nextToken());   // 탐색을 시작할 정점의 번호
		
		// 그래프 그리기
		map = new boolean[N+1][N+1];
		for (int i=0; i<M; i++) {
			s = bf.readLine();
			st = new StringTokenizer(s);
			int node1 = Integer.parseInt(st.nextToken());
			int node2 = Integer.parseInt(st.nextToken());
			
			map[node1][node2] = true;
			map[node2][node1] = true;
		}
		
		// 방문 배열 생성 및 초기화
		visited = new boolean[N+1];
		
		// 깊이 우선 탐색
		dfs(V);
		System.out.println();
		
		// 방문 배열 초기화
		visited = new boolean[N+1];
		bfs(V);

	}
	
	public static void dfs(int node1) {
		visited[node1] = true;
		System.out.print(node1 + " ");
		
		for (int node2=1; node2<=N; node2++) {
			// 탐색하려는 노드와 간선이 연결되어 있고 해당 노드가 아직 방문하지 않은 노드라면
			if (map[node1][node2] == true && visited[node2] == false) {
				dfs(node2);
			}
		}
	}
	
	public static void bfs(int node1) {
		Queue<Integer> q = new LinkedList<>();
		q.add(node1);
		visited[node1] = true;
		
		while (!q.isEmpty()) {
			node1 = q.poll();
			System.out.print(node1 + " ");
			for (int node2=1; node2<=N; node2++) {
				if (map[node1][node2] == true && visited[node2] == false) {
					q.add(node2);
					visited[node2] = true;
				}
			}
			
		}
	}

}

