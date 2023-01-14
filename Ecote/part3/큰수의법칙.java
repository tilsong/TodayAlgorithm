package part3;

import java.util.Arrays;
import java.util.Scanner;

public class 큰수의법칙 {
	// public static void main(String[] args) {
	// 	int [] arr = {2,4,5,6,4};
	// 	System.out.println(mySolution(5, 8, 3, arr));
	// }

	// 40m (Success)
	// 입력: 배열의 크기(n), 더하기 횟수(m), 연속 더하기 횟수(k), 여러 수 배열(int []) , 출력: 가장 큰 수(int n)
	// 풀이 방향: 그리디
	//			가장 큰 수와 두 번째로 큰 수를 구하여 가장 큰 수를 k번, 두번째로 큰 수를 1번으로 번갈아 가면서 m번 만큼 더한다.
	//			만약 가장 큰 수와 두번째로 큰 수가 같으면 이에 m번을 곱한 값이 답이 된다.
	// 시간 복잡도: 의미 없음.

	public static int mySolution(int n, int m, int k, int [] arr) {
		int answer = 0;

		Arrays.sort(arr);
		int big = arr[n-1];
		int second = arr[n-2];

		// 가장 큰 수와 두번째로 큰 수가 같으면
		if(big == second) return big * m;

		return m/(k+1)*(big*k + second) + m%(k+1)*big;
	}


	// 풀이 방향: 반복 횟수를 보다 논리적으로 표현한 것 같다. 코드를 보면서 이해가 가능하다.
	//			반면 나는 머릿 속으로 이게 맞겠지하고 계산은 했는데, 다시 설명하라고 하면 나 자신도 기억나지 않을 것 같다.
	//			가능하면 이해 가능한 표현으로 코드를 작성하는 것이 맞는 것 같다.
	//			지금은 어떻게 어떻게 풀었지만, 만약 오답이었다면 논리를 수정해 나가기 어려웠을 것 같다.
	//			가장 큰 수와 두번째로 큰 수를 구하는 것은 당연한 것이고,
	//			논리적인 횟수를 구하는 방향으로 나가야 했던 것 같다..
	// 시간 복잡도: 의미 없음.
	public static void bookSolution() {
			Scanner sc = new Scanner(System.in);

			// N, M, K를 공백을 기준으로 구분하여 입력 받기
			int n = sc.nextInt();
			int m = sc.nextInt();
			int k = sc.nextInt();

			// N개의 수를 공백을 기준으로 구분하여 입력 받기
			int[] arr = new int[n];
			for (int i = 0; i < n; i++) {
				arr[i] = sc.nextInt();
			}

			Arrays.sort(arr); // 입력 받은 수들 정렬하기
			int first = arr[n - 1]; // 가장 큰 수
			int second = arr[n - 2]; // 두 번째로 큰 수

			// 가장 큰 수가 더해지는 횟수 계산
			int cnt = (m / (k + 1)) * k;
			cnt += m % (k + 1);

			int result = 0;
			result += cnt * first; // 가장 큰 수 더하기
			result += (m - cnt) * second; // 두 번째로 큰 수 더하기

			System.out.println(result);
	}

}
