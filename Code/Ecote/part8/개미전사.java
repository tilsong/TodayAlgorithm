package Ecote.part8;

// 745
public class 개미전사 {
	public static void main(String[] args) {
		int[] arr = {1,3, 1, 5, 7, 11, 7, 4, 2};
		System.out.println(mySolution(arr));
	}

	// 28m (Success)
	// 입력: 식량 창고 정보(int[] arr)
	// 출력: 얻을 수 있는 식량의 최댓값(int max)
	// 풀이 방향: DP
	//			점화식 -> f[n]을 구할 때, f[n-2] + arr[n]과 f[n-1] 중 큰 수를 f[n]에 대입한다.
	//
	//			풀고 나서 느낀 점은, dfs/bfs가 어떻게 그래프를 만드느냐의 문제였다면
	//			dp는 어떻게 점화식을 만드느냐에 대한 문제라는 것이다.
	//			패턴이 나올 때까지 어느 정도 대입을 통해 문제를 풀어보고, 패턴이 나오면
	//			이를 점화식으로 만들어 반복문과 메모라이제이션을 해주는 방향으로 가는 것이 좋은 듯하다.
	// 시간 복잡도: O(n)
	public static int mySolution(int[] arr) {
		int[] dp = new int[arr.length+1];

		dp[1] = arr[0];
		dp[2] = Math.max(arr[0], arr[1]);

		for (int i = 3; i <= arr.length; i++) {
			dp[i] = Math.max((dp[i-2] + arr[i-1]), dp[i-1]);
		}

		return dp[arr.length];
	}
}
