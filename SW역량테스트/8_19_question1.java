import java.util.Arrays;
import java.util.Scanner;

public class test {
  
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        for (int test_case = 1; test_case <= 10; test_case++) {
            // 입력 받기
            int N = sc.nextInt();
            
            int[][] board = new int[N][N];
            
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    board[i][j] = sc.nextInt();
                }
            }
            
            // 하측 하강: 왼쪽부터 한 열씩 계산해보자
            L: for (int j = 0; j < N; j++) {
                // 해당 열의 맨 윗칸이 비어있음
                if (board[0][j] == 0) {
                    continue;
                }
                
                // 해당 열의 맨 위칸에 블록이 있음
                else {
                    float power = 1;
                    int myCells = 1;
                    
                    // 해당 열의 두번째 칸부터 블록이 있는지 쭉 검사
                    for (int i = 1; i < N; i++) {
                        // 이 칸에 블록이 없다면
                        if (board[i][j] == 0) {
                            // 다음칸에서는 내 하강력의 1.9배가 됨.
                            power *= 1.9;
                            board[i - myCells][j] = 0;
                            board[i][j] = 1;
                            continue;
                        }
                        else {
                            // 다음칸에도 블록이 있는지 끝날때까지 검사
                            int cells = 1;
                            for (int k = i+1; k<N; k++) {
                                // 아직 한 덩어리
                                if (board[k][j] == 1) {
                                    cells++;
                                }
                                // 블록 끝!
                                else {
                                    break;
                                }
                            }
                            
                            // 다 검사했으면 현재 내 하강력과 저항력(칸수)를 비교
                            // 다음 턴에 하강!
                            if (power > cells) {
                                power += cells;
                                i += cells - 1;  
                                myCells = myCells + cells;
                            }
                            // 그 자리에서 유지 -> 다음 열 탐색
                            else {
                                continue L;
                            }
                        }
                    }
                }
            }
            
            // 우측 하강: 위쪽부터 한 행씩 계산해보자
            L: for (int i = 0; i < N; i++) {
                // 해당 행의 맨 왼쪽칸이 비어있음
                if (board[i][0] == 0) {
                    continue;
                }
                // 해당 행의 맨 왼쪽칸에 블록이 있음
                else {
                    float power = 1;
                    int myCells = 1;
                    
                    // 해당 행의 두번째 칸부터 블록이 있는지 쭉 검사
                    for (int j = 1; j < N; j++) {
                        // 이 칸에 블록이 없다면
                        if (board[i][j] == 0) {
                            // 다음칸에서는 내 하강력의 1.9배가 됨.
                            power *= 1.9;
                            board[i][j - myCells] = 0;
                            board[i][j] = 1;
                            continue;
                        }
                        else {
                            // 다음칸에도 블록이 있는지 끝날때까지 검사
                            int cells = 1;
                            for (int k = j+1; k<N; k++) {
                                // 아직 한 덩어리
                                if (board[i][k] == 1) {
                                    cells++;
                                }
                                // 블록 끝!
                                else {
                                    break;
                                }
                            }
                            
                            // 다 검사했으면 현재 내 하강력과 저항력(칸수)를 비교
                            // 다음 턴에 하강!
                            if (power > cells) {
                                power += cells;
                                j += cells - 1;  
                                myCells = myCells + cells;
                            }
                            // 그 자리에서 유지 -> 다음 열 탐색
                            else {
                                continue L;
                            }
                        }
                    }
                }
            }
            
            // 답
            int ans1 = 0;
            int ans2 = 0;
            
            for (int j = 0; j < N; j++) {
                if (board[N-1][j] == 1) {
                    ans1++;
                }
            }
            
            for (int i = 0; i < N; i++) {
                if (board[i][N-1] == 1) {
                    ans2++;
                }
            }
          
            System.out.println("#" + test_case + " " + ans1 + " " + ans2);
        }
    }
}
