import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	
	public static class Node {
		int idx, cost;
		
		Node (int idx, int cost) {
			this.idx = idx;
			this.cost = cost;
		}

		@Override
		public String toString() {
			return "Node [idx=" + idx + ", cost=" + cost + "]";
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(bf.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		boolean[] visited = new boolean[100001];
		
		PriorityQueue<Node> pq = new PriorityQueue<>((node1, node2) -> {
			if (node1.cost != node2.cost) {
				return node1.cost - node2.cost;
			} else {
				return node1.idx - node2.idx;
			}
		});  // cost를 기준으로 오름차순, cost가 같다면 idx를 기준으로 오름차순
		
		pq.add(new Node(N, 0));
		visited[N] = true;
		
		while (!pq.isEmpty()) {
			Node node = pq.poll();
			
			// 도착했다면 
			if (node.idx == K) {
				System.out.println(node.cost);
				return;
			} else {
				// 몇 이하여야 하지...?
				if (node.idx * 2 <= K + 1 && !visited[node.idx * 2]) {
					pq.add(new Node(node.idx * 2, node.cost));
					visited[node.idx * 2] = true;
				}
				if (node.idx - 1 >= 0 && !visited[node.idx - 1]) {
					pq.add(new Node(node.idx - 1, node.cost + 1));
					visited[node.idx - 1] = true;
				}
				if (node.idx + 1 <= K && !visited[node.idx + 1]) {
					pq.add(new Node(node.idx + 1, node.cost + 1));
					visited[node.idx + 1] = true;
				}
			}
		}
	}
}
