import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

// 지도 + 최단경로 -> BFS!
// K까지 저장할 삼차원 방문 배열 필요

class Monkey {
   int r, c, jumpCnt, horseCnt;
   
   Monkey(int r, int c, int jumpCnt, int horseCnt) {
      this.r = r;
      this.c = c;
      this.jumpCnt = jumpCnt;
      this.horseCnt = horseCnt;
   }

   @Override
   public String toString() {
      return "Monkey [r=" + r + ", c=" + c + ", jumpCnt=" + jumpCnt + ", horseCnt=" + horseCnt + "]";
   }
   
}

public class Main {
   static int[][] map;
   static boolean[][][] visited;   // 세번째 요소는 말처럼 뛴 점프의 수(= horseCnt)
   static int K;
   static int row, col;
   static int answer;
   

   public static void main(String[] args) throws IOException {
      BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
      K = Integer.parseInt(bf.readLine());
      
      String s = bf.readLine();
      StringTokenizer st = new StringTokenizer(s);
      col = Integer.parseInt(st.nextToken());   // 문제에서 W
      row = Integer.parseInt(st.nextToken());   // 문제에서 H
      
      map = new int[row][col];
      visited = new boolean[row][col][K+1];
      
      // map 입력받기
      for (int i = 0; i < row; i++) {
         s = bf.readLine();
         st = new StringTokenizer(s);
         for (int j = 0; j < col; j++) {
            map[i][j] = Integer.parseInt(st.nextToken());
         }
      }
      
      bfs(new Monkey(0, 0, 0, 0));
      System.out.println(answer);

   }
   
   public static void bfs(Monkey startMonkey) {
      int[] dr = {-1, 1, 0, 0, -2, -2, -1, -1, 1, 1, 2, 2};
      int[] dc = {0, 0, -1, 1, -1, 1, -2, 2, -2, 2, -1, 1};
      
      Queue<Monkey> q = new ArrayDeque<>();
      
      // 초기 노드 추가
      q.offer(startMonkey);
      visited[0][0][0] = true;
      
      while(!q.isEmpty()) {
         Monkey m = q.poll();
         
         // basis part
         if (m.r == row - 1 && m.c == col - 1) {
            answer = m.jumpCnt;
            return;
         }
         
         // 이미 K번 만큼 말점프를 뛰었다면
         if (m.horseCnt == K) {
            // 더 이상 말처럼 뛸 수 없으므로 무조건 사방탐색
            for (int i=0; i<4; i++) {
               int nr = m.r + dr[i];
               int nc = m.c + dc[i];
               
               // 다음에 이동할 칸이 지도를 벗어나지 않고 장애물이 없으면
               if (nr >=0 && nr < row && nc >= 0 && nc < col && map[nr][nc] == 0 && visited[nr][nc][K] == false) {
                  q.offer(new Monkey(nr, nc, m.jumpCnt+1, K));
                  visited[nr][nc][K] = true;
               }
            }
         }
         else {
            // 아직 말점프를 뛸 수 있는 상태
            for (int i=0; i<12; i++) {
               int nr = m.r + dr[i];
               int nc = m.c + dc[i];
               
               // 다음에 이동할 칸이 지도를 벗어나지 않고 장애물이 없으면
               if (nr >=0 && nr < row && nc >= 0 && nc < col && map[nr][nc] == 0) {
            	  // 원숭이 점프(4방 탐색) 하는 경우
                  if (i < 4 && visited[nr][nc][m.horseCnt] == false) {
                     q.offer(new Monkey(nr, nc, m.jumpCnt+1, m.horseCnt));
                     visited[nr][nc][m.horseCnt] = true;
                  }
                  // 말 점프(8방 탐색) 하는 경우
                  else if (visited[nr][nc][m.horseCnt+1] == false) {
                     q.offer(new Monkey(nr, nc, m.jumpCnt+1, m.horseCnt+1));
                     visited[nr][nc][m.horseCnt + 1] = true;
                  }
               }
            }
         }
      }
      answer = -1;
   }
}