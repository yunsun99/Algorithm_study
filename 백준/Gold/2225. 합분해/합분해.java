import java.io.*;
import java.util.*;

public class Main {
    static final int MOD = 1_000_000_000;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()); // 만들 숫자
        int K = Integer.parseInt(st.nextToken()); // 더하는 개수

        // 1차원 DP: dp[n] = 현재 "k개"로 n을 만드는 방법 수
        // 기본행(k=1)은 dp[n]=1 (n을 한 개로 만드는 방법은 n 자체 하나!)
        int[] dp = new int[N + 1];
        Arrays.fill(dp, 1);

        // k=2..K로 확장!
        for (int k = 2; k <= K; k++) {
            for (int n = 1; n <= N; n++) {
                dp[n] = (dp[n] + dp[n - 1]) % MOD;
            }
        }

        System.out.println(dp[N] % MOD);
    }
}
