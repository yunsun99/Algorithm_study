import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Scanner;

// 입력을 인접행렬로 주는데 인접리스트로 바꿔야 한다니ㅠ
// 인접행렬로 하면 안된다는 데 이유가 뭐지? 메모리 초과가 나나?
// 메모리 계산하는 거 나도 공부해야겠다

// 이문제는 MST로 푸는 문제이고, 따라서 크루스칼이나 프림으로 풀면 된다
// 프림이 더 구현이 쉽기 때문에, 프림으로 푼다!
// 이 문제를 보고 프림으로 푼다는 생각을 어떻게 떠올릴까?
// "가중치"가 있는 최소 비용(거리)를 구하기 때문!


// cost가 작은 순으로 정렬
class Edge implements Comparable<Edge> {
	int planet;
	int cost;
	
	Edge (int planet, int cost) {
		this.planet = planet;
		this.cost = cost;
	}

    @Override
    public int compareTo(Edge o) {
        // return this.cost - o.cost;
    	if (this.cost == o.cost) {
    		return this.planet - o.planet;
    	}
    	return this.cost - o.cost;
    }
}

public class Main {
	
	// 행성 간의 비용을 인접리스트로 받을 예정
	static ArrayList<Edge>[] adjList;
	static int N;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        
        adjList = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) adjList[i] = new ArrayList<Edge>();
        
        // 입력 받기
        for (int i = 1; i <= N; i++) {
           for (int j = 1; j <= N; j++) {
        	   int cost = sc.nextInt();
        	   if (i == j) {
        		   continue;   // 자기 자신을 향한 길은 제외
        	   }
        	   adjList[i].add(new Edge(j, cost));
           }
        }
        
//        for (int i = 1; i <= N; i++) {
//			System.out.println(adjList[i].toString());
//		}
        
        // 최소 비용의 합을 구하기 위해 프림 알고리즘 수행.
        prim(1);
        
        sc.close();
    }
    
    private static void prim(int start) {
    	boolean[] visited = new boolean[N + 1];
    	
    	PriorityQueue<Edge> pq = new PriorityQueue<>();
    	pq.offer(new Edge(start, 0));
    	
    	long total = 0;
    	while (!pq.isEmpty()) {
    		// 우선순위 큐에서 간선을 하나 꺼냄.
    		// 우선순위 큐 안에서 정렬이 일어나서 가장 cost가 낮은 간선부터 나옴.
    		Edge edge = pq.poll();
    		int planet = edge.planet;
    		int cost = edge.cost;
    		
    		// 간선과 연결된 정점이 이미 방문한 정점이라면 넘어감.
    		if (visited[planet] == true) continue;
    		
    		// 간선과 연결된 정점이 방문한 적 없는 정점이라면 
    		// 방문표시를 하고 정점과 연결된 간선들을 탐색함.
    		visited[planet] = true;
    		total += cost;
    		
    		for (Edge next : adjList[planet]) {
    			// 방문한 적 없는 정점이라면 우선순위 큐에 간선 추가
    			if (!visited[next.planet]) pq.offer(next);
			}
    		
    	}
    	System.out.println(total);
    }

}