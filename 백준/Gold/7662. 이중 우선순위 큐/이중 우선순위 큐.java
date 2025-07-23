import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        while (T-- > 0) {
            int k = Integer.parseInt(br.readLine());
            TreeMap<Integer, Integer> map = new TreeMap<>();

            for (int i = 0; i < k; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                String cmd = st.nextToken();
                int num = Integer.parseInt(st.nextToken());

                if (cmd.equals("I")) {
                    // 삽입
                    map.put(num, map.getOrDefault(num, 0) + 1);
                } else {
                    // 삭제
                    if (map.isEmpty()) continue;

                    int target = (num == 1) ? map.lastKey() : map.firstKey();
                    int count = map.get(target);

                    if (count == 1) map.remove(target);
                    else map.put(target, count - 1);
                }
            }

            // 결과 출력
            if (map.isEmpty()) {
                System.out.println("EMPTY");
            } else {
                System.out.println(map.lastKey() + " " + map.firstKey());
            }
        }
    }
}
