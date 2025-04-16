import java.util.*;
import java.io.*;
import java.lang.*;

class CCTV {
	int row, col, type;

	CCTV (int row, int col, int type) {
		this.row = row;
		this.col = col;
		this.type = type;
	}
}

public class Main {
	static int N, M;
	static int[][] map;
	static List<CCTV> cctvList;
	static int minBlindSpots = Integer.MAX_VALUE;

	// 상, 하, 좌, 우
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};

	// CCTV별 방향 정의
	static int[][][] directions = {
		// 1번 cctv
		{{0}, {1}, {2}, {3}},
		// 2번 cctv
		{{0, 1}, {2, 3}},
		// 3번 cctv
		{{0, 3}, {1, 3}, {1, 2}, {0, 2}},
		// 4번 cctv		
		{{0, 2, 3}, {0, 1, 3}, {1, 2, 3}, {0, 1, 2}},
		// 5번 cctv
		{{0, 1, 2, 3}}
	};

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();

		map = new int[N][M];
		cctvList = new ArrayList<>();

		// 지도와 CCTV 입력받기
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				map[i][j] = sc.nextInt();

				if (map[i][j] >= 1 && map[i][j] <= 5) {
					cctvList.add(new CCTV(i, j, map[i][j]));
				}
			}
		}

		dfs(0, map);
		System.out.println(minBlindSpots);
	}

	public static void dfs(int depth, int[][] map) {
		// 종료조건
		if (depth == cctvList.size()) {
			// 사각지대 크기 세기
			int blindSpots = 0;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					if (map[i][j] == 0) {
						blindSpots++;
					}
				}
			}

			// 최솟값과 비교
			minBlindSpots = blindSpots < minBlindSpots ? blindSpots : minBlindSpots;

			return;
		}

		// 몇번째 CCTV인지
		CCTV cctv = cctvList.get(depth);
		int type = cctv.type;

		for (int[] dirs : directions[type - 1]) {   // dirs = {0, 1} -> 선택한 방향
			// 지도 복사
			int[][] copiedMap = copyMap(map);

			// 복사된 지도에 감시지역 표시
			mark(copiedMap, cctv, dirs);

			// 다음 cctv로 넘어감
			dfs(depth + 1, copiedMap);
		}


	}

	public static int[][] copyMap(int[][] originalMap) {
		int[][] newMap = new int[N][M];

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				newMap[i][j] = originalMap[i][j];
			}
		}

		return newMap;
	}

	public static void mark(int[][] copiedMap, CCTV cctv, int[] dirs) {
		int r = cctv.row;
		int c = cctv.col;

		for (int dir : dirs) {   // dir = 0
			int nr = r;
			int nc = c;

			while (true) {
				nr += dr[dir];
				nc += dc[dir];

	            if (nr < 0 || nr >= N || nc < 0 || nc >= M || map[nr][nc] == 6) break;
	            if (copiedMap[nr][nc] == 0) copiedMap[nr][nc] = 9; // 감시 영역
			}
		}

	}
}