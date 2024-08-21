import java.util.Scanner;

class Tile {
    int value;
    boolean isMerged;
    Tile (int value, boolean isMerged) {
        this.value = value;
        this.isMerged = isMerged;
    }

}

public class Solution {

	public static void main(String[] args) {
	    Scanner sc = new Scanner(System.in);
	    int T = sc.nextInt();
	    
	    for (int test_case = 1; test_case <= T; test_case++) {
	        // 입력 받기
	        int N = sc.nextInt();
	        String s = sc.next();
	        
	        Tile[][] board = new Tile[N][N];
	        
	        for (int i = 0 ; i < N; i++) {
	            for (int j = 0; j < N; j++) {
	                board[i][j] = new Tile(sc.nextInt(), false);
	            }
	        }
	        
	        // 방향을 기준으로 분기처리
	        switch(s) {
	        case "left":            
	        	// 가장 위쪽 열부터 한 row씩 탐색
	            for (int row = 0; row < N; row++) {
	                // 왼쪽 칸부터 오른쪽으로 가면서 각 칸의 타일을 왼쪽으로 옮기기
	                for (int col = 1; col < N; col++) {
	                	
	                	int currentCol = col;
	                	
	                    // 칸에 탐색할 타일이 없으면 오른쪽 칸 탐색하기
	                    if (board[row][currentCol].value == 0) {
	                        continue;
	                    }
	                    
	                    // 해당 칸에 타일이 존재함
	                    
	                    // 바로 왼쪽 칸이 비어있으면 비어있지 않은 칸이 나올 때까지 계속 타일을 왼쪽으로 보냄, 배열의 인덱스가 보드를 벗어나지 않도록 조심해야 함
	                    while ((currentCol - 1) >= 0 && board[row][currentCol - 1].value == 0) {
	                        board[row][currentCol - 1].value = board[row][currentCol].value;
	                        board[row][currentCol].value = 0;
	                        currentCol = currentCol - 1;
	                    }
	                    
	                    // 바로 왼쪽 칸에 타일이 있다면
	                    // 같은 숫자라면 합치고 원래 칸을 비움, 합칠 타일이 합쳐진 적이 없는 타일이어야 함, 배열의 인덱스가 보드를 벗어나지 않도록 조심해야 함
	                    if ((currentCol - 1) >= 0 && board[row][currentCol].value == board[row][currentCol - 1].value && !board[row][currentCol - 1].isMerged) {
	                    	board[row][currentCol - 1].value = 2 * board[row][currentCol - 1].value;
	                        board[row][currentCol].value = 0;
	                    	board[row][currentCol - 1].isMerged = true;
	                        currentCol = currentCol - 1;
	                    }
	                    
	                    // 합친 타일을 최대한 왼쪽으로 옮기기
	                    while ((currentCol - 1) >= 0 && board[row][currentCol - 1].value == 0) {
	                        board[row][currentCol - 1].value = board[row][currentCol].value;
	                        board[row][currentCol].value = 0;
	                        currentCol = currentCol - 1;
	                    }
	                }
	            }
	            break;
	        case "right":
	            // 가장 위쪽 열부터 한 row씩 탐색
	            for (int row = 0; row < N; row++) {
	                // 오른쪽 칸부터 왼쪽으로 가면서 각 칸의 타일을 오른쪽으로 옮기기
	                for (int col = N-2; col >= 0; col--) {
	                	
	                	int currentCol = col;
	                	
	                    // 칸에 탐색할 타일이 없으면 왼쪽 칸 탐색하기
	                    if (board[row][currentCol].value == 0) {
	                        continue;
	                    }
	                    
	                    // 해당 칸에 타일이 존재함
	                    
	                    // 바로 오른쪽 칸이 비어있으면 비어있지 않은 칸이 나올 때까지 계속 타일을 오른쪽으로 보냄, 배열의 인덱스가 보드를 벗어나지 않도록 조심해야 함
	                    while ((currentCol + 1) < N && board[row][currentCol + 1].value == 0) {
	                        board[row][currentCol + 1].value = board[row][currentCol].value;
	                        board[row][currentCol].value = 0;
	                        currentCol = currentCol + 1;
	                    }
	                    
	                    // 바로 오른쪽 칸에 타일이 있다면
	                    // 같은 숫자라면 합치고 원래 칸을 비움, 합칠 타일이 합쳐진 적이 없는 타일이어야 함, 배열의 인덱스가 보드를 벗어나지 않도록 조심해야 함
	                    if ((currentCol + 1) < N && board[row][currentCol].value == board[row][currentCol + 1].value && !board[row][currentCol + 1].isMerged) {
	                    	board[row][currentCol + 1].value = 2 * board[row][currentCol + 1].value;
	                        board[row][currentCol].value = 0;
	                    	board[row][currentCol + 1].isMerged = true;
	                        currentCol = currentCol + 1;
	                    }
	                    
	                    // 합친 타일을 최대한 오른쪽으로 보내기
	                    while ((currentCol + 1) < N && board[row][currentCol + 1].value == 0) {
	                        board[row][currentCol + 1].value = board[row][currentCol].value;
	                        board[row][currentCol].value = 0;
	                        currentCol = currentCol + 1;
	                    }
	                }
	            }
	            break;
	        case "up":
	            // 가장 왼쪽 열부터 한 column씩 탐색
	            for (int col = 0; col < N; col++) {
	                // 위 칸부터 아래로 내려가면서 각 칸의 타일을 위로 옮기기
	                for (int row = 1; row < N; row++) {
	                	
	                	int currentRow = row;
	                	
	                    // 칸에 탐색할 타일이 없으면 아래 칸 탐색하기
	                    if (board[currentRow][col].value == 0) {
	                        continue;
	                    }
	                    
	                    // 해당 칸에 타일이 존재함
	                    
	                    // 바로 위 칸이 비어있으면 비어있지 않은 칸이 나올 때까지 계속 타일을 올림, 배열의 인덱스가 보드를 벗어나지 않도록 조심해야 함
	                    while ((currentRow - 1) >= 0 && board[currentRow - 1][col].value == 0) {
	                        board[currentRow - 1][col].value = board[currentRow][col].value;
	                        board[currentRow][col].value = 0;
	                        currentRow = currentRow - 1;
	                    }
	                    
	                    // 바로 위 칸에 타일이 있다면
	                    // 같은 숫자라면 합치고 원래 칸을 비움, 합칠 타일이 합쳐진 적이 없는 타일이어야 함, 배열의 인덱스가 보드를 벗어나지 않도록 조심해야 함
	                    if ((currentRow - 1) >= 0 && board[currentRow][col].value == board[currentRow - 1][col].value && !board[currentRow - 1][col].isMerged) {
	                    	board[currentRow - 1][col].value = 2 * board[currentRow - 1][col].value;
	                        board[currentRow][col].value = 0;
	                    	board[currentRow - 1][col].isMerged = true;
	                        currentRow = currentRow - 1;
	                    }
	                    
	                    // 합친 타일을 최대한 내리기
	                    while ((currentRow - 1) >= 0 && board[currentRow - 1][col].value == 0) {
	                        board[currentRow - 1][col].value = board[currentRow][col].value;
	                        board[currentRow][col].value = 0;
	                        currentRow = currentRow - 1;
	                    }
	                }
	            }
	            break;
	        case "down":
	            // 가장 왼쪽 열부터 한 column씩 탐색
	            for (int col = 0; col < N; col++) {
	                // 아래 칸부터 위로 올라가면서 각 칸의 타일을 아래로 옮기기
	                for (int row = N-2; row >= 0; row--) {
	                	
	                	int currentRow = row;
	                	
	                    // 칸에 탐색할 타일이 없으면 위 칸 탐색하기
	                    if (board[currentRow][col].value == 0) {
	                        continue;
	                    }
	                    
	                    // 해당 칸에 타일이 존재함
	                    
	                    // 바로 아래 칸이 비어있으면 비어있지 않은 칸이 나올 때까지 계속 타일을 내림, 배열의 인덱스가 보드를 벗어나지 않도록 조심해야 함
	                    while ((currentRow + 1) < N && board[currentRow + 1][col].value == 0) {
	                        board[currentRow + 1][col].value = board[currentRow][col].value;
	                        board[currentRow][col].value = 0;
	                        currentRow = currentRow + 1;
	                    }
	                    
	                    // 바로 아래 칸에 타일이 있다면
	                    // 같은 숫자라면 합치고 원래 칸을 비움, 합칠 타일이 합쳐진 적이 없는 타일이어야 함, 배열의 인덱스가 보드를 벗어나지 않도록 조심해야 함
	                    if ((currentRow + 1) < N && board[currentRow][col].value == board[currentRow + 1][col].value && !board[currentRow + 1][col].isMerged) {
	                    	board[currentRow + 1][col].value = 2 * board[currentRow + 1][col].value;
	                        board[currentRow][col].value = 0;
	                    	board[currentRow + 1][col].isMerged = true;
	                        currentRow = currentRow + 1;
	                    }
	                    
	                    // 합친 타일을 최대한 내리기
	                    while ((currentRow + 1) < N && board[currentRow + 1][col].value == 0) {
	                        board[currentRow + 1][col].value = board[currentRow][col].value;
	                        board[currentRow][col].value = 0;
	                        currentRow = currentRow + 1;
	                    }
	                }
	            }
	            break;
	        }
	        
	        System.out.println("#" + test_case);
	        for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
			        System.out.print(board[i][j].value + " ");
				}
				System.out.println();
			}
	    }
	    sc.close();
	}
}