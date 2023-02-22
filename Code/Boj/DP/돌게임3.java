package Boj.DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 돌게임3 {
	// Input/Output Sample
	// 6
	// SK
	//
	// 40m (Success)
	// 입력: 첫째 줄에 N이 주어진다. (1 ≤ N ≤ 1000)
	// 출력: 상근이가 게임을 이기면 SK를, 창영이가 게임을 이기면 CY을 출력한다.
	// 풀이 방향: 경우의 수를 다 따져서, 확실한 필승의 경우의 수를 기록하고, 이를 기반으로 점화식이 이루어져야 했다.
	//			 N-1, N-3, N-4 모두 SK의 승리인 경우, N의 경우에 CY는 필승하게 된다.
	// 시간 복잡도: O(N), 최대 1000
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int n = Integer.parseInt(st.nextToken());
		boolean[] result = new boolean[1001];
		result[1] = true; // true -> SK의 승리
		result[2] = false; // false -> CY의 승리
		result[3] = true;
		result[4] = true;
		result[5] = true;
		result[6] = true;
		result[7] = false;
		result[8] = true;
		result[9] = false;
		result[10] = true;


		for (int i = 11; i <= n; i++) {
			if (result[i-1] && result[i-3] && result[i-4]) {
				result[i] = false;
			} else {
				result[i] = true;
			}
		}

		if (result[n]) {
			System.out.println("SK");
		} else {
			System.out.println("CY");
		}
	}
}
