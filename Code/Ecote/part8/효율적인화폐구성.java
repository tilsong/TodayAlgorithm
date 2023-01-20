package Ecote.part8;

public class 효율적인화폐구성 {
	public static void main(String[] args) {
		int[] arr = {2,3};
		System.out.println(mySolution(arr,15));
	}

	// 75m (Success)
	// 입력: 화폐 구성 배열(int[] arr), 특정 화폐들의 조합이 되어야 하는 값(int m)
	// 출력: m을 만들기 위해 화폐를 사용해야 하는 개수(int count)
	// 풀이 방향: DP
	//			결국 f(m)을 만들기 위한 점화식을 만들어야만 하고, 이를 위해선 완전탐색이 사용될 수 있으나,
	//			너무 시간이 많이 걸리므로 dp 테이블을 사용한다.
	//			f(1)부터 가능한 조합을 찾고, 안되면 -1을 넣는다. 되는 조합들 중 더 적은 화폐 개수를 쓰는 조합을 선택한다.
	//			이를 f(m)까지 수행.
	//
	//			다 푼 뒤
	//			문제를 부수고 싶었다
	//			못 푼 나도 부수고 싶었다
	//			다 부술거야
	//
	//			(와장창)
	//
	//			그래도 마침내 해냈으니 칭찬해~
	// 시간 복잡도:
	public static int mySolution(int[] arr, int m) {
		int[] dp = new int[m+1];

		// 화폐 구성을 통해 1개로 가능한 최소 단위 넣기
		for (int i = 0; i < arr.length; i++) {
			dp[arr[i]] = 1;
		}

		for (int i = 1; i <= m; i++) {
			// dp 배열에 순서대로 m까지 내용 넣기
			if (dp[i] != 1) { // 화폐 구성 요소는 제외
				for (int j = 1; j < i; j++) {
					if (dp[j] > 0 && dp[i-j] > 0) { // 조합이 있으면
						// 최소인지도 봐야 함
						if (dp[i] == 0 | dp[i] > dp[j] + dp[i-j]) {
							dp[i] = dp[j] + dp[i-j];
						}
					}
				}
			}
		}

		if (dp[m] == 0) return -1; // 없으면
		return dp[m];
	}
}
