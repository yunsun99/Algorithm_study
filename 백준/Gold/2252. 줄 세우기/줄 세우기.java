import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int M = sc.nextInt();
		
		// int[][] students = new int[N+1][N+1];
		ArrayList<Integer>[] students = new ArrayList[N+1];
		for (int i = 1; i <= N; i++) {
			students[i] = new ArrayList<Integer>();
		}
		
		int[] indegrees = new int[N+1];
		ArrayList<Integer> answer = new ArrayList<Integer>();
		
		for (int i = 0; i < M; i++) {
			int student1 = sc.nextInt();
			int student2 = sc.nextInt();
			// students[student1][student2] = 1;
			students[student1].add(student2);
			indegrees[student2]++;
		}
		
		// indegree가 0인 정점을 저장할 큐
		Queue<Integer> q = new ArrayDeque<Integer>();

		for (int i = 1; i <= N; i++) {
			if (indegrees[i] == 0) {
				indegrees[i] = -1;
				q.offer(i);
			}
		}
		
		while(!q.isEmpty()) {
			int stu = q.poll();
			answer.add(stu);
			
			for (int i = 0; i < students[stu].size(); i++) {
				int next = students[stu].get(i);
				indegrees[next]--;
				if (indegrees[next] == 0) {
					indegrees[next] = -1;
					q.offer(next);
				}
			}
			
		}
		
		for (int i = 0; i < answer.size(); i++) {
			System.out.print(answer.get(i) + " ");
		}
		
		sc.close();
	}

}
