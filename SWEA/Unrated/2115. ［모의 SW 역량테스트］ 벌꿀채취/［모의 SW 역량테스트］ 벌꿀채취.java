import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

class Worker{
	int startRow;
	int startCol;
	int maxProfit;
	
	Worker (int startRow, int startCol, int maxProfit) {
		this.startRow = startRow;
		this.startCol = startCol;
		this.maxProfit = maxProfit;
	}
}

public class Solution {

	static int N, M, C;
    static int[][] map;
    static int maxProfit;

	public static void main(String[] args) {
	    Scanner sc = new Scanner(System.in);
	    int T = sc.nextInt();
	    
	    for (int test_case = 1; test_case <= T; test_case++) {
	        N = sc.nextInt();
	        M = sc.nextInt();
	        C = sc.nextInt();
	        
	        map = new int[N][N];
	        int[] worker = new int[M];
	        
	        // 벌통에 있는 꿀의 양의 정보 입력 받기
	        for (int i = 0; i < N; i++) {
	            for (int j = 0; j < N; j++) {
	                map[i][j] = sc.nextInt();
	            }
	        }
	        
	        ArrayList<Worker> workerList = new ArrayList<Worker>();
	        
	        // 일꾼이 채집할 벌꿀을 선택함 (이 부분이 어려웠음ㅠㅠㅠ 일꾼 2명을 어떻게 구분하지..?)
	        // 나중에 베스트 2명 뽑고 둘이 겹치는지 안겹치는지 검사하자..
	        for (int i = 0; i < N; i++) {
	            for (int j = 0; j < N; j++) {
	                // 아직 일꾼이 채집할 벌꿀을 선택하지 않았고 열의 수가 부족하다면 다음 행을 탐색한다
	                if (j + M > N) {
	                    break;
	                }
	                
	                // 일꾼이 채집할 벌꿀을 배열에 담는다
	                for (int k = 0; k < M; k++) {
	                    worker[k] = map[i][j+k];
	                }
	                Arrays.sort(worker);
	                
	                // 선택한 벌꿀에서 얻을 수 있는 최대 이익을 구한다.
	                maxProfit = 0;
	                getMaxProfit(worker, 0, new boolean[M]);
	                workerList.add(new Worker(i, j, maxProfit));
	            }
	        }
	        
	        workerList.sort(new Comparator<Worker>() {
				@Override
				public int compare(Worker o1, Worker o2) {
					return o2.maxProfit - o1.maxProfit;
				}
			});
	        
	        int worker1 = workerList.get(0).maxProfit;
	        int worker2 = 0;
	        for (int i = 1; i < workerList.size(); i++) {
				if (workerList.get(0).startRow != workerList.get(i).startRow) {
					worker2 = workerList.get(i).maxProfit;
					break;
				}
			}
	        int answer = worker1 + worker2;
	        
	        System.out.println("#" + test_case + " " + answer);
	    }
	    
	    sc.close();
	}
	
	private static void getMaxProfit(int[] worker, int idx, boolean[] sel) {
	    // 부분집합을 구해서 다 돌려보려고 하는데 시간초과나 메모리초과 날 것 같아서 무섭다ㅠ
	    if (idx == M) {
	        // ArrayList에 부분집합 구현
	        ArrayList<Integer> selected = new ArrayList<>();
	        int sum = 0;
	        int profit = 0;
	        
	        for (int i = 0; i < M; i++) {
	            if (sel[i] == true) {
	                selected.add(worker[i]);
	                sum += worker[i];
	            }
	        }
	        
	        if (sum > C) {
	            return;
	        }
	        
	        for (int i = 0; i < selected.size(); i++) {
	            profit += selected.get(i) * selected.get(i);
	        }
	        
	        maxProfit = profit > maxProfit ? profit : maxProfit;
	        return;
	    }
	    
	    sel[idx] = true;
	    getMaxProfit(worker, idx+1, sel);
	    
	    sel[idx] = false;
	    getMaxProfit(worker, idx+1, sel);
	    
	}

}
