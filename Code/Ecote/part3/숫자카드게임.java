package Ecote.part3;

import java.util.Arrays;
import java.util.Scanner;

public class 숫자카드게임 {
	// public static void main(String[] args) {
	// 	int [][] arr = {
	// 		{3,1,2},
	// 		{4,1,4},
	// 		{2,2,2}
	// 	};
	// 	System.out.println(mySolution(3,3,arr));
	// }

	// 6m (Success)
	// 입력: 행 개수(int n), 열 개수(int m), 2차원 자연수 배열(int[][] arr) , 출력: 가장 큰 수의 카드 숫자(int big)
	// 풀이 방향: 그리디.
	// 			각 행에서 가장 작은 수의 숫자가 행에서는 가장 큰 숫자가 될 때, 해당 숫자를 고른다.
	// 시간 복잡도: 배열의 1차원 길이 * 2차원 길이 (맞나?-sort 복잡도 어케함)
	public static int mySolution(int n, int m, int[][] arr) {
		int big = 0;

		for (int i = 0; i < arr.length; i++) {
			Arrays.sort(arr[i]);
			if (big < arr[i][0]) {
				big = arr[i][0];
			}
		}

		return big;
	}

	// 풀이 방향: 거의 동일. 입력 받는 내용이 추가되었다는 것. 그리고 입력을 받으면서 로직이 진행되었다는 점이 참신.
	// 시간 복잡도: 의미 없음.
	public static void bookSolution() {
		Scanner sc = new Scanner(System.in);

		// N, M을 공백을 기준으로 구분하여 입력 받기
		int n = sc.nextInt();
		int m = sc.nextInt();
		int result = 0;

		// 한 줄씩 입력 받아 확인하기
		for (int i = 0; i < n; i++) {
			// 현재 줄에서 '가장 작은 수' 찾기
			int min_value = 10001;
			for (int j = 0; j < m; j++) {
				int x = sc.nextInt();
				min_value = Math.min(min_value, x);
			}
			// '가장 작은 수'들 중에서 가장 큰 수 찾기
			result = Math.max(result, min_value);
		}

		System.out.println(result); // 최종 답안 출력
	}

}
