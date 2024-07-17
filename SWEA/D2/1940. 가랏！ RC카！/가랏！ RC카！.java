
import java.util.Scanner;
import java.io.FileInputStream;

class Solution
{
	public static void main(String args[]) throws Exception
	{
		Scanner sc = new Scanner(System.in);
		int T;
		T=sc.nextInt();

		for(int test_case = 1; test_case <= T; test_case++)
		{
            int distance = 0;
            int velocity = 0;
			int N = sc.nextInt();
            
            // 명령 수만큼 실행
            for (int i = 0; i < N; i++) {
            	int command_type = sc.nextInt();
                
                if (command_type == 0) {
                	distance += velocity;
                } else if (command_type == 1) {
                	int acceleration = sc.nextInt();
                    velocity = velocity + acceleration;
                    distance += velocity;
                } else if (command_type == 2) {
                	int acceleration = sc.nextInt();
                    velocity = velocity - acceleration;
                    if (velocity < 0)
                        velocity = 0;
                    distance += velocity;
                }
            }
            System.out.println("#" + test_case + " " + distance);
		}
	}
}