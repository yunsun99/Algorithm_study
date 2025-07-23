import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static long B;
    static int[][] A;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        B = Long.parseLong(st.nextToken());

        A = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                A[i][j] = Integer.parseInt(st.nextToken()) % 1000;
            }
        }

        int[][] result = matrixPower(A, B);

        // 출력
        StringBuilder sb = new StringBuilder();
        for (int[] row : result) {
            for (int num : row) {
                sb.append(num % 1000).append(' ');
            }
            sb.append('\n');
        }

        System.out.print(sb);
    }

    // 행렬 제곱
    static int[][] matrixPower(int[][] matrix, long exp) {
        if (exp == 1) return matrix;

        int[][] half = matrixPower(matrix, exp / 2);
        int[][] result = multiply(half, half);

        if (exp % 2 == 1) {
            result = multiply(result, A);
        }

        return result;
    }

    // 행렬 곱셈
    static int[][] multiply(int[][] m1, int[][] m2) {
        int[][] result = new int[N][N];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                int sum = 0;
                for (int k = 0; k < N; k++) {
                    sum += (m1[i][k] * m2[k][j]) % 1000;
                }
                result[i][j] = sum % 1000;
            }
        }

        return result;
    }
}
