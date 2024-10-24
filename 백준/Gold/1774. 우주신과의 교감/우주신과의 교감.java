import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Road implements Comparable<Road> {
    int start;
    int end;
    double distance;

    public Road(int start, int end, double distance) {
        this.start = start;
        this.end = end;
        this.distance = distance;
    }

    @Override
    public int compareTo(Road r) {
        return Double.compare(this.distance, r.distance);
    }
}

public class Main {

    static int[] root;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());

        int numberOfGods = Integer.parseInt(tokenizer.nextToken()); // 우주신의 수
        int numberOfPaths = Integer.parseInt(tokenizer.nextToken()); // 이미 연결된 통로 수

        int[][] godsCoordinates = new int[numberOfGods + 1][2]; // 우주신들의 좌표

        for (int i = 1; i <= numberOfGods; i++) {
            tokenizer = new StringTokenizer(reader.readLine());
            godsCoordinates[i][0] = Integer.parseInt(tokenizer.nextToken()); // x좌표
            godsCoordinates[i][1] = Integer.parseInt(tokenizer.nextToken()); // y좌표
        }

        root = new int[numberOfGods + 1];

        // 초기화: 각 노드의 부모를 자기 자신으로 설정
        for (int i = 1; i <= numberOfGods; i++) {
            root[i] = i;
        }

        // 이미 연결된 통로 처리
        for (int i = 0; i < numberOfPaths; i++) {
            tokenizer = new StringTokenizer(reader.readLine());
            int god1 = Integer.parseInt(tokenizer.nextToken());
            int god2 = Integer.parseInt(tokenizer.nextToken());

            unite(god1, god2); // 이미 연결된 우주신들끼리 합침
        }

        PriorityQueue<Road> roadsQueue = new PriorityQueue<>();

        // 모든 우주신 사이의 가능한 거리 계산 후 큐에 추가
        for (int i = 1; i < numberOfGods; i++) {
            for (int j = i + 1; j <= numberOfGods; j++) {
                int x1 = godsCoordinates[i][0];
                int y1 = godsCoordinates[i][1];
                int x2 = godsCoordinates[j][0];
                int y2 = godsCoordinates[j][1];

                double distance = Math.sqrt(Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2));

                roadsQueue.add(new Road(i, j, distance));
            }
        }

        double totalWeight = 0;

        // 크루스칼 알고리즘 적용
        while (!roadsQueue.isEmpty()) {
            Road road = roadsQueue.poll();

            // 두 우주신이 같은 그룹에 속하지 않으면 연결
            if (unite(road.start, road.end)) {
                totalWeight += road.distance; // 거리(가중치)를 결과에 더함
            }
        }

        System.out.printf("%.2f%n", totalWeight);
    }

    // 최상위 부모(루트)를 찾음
    public static int findRoot(int node) {
        if (root[node] == node) {
            return node;
        } else {
            return root[node] = findRoot(root[node]); // 경로 압축
        }
    }

    // 두 노드를 연결 (합침)
    public static boolean unite(int nodeA, int nodeB) {
        int rootA = findRoot(nodeA);
        int rootB = findRoot(nodeB);

        if (rootA != rootB) {
            root[rootA] = rootB;
            return true;
        }
        return false;
    }
}
