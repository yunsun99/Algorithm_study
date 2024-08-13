import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

   static class Node {
       int row, col, isBroken, cnt;
       Node (int row, int col, int isBroken, int cnt) {
           this.row = row;
           this.col = col;
           this.isBroken = isBroken;
           this.cnt = cnt;
       }
   }
   
   static int[][] map;
   static BufferedReader bf;
   static StringTokenizer st;
   static String s;
   static String[] sArr;
   static int N, M;
   static Queue<Node> q;
   static boolean[][][] visited;
   
   static int[] rowChange = {0, 0, -1, 1};
   static int[] colChange = {-1, 1, 0, 0};
   
   public static void main(String[] args) throws IOException {
      bf = new BufferedReader(new InputStreamReader(System.in));
      s = bf.readLine();
      st = new StringTokenizer(s);
      
      // map의 크기
      N = Integer.parseInt(st.nextToken());
      M = Integer.parseInt(st.nextToken());
      
      // (1, 1) 부터 시작하기 때문에 한 칸씩 늘려줌
      map = new int[N+1][M+1];
      visited = new boolean[N+1][M+1][2];   
      // map 입력받기
      for (int i=1; i<=N; i++) {
          sArr = bf.readLine().split("");
          for (int j=1; j<=M; j++) {
              map[i][j] = Integer.parseInt(sArr[j-1]);
          }
      }
      
      int answer = bfs(new Node(1, 1, 0, 0));
      
      System.out.println(answer);
   }
   
   public static int bfs(Node startNode) {
      
      // 큐 생성 및 시작노드 큐에 추가
      q = new ArrayDeque<>();
      q.offer(startNode);
      visited[startNode.row][startNode.col][startNode.isBroken] = true;
      
      while (!q.isEmpty()) {
         
         Node node = q.poll();

         // basis part
         if (node.row == N && node.col == M) {
            return node.cnt + 1;
         }
         
         for (int j=0; j<4; j++) {
            int newRow = node.row + rowChange[j];
            int newCol = node.col + colChange[j];
            
            // 가고 싶은 셀의 범위가 맵의 범위를 벗어나지 않는지 확인
            if (newRow >= 1 && newRow <= N && newCol >= 1 && newCol <= M) {
               // 아직 내가 벽을 부순적이 없는데
               if (node.isBroken == 0) {
                  // 다음 가고 싶은 칸도 벽이 아니고, 해당 칸에 벽 안 부순 애가 간 적이 없다면
                  if (map[newRow][newCol] == 0 && visited[newRow][newCol][0] == false) {
                     q.offer(new Node(newRow, newCol, 0, node.cnt + 1));
                     visited[newRow][newCol][0]= true;
                  }
                  // 다음 가고 싶은 칸이 벽이고, 해당 칸에 벽 부순 애가 간 적이 없다면
                  else if (map[newRow][newCol] == 1 && visited[newRow][newCol][1] == false) {
                     q.offer(new Node(newRow, newCol, 1, node.cnt + 1));
                     visited[newRow][newCol][1]= true;
                  }
               }
               // 내가 이미 벽을 부순적이 있으면
               else if (node.isBroken == 1) {
                  // 이제 벽이 아닌 칸만 갈 수 있고, 해당 칸에 벽 부순 애가 간 적이 없다면
                  if (map[newRow][newCol] == 0 && visited[newRow][newCol][1] == false) {
                     q.offer(new Node(newRow, newCol, 1, node.cnt + 1));
                     visited[newRow][newCol][1]= true;
                  }
               }
            }
         }
          
      }
      return -1;
   }
}