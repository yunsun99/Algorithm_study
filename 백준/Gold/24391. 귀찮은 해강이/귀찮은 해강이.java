import java.util.*;


public class Main {
    public static int[] parent;
    public static int[] classList;
    public static int N;
    public static int M;
    public static int answer;
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        N = sc.nextInt();
        M = sc.nextInt();
        
        parent = new int[N];
        classList = new int[N];
        
        for(int i=0;i<N;i++) {
            parent[i] = i;
        }
        
        for(int i=0;i<M ;i++) {
            int start= sc.nextInt()-1;
            int end = sc.nextInt()-1;
            union(start ,end);
        }
        
        for(int i=0 ;i<N;i++) {
            classList[i] = sc.nextInt()-1;
        }
        sc.close();
        
        for(int i=0;i<N-1;i++) {
            if(!isSame(classList[i] , classList[i+1])) {
                answer++;
            }
        }
        System.out.println(answer);
    }

    public static int find(int x) {
        if(parent[x]==x) {
            return x;
        }else {
            return parent[x] = find(parent[x]);
        }
    }
    
    public static void union(int x, int y) {
        int rootX = find(x);
        int rootY = find(y);
        
        if(rootX != rootY) {
            parent[rootY] = rootX;
        }
    }
    
    public static boolean isSame(int x, int y) {
        if(find(x) == find(y)) {
            return true;
        }else {
            return false;
        }
    }
    
}