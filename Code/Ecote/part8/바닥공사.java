package Ecote.part8;

public class 바닥공사 {
	public static void main(String[] args) {
		// System.out.println(mySolution(3));
		System.out.println(bookSolution(3));
	}

	// 40m (Fail -> Success)
	// 입력: 바닥의 가로 길이(int n)
	// 출력: 타일 까는 경우의 수 (int count)
	// 풀이 방향: DP
	// 			재풀이 후 -> 정확히 이해하고 답을 찾지 못했다. 점화식처럼 보이는 것을 찾으려고 했던 것 같다.
	//			그래서 논리적이지는 않으나 대충 맞으니까 코드를 넣어봤다.
	//			그러나.. 논리적으로 먼저 정립되지 않는다면 아무 의미가 없다는 생각이 든다.
	//			다음 문제는 논리적으로 해결한 뒤에, 문제를 풀겠다.
	// 시간 복잡도: O(n)
	// -> 오답. 아래 bookSolution 보기
	public static long mySolution(int n) {
		long[] dp = new long[n+1];

		dp[1] = 1;

		for (int i = 2; i <= n; i++) {
			if (i%2 != 0) { // 홀수면
				dp[i] = (dp[i-1]*2 - 1)%796796;
			} else { // 짝수면
				dp[i] = (dp[i-1]*2 + 1)%796796;
			}
		}

		return dp[n];
	}

	// 풀이 방식: 처음엔 풀이도 이해가 되지 않았다. 그런데 보다보니 논리가 맞았고 논리대로 푸니까 답이었다.
	//			결국 dp는 점화식의 논리를 어떻게 빠르고 정확하게 찾는가가 중요한 것 같다. ㅠㅠ
	public static long bookSolution(int n) {
		long[] dp = new long[n+1];
		dp[1] = 1;
		dp[2] = 3;

		for (int i = 3; i <= n; i++) {
			dp[i] = (dp[i-1] + dp[i-2] * 2)%796796;
		}

		return dp[n];
	}
}
