package Ecote.part6;

import java.util.Arrays;

public class 두배열의원소교체 {
	public static void main(String[] args) {
		int[] a = {1,2,5,4,3};
		int[] b = {5,5,6,6,5};

		mySolution(5,3,a,b);
	}

	// 15m (Success)
	// 입력: 배열의 크기(int n), 최대 바꿔치기 수(int k), 배열 A(int[] a), 배열 B(int[] b)
	// 출력: 바꿔치기한 배열 A의 요소 합의 최댓값
	// 풀이 방향: 정렬 후, 각 배열의 가장 큰 값과 작은 값을 비교.
	// 시간 복잡도: O(n+k) // k는 배열 요소의 최대 값
	public static void mySolution(int n, int k, int[] a, int[] b) {
		int result = 0;

		Arrays.sort(a);
		Arrays.sort(b);

		for (int i = 0; i < k; i++) {
			if (a[i] < b[n-1-i]){
				a[i] = b[n-1-i];
			}
		}

		for (int i = 0; i < n; i++) {
			result += a[i];
		}

		System.out.println("result: " + result);
	}
}
