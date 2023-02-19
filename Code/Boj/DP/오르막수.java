package Boj.DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 오르막수 {
	// Input/Output Sample
	// 3
	// 220
	//
	// 60m (Fail -> Success)
	// 입력: 첫째 줄에 N (1 ≤ N ≤ 1,000)이 주어진다.
	// 출력: 첫째 줄에 길이가 N인 오르막 수의 개수를 10,007로 나눈 나머지를 출력한다.
	// 풀이 방향: 점화식 방향을 잘못 잡은 것 같다. 아니 방향은 맞는데 구체적인 코드가 깔끔하지 않아서
	//			계속 헤매었던 듯 싶다.
	// 시간 복잡도: O(N*10*10)

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int n = Integer.parseInt(st.nextToken());

		int[][] arr = new int[n+1][10];
		Arrays.fill(arr, 1);

		for (int i = 2; i <= n; i++) {
			for (int j = 0; j < 10; j++) {
				for (int k = j; k < 10 ; k++) {
					arr[i][j] += (arr[i-1][k] % 10007);
				}
			}
		}

		int count = 0;
		for (int i = 0; i < 10; i++) {
			count += (arr[n][i] % 10007);
		}
		System.out.println(count % 10007);
	}
}
