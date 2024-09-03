import java.util.Scanner;

//주어진 예시만 보고 사방탐색 + 팔방탐색인줄 알았다...ㅠㅠ
//최대한 많은 집을 포함시켜야 하니, K를 최대부터 점점 줄여가며 탐색하면 좋겠어
//대신, 모든 칸(N^2)에 대해서 K를 일일히 탐색하면 너무 비효율적일 것 같아.
//그럼 처음부터 도시에 있는 모든 집을 다 포함한다고 해도 손해가 나면 탐색하지 말자!
//입력받을 때 도시에 있는 집 개수를 세야겠다.

public class Solution {

 public static void main(String[] args) {
     Scanner sc = new Scanner(System.in);
     int T = sc.nextInt();
     
     for (int test_case = 1; test_case <= T; test_case++) {
         int N = sc.nextInt();
         int M = sc.nextInt();
         int[][] map = new int[N][N];
//         int[] dr = {-1, 1, 0, 0, -1, -1, 1, 1};  // 앞쪽은 사방탐색, 뒤쪽은 팔방탐색
//         int[] dc = {0, 0, -1, 1, -1, 1, -1, 1};
         int homeTotalNum = 0;
         int maxProfit;
         int maxHomeNum = 0;
         
         // 도시 정보 입력 받기
         for (int i = 0; i < N; i++) {
             for (int j = 0; j < N; j++) {
                 map[i][j] = sc.nextInt();
                 if (map[i][j] == 1) homeTotalNum++;
             }
         }
         
         // 도시에 있는 모든 집에 서비스를 제공할 때의 수익
         maxProfit = homeTotalNum * M;
         
         for (int K = N+1; K > 0; K--) {
             int cost = K*K + (K-1)*(K-1);   // K영역을 서비스하는 운영 비용
             if (cost > maxProfit) continue;   // 모든 집을 다 포함해도 손해가 나면 서비스 영역을 줄임
             
             int profit = 0;
             
             // K영역 안에 포함된 집의 수를 모든 칸에 대하여 탐색
             for (int i = 0; i < N; i++) {
                 for (int j = 0; j < N; j++) {
                     int homeNum = 0;
                     
                     // 중심 칸 탐색
                     if (map[i][j] == 1) homeNum++;
                     
                     // r과 c의 절댓값의 합이 k인 주변 칸 탐색
                     for (int k = 1; k <= K-1; k++) {
                    	 // 행이 그대로고 열만 k만큼 이동한 경우
                    	 if (j+k >= 0 && j+k < N && map[i][j+k] == 1) homeNum++; 
                    	 if (j-k >= 0 && j-k < N && map[i][j-k] == 1) homeNum++; 
                    	 // 열이 그대로고 행만 k만큼 이동한 경우
                    	 if (i+k >= 0 && i+k < N && map[i+k][j] == 1) homeNum++; 
                    	 if (i-k >= 0 && i-k < N && map[i-k][j] == 1) homeNum++; 
                    	 
                    	 // 행, 열 둘다 이동한 경우
                         // r, c 둘 다 양수인 좌표만 계산해서 부호 바꿔서 4개의 사분면 다 적용하자
                    	 for (int r = 1; r < k; r++) {
                    		 int c = k - r;
                    		 
                             if (i+r >= 0 && i+r < N && j+c >= 0 && j+c < N && map[i+r][j+c] == 1) homeNum++;
                             if (i-r >= 0 && i-r < N && j+c >= 0 && j+c < N && map[i-r][j+c] == 1) homeNum++;
                             if (i+r >= 0 && i+r < N && j-c >= 0 && j-c < N && map[i+r][j-c] == 1) homeNum++;
                             if (i-r >= 0 && i-r < N && j-c >= 0 && j-c < N && map[i-r][j-c] == 1) homeNum++;

                    	 }
                     }
                     
                     // 손해가 나면 다음 구역을 탐색함
                     profit = homeNum * M;
                     if (cost > profit) continue;
                     
                     // 손해가 나지 않는다면 최대한 많은 집을 포함하는지 확인
                     maxHomeNum = homeNum > maxHomeNum ? homeNum : maxHomeNum;
                 }
             }
             if (maxHomeNum != 0) {
            	 break;
             }
         }
         
         System.out.println("#" + test_case + " " + maxHomeNum);
     }
     sc.close();
 }

}