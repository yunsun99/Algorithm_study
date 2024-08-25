import java.util.Scanner;

public class Solution {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        
        for (int test_case = 1; test_case <= T; test_case++) {
            // 입력 받기
            int H = sc.nextInt();
            int W = sc.nextInt();

            char[][] map = new char[H][W];
            
            for (int i = 0; i < H; i++) {
                String line = sc.next();
                for (int j = 0; j < W; j++) {
                    map[i][j] = line.charAt(j);
                }
            }
            
            int N = sc.nextInt();
            
            String inputs = sc.next();
            
            // 본문 시작 아오 입력 길어
            int tankRow = Integer.MAX_VALUE; 
            int tankCol = Integer.MAX_VALUE;;
            int tankDir = Integer.MAX_VALUE;
            
            // 우선....전차부터 찾자
            L: for (int i = 0; i < H; i++) {
                for (int j = 0; j < W; j++) {
                    if (map[i][j] == '^') {
                        tankRow = i;
                        tankCol = j;
                        tankDir = 1;
                        break L;
                    }
                    else if (map[i][j] == 'v') {
                        tankRow = i;
                        tankCol = j;
                        tankDir = 2;
                        break L;
                    }
                    else if (map[i][j] == '<') {
                        tankRow = i;
                        tankCol = j;
                        tankDir = 3;
                        break L;
                    }
                    else if (map[i][j] == '>') {
                        tankRow = i;
                        tankCol = j;
                        tankDir = 4;
                        break L;
                    }
                }
            }
            
            // 전차 찾았으면 input 한 개씩 처리해보자...
            for (int i = 0; i < N; i++) {
                char input = inputs.charAt(i);
                switch(input) {
                // Shoot!
                case 'S':
                	int shellRow = tankRow;
                	int shellCol = tankCol;
                    switch(tankDir) {
                    // 위로 슛
                    case 1:
                    	// 평지거나 물이면 계속 날아감
                        while ( shellRow-1 >= 0 && (map[shellRow-1][shellCol] == '.' || map[shellRow-1][shellCol] == '-') ) {
                        	shellRow -= 1;
                        }
                        // 벽돌로 만들어진 벽이면 벽이 파괴되어 평지가 됨 
                        if ( shellRow-1 >= 0 && map[shellRow-1][shellCol] == '*' ) {
                        	map[shellRow-1][shellCol] = '.';
                        }
                        // 강철로 만들어진 벽이거나 지도 밖을 나가면 무시
                        break;
                    // 아래로 슛
                    case 2:
                    	// 평지거나 물이면 계속 날아감
                        while ( shellRow+1 < H && (map[shellRow+1][shellCol] == '.' || map[shellRow+1][shellCol] == '-') ) {
                        	shellRow += 1;
                        }
                        // 벽돌로 만들어진 벽이면 벽이 파괴되어 평지가 됨 
                        if ( shellRow+1 < H && map[shellRow+1][shellCol] == '*' ) {
                        	map[shellRow+1][shellCol] = '.';
                        }
                        // 강철로 만들어진 벽이거나 지도 밖을 나가면 무시
                        break;
                    // 왼쪽으로 슛
                    case 3:
                    	// 평지거나 물이면 계속 날아감
                        while ( shellCol-1 >= 0 && (map[shellRow][shellCol-1] == '.' || map[shellRow][shellCol-1] == '-') ) {
                        	shellCol -= 1;
                        }
                        // 벽돌로 만들어진 벽이면 벽이 파괴되어 평지가 됨 
                        if ( shellCol-1 >= 0 && map[shellRow][shellCol-1] == '*' ) {
                        	map[shellRow][shellCol-1] = '.';
                        }
                        // 강철로 만들어진 벽이거나 지도 밖을 나가면 무시
                        break;
                    // 오른쪽으로 슛
                    case 4:
                    	// 평지거나 물이면 계속 날아감
                        while ( shellCol+1 < W && (map[shellRow][shellCol+1] == '.' || map[shellRow][shellCol+1] == '-') ) {
                        	shellCol += 1;
                        }
                        // 벽돌로 만들어진 벽이면 벽이 파괴되어 평지가 됨 
                        if ( shellCol+1 < W && map[shellRow][shellCol+1] == '*' ) {
                        	map[shellRow][shellCol+1] = '.';
                        }
                        // 강철로 만들어진 벽이거나 지도 밖을 나가면 무시
                        break;
                    }
                    break;
                case 'U':
                	// 전차가 바라보는 방향을 위쪽으로 바꿈
                	tankDir = 1;
                	// 한 칸 위의 칸이 평지라면 그 칸으로 이동함
                    if ( tankRow-1 >= 0 && map[tankRow-1][tankCol] == '.' ) {
                    	map[tankRow][tankCol] = '.';
                    	map[tankRow-1][tankCol] = '^';
                    	tankRow -= 1;
                    }
                    // 한 칸 위 칸이 평지가 아니라면 제자리에서 방향만 바꿈
                    else {
                    	map[tankRow][tankCol] = '^';
                    }
                    break;
                case 'D':
                	// 전차가 바라보는 방향을 아래쪽으로 바꿈
                	tankDir = 2;
                	// 한 칸 아래 칸이 평지라면 그 칸으로 이동함
                    if ( tankRow+1 < H && map[tankRow+1][tankCol] == '.' ) {
                    	map[tankRow][tankCol] = '.';
                    	map[tankRow+1][tankCol] = 'v';
                    	tankRow += 1;
                    }
                    // 한 칸 아래 칸이 평지가 아니라면 제자리에서 방향만 바꿈
                    else {
                    	map[tankRow][tankCol] = 'v';
                    }
                    break;
                case 'L':
                	// 전차가 바라보는 방향을 왼쪽으로 바꿈
                	tankDir = 3;
                	// 한 칸 왼쪽 칸이 평지라면 그 칸으로 이동함
                    if ( tankCol-1 >= 0 && map[tankRow][tankCol-1] == '.' ) {
                    	map[tankRow][tankCol] = '.';
                    	map[tankRow][tankCol-1] = '<';
                    	tankCol -= 1;
                    }
                    // 한 칸 왼쪽 칸이 평지가 아니라면 제자리에서 방향만 바꿈
                    else {
                    	map[tankRow][tankCol] = '<';
                    }
                    break;
                case 'R':
                	// 전차가 바라보는 방향을 오른쪽으로 바꿈
                	tankDir = 4;
                	// 한 칸 오른쪽 칸이 평지라면 그 칸으로 이동함
                    if ( tankCol+1 < W && map[tankRow][tankCol+1] == '.' ) {
                    	map[tankRow][tankCol] = '.';
                    	map[tankRow][tankCol+1] = '>';
                    	tankCol += 1;
                    }
                    // 한 칸 오른쪽 칸이 평지가 아니라면 제자리에서 방향만 바꿈
                    else {
                    	map[tankRow][tankCol] = '>';
                    }
                    break;
                }
            }
            
            System.out.print("#" + test_case + " ");
            for (int i = 0; i < H; i++) {
				for (int j = 0; j < W; j++) {
					System.out.print(map[i][j]);
				}
				System.out.println();
			}
        }
    }

}