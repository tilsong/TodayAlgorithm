package Boj.DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 일이삼더하기사 {
	// Input/Output Sample
	// 3
	// 4
	// 7
	//
	// 4
	// 8
	// 14
	//
	// 60m (Fail -> Success)
	// 입력: 첫째 줄에 테스트 케이스의 개수 T가 주어진다. 각 테스트 케이스는 한 줄로 이루어져 있고, 정수 n이 주어진다. n은 양수이며 10,000보다 작거나 같다.
	// 출력: 각 테스트 케이스마다, n을 1, 2, 3의 합으로 나타내는 방법의 수를 출력한다.
	// 풀이 방향: n >= 4 일 때, n-1,n-2,n-3의 경우를 고려해야 한다는 것은 알았는데 그 이상으로는 생각을 못했던 것 같다.
	//			또한 수열의 중복 제거를 위해 오름차순을 고려해야 한다는 점을 알게 되었다..
	// 시간 복잡도: O(N)

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int[][] dp = new int[10001][4];
		int t = Integer.parseInt(st.nextToken());

		dp[1][1] = 1;
		dp[1][2] = 0;
		dp[1][3] = 0;

		dp[2][1] = 1;
		dp[2][2] = 1;
		dp[2][3] = 0;

		dp[3][1] = 1;
		dp[3][2] = 1;
		dp[3][3] = 1;

		for (int i = 4; i < dp.length; i++) {
			dp[i][1] = dp[i-1][1];
			dp[i][2] = dp[i-2][1] + dp[i-2][2];
			dp[i][3] = dp[i-3][1] + dp[i-3][2] + dp[i-3][3];
		}

		for (int i = 0; i < t; i++) {
			st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());
			System.out.println(dp[n][1] + dp[n][2] + dp[n][3]);
		}
	}
}
