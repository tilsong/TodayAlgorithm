package Ecote.part8;

public class 피보나치 {
	public static void main(String[] args) {
		long before = System.nanoTime();

		// System.out.println(recursiveSolution1(47)); // time: 6439259500
		// System.out.println(recursiveSolution2(47)); // time: 6428225000
		System.out.println(iterSolution(47));       // time: 222000

		System.out.println("time: " + (System.nanoTime() -before));

		// 탑다운 방식은 시간 상으로는 큰 차이가 없다. 내부적으로 캐싱 코드가 추가되지 않았을까 생각해본다.
		// 반면 보텀업 방식은 매우 빠르게 작동한다.
	}

	// a(n) = a(n-1) + a(n-2), a(1) = 1, a(2) = 1
	// n번째 요소의 값은?
	public static long recursiveSolution1(int n) {
		if (n == 1 | n == 2) return 1;
		return recursiveSolution1(n-1) + recursiveSolution1(n-2);
	}

	// 탑다운
	static final long[] memo = new long[51]; // 메모제이션 사용
	public static long recursiveSolution2(int n) {
		if (n == 1 | n == 2) return 1;
		if (memo[n] != 0) return memo[n];
		memo[n] = recursiveSolution1(n-1) + recursiveSolution1(n-2);
		return memo[n];
	}

	// 보텀업
	public static long iterSolution(int n) {
		memo[1] = 1;
		memo[2] = 1;

		for (int i = 3; i <= n; i++) {
			memo[i] = memo[i-1] + memo[i-2];
		}

		return memo[n];
	}
}
