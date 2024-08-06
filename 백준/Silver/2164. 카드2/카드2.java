import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
	static BufferedReader bf;
	static Queue<Integer> q;

	public static void main(String[] args) throws IOException {
		// N 입력 받기
		bf = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(bf.readLine());
		
		// 큐 생성
		q = new LinkedList<>();
		
		// 큐에 카드를 1부터 넣음
		for (int i=1; i<=N; i++) {
			q.add(i);
		}
		
		while(q.size() > 1) {
			q.remove();
			int popped = q.remove();
			q.add(popped);
		}
		
		System.out.println(q.remove());
	}
}
