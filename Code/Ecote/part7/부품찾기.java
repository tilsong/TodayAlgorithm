package Ecote.part7;

import java.util.Arrays;

public class 부품찾기 {
	public static void main(String[] args) {
		int[] arr = {8,3,7,9,2};
		int[] arr2 = {5,7,9};

		// mySolution(5,arr, 3, arr2);
		myBinarySolution(5,arr, 3, arr2);
	}

	// 15m (Success)
	// 입력: 매장 부품 수(int n), 매장 부품 번호 배열(int[] arr), 요청 부품 수(int m), 매장 부품 번호 배열(int[] arr2)
	// 출력: 부품 있는지 여부 배열
	// 풀이 방향: 배열의 수가 100만 이하이므로, 계수 정렬을 사용한다. 
	// 시간 복잡도: O(N+K+logN) N은 최대 백만, M은 최대 십만 => 얼마 안됨!
	public static void mySolution(int n, int[] arr, int m, int[] arr2) {
		int[] sortedArr = new int[1000001];

		// 매장 부품 번호 배열을 정렬
		for (int i = 0; i < n; i++) {
			sortedArr[arr[i]]++;
		}
		// 요청 부품 파악
		for (int i = 0; i < arr2.length; i++) {
			if (sortedArr[arr2[i]] == 1) {
				System.out.print("yes ");
			} else {
				System.out.print("no ");
			}
		}
	}

	// 13m (Success)
	// 풀이 방향: 상품 배열 정렬 후, 이를 이진탐색으로 찾기
	// 시간 복잡도: O((M+N)*logN) N은 최대 백만, M은 최대 십만 => 얼마 안됨!
	public static void myBinarySolution(int n, int[] arr, int m, int[] arr2) {
		Arrays.sort(arr); // 정렬

			// 이진 탐색
			for (int i = 0; i < m; i++) {
				if (bs(arr, arr2[i],0,arr.length-1) == -1) {
					System.out.println("no");
				} else {
					System.out.println("yes");
				}
			}
	}

	public static int bs(int[] arr, int target, int start, int end) {
		if (start > end) return -1;
		int mid = (start + end)/2;

		if (arr[mid] == target) return mid;
		else if (arr[mid] < target) return bs(arr, target, mid+1, end);
		return bs(arr, target, start, mid-1);
	}

	// set을 사용해서 쉽게 해결할 수도 있다..

}
