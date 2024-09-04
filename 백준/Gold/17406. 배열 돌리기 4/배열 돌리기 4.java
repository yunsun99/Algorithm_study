import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

// 일단 회전 연산 정보를 다 받은 후 순열을 이용해서 모든 경우의 회전 결과를 구해야 할 듯!
// N*M인데 중간에 N*N으로 풀어서 디버깅하는데 한참 걸렸다..ㅠㅠ
// 순열을 돌릴 때 첫번째 순열을 돌리고, 그 바뀐 배열 값 그대로 두번째 순열을 돌려서 이 역시도 디버깅이 한참 걸렸다..

class Rotation {
    int r, c, s;
    public Rotation(int r, int c, int s) {
        this.r = r;
        this.c = c;
        this.s = s;
    }
}

public class Main {
    static int N, M, K;
    static int minArrVal = Integer.MAX_VALUE;
    static Rotation[] rotations;
    static int[][] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        String str = bf.readLine();
        StringTokenizer st = new StringTokenizer(str);
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        arr = new int[N+1][M+1];
        rotations = new Rotation[K];
        
        // 배열 정보 입력 받기
        for (int i = 1; i <= N; i++) {
            str = bf.readLine();
            st = new StringTokenizer(str);
            for (int j = 1; j <= M; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        
        // 회전 연산 정보 입력 받기
        for (int i = 0; i < K; i++) {
            str = bf.readLine();
            st = new StringTokenizer(str);
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());
            
            rotations[i] = new Rotation(r, c, s);
        }
        
        // 1-K를 포함하는 순열 만들기
        permutation(new int[K], 0, new boolean[K+1]);
        
        bw.write(String.valueOf(minArrVal));
        bw.flush();
        bw.close();
    }
    
    private static void permutation(int[] sel, int selIdx, boolean[] v) {
    	// 순열이 완성됨
        if (selIdx == K) {
        	// 배열을 그대로 쓰지 말고 깊은 복사를 해야 함에 유의!!!
        	int[][] newArr = new int[N+1][M+1];
        	for (int j = 1; j <= N; j++) {
				for (int k = 0; k <= M; k++) {
					newArr[j][k] = arr[j][k];
				}
			}
        	// 순열 순서대로 회전 연산을 수행
            for (int i = 0; i < K; i++) {
				rotate(newArr, sel[i]);
			}
            
            // 회전 연산 수행을 마치고 각 행의 있는 모든 수의 합 중 최솟값 구하기
            int arrVal = Integer.MAX_VALUE;   // 이번에 구한 순열대로 회전한 후 배열 값
            for (int i = 1; i <= N; i++) {
				int rowSum = 0;
				for (int j = 1; j <= M; j++) {
					rowSum += newArr[i][j];
				}
				arrVal = rowSum < arrVal ? rowSum : arrVal;
			}
            minArrVal = arrVal < minArrVal ? arrVal : minArrVal;
            return;
        }
        
        // 순열 생성 중
        for (int i = 0; i < K; i++) {
            if (v[i] == true) continue;
            
            // 선택한 경우
            sel[selIdx] = i;
            v[i] = true;
            permutation(sel, selIdx+1, v);
            
            // 선택하지 않은 경우
            v[i] = false;
        }
    }
    
    private static void rotate(int[][] newArr, int rotateIdx) {
    	Rotation rot = rotations[rotateIdx];
    	int r = rot.r;
    	int c = rot.c;
    	int s = rot.s;
    	
    	// 중심으로부터 떨어진 거리 순으로 작은 네모부터 회전시키기
    	for (int dis = 1; dis <= s; dis++) {
			int temp = newArr[r-dis][c-dis];
			
			// 진행방향의 역순으로 저장 -> 진행방향 순으로 저장 시 모든 칸이 첫번째 칸의 값을 저장하므로 피해야 함
			// 좌측 열 위로 이동
			for (int row = r-dis; row < r+dis; row++) {
				newArr[row][c-dis] = newArr[row+1][c-dis];
			}
			// 아래쪽 행 왼쪽으로 이동
			for (int col = c-dis; col < c+dis; col++) {
				newArr[r+dis][col] = newArr[r+dis][col+1];
			}
			// 우측 열 아래로 이동
			for (int row = r+dis; row > r-dis; row--) {
				newArr[row][c+dis] = newArr[row-1][c+dis];
			}
			// 위쪽 행 오른쪽으로 이동
			for (int col = c+dis; col > c-dis; col--) {
				newArr[r-dis][col] = newArr[r-dis][col-1];
			}
			// 덮어씌워진 첫 번째 칸의 값 복구해서 두번째 칸에 넣어줌.
			newArr[r-dis][c-dis+1] = temp;
		}
    }

}