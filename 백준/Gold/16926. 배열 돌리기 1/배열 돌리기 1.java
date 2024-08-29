import java.util.Scanner;

public class Main {
	
	static int N, M;
	static int rectangles;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		M = sc.nextInt();
		int R = sc.nextInt();
		
		int[][] arr = new int[N][M];
		
		// 배열 입력받기
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				arr[i][j] = sc.nextInt();
			}
		}
		
		// 회전을 도는 네모의 수
		rectangles = Math.min(N, M) / 2;
		
		// 회전 시키기
		for (int i = 0; i < R; i++) {
			rotate(arr);
		}
		
		// 정답 출력
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				System.out.print(arr[i][j] + " ");
			}
			System.out.println();
		}
		
		sc.close();
	}
	
	private static void rotate(int[][] arr) {
		int temp = 0;
		
		for (int rec = 0; rec < rectangles; rec++) {
			// 최초 네모의 맨윗줄 맨왼쪽 칸은 값이 증발해버리는 문제가 있기에 미리 temp에 보관
			temp = arr[rec][rec];
			
			// 네모의 윗줄 -> 맨 왼쪽 칸을 제외하고는 왼쪽으로 이동
			for (int j = rec + 1; j < M - rec; j++) {
				arr[rec][j-1] = arr[rec][j];
			}
			
			// 네모의 오른쪽 줄 -> 맨 위쪽 칸을 제외하고는 위로 간다
			for (int i = rec + 1; i < N - rec; i++) {
				arr[i-1][(M-1)-rec] = arr[i][(M-1)-rec];
			}
			
			// 네모의 아랫줄 -> 맨 오른쪽 칸을 제외하고는 오른쪽으로 간다
			for (int j = (M-1)-rec - 1; j >= rec; j--) {
				arr[(N-1)-rec][j+1] = arr[(N-1)-rec][j];
			}
			
			// 네모의 왼쪽줄 -> 맨 아래쪽 칸을 제외하고는 아래로 간다
			for (int i = (N-1)-rec - 1; i >= rec; i--) {
				arr[i+1][rec] = arr[i][rec];
			}
			
			
			// 잘못 덮어씌워지는 값 복구
			arr[rec+1][rec] = temp; 
		}
	}
}
