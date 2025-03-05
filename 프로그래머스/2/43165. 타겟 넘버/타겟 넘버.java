class Solution {
    static int[] staticNumbers;
    static int targetNumber;
    static int answer;
    
    public int solution(int[] numbers, int target) {
        staticNumbers = numbers;
        targetNumber = target;
        
        // dfs일 듯
        // 방문 배열이 필요가 없나?
        // 어차피 순서가 정해져 있어..
        dfs(0, 0);
        
        
        return answer;
    }
    
    static void dfs(int idx, int sum) {
        // basis part
        if (idx == staticNumbers.length) {
            if (sum == targetNumber) answer++;
            return;
        }
        
        // inductive part
        dfs(idx + 1, sum - staticNumbers[idx]);
        dfs(idx + 1, sum + staticNumbers[idx]);
    }
}