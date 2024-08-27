import java.util.Scanner;

// 요정이 먹은 칸을 0으로 만들고
// 덩어리 칸은 -1로 만들어서 덩어리를 구분하자!

class Cell {
    int r, c;
    Cell (int r, int c) {
        this.r = r;
        this.c = c;
    }
}

public class Solution {
	static int N;
	static int[][] cheese;
	static int[][] todayCheese;
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();

        for (int test_case = 1; test_case <= T; test_case++) {
            N = sc.nextInt();   // 치즈 한 변의 길이
            cheese = new int[N][N];
            int maxTaste = Integer.MIN_VALUE;
            int maxCheeseNum = 1;

            // 치즈의 칸별 맛있는 정도 입력 받기
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    cheese[i][j] = sc.nextInt();
                    maxTaste = cheese[i][j] > maxTaste ? cheese[i][j] : maxTaste;
                }
            }

            // x일차의 치즈 덩어리 개수 확인하기
            // 마지막 날까지 다 먹으면 치즈 덩어리가 0개 이므로 마지막 날은 제외함
            for (int x = 1; x < maxTaste; x++) {

                // 요정이 먹은 치즈 칸 비우기
                for (int i = 0; i < N; i++) {
                    for (int j = 0; j < N; j++) {
                        if (cheese[i][j] == x) {
                            cheese[i][j] = 0;
                        }
                    }
                }
                
            	// 새로운 치즈 데이터 복사하기
                todayCheese = new int[N][N];
                for (int i = 0; i < N; i++) {
                    for (int j = 0; j < N; j++) {
                    	todayCheese[i][j] = cheese[i][j];
                    }
                }

                // 치즈 덩어리 세기
                int cheeseNum = 0;
                for (int i = 0; i < N; i++) {
                    for (int j = 0; j < N; j++) {
                        // 이 칸에 치즈가 있다면
                        if (todayCheese[i][j] > 0) {
                        	cheeseNum++;
                            dfs(new Cell(i, j));
                        }
                    }
                }
                maxCheeseNum = cheeseNum > maxCheeseNum ? cheeseNum : maxCheeseNum;

            }
            System.out.println("#" + test_case + " " + maxCheeseNum);
        }

        sc.close();
    }

	private static void dfs(Cell cell) {
		int r = cell.r;
		int c = cell.c;
		
		// 사방탐색
	    for (int k = 0; k < 4; k++) {
		    int newRow = r + dr[k];
		    int newCol = c + dc[k];
		    // 요정이 먹지도 않았고 아직 방문하지도 않았다면 치즈 덩어리에 포함되어 있으므로
		    if (newRow >= 0 && newRow < N && newCol >= 0 && newCol < N && todayCheese[newRow][newCol] > 0) {
		    	todayCheese[newRow][newCol] = -1;
		    	dfs(new Cell(newRow, newCol));
		    }
	  	}	
	}
	
	
}