package Ecote.part8;

public class 일로만들기 {
	public static void main(String[] args) {
		// System.out.println(mySolution(26));
		System.out.println(bookSolution(26));
	}

	// 60m (Fail -> Success)
	// 입력: 정수 x(int x)
	// 출력: x를 1로 만드는 최소 연산 횟수(int count)
	// 풀이 방향: before | 5로 일단 나누고, 나머지 다른 연산을 사용한다.
	// 			after  | 접근 부터 잘못되었다. dp 문제인데 점화식을 생각도 안하고 그리디 처럼 풀려고 했던 것 같다!
	// 시간 복잡도: O(n)
	// -> 오답. 아래 bookSolution 보기
	public static int mySolution(int x) {
		int count = 0;

		while (x > 1) {
			if (x%5 == 0) {
				x /= 5;
				count++;
				continue;
			} else if (x%3 == 0) {
				x/= 3;
				count++;
				continue;
			} else if (x%2 == 0) {
				x /= 2;
				count++;
				continue;
			}
			x --;
			count++;
		}

		return count;
	}

	// 풀이 방식: f(n) = f(n-1)+1 이거나, 5,3,2로 나누어지면 해당 f(n/5) + 1 ,f(n/3) + 1,f(n/2) + 1
	//			중 하나일 수 있다. 단, f(1) = 0.
	public static int bookSolution(int n) {
		int[] dp = new int[n+1]; // dp[0~n]

		// n=0, count=0 | n=1, count=0
		for (int i = 2; i <= n; i++) {
			dp[i] = dp[i-1] + 1;

			if (i % 2 == 0) {
				dp[i] = Math.min(dp[i], dp[i/2] + 1);
			}
			if (i % 3 == 0) {
				dp[i] = Math.min(dp[i], dp[i/3] + 1);
			}
			if (i % 5 == 0) {
				dp[i] = Math.min(dp[i], dp[i/5] +1);
			}
		}

		return dp[n];
 	}
}
